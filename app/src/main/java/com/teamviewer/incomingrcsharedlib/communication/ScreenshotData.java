package com.teamviewer.incomingrcsharedlib.communication;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.util.Log;

import java.io.FileDescriptor;

/**
 * Created by fuzion24 on 5/21/15.
 */
public class ScreenshotData implements Parcelable {

    FileDescriptor a;
    ParcelFileDescriptor b;

    public ScreenshotData(FileDescriptor fd) {
        super();
        this.a = fd;
    }

    public static final Parcelable.Creator<ScreenshotData> CREATOR = new Parcelable.Creator<ScreenshotData>() {

        public ScreenshotData createFromParcel(Parcel src) {
            return new ScreenshotData(src);
        }

        public ScreenshotData[] newArray(int size) {
            return new ScreenshotData[size];
        }

    };


    private ScreenshotData(Parcel arg3) {
        super();
        this.b = arg3.readFileDescriptor();
        if(this.b == null) {
            Log.d("ScreenshotData", "ScreenshotData(Parcel in): could not resolve filedescriptor");
        }
        else {
            this.a = this.b.getFileDescriptor();
        }
    }


    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel arg2, int arg3) {
        arg2.writeFileDescriptor(this.a);
    }
}

