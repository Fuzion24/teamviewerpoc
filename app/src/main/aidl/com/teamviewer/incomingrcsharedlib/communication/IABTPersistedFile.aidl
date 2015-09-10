package com.teamviewer.incomingrcsharedlib.communication;


parcelable IABTPersistedFile;

interface IABTPersistedFile {

    void close();

    boolean delete();

    boolean exists();

    int read(byte[] arg1);

    long skip(long arg1);

    int write(byte[] arg1);

    int writeWithCount(byte[] arg1, int arg2, int arg3);
}