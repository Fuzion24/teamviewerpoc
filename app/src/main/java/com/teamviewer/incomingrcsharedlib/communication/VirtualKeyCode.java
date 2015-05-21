package com.teamviewer.incomingrcsharedlib.communication;

import android.os.Parcel;
import android.os.Parcelable;

public enum VirtualKeyCode implements Parcelable {

    VK_BACK("VK_BACK", 0, 8, 67),
    VK_TAB("VK_TAB", 1, 9, 61),
    VK_RETURN("VK_RETURN", 2, 13, 66),
    VK_SHIFT("VK_SHIFT", 3, 16, 0),
    VK_CONTROL("VK_CONTROL", 4, 17, 0),
    VK_PAGEUP("VK_PAGEUP", 5, 33, 92),
    VK_PAGEDOWN("VK_PAGEDOWN", 6, 34, 93),
    VK_END("VK_END", 7, 35, 123),
    VK_HOME("VK_HOME", 8, 36, 122),
    VK_LEFT("VK_LEFT", 9, 37, 21),
    VK_UP("VK_UP", 10, 38, 19),
    VK_RIGHT("VK_RIGHT", 11, 39, 22),
    VK_DOWN("VK_DOWN", 12, 40, 20),
    VK_PRINT("VK_PRINT", 13, 42, 0),
    VK_INSERT("VK_INSERT", 14, 45, 124),
    VK_DELETE("VK_DELETE", 15, 46, 112),
    VK_LWIN("VK_LWIN", 16, 91, 3),
    VK_RWIN("VK_RWIN", 17, 92, 3),
    VK_APPS("VK_APPS", 18, 93, 82),
    VK_NUMPAD0("VK_NUMPAD0", 19, 96, 7),
    VK_NUMPAD1("VK_NUMPAD1", 20, 97, 8),
    VK_NUMPAD2("VK_NUMPAD2", 21, 98, 9),
    VK_NUMPAD3("VK_NUMPAD3", 22, 99, 10),
    VK_NUMPAD4("VK_NUMPAD4", 23, 100, 11),
    VK_NUMPAD5("VK_NUMPAD5", 24, 101, 12),
    VK_NUMPAD6("VK_NUMPAD6", 25, 102, 13),
    VK_NUMPAD7("VK_NUMPAD7", 26, 103, 14),
    VK_NUMPAD8("VK_NUMPAD8", 27, 104, 15),
    VK_NUMPAD9("VK_NUMPAD9", 28, 105, 16),
    VK_NUMPAD_MUL("VK_NUMPAD_MUL", 29, 106, 17),
    VK_NUMPAD_ADD("VK_NUMPAD_ADD", 30, 107, 81),
    VK_NUMPAD_SUB("VK_NUMPAD_SUB", 31, 109, 69),
    VK_NUMPAD_COMMA("VK_NUMPAD_COMMA", 32, 110, 55),
    VK_NUMPAD_DIV("VK_NUMPAD_DIV", 33, 111, 76),
    VK_ALT("VK_ALT", 34, 164, 0);

    int J;
    int K;

    public static final Parcelable.Creator<VirtualKeyCode> CREATOR = new Parcelable.Creator<VirtualKeyCode>() {

        public VirtualKeyCode createFromParcel(Parcel src) {
            return VirtualKeyCode.values()[src.readInt()];
        }

        public VirtualKeyCode[] newArray(int size) {
            return new VirtualKeyCode[size];
        }

    };



    private VirtualKeyCode(String arg1, int arg2, int arg3, int arg4) {
        this.J = arg3;
        this.K = arg4;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel arg2, int arg3) {
        arg2.writeInt(this.ordinal());
    }
}