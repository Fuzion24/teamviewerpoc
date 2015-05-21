package com.nowsecure.twpoc;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import java.math.BigInteger;
import javax.security.cert.X509Certificate;

public class CertToys {

    public static void doStuff(Context ctx){
        BigInteger a = new BigInteger("1287658381");
        BigInteger thisSerial = a(ctx.getPackageName(), ctx).getSerialNumber();
        Log.d("TWPOC", String.valueOf(thisSerial));
        Log.d("TWPOC", String.valueOf(a));


    }


    private static X509Certificate a(String arg5, Context arg6) {
        X509Certificate v0 = null;
        try {
            PackageManager v1_2 = arg6.getPackageManager();
            if(v1_2 == null) {
                Log.d("PackageSignatureInfo", "packageManager is null");
                return v0;
            }

            Signature[] v1_3 = v1_2.getPackageInfo(arg5, 64).signatures;
            if(v1_3.length != 1) {
                Log.d("PackageSignatureInfo", "invalid number of signatures (" + v1_3.length + ") for package \'"
                        + arg5 + "\'");
                return v0;
            }

            Signature v1_4 = v1_3[0];
            if(v1_4 == null) {
                Log.d("PackageSignatureInfo", "signature is null");
                return v0;
            }

            v0 = X509Certificate.getInstance(v1_4.toByteArray());
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return v0;
    }

}
