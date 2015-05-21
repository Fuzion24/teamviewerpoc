// IAddonService.aidl
package com.teamviewer.incomingrcsharedlib.communication;

import com.teamviewer.incomingrcsharedlib.communication.KeyAction;
import com.teamviewer.incomingrcsharedlib.communication.PointerAction;
import com.teamviewer.incomingrcsharedlib.communication.ScreenshotData;
import com.teamviewer.incomingrcsharedlib.communication.ScreenshotInfo;
import com.teamviewer.incomingrcsharedlib.communication.VirtualKeyCode;

interface IAddonService {

    void injectUnicode(int arg1);

    void startPointerSequence(int arg1, int arg2, int arg3);

    void injectAndroidKeyCode(int arg1,in KeyAction arg2);

    void addPointerAction(int arg1,in PointerAction arg2, int arg3, int arg4);

    void addPointerAction2(int arg1,in PointerAction arg2, int arg3, int arg4, long arg5);

    void injectVirtualKeyCode(in VirtualKeyCode arg1,in KeyAction arg2);

    boolean verify();

    boolean copyScreenshot(in ScreenshotData arg1, int arg2);

    boolean isAvailable();

    void init();

    ScreenshotInfo getScreenshot();

    void doNothing();

    void cancelPointerSequence();
}
