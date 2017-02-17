package com.tyagi.repoproject.interfaces.basic;


import com.tyagi.repoproject.main.AndroidPreference;
import com.tyagi.repoproject.main.CoreApplication;
import com.tyagi.repoproject.utility.UtilExceptionHandler;

/**
 * Created by Rajneesh on 15-02-2017.
 */

public interface InstancesInterface {
    public static final CoreApplication mCoreApplication = CoreApplication.getInstance();
    public static final AndroidPreference mAndroidPreference = mCoreApplication.androidPreference;
    public static final UtilExceptionHandler mUtilExceptionHandler = UtilExceptionHandler.getInstance();

}
