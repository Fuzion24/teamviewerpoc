package com.teamviewer.incomingrcsharedlib.communication;

import android.os.Parcel;
import android.os.Parcelable;

public enum PointerAction implements Parcelable {
  Unknown, Down, Up, Move, Hover, Cancel;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel arg2, int arg3) {
        arg2.writeInt(this.ordinal());
    }

}

/*    static {
        PointerAction.a = new PointerAction("Unknown", 0);
        PointerAction.b = new PointerAction("Down", 1);
        PointerAction.c = new PointerAction("Up", 2);
        PointerAction.d = new PointerAction("Move", 3);
        PointerAction.e = new PointerAction("Hover", 4);
        PointerAction.f = new PointerAction("Cancel", 5);
        PointerAction.g = new PointerAction[]{PointerAction.a, PointerAction.b, PointerAction.c, PointerAction
                .d, PointerAction.e, PointerAction.f};
        PointerAction.CREATOR = new d();
    }

*/