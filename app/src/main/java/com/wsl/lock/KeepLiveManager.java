package com.wsl.lock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.lang.ref.WeakReference;

/**
 * @author: wsl on 2017/7/20 10:21
 * e-mail：863476604@qq.com
 */

public class KeepLiveManager {
    private static KeepLiveManager mKeepLiveManager;
    private Context mContext;
    private WeakReference<Activity> mActivityWeakReference;

    private KeepLiveManager(Context context) {
        this.mContext = context;
    }

    public static KeepLiveManager getInstance(Context context) {
        if (mKeepLiveManager == null) {
            synchronized (KeepLiveManager.class) {
                if (mKeepLiveManager == null) {

                    mKeepLiveManager = new KeepLiveManager(context.getApplicationContext());
                }
            }
        }
        return mKeepLiveManager;
    }

    public void setKeepLiveActivity(Activity activity) {
        mActivityWeakReference = new WeakReference<Activity>(activity);
    }

    public void finishKeepLiveActivity() {
        if (mActivityWeakReference != null && mActivityWeakReference.get() != null) {
            Activity activity = mActivityWeakReference.get();
            activity.finish();
        }
    }

    public void startKeepLiveActivity() {
        Intent intent = new Intent(mContext, OnePixActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}
