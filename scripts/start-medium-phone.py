#!/usr/bin/env python3
"""Start Medium Phone, check boot completion, and retry once without wiping data."""

import argparse
from datetime import datetime
import os
from pathlib import Path
import subprocess
import sys
import time


AVD = "Medium_Phone_API_37.0"
SDK = Path(os.environ.get("ANDROID_HOME", Path.home() / "Library/Android/sdk"))
ADB = SDK / "platform-tools/adb"
EMULATOR = SDK / "emulator/emulator"


def adb(*args, timeout=5):
    try:
        result = subprocess.run(
            [str(ADB), *args], capture_output=True, text=True, timeout=timeout
        )
        return result.returncode, result.stdout.strip()
    except subprocess.TimeoutExpired:
        return 124, ""


def avd_name(serial):
    code, output = adb("-s", serial, "emu", "avd", "name")
    if code == 0:
        return next((line.strip() for line in output.splitlines() if line.strip()), "")
    return ""


def find_device():
    code, output = adb("devices")
    if code != 0:
        raise RuntimeError("Cannot query ADB. Check the SDK ADB server first.")
    for line in output.splitlines():
        fields = line.split()
        if fields and fields[0].startswith("emulator-"):
            serial = fields[0]
            # Emulator console commands work even when the ADB transport is offline.
            if avd_name(serial) == AVD:
                return serial
    return None


def booted(serial):
    code, value = adb("-s", serial, "shell", "getprop", "sys.boot_completed")
    return code == 0 and value == "1"


def wait_for_boot(serial, process=None, timeout=180):
    started = time.monotonic()
    last_progress = 0
    online = False
    while time.monotonic() - started < timeout:
        if process is not None and process.poll() is not None:
            raise RuntimeError(f"Emulator exited with code {process.returncode}.")
        serial = serial or find_device()
        if serial:
            code, state = adb("-s", serial, "get-state")
            online = online or (code == 0 and state == "device")
            if code == 0 and state == "device" and booted(serial):
                print(f"Ready: {AVD} ({serial}), {time.monotonic() - started:.0f}s.")
                return serial
        elapsed = time.monotonic() - started
        # Healthy boots here connect ADB in about 20 seconds. A persistent offline
        # transport is the observed early-boot hang, so do not wait five minutes.
        if not online and elapsed >= 90:
            break
        if elapsed - last_progress >= 15:
            print(f"Waiting for Android to boot... {elapsed:.0f}s", flush=True)
            last_progress = elapsed
        time.sleep(2)
    raise RuntimeError("Android did not complete boot within the time limit.")


def stop_device(serial):
    # Recheck identity before any mutation; never stop another emulator or a phone.
    if not serial or avd_name(serial) != AVD:
        raise RuntimeError("Cannot confirm device identity; no device was stopped.")
    code, _ = adb("-s", serial, "emu", "kill")
    if code != 0:
        raise RuntimeError("Cannot stop the emulator through its console.")
    for _ in range(20):
        if avd_name(serial) != AVD:
            time.sleep(3)  # Allow disk locks to be released after transport closure.
            return
        time.sleep(1)
    raise RuntimeError("The emulator has not stopped. Stop it in Device Manager.")


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--check", action="store_true", help="Only check; never start or stop a device")
    args = parser.parse_args()
    if not ADB.is_file() or not EMULATOR.is_file():
        raise RuntimeError(f"SDK tools not found under {SDK}. Set ANDROID_HOME.")
    serial = find_device()
    if args.check:
        if serial and booted(serial):
            print(f"Ready: {AVD} ({serial}).")
            return 0
        print(f"Not ready: {AVD}.")
        return 1
    if serial and booted(serial):
        print(f"Already ready: {AVD} ({serial}).")
        return 0

    logs = Path.home() / ".android/emulator-start-logs"
    logs.mkdir(parents=True, exist_ok=True)
    for attempt in range(2):
        process = None
        logfile = None
        if serial is None:
            logfile = logs / f"medium-phone-{datetime.now():%Y%m%d-%H%M%S}-{attempt + 1}.log"
            with logfile.open("w") as output:
                process = subprocess.Popen(
                    [str(EMULATOR), "-avd", AVD, "-no-snapshot", "-show-kernel"],
                    stdin=subprocess.DEVNULL, stdout=output, stderr=subprocess.STDOUT,
                    start_new_session=True,
                )
            print(f"Starting {AVD}. Log: {logfile}", flush=True)
        try:
            wait_for_boot(serial, process)
            return 0
        except RuntimeError as error:
            print(str(error), file=sys.stderr, flush=True)
            serial = find_device()
            if attempt == 1:
                print(f"No more retries. Logs: {logs}", file=sys.stderr)
                return 1
            if serial:
                stop_device(serial)
            elif process is not None and process.poll() is None:
                raise RuntimeError("Emulator identity unavailable; not starting a duplicate.")
            print("Retrying a cold boot once; installed apps and data are preserved.", flush=True)
            serial = None
    return 1


if __name__ == "__main__":
    try:
        sys.exit(main())
    except (RuntimeError, OSError) as error:
        print(str(error), file=sys.stderr)
        sys.exit(1)
