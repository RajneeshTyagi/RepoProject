package com.tyagi.repoproject.main;

import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Rajneesh on 15-02-2017.
 */

public  class CoreApplication extends Application {


    private static CoreApplication coreApplication ;

    public static CoreApplication getInstance(){
        return coreApplication;
    }


    public static AndroidPreference androidPreference;

    private Activity currentActivity ;

    public static final String TAG = CoreApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        coreApplication = this ;
//        initialize();
        androidPreference = AndroidPreference.getInstance(this);
    }



    private int versionCode ;
    private String versionName ;

    public int getVersionCode(){
        fillVersionData();
        return versionCode;
    }

    public String getVersionName(){
        fillVersionData();
        return versionName;
    }

    private void fillVersionData(){
        if(versionCode == 0 || versionName == null) {
            PackageInfo pInfo = null;
            try {
                pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                versionCode = pInfo.versionCode;
                versionName = pInfo.versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCurrentActivity(Activity activity){
        this.currentActivity = activity;
    }

    public Activity getCurrentActivity(){
        return currentActivity;
    }


//    public abstract void initialize();

}
