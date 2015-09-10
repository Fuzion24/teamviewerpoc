// IABTPersistence.aidl
package com.absolute.android.persistence;

import com.teamviewer.incomingrcsharedlib.communication.KeyAction;
import com.teamviewer.incomingrcsharedlib.communication.PointerAction;
import com.teamviewer.incomingrcsharedlib.communication.ScreenshotData;
import com.teamviewer.incomingrcsharedlib.communication.ScreenshotInfo;
import com.teamviewer.incomingrcsharedlib.communication.VirtualKeyCode;


interface IABTPersistence {
    void downloadApk(String arg1, int arg2, String arg3, String arg4, String arg5, IABTDownloadReceiver
            arg6, int arg7);

    AppProfile[] getAllApplicationProfiles();

    void getAppInfo(String arg1, String arg2, String arg3, String arg4, IABTGetAppInfoReceiver arg5);

    AppProfile getApplicationProfile(String arg1);

    String getDeviceId();

    String getDiagnostics();

    IABTPersistenceLog getLog(String arg1);

    int getPersistedAppCount();

    IABTPersistedFile getPersistedFile(String arg1, String arg2, boolean arg3);

    int getState();

    int getVersion();

    void install(AppProfile arg1, String arg2, IABTResultReceiver arg3);

    void invokeMethodAsSystem(MethodSpec arg1, IABTResultReceiver arg2);

    void refreshDeviceId();

    void registerPing(String arg1, IABTPing arg2, int arg3);

    void setAllPersistence(boolean arg1);

    void setApplicationProfile(AppProfile arg1);

    void setPersistence(String arg1, boolean arg2);

    void setState(int arg1);

    void testFirmwareUpdate();

    void uninstall(String arg1, boolean arg2, IABTResultReceiver arg3);

    void unregisterPing(String arg1);
}
