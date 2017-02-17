package com.tyagi.repoproject.activitys.basic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.assent.Assent;
import com.afollestad.assent.AssentCallback;
import com.afollestad.assent.PermissionResultSet;

/**
 * Created by Rajneesh on 15-02-2017.
 */

public abstract class CustomAppCompatActivity_Permissions extends CustomAppCompatActivity {

    public void grantPermission(@NonNull final String permission, @NonNull final int requestCode) {
        if (!Assent.isPermissionGranted(permission)) {
            // The if statement checks if the permission has already been granted before
            Assent.requestPermissions(new AssentCallback() {
                @Override
                public void onPermissionResult(PermissionResultSet result) {
                    boolean isGranted = result.isGranted(permission);
                    if (isGranted) {
                        permissionGranted(permission, requestCode);
                    } else {
                        permissionDenied(permission, requestCode);
                    }
                }
            }, requestCode, permission);
        } else {
            permissionGranted(permission, requestCode);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Updates the activity when the Activity is first created
        // That way you can request permissions from within onCreate()
        Assent.setActivity(this, this);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        // Updates the activity every time the Activity becomes visible again
        Assent.setActivity(this, this);

        super.onResume();
    }

    @Override
    protected void onPause() {
        // Cleans up references of the Activity to avoid memory leaks
        if (isFinishing())
            Assent.setActivity(this, null);
        super.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Lets Assent handle permission results, and contact your callbacks
        Assent.handleResult(permissions, grantResults);
        onRequestPermissionsResultCustom(requestCode, permissions, grantResults);
    }

    abstract public void permissionGranted(String permission, int requestCode);

    abstract public void permissionDenied(String permission, int requestCode);

    abstract public void onRequestPermissionsResultCustom(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);

    /**
     * See http://developer.android.com/guide/topics/security/permissions.html#normal-dangerous for
     * a list of 'dangerous' permissions that require a permission request on API 23.
     */

    public static final int REQUEST_CODE_READ_CALENDAR = 101;
    public static final int REQUEST_CODE_WRITE_CALENDAR = 102;

    public static final int REQUEST_CODE_CAMERA = 103;

    public static final int REQUEST_CODE_READ_CONTACTS = 104;
    public static final int REQUEST_CODE_WRITE_CONTACTS = 105;
    public static final int REQUEST_CODE_GET_ACCOUNTS = 106;

    public static final int REQUEST_CODE_ACCESS_FINE_LOCATION = 107;
    public static final int REQUEST_CODE_ACCESS_COARSE_LOCATION = 108;

    public static final int REQUEST_CODE_RECORD_AUDIO = 109;

    public static final int REQUEST_CODE_READ_PHONE_STATE = 110;
    public static final int REQUEST_CODE_CALL_PHONE = 111;
    public static final int REQUEST_CODE_READ_CALL_LOG = 112;
    public static final int REQUEST_CODE_WRITE_CALL_LOG = 113;
    public static final int REQUEST_CODE_ADD_VOICEMAIL = 114;
    public static final int REQUEST_CODE_USE_SIP = 115;
    public static final int REQUEST_CODE_PROCESS_OUTGOING_CALLS = 116;

    public static final int REQUEST_CODE_BODY_SENSORS = 117;

    public static final int REQUEST_CODE_SEND_SMS = 118;
    public static final int REQUEST_CODE_RECEIVE_SMS = 119;
    public static final int REQUEST_CODE_READ_SMS = 120;
    public static final int REQUEST_CODE_RECEIVE_WAP_PUSH = 121;
    public static final int REQUEST_CODE_RECEIVE_MMS = 122;

    public static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 123;
    public static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 124;

    public static final int REQUEST_CODE_SYSTEM_ALERT_WINDOW = 125;
    public static final int REQUEST_CODE_WRITE_EXTERNAL_STORRAGE = 126;
    public static final int REQUEST_CODE_READ_EXTERNAL_STORRAGE = 127;


}
