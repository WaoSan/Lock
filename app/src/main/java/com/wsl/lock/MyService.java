package com.wsl.lock;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "wsl====";

    @Override
    public void onCreate() {

        ScreenStateUtils screenStateUtils = new ScreenStateUtils(this);
        screenStateUtils.begin(new ScreenListener() {
            @Override
            public void onScreenOn() {
                Log.i(TAG, "onScreenOn");
                KeepLiveManager.getInstance(MyService.this).finishKeepLiveActivity();
            }

            @Override
            public void onScreenOff() {
                Log.i(TAG, "onScreenOff");
                KeepLiveManager.getInstance(MyService.this).startKeepLiveActivity();
            }

            @Override
            public void onScreenPresent() {
                Log.i(TAG, "onScreenPresent");
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
