package trackwareschoolbus.parentschool.utilityParent;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.preference.PreferenceManager;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import trackwareschoolbus.parentschool.API.ApiFacade;
import trackwareschoolbus.parentschool.API.ApiRequest;
import trackwareschoolbus.parentschool.API.enumApi.EnumMethodApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumTypeHeader;
import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallBack;
import trackwareschoolbus.parentschool.MainFragmentActivity;
import trackwareschoolbus.parentschool.PermissionsHandlerActivity;
import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.api_v3.SchoolDataHolder;
import trackwareschoolbus.parentschool.api_v3.SharedPreferencesHelperV3;
import trackwareschoolbus.parentschool.app.Application;
import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener;

//import static android.Manifest.permission.CALL_PHONE;

/**
 * Created by  on 3/11/2017.
 */

public class UtilityParent implements IRestCallBack {

    public static final String LANGUAGE = "LANGUAGE";
    public static final String LANGUAGE_SELECTED = "LANGUAGE_SELECTED";
    public static final String TOKEN = "TOKEN";
    public static final String FIREBASE_TOKEN = "FIREBASE_TOKEN";
    public static final String AUTH = "AUTH";
    //    public static final String IS_REGISTER = "isRegister";
    public static final String IS_LOgIn = "isLogIn";
    public static final String ADDED_STUEDENTS = "ADDED_STUEDENTS";
    public static final String SECREET_TOKEN = "SECREET_TOKEN";
    public static String PIN_Code = "pin";
    public static String EMAIL = "email";
    public static String WEB_API_KEY_GCM = "AIzaSyDD3e2fti6JUgpUjOsMHwOQBe-Vc9ON7kk";
    //    public static String LOG_IN_DATA = "logInData";
    public static String PRESS_ABSENT = "press_absent";
    public static String STUDENT_NAME = "student_name";
    public static final String PARENT_ID = "PARENT_ID";
    public static final String PARENT_NAME = "PARENT_NAME";
    public static final String MOBILE_NUMBER = "MOBILE_NUMBER";
    public static final String TUTORIAL_DONE = "TUTORIAL_DONE";
    /**/
    public static final String SETTING_NOTIFICATION_CHECK_IN = "check_in";
    public static final String SETTING_NOTIFICATION_CHECK_OUT = "check_out";
    public static final String SETTING_NOTIFICATION_NEARBY = "nearby";
    /**/


    public static Drawable getTintDrawable(Context context, @DrawableRes int drawableResId, int color) {
        @SuppressLint("RestrictedApi")
        Drawable drawable = AppCompatDrawableManager.get().getDrawable(context, drawableResId);
        drawable = DrawableCompat.wrap(drawable).getConstantState().newDrawable();
        DrawableCompat.setTint(drawable, color);
        return drawable;
    }

    public static Drawable getTintListDrawable(Context context, @DrawableRes int drawableResId, ColorStateList colorStateList) {
        @SuppressLint("RestrictedApi")
        Drawable drawable = AppCompatDrawableManager.get().getDrawable(context, drawableResId);
        drawable = DrawableCompat.wrap(drawable).getConstantState().newDrawable();
        DrawableCompat.setTintList(drawable, colorStateList);
        return drawable;
    }

