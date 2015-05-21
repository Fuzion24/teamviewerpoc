package com.teamviewer.incomingrcsharedlib.communication;

import android.os.Parcel;
import android.os.Parcelable;


public enum KeyAction implements Parcelable {

    UNKNOWN, DOWN,UP,CANCEL;

    public static final Parcelable.Creator<KeyAction> CREATOR = new Parcelable.Creator<KeyAction>() {

        public KeyAction createFromParcel(Parcel src) {
            return KeyAction.values()[src.readInt()];
        }

        public KeyAction[] newArray(int size) {
            return new KeyAction[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ordinal());
    }
}
