package com.tyagi.repoproject.utility;

public class UtilExceptionHandler {

    private final boolean isShowError = true;

    public void printStackTrace(Exception e) {
        if (isShowError)
            e.printStackTrace();
    }
/*

    public static void handleRetrofitError(Activity curr_activity, RetrofitError error){
        try{
            com.app.mazkara.helper.UtilToastMessage obj_toast = UtilToastMessage.getInstance();

            if (error.getKind() == RetrofitError.Kind.NETWORK) {
                obj_toast.makeToast(curr_activity, "Internet Not connected", UtilGlobal.MODE_RELEASE);
            } else if (error.getKind() == RetrofitError.Kind.HTTP) {
                obj_toast.makeToast(curr_activity, "Not to able to reach host !!", UtilGlobal.MODE_RELEASE);
            } else if (error.getKind() == RetrofitError.Kind.UNEXPECTED) {
                obj_toast.makeToast(curr_activity, "Sorry !! Something went wrong. Please try Later.", UtilGlobal.MODE_RELEASE);
            }
        } catch(Exception e){

        }
    }
*/

    public static void handleRetrofitErrorStackTrace(Exception exception) {
//        if(UtilGlobal.isValidMode(UtilGlobal.MODE_DEVELOPMENT)) {
        exception.printStackTrace();
//        }
    }

    private static UtilExceptionHandler uniqInstance;

    public static synchronized UtilExceptionHandler getInstance() {
        if (uniqInstance == null) {
            uniqInstance = new UtilExceptionHandler();
        }
        return uniqInstance;
    }
}
