package com.lunaris.vayu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ActionKeyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String gesture = intent.getStringExtra("gesture_type");

        if ("DOUBLE_PRESS".equals(gesture) || "LONG_PRESS".equals(gesture)) {
            VayuFloatingOverlay.getInstance(context).showOverlay();
        }
    }
}
