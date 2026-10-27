package io.github.luyzfernando08.sleeptimer;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

/**
 * Puts the TV to sleep for Timer. The "lock screen" global action (Android 9 and later) sends the device to
 * sleep, which an ordinary app cannot do otherwise; a device admin's lockNow() is ignored on boxes without the
 * device-admin feature, the Mi Box S among them. The service asks for no events and reads no windows.
 */
public class SleepService extends AccessibilityService {
    private static SleepService instance;

    static boolean running() {
        return instance != null;
    }

    static boolean sleep() {
        SleepService service = instance;
        if (service == null) return false;
        if (android.os.Build.VERSION.SDK_INT >= 28 && service.performGlobalAction(GLOBAL_ACTION_LOCK_SCREEN)) {
            return true;
        }
        try {
            Runtime.getRuntime().exec(new String[]{"input", "keyevent", "223"});
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void onServiceConnected() {
        instance = this;
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
    }

    @Override
    public void onInterrupt() {
    }

    @Override
    public void onDestroy() {
        instance = null;
        super.onDestroy();
    }
}
