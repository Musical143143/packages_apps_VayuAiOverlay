package com.lunaris.vayu;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import com.airbnb.lottie.LottieAnimationView;

public class VayuFloatingOverlay {
    private static VayuFloatingOverlay sInstance;
    private final Context mContext;
    private final WindowManager mWindowManager;
    private View mOverlayView;

    private VayuFloatingOverlay(Context context) {
        mContext = context.getApplicationContext();
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
    }

    public static synchronized VayuFloatingOverlay getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new VayuFloatingOverlay(context);
        }
        return sInstance;
    }

    public void showOverlay() {
        if (mOverlayView != null) return;

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        );

        params.gravity = Gravity.CENTER;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        mOverlayView = inflater.inflate(R.layout.vayu_floating_card, null);

        LottieAnimationView animView = mOverlayView.findViewById(R.id.lottie_ai_anim);
        animView.playAnimation();

        // Auto-dismiss on click
        mOverlayView.setOnClickListener(v -> hideOverlay());

        mWindowManager.addView(mOverlayView, params);
    }

    public void hideOverlay() {
        if (mOverlayView != null) {
            mWindowManager.removeView(mOverlayView);
            mOverlayView = null;
        }
    }
}
