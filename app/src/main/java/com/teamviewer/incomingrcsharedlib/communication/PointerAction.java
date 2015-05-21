package com.teamviewer.incomingrcsharedlib.communication;

import android.os.Parcel;
import android.os.Parcelable;
public enum PointerAction implements Parcelable {

    Unknown, Down, Up, Move, Hover, Cancel;

    public static final Parcelable.Creator<PointerAction> CREATOR = new Parcelable.Creator<PointerAction>() {

        public PointerAction createFromParcel(Parcel src) {
            return PointerAction.values()[src.readInt()];
        }

        public PointerAction[] newArray(int size) {
            return new PointerAction[size];
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
