package com.tyagi.repoproject.utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.tyagi.repoproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rajneesh on 15-02-2017.
 */

public class Utility {

    /**
     * The progress dialog.
     */
    public static ProgressDialog progressDialog;

    /**
     * Function to display simple Alert Dialog.
     *
     * @param context - application context
     * @param title   - alert dialog title
     * @param message - alert message
     * @author mayursharma
     */

    public static void showAlertDialog(Context context, String title, String message) {
        if (context != null && context instanceof Activity) {
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(context, R.style.Theme_AppCompat_Light_Dialog_Alert).create();

            title = TextUtils.isEmpty(title) ? context.getResources().getString(R.string.app_name) : title;

            // Setting Dialog Title
            alertDialog.setTitle(title);

            // Setting Dialog Message
            alertDialog.setMessage(message);

            // Setting alert dialog icon
            // alertDialog.setIcon((status) ? R.drawable.success :
            // R.drawable.fail);

            // Setting OK Button
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            // Showing Alert Message
            alertDialog.show();
        }
    }

    public static AlertDialog showAlertDialog(Context context, String title, String message, DialogInterface.OnClickListener listener) {
        if (context != null && context instanceof Activity && !((Activity) context).isFinishing()) {
            AlertDialog alertDialog;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                alertDialog = new AlertDialog.Builder(context, R.style.Theme_AppCompat_Light_Dialog_Alert).create();
            } else {
                alertDialog = new AlertDialog.Builder(context).create();
            }

            title = TextUtils.isEmpty(title) ? context.getResources().getString(R.string.app_name) : title;

            // Setting Dialog Title
            alertDialog.setTitle(title);

            // Setting Dialog Message
            alertDialog.setMessage(message);

            // Setting alert dialog icon
            // alertDialog.setIcon((status) ? R.drawable.success :
            // R.drawable.fail);

            // Setting Cancel Button
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", listener);

            // Setting OKAY Button
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Okay", listener);

            // Showing Alert Message
            alertDialog.show();

            return alertDialog;
        }
        return null;
    }

    /**
     * Function to display simple Progress Dialog.
     *
     * @param context      - activity context
     * @param title        - progress dialog title
     * @param message      - progress message
     * @param isCancelable the is cancelable
     * @author mayursharma
     */
    public static void showProgressDialog(Context context, String title, String message, boolean... isCancelable) {
        hideProgressDialog();
        if (context != null && context instanceof Activity) {
            title = TextUtils.isEmpty(title) ? context.getResources().getString(R.string.app_name) : title;
            message = TextUtils.isEmpty(message) ? "Loading. Please wait.." : message;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                progressDialog = new ProgressDialog(context);
            } else {
                progressDialog = new ProgressDialog(context, ProgressDialog.THEME_HOLO_LIGHT);
            }
            progressDialog.setTitle(title);
            progressDialog.setMessage(message);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            if (isCancelable.length > 0) {
                progressDialog.setCancelable(isCancelable[0]);
            }

            progressDialog.show();
        }
    }

    /**
     * Function to hide progress Dialog.
     *
     * @author mayursharma
     */
    public static void hideProgressDialog() {
        try {
            if (progressDialog != null) {
                if (progressDialog.isShowing()) {
                    progressDialog.hide();
                    progressDialog.dismiss();
                }
                progressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // validating email id
    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    //Get Map from Object
    public static Map<String, String> getMapFromJsonObj(JSONObject jsonObject) {
        HashMap<String, String> map = new HashMap<String, String>();
        if (jsonObject != null) {
            Iterator<?> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                String value = null;
                try {
                    value = jsonObject.getString(key);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                map.put(key, value);
            }
        }
        return map;
    }


    public static boolean isTablet(Context context) {
        if ((context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_LARGE

                ||

                (context.getResources().getConfiguration().screenLayout &
                        Configuration.SCREENLAYOUT_SIZE_MASK) ==
                        Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            // on a large screen device ...

            return true;

        }
        return false;
    }


    public static String getScreenDensity(Activity context) {
        String dpi = "";
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        switch (metrics.densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                dpi = "ldpi";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                dpi = "mdpi";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                dpi = "hdpi";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                dpi = "xhdpi";
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                dpi = "xxhdpi";
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                dpi = "xxxhdpi";
                break;
        }
        return dpi;
    }

    /*
    * get Device name
    */
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    public static boolean hasGPS(Context mContext) {
        PackageManager packageManager = mContext.getPackageManager();
        boolean hasGPS = packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);
        return hasGPS;
/*
        LocationManager manager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        return manager.getAllProviders().size() > 0; */
    }

}