    public static void showMessage(Activity applicationContext, String message) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show();
    }

    public static void shakeViews(View... v) {
        for (int i = 0; i < v.length; i++) {
            v[i].startAnimation(AnimationUtils.loadAnimation(v[i].getContext(), R.anim.shake_shake));
        }

    }


    public static void shoeMessageToast(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    //region Shared Preference
    public static void clearAll() {
        Application.getPreferences().edit().clear().commit();

    }

    public static String getStringShared(String key) {
        return Application.getPreferences().getString(key, "");
    }


    public static String getStringSharedForFireBase(String key) {
        try {
            return Application.getPreferences().getString(key, "No Data");

        } catch (Exception e) {
            return "No Data";
        }

    }

    public static void setReferrerDate(String key, long date) {
        SharedPreferencesHelperV3.INSTANCE.sharedPreferencesInstance().edit().putLong(key, date).commit();
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double rlat1 = Math.PI * lat1 / 180.0f;
        double rlat2 = Math.PI * lat2 / 180.0f;
        double rlon1 = Math.PI * lon1 / 180.0f;
        double rlon2 = Math.PI * lon2 / 180.0f;

        double theta = lon1 - lon2;
        double rtheta = Math.PI * theta / 180.0f;

        double dist = Math.sin(rlat1) * Math.sin(rlat2) + Math.cos(rlat1) * Math.cos(rlat2) * Math.cos(rtheta);
        dist = Math.acos(dist);
        dist = dist * 180.0f / Math.PI;
        dist = dist * 60.0f * 1.1515f;

        if (unit == "K") {
            dist = dist * 1.609344f;
        }
        if (unit == "M") {
            dist = dist * 1.609344 * 1000f;
        }
        if (unit == "N") {
            dist = dist * 0.8684f;
        }
        return dist;
    }
//    public static String getReferrerDate(String key) {
//
//        Long stored_date = Application.getInstance().getPreferences().getLong(key, new Date().getTime());
//        Date date = new Date(stored_date);
//        return DateFormat.getDateInstance().format(date) + " - " + new SimpleDateFormat("HH:mm:ss.SSS").format(date);
//    }

//    public static ArrayList<KidBean> getAllKidsList(ArrayList<Integer> onlineKids) {
//        ArrayList<KidBean> temp_listKidBean = new ArrayList<KidBean>();
//        if (UtilityParent.getStringShared(UtilityParent.LOG_IN_DATA) != null) {
//            try {
//
//                Log.v("LOG_IN_DATA",UtilityParent.getStringShared(UtilityParent.LOG_IN_DATA));
//
//                LogInData resp = MyGson.getGson().fromJson(UtilityParent.getStringShared(UtilityParent.LOG_IN_DATA), LogInData.class);
//
////                LogInData resp = MyGson.getGson().fromJson(PathUrl.TEST_LOGIN_JSON, LogInData.class);
//
//
//
//                ArrayList<Kid> allKids = new ArrayList<Kid>(resp.getKids());
//
//        /**/
//
//                for (int i = 0; i < allKids.size(); i++) {
//                    KidBean kidBean = new KidBean();
//                    if (!onlineKids.contains(allKids.get(i).getId())) {
//
//
//                        kidBean.setId(allKids.get(i).getId());
//                        kidBean.setLatitude(Double.parseDouble(allKids.get(i).getDropOffLat()));
//                        kidBean.setLongitude(Double.parseDouble(allKids.get(i).getDropOffLng()));
//                /**/
//                        String[] studentName = allKids.get(i).getName().split(" ");
//                        kidBean.setStudentName(studentName[0]);
//                /**/
//                        kidBean.setStudentGrade(allKids.get(i).getStudentGrade());
//                        kidBean.setSchoolName(allKids.get(i).getSchoolName());
//                        if (allKids.get(i).getAvatar()== null ||allKids.get(i).getAvatar()=="null"){
//                            kidBean.setAvatar("null");
//                        }else {
//                            kidBean.setAvatar(allKids.get(i).getAvatar());}
//
//                        kidBean.setBusID(-1);
//                        kidBean.setDriverMobileToken("-");
//                        kidBean.setSchoolPhoneNumber(allKids.get(i).getSchoolMobileNumber());
//                        kidBean.setDriverPhoneNumber("-");
//                        kidBean.setRoundType("-");
//                        kidBean.setRoundId(-1);
//                       // kidBean.setOffline(true);
//
//
//                        temp_listKidBean.add(kidBean);
//                    }else {
//
//                         temp_listKidBean.add(kidBean);
//                    }
//
//                }
//        /**/
//
//
//            }  catch (Exception e) {
//               e.printStackTrace();
//            }
//        }
//        return temp_listKidBean;
//
//    }


    public static void setStringShared(String key, String value) {
        Application.getPreferences().edit().putString(key, value).commit();
    }

    public static int getIntShared(String key) {
        return Application.getPreferences().getInt(key, 0);
    }

    public static void setIntShared(String key, int value) {
        Application.getPreferences().edit().putInt(key, value).commit();
    }

    public static boolean getBooleanShared(String key) {
        return Application.getPreferences().getBoolean(key, false);
    }

    public static void setBooleanShared(String key, boolean value) {
        Application.getPreferences().edit().putBoolean(key, value).commit();
    }


    private static void saveSchoolHolders(ArrayList<SchoolDataHolder> list) {
        SharedPreferences.Editor editor = Application.getPreferences().edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("SchoolHolders", json);
        editor.commit();

    }

    private static ArrayList<SchoolDataHolder> getSchoolHolders() {
        try {
            SharedPreferences prefs = Application.getPreferences();
            Gson gson = new Gson();
            String json = prefs.getString("SchoolHolders", null);
            Type type = new TypeToken<ArrayList<SchoolDataHolder>>() {
            }.getType();
            ArrayList<SchoolDataHolder> data = gson.fromJson(json, type);
            if (data != null)
                return gson.fromJson(json, type);
        } catch (Exception e) {
        }
        return new ArrayList<SchoolDataHolder>();

    }

    private static ArrayList<SchoolDataHolder> savedholders = new ArrayList<>();

    public static ArrayList<SchoolDataHolder> getSavedlDataHolders() {
        if (savedholders.size() == 0) {
            savedholders.addAll(UtilityParent.getSchoolHolders());
        }
        return savedholders;
    }

    public static void clearSavedlDataHolders() {
         savedholders.clear();
    }

    public static void saveCurrentHolders() {
        if (savedholders != null && savedholders.size() != 0) {
            saveSchoolHolders(savedholders);
        }

    }

    public static boolean isEmptyString(String value) {
        try {
            if (value == null || value.trim().toLowerCase().equals("null") || value.trim().isEmpty()) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public static String getDateFormat(String format) {
        return new SimpleDateFormat(format, Locale.ENGLISH)
                .format(Calendar.getInstance(Locale.US).getTime());
    }


    public static Calendar createCalendar(int day, int month, int year) {
        Calendar calendarInstance = Calendar.getInstance(Locale.US);
        calendarInstance.set(Calendar.DAY_OF_MONTH, day);
        calendarInstance.set(Calendar.YEAR, year);
        calendarInstance.set(Calendar.MONTH, month);
        return calendarInstance;
    }

//    public static boolean afterDate(String toDate, String dateSearch) {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        Date strDate = null;
//        boolean value = false;
//        try {
//            strDate = sdf.parse(dateSearch);
//
//            if (sdf.parse(toDate).after(strDate)) {
//                value = true;
//            }
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return value;
//    }

//    public static boolean beforaDate(String fromDate, String dateSearch) {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        Date strDate = null;
//        boolean value = false;
//        try {
//            strDate = sdf.parse(dateSearch);
//
//            if (sdf.parse(fromDate).before(strDate)) {
//                value = true;
//            }
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return value;
//    }


    public static void setLocale(String lang, Activity activity, Class<MainFragmentActivity> baseActivityClass) {
        Locale myLocale = new Locale(lang);
        Resources res = activity.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(activity, baseActivityClass);
        activity.startActivity(refresh);
        activity.finish();
    }

    public static void setLocale(String lang, Context activity) {
        Locale myLocale = new Locale(lang);
        Resources res = activity.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }


    public static Map<String, String> typeHeaderMap(EnumTypeHeader type, boolean auth) {

        Map<String, String> headers = new HashMap<String, String>();


        if (type != EnumTypeHeader.EMPTY) {
            if (type == EnumTypeHeader.FORM) {
                headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            } else {
                headers.put("Content-Type", "application/json; charset=utf-8");
            }
        }

        if (auth) {
            headers.put("Authorization", getStringShared(UtilityParent.AUTH));
        }
        headers.put("locale", UtilityParent.getStringShared(UtilityParent.LANGUAGE));

        return headers;

    }
//    public static AlertDialog.Builder adb;
//    public static void showMessageDialog(Context mActivity, String tittle, String message) {
//        try {
//
//            if (message!=null && !message.equals(""))
//            UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeMessage(message);
//            else
//                UtilDialogs.showGeneralErrorDialog(mActivity);
////        if (adb==null) {
////            adb = new AlertDialog.Builder(mActivity);
////            adb.setTitle(tittle);
////            adb.setMessage(message);
////            adb.setIcon(android.R.drawable.ic_dialog_alert);
////        }
////        adb.setPositiveButton(mActivity.getString(R.string.ok), new DialogInterface.OnClickListener() {
////            public void onClick(DialogInterface dialog, int which) {
////                dialog.dismiss();
////            }
////        });
////        adb.show();
//    }catch (RuntimeException e){
//
//        }
//    }

    public static void showMessage(Context mContext, String tittle, final String message) {
        final AlertDialog.Builder adb = new AlertDialog.Builder(mContext);
        adb.setTitle(tittle);
        adb.setMessage(message);
        adb.setIcon(android.R.drawable.ic_dialog_alert);
        adb.setPositiveButton(mContext.getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        adb.show();

    }

    public static Date getDateSevenDayesBefore() {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeZone(TimeZone.getDefault());
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getDateNow() {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeZone(TimeZone.getDefault());
//        calendar.add(Calendar.DAY_OF_YEAR, -7);
        return calendar.getTime();
    }

    public static String getTimeZone() {
        Calendar c = Calendar.getInstance();
        return c.getTimeZone().toString();
    }

    public static boolean isNetworkAvailable(Activity mActivity) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void sentryLog(String methodName, String message) {


        final String messagess = UtilityParent.getStringShared("SECREETSECREET") + ", " + methodName + ", MESSAGE::" + message;

        callRestAPI("http://tikramservices.com/API/insert_test.php"
                ,
                new HashMap() {{
                    put("message", messagess);
                    put("app", "PARENT");

                }}
                ,
                EnumMethodApi.POST
                ,
                UtilityParent.this
                ,
                EnumNameApi.ABSENT
                ,
                UtilityParent.typeHeaderMap(EnumTypeHeader.FORM, true)
                ,
                EnumTypeHeader.FORM
        );
    }

    public static void callRestAPI(
            String PATH_URL,
            HashMap params,
            EnumMethodApi verb,
            IRestCallBack restCallBack,
            EnumNameApi enumNameApi,
            Map<String, String> mapHeader,
            EnumTypeHeader enumTypeHeader
    ) {
        ApiFacade callApi = new ApiFacade();
        callApi.onStartVolley(new ApiRequest(PATH_URL,
                        params,
                        verb,
                        restCallBack,
                        enumNameApi,
                        mapHeader
                )
                ,
                enumTypeHeader
        );
    }


    //    public static void makeCall(Activity mActivity,String number) {
//        try
//        {
//            if(Build.VERSION.SDK_INT > 22)
//            {
//                if (ActivityCompat.checkSelfPermission(mActivity, CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//
//                    ActivityCompat.requestPermissions(mActivity, new String[]{CALL_PHONE}, 101);
//                    checkAllPermissions
//                    return;
//                }
//
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                callIntent.setData(Uri.parse("tel:" + number));
//                mActivity.startActivity(callIntent);
//
//            }
//            else {
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                callIntent.setData(Uri.parse("tel:" + number));
//                mActivity.startActivity(callIntent);
//            }
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//
//
//    }
    public static void textJustify(String text, TextView textview) {
        textview.setText(String.valueOf(Html
                .fromHtml("<![CDATA[<body style=\"text-align:justify;color:#222222; \">"
                        + text
                        + "</body>]]>")));
    }

    @Override
    public void onRestCallBack(String response, EnumNameApi nameApiEnum) {

    }

    @Override
    public void onRestCallBack(JSONObject response, EnumNameApi nameApiEnum) {

    }

    @Override
    public void onRestCallBack(VolleyError volleyError, EnumNameApi nameApiEnum) {

    }

    @Override
    public void onRestCallBack(NetworkResponse networkResponse, EnumNameApi nameApiEnum) {

    }


    public static int getCountOfDays(String createdDateString, String expireDateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        Date createdConvertedDate = null, expireCovertedDate = null, todayWithZeroTime = null;
        try {
            createdConvertedDate = dateFormat.parse(createdDateString);
            expireCovertedDate = dateFormat.parse(expireDateString);

            Date today = new Date();

            todayWithZeroTime = dateFormat.parse(dateFormat.format(today));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int cYear = 0, cMonth = 0, cDay = 0;

        if (createdConvertedDate.after(todayWithZeroTime)) {
            Calendar cCal = Calendar.getInstance(Locale.US);

            cCal.setTime(createdConvertedDate);
            cYear = cCal.get(Calendar.YEAR);
            cMonth = cCal.get(Calendar.MONTH);
            cDay = cCal.get(Calendar.DAY_OF_MONTH);

        } else {
            Calendar cCal = Calendar.getInstance(Locale.US);
            cCal.setTime(todayWithZeroTime);
            cYear = cCal.get(Calendar.YEAR);
            cMonth = cCal.get(Calendar.MONTH);
            cDay = cCal.get(Calendar.DAY_OF_MONTH);
        }


    /*Calendar todayCal = Calendar.getInstance();
    int todayYear = todayCal.get(Calendar.YEAR);
    int today = todayCal.get(Calendar.MONTH);
    int todayDay = todayCal.get(Calendar.DAY_OF_MONTH);
    */

        Calendar eCal = Calendar.getInstance(Locale.US);
        eCal.setTime(expireCovertedDate);

        int eYear = eCal.get(Calendar.YEAR);
        int eMonth = eCal.get(Calendar.MONTH);
        int eDay = eCal.get(Calendar.DAY_OF_MONTH);

        Calendar date1 = Calendar.getInstance(Locale.US);
        Calendar date2 = Calendar.getInstance(Locale.US);

        date1.clear();
        date1.set(cYear, cMonth, cDay);
        date2.clear();
        date2.set(eYear, eMonth, eDay);

        long diff = date2.getTimeInMillis() - date1.getTimeInMillis();

        float dayCount = (float) diff / (24 * 60 * 60 * 1000);

        return (int) dayCount;
    }


    public static boolean isEmptyString(String... value) {
        boolean check = false;
        try {
            for (String str : value) {
                if (isEmptyString(str))
                    return true;
            }
        } catch (Exception e) {
            check = true;
        }

        return false;

    }

//    private static void goToSavingMood(Activity activity) {
//        try {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                Intent intent = new Intent();
//                intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
//                intent.setData(Uri.parse("package:" + activity.getApplication().getPackageName()));
//                activity.startActivity(intent);
//            }
//
//        } catch (Exception e) {
////            Crashlytics.logException(e);
//        }
//    }

//    private static void checkPowerPermission(Activity activity) {
//        if (Build.VERSION.SDK_INT >= 23) {
//            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS}, 9991);
//        }
//    }
//
//    private static boolean checkSavingMood(Activity activity) {
//        try {
//            PowerManager pm = (PowerManager) activity.getApplication().getSystemService(Context.POWER_SERVICE);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if (!pm.isIgnoringBatteryOptimizations(activity.getApplication().getPackageName())) {
//                    return true;
//
//                }
//            }
//        } catch (Exception e) {
//            return false;
//        }
//        return false;
//    }


    public static void showNotificationIssueMessage(PermissionsHandlerActivity activity) {
        activity.batteryOptimizationsPermission(new OnActionDoneListener<Boolean>() {
            @Override
            public void OnActionDone(Boolean aBoolean) {
                if (aBoolean) {
//                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Intent intent = new Intent();
                    String packageName = activity.getApplication().getPackageName();
                    PowerManager pm = (PowerManager) activity.getApplication().getSystemService(Context.POWER_SERVICE);
                    if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                        intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                        intent.setData(Uri.parse("package:" + packageName));
                        activity.startActivity(intent);
                    }
//                    }
                }
            }
        });
//        if (checkSavingMood(activity)) {
//            checkPowerPermission(activity);
//            final AlertDialog.Builder adb = new AlertDialog.Builder(activity);
//            adb.setTitle(R.string.notification_issue_alert_title);
//            adb.setMessage(R.string.notification_issue_alert_message);
//            adb.setIcon(android.R.drawable.ic_dialog_alert);
//            adb.setPositiveButton(activity.getString(R.string.notification_issue_alert_button), new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                    goToSavingMood(activity);
//                }
//            });
//            adb.setNegativeButton(activity.getString(R.string.cancel), new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });
//            adb.show();
//        }


    }


}
