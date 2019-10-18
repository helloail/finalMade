package com.example.foryoudicodingsubmissionfour.helper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public class ViewAnimation {

    public static void fadeOut(final View v) {

        ViewAnimation.fadeOut(v, null);
    }

    public static void fadeOut(final View v, final AnimListener animListener) {
        v.setAlpha(1.0f);
        v.animate()
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (animListener != null) animListener.onFinish();
                        super.onAnimationEnd(animation);
                    }
                })
                .alpha(0.0f);
    }

    public interface AnimListener {
        void onFinish();
    }

}
