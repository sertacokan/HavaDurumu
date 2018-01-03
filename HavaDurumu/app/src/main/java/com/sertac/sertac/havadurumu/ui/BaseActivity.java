package com.sertac.sertac.havadurumu.ui;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sertac.sertac.havadurumu.broadcastreceivers.InternetConnectionReceiver;

public abstract class BaseActivity extends AppCompatActivity {

    private InternetConnectionReceiver internetConnectionReceiver;
    private boolean networkState;
    protected abstract int layout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout());
        internetConnectionReceiver=new InternetConnectionReceiver();
        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(internetConnectionReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
    unregisterReceiver(internetConnectionReceiver);
    super.onDestroy();
    }

    public boolean isNetworkState() {
        return networkState;
    }
}
