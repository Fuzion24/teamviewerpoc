/*
 * Copyright (C) 2014 Opersys inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.opersys.raidl;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.opersys.raidl.output.AidlOutputFormatter;
import com.opersys.raidl.output.ListOutputFormatter;
import com.opersys.raidl.output.OutputFormatter;
import com.opersys.raidl.output.SingleAidlOutputFormatter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class Raidl {

    private static String TAG = "Raidl";


    private static boolean isRemoteMethod(Method method) {
        boolean isRemoteMethod = false;

        for (Class<?> methodException : method.getExceptionTypes()) {
            if (methodException == RemoteException.class) {
                isRemoteMethod = true;
                break;
            }
        }

        return isRemoteMethod;
    }

    private static Class tryloadServiceClass(String serviceClassName) throws ClassNotFoundException {
        Class serviceClass = null;
        int idx = 0;

        // This is a list of namespaces in which to search for service that might return a simplified
        // interface name instead of a canonical name.
        String[] androidNamespacesPrefixes = {
                "",
                "android.os.",
                "android.os.storage.",
                "android.service.",
                "android.service.notification.",
                "android.service.textservice.",
                "android.accessibilityservice"
        };

        while (serviceClass == null) {
            String augmentedInterfaceName;

            if (idx == androidNamespacesPrefixes.length)
                throw new ClassNotFoundException("Class not found for "
                        + serviceClassName
                        + " (C++ services not supported)");

            augmentedInterfaceName = androidNamespacesPrefixes[idx++] + serviceClassName;

            try {
                serviceClass = Raidl.class.getClassLoader().loadClass(augmentedInterfaceName);
            } catch (ClassNotFoundException ex) {
                serviceClass = null;
            }
        }

        return serviceClass;
    }

    private static boolean looksLikeTransactionCode(String transactionCodeName) {
        return transactionCodeName.startsWith("TRANSACTION_") || transactionCodeName.endsWith("_TRANSACTION");
    }

    private static String getMethodNameForTransaction(String serviceName, String transactionCodeName) {
        // This is a list of methods in IActivityManager for which the service code doesn't
        // quite give us the method name after our simple transformation.
        String[][] activityServiceQuirks = {
                {"clearAppData", "clearApplicationUserData"}, // 4.1
                {"getDeviceConfiguration", "getDeviceConfigurationInfo"}, // 4.1
                {"startBackupAgent", "bindBackupAgent"}, // 4.1
                {"killApplicationWithAppid", "killApplicationWithAppId"}, // 4.4
                {"resizeStack", "resizeStackBox"}, // 4.4
        };

        if (transactionCodeName.startsWith("TRANSACTION_")) {
            return transactionCodeName.replace("TRANSACTION_", "");
        }
        // This is to handle transaction codes in the style of IActivityManager.java
        else if (transactionCodeName.endsWith("_TRANSACTION")) {
            String[] transactMethNameParts = transactionCodeName.replace("_TRANSACTION", "").split("_");
            String transactMethName = "";

            for (String namePart : transactMethNameParts) {
                if (transactMethName.equals(""))
                    transactMethName += namePart.toLowerCase();
                else
                    transactMethName += namePart.substring(0, 1) + namePart.substring(1).toLowerCase();
            }

            if (serviceName.equals("activity")) {
                for (String[] quirk : activityServiceQuirks) {
                    if (quirk[0].equals(transactMethName))
                        return quirk[1];
                }
            }

            return transactMethName;
        }

        throw new IllegalArgumentException(
                "Codename doesn't look like a transaction code constant: " + transactionCodeName);
    }

    public static int reverseAidl(IBinder serviceBinder) {

        String serviceClassName;
        Class<?> serviceClass = null, serviceStubClass;
        SortedMap<Integer, String> serviceCodesMethods;
        Map<String, Method> serviceMethods;
        boolean singleDisplay = false;
        AidlService aidlService;
        OutputFormatter outputFormatter;

        // Determine if we output a full AIDL or just the signature of one method.

                outputFormatter = new AidlOutputFormatter();




        try {
            serviceClassName = serviceBinder.getInterfaceDescriptor();



            serviceClass = tryloadServiceClass(serviceClassName);

            serviceStubClass = serviceClass;

            aidlService = new AidlService(serviceClassName, serviceClass);

            serviceCodesMethods = new TreeMap<Integer, String>();

            // Get the transaction codes.
            for (Field serviceField : serviceStubClass.getDeclaredFields()) {
                int serviceFieldValue;
                String methodName;

                if (serviceField.getType() == int.class && looksLikeTransactionCode(serviceField.getName())) {
                    serviceField.setAccessible(true);
                    serviceFieldValue = serviceField.getInt(null);
                    methodName = getMethodNameForTransaction(serviceClassName, serviceField.getName());
                    serviceCodesMethods.put(serviceFieldValue, methodName);
                }
            }

            serviceMethods = new HashMap<String, Method>();

            // Get the methods by name.
            for (Method serviceMethod : serviceClass.getMethods())
                serviceMethods.put(serviceMethod.getName(), serviceMethod);

            for (Integer serviceCode : serviceCodesMethods.keySet()) {
                Method serviceMethod;
                String serviceCodeMethodName;

                serviceCodeMethodName = serviceCodesMethods.get(serviceCode);

                // Examine just what the user passed as command line argument.



                serviceMethod = serviceMethods.get(serviceCodeMethodName);

                if (serviceMethod != null && isRemoteMethod(serviceMethod))
                    aidlService.addMethod(serviceCode, serviceMethod);
            }


        } catch (RemoteException e) {
            String s = "Error communicating with Binder";
            System.err.println(s);
            Log.e(TAG, s, e);
            return 1;

        } catch (ClassNotFoundException e) {
            String s = "Failed to load class for service (C++ services not supported)'";
            System.err.println(s);
            Log.e(TAG, s, e);
            return 1;

        } catch (IllegalAccessException e) {
            String s = "Illegal access exception for service";
            System.err.println(s);
            Log.e(TAG, s, e);
            return 1;
        }

        return 0;
    }
}