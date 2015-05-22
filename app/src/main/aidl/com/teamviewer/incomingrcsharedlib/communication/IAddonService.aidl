// IAddonService.aidl
package com.teamviewer.incomingrcsharedlib.communication;

import com.teamviewer.incomingrcsharedlib.communication.KeyAction;
import com.teamviewer.incomingrcsharedlib.communication.PointerAction;
import com.teamviewer.incomingrcsharedlib.communication.ScreenshotData;
import com.teamviewer.incomingrcsharedlib.communication.ScreenshotInfo;
import com.teamviewer.incomingrcsharedlib.communication.VirtualKeyCode;

interface IAddonService {

    boolean verify();

    boolean isAvailable();

    void init();

    void doNothing();

    ScreenshotInfo getScreenshotInfo();

    boolean copyScreenshot(in ScreenshotData arg1, int arg2);

    void injectUnicode(int arg1);

    void injectVirtualKeyCode(in VirtualKeyCode arg1, in KeyAction arg2);

    void injectAndroidKeyCode(int arg1,in KeyAction arg2);

    void cancelPointerSequence();

    void startPointerSequence(int arg1, int arg2, int arg3);

    void addPointerAction(int arg1,in PointerAction arg2, int arg3, int arg4);

    void addPointerAction2(int arg1,in PointerAction arg2, int arg3, int arg4, long arg5);

}
