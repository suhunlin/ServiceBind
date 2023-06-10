package com.suhun.servicebind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String tag = MainActivity.class.getSimpleName();
    private MyService myService;
    private TextView showLuckyTextView;
    private Button getLuckyNumBtn;
    private ServiceConnection serviceConnection =
            new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    Log.d(tag, "+++++MainActivity onServiceConnected+++++");
                    MyService.LocalService mBinder = (MyService.LocalService)service;
                    myService = mBinder.getService();
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    Log.d(tag, "+++++MainActivity onServiceDisconnected+++++");

                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "+++++MainActivity onCreate+++++");
        setContentView(R.layout.activity_main);
        showLuckyTextView = findViewById(R.id.showLuckyNum);
        getLuckyNumBtn = findViewById(R.id.createLuckyNum);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag, "+++++MainActivity onStart+++++");
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag, "+++++MainActivity onStop+++++");
        unbindService(serviceConnection);
    }

    public void getLuckNumberFun(View view){
        showLuckyTextView.setText(""+ myService.createLuckyNum());
    }
}