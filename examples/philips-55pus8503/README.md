# Philips 55PUS8503/12 (`PH7M_EU_5596`, TPM171E)

2018 Philips 55" 4K Ambilight Android TV on Android TV 8.0.0 (`API 26`, build `OC.4718`).

## Files in this folder

- `debloat.txt`: Disables 45 telemetry, ACR screen-tracking, retail demo, NetTV portal, and unused streaming/game packages while keeping **YouTube**, **Sportzfy**, **Google Play Store**, Voice Assistant, Chromecast, Ambilight + Hue, Samsung Smart View / DLNA, Live TV / HDMI inputs, and the stock Android TV launcher (`com.google.android.tvlauncher`) enabled as an instant fallback.
- `config.json`: Complete launcher overlay with the **Moskee Al-Fath · Boxtel** 365-day Mawaqit Adhan & Iqama schedule, high-contrast 14-landmark Islamic sites slideshow settings, custom greeting, hidden storage line, and App Library drawer button.
- `tv.json`: Device metadata for the repository table.

## Switching Between Launchers (Instant Fallback)

Both the CleanYourTV launcher (`com.gathin.tvhome`) and the debloated stock Android TV launcher (`com.google.android.tvlauncher`) remain installed and enabled on the TV so you can switch back and forth at any time in one command:

### Set CleanYourTV (`com.gathin.tvhome`) as Permanent Default Home
```powershell
$env:TV_HOST="<tv ip>"
tools\venv\Scripts\python.exe -I tools\tv.py "cmd package set-home-activity --user 0 com.gathin.tvhome/.MainActivity"
```

### Switch Back to Debloated Stock Android TV Launcher (`com.google.android.tvlauncher`)
```powershell
$env:TV_HOST="<tv ip>"
tools\venv\Scripts\python.exe -I tools\tv.py "pm enable --user 0 com.google.android.tvlauncher; cmd package set-home-activity --user 0 com.google.android.tvlauncher/.MainActivity; am start -a android.intent.action.MAIN -c android.intent.category.HOME -n com.google.android.tvlauncher/.MainActivity"
```

### Apply or Update the Islamic Sites + Moskee Al-Fath Theme
```powershell
powershell -ExecutionPolicy Bypass -File tools\theme.ps1 -TvHost <tv ip> themes\islamic-sites
```
