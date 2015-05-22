package com.nowsecure.twpoc;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.MemoryFile;
import android.util.Log;

import com.teamviewer.incomingrcsharedlib.communication.IAddonService;
import com.teamviewer.incomingrcsharedlib.communication.ScreenshotData;
import com.teamviewer.incomingrcsharedlib.communication.ScreenshotInfo;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class MainActivity extends Activity implements ServiceConnection {

    private static final String TAG = "TWPOC";

    private IAddonService addOnService;
    private ScreenshotData screenshotData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CertToys.doStuff(this);
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
            addOnService = IAddonService.Stub.asInterface(iBinder);
            addOnService.init();
            Log.d(TAG, "IsAvailable: " + String.valueOf(addOnService.verify()));
            Log.d(TAG, "verify: " + String.valueOf(addOnService.verify()));

            ScreenshotInfo ssi = addOnService.getScreenshotInfo();

            Log.d(TAG, "ScreenShot info " + ssi.a);
            Log.d(TAG, "ScreenShot info " + ssi.b);
            Log.d(TAG, "ScreenShot info " + ssi.c);
            Log.d(TAG, "ScreenShot info " + ssi.d);

            File f = getFilesDir().createTempFile("derp","img");
            f.setWritable(true, false);
            FileOutputStream s = new FileOutputStream(f);

            screenshotData = new ScreenshotData(s.getFD());
            addOnService.copyScreenshot(screenshotData, 0);

            Log.d(TAG, "ScreenshotData: " + screenshotData);

            Log.d(TAG, "We made it here without crashing.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        Log.d(TAG, "onServiceDisconnected " + componentName);
        addOnService = null;
    }
}
