//package trackwareschoolbus.parentschool.toolsV2;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.preference.PreferenceManager;
//
//import trackwareschoolbus.parentschool.app.Application;
//
//
///**
// * Created by  on 2/20/2018.
// */
//
//public class SharedPrefToolsV2 implements ConstantsV2.SHARED_PREFERENCES_KEYS {
//
//    private static SharedPreferences sharedPreferences;
//
//    public static SharedPreferences getPreferences() {
//        return sharedPreferences;
//    }
//
//    public static void setSharedPreferences(Context context) {
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//    public static String getLastUserState() {
//        try {
//            return getPreferences().getString(LAST_USER_STATE.LAST_USER_STATE, LAST_USER_STATE.SELECTING_LANGUAGE);
//        } catch (Exception e) {
//            return LAST_USER_STATE.SELECTING_LANGUAGE;
//
//        }
//    }
//
//    public static boolean setLastUserState(String last_user_state) {
//        try {
//            return setValue(LAST_USER_STATE.LAST_USER_STATE, last_user_state);
//        } catch (Exception e) {
//            return false;
//
//        }
//    }
//
//
//    public static boolean setMobileNumber(String number) {
//        return setValue(USER_SAVED_MOBILE_NUMBER, number);
//    }
//
//
//
//    public static boolean setPin(String pin) {
//        return setValue(USER_SAVED_PIN, pin);
//    }
//
//
//    public static boolean setEmail(String email) {
//        return setValue(USER_SAVED_EMAIL, email);
//    }
//
//
//
//    public static boolean setCurrentAppLanguage(String user_selected_language) {
//        return setValue(USER_SELECTED_LANGUAGE, user_selected_language);
//    }
//
//
////    public static boolean setLogInData(LogInResponse logInResponse) {
////        try {
////            String stringLogInResponse = GsonUtils.getGson().toJson(logInResponse);
////            return setValue(USER_LOG_IN_DATA, stringLogInResponse);
////        } catch (Exception e) {
////            e.printStackTrace();
////            return false;
////        }
////    }
////
////    public static LogInResponse getLogInData() {
////        try {
////            return GsonUtils.StringToGson(getStringValue(USER_LOG_IN_DATA));
////        } catch (Exception e) {
////            e.printStackTrace();
////            return null;
////        }
////    }
//
//
//}
