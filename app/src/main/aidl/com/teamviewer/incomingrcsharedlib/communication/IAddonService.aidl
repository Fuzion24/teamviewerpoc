// IAddonService.aidl
package com.teamviewer.incomingrcsharedlib.communication;

import com.teamviewer.incomingrcsharedlib.communication.KeyAction;
import com.teamviewer.incomingrcsharedlib.communication.PointerAction;
import com.teamviewer.incomingrcsharedlib.communication.ScreenshotData;
import com.teamviewer.incomingrcsharedlib.communication.ScreenshotInfo;
import com.teamviewer.incomingrcsharedlib.communication.VirtualKeyCode;

interface IAddonService {

    void a(int arg1);

    void b(int arg1, int arg2, int arg3);

    void c(int arg1,in KeyAction arg2);

    void d(int arg1,in PointerAction arg2, int arg3, int arg4);

    void e(int arg1,in PointerAction arg2, int arg3, int arg4, long arg5);

    void f(in VirtualKeyCode arg1,in KeyAction arg2);

    boolean g();

    boolean h(in ScreenshotData arg1, int arg2);

    boolean i();

    void j();

    ScreenshotInfo k();

    void l();

    void m();
}
