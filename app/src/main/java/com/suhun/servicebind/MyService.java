package com.suhun.servicebind;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class MyService extends Service {
    private String tag = MyService.class.getSimpleName();
    private boolean isBind;
    private Binder mBinder;

    public MyService() {
        Log.d(tag, "+++++MyService was born+++++");
        mBinder = new LocalService();
        isBind = false;
    }

    public class LocalService extends Binder{
        public LocalService(){
            Log.d(tag, "+++++LocalService was born+++++");
        }
        MyService getService(){
            return  MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(tag, "+++++MyService onBind+++++");
        isBind = true;
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(tag, "+++++MyService unBind+++++");
        isBind = false;
        return super.onUnbind(intent);
    }

    public int createLuckyNum(){
        return new Random().nextInt(49) + 1;
    }
}