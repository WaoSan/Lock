package com.wsl.lock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;

/**
 * @author: wsl on 2017/7/20 9:30
 * e-mail：863476604@qq.com
 */
public class ScreenStateUtils {
    private ScreenListener mListener;
    private Context mContext;
    private ScreenReceiver mScreenReceiver;

    public ScreenStateUtils(Context context) {
        mContext = context;
    }

    public void begin(ScreenListener listener) {

        mListener = listener;
        mScreenReceiver = new ScreenReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        mContext.registerReceiver(mScreenReceiver, filter);
        mContext.registerReceiver(mScreenReceiver, filter);
    }

    public void end() {
        mContext.unregisterReceiver(mScreenReceiver);
    }

    private class ScreenReceiver extends BroadcastReceiver {
        private static final String TAG = "ScreenReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TextUtils.equals(action, Intent.ACTION_SCREEN_ON)) {
                mListener.onScreenOn();
            } else if (TextUtils.equals(action, Intent.ACTION_SCREEN_OFF)) {
                mListener.onScreenOff();
            } else if (TextUtils.equals(action, Intent.ACTION_USER_PRESENT)) {
                mListener.onScreenPresent();
            }
        }
    }
}
