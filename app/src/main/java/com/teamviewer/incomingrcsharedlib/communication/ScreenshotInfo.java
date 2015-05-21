package com.teamviewer.incomingrcsharedlib.communication;

import android.os.Parcel;
import android.os.Parcelable;

public class ScreenshotInfo implements Parcelable {
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;


    public ScreenshotInfo() {
        super();
    }

    public ScreenshotInfo(int arg1, int arg2, int arg3, int arg4, int arg5) {
        super();
        this.b = arg1;
        this.a = arg2;
        this.c = arg3;
        this.e = arg4;
        this.d = arg5;
    }

    private ScreenshotInfo(Parcel arg2) {
        super();
        this.a = arg2.readInt();
        this.b = arg2.readInt();
        this.c = arg2.readInt();
        this.d = arg2.readInt();
        this.e = arg2.readInt();
    }


    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel arg2, int arg3) {
        arg2.writeInt(this.a);
        arg2.writeInt(this.b);
        arg2.writeInt(this.c);
        arg2.writeInt(this.d);
        arg2.writeInt(this.e);
    }
}
