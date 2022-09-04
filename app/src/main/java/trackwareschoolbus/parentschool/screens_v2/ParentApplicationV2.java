//package trackwareschoolbus.parentschool.screens_v2;
//
//import android.content.SharedPreferences;
//import android.content.res.Configuration;
//import android.content.res.Resources;
//import android.preference.PreferenceManager;
//import android.text.TextUtils;
//
//import androidx.multidex.MultiDexApplication;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.Volley;
//import com.google.gson.JsonObject;
//
//import org.json.JSONObject;
//
//import java.util.Arrays;
//import java.util.Locale;
//
//import io.sentry.SentryClient;
//import trackwareschoolbus.parentschool.API.ApiRequest;
//import trackwareschoolbus.parentschool.toolsV2.ConstantsV2;
//import trackwareschoolbus.parentschool.toolsV2.SharedPrefToolsV2;
//
////import com.google.android.gms.analytics.GoogleAnalytics;
////import com.google.android.gms.analytics.Tracker;
////import trackwareschoolbus.parentschool.PhoenixPlug;
//
///**
// * Created by  on 3/6/17.
// */
//
//public class ParentApplicationV2 extends MultiDexApplication {
//
//    private static ParentApplicationV2 mInstance;
//    private SharedPreferences preferences;
//    private final static String LOG_TAG = ParentApplicationV2.class.getSimpleName();
////    LocalizationApplicationDelegate localizationDelegate = new LocalizationApplicationDelegate(this);
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        String lang = SharedPrefToolsV2.getCurrentAppLanguage();
////        String lang = UtilityParent.getStringShared(UtilityParent.LANGUAGE).equals("ar") ? "ar" : "en";
//        setLocale(new Locale(lang));
//    }
//
//    private void setLocale(Locale locale) {
//        Configuration conf = getBaseContext().getResources().getConfiguration();
//        conf.locale = locale;
//        getBaseContext().getResources().updateConfiguration(conf, getResources().getDisplayMetrics());
//        Configuration systemConf = Resources.getSystem().getConfiguration();
//        systemConf.locale = locale;
//        Resources.getSystem().updateConfiguration(systemConf, Resources.getSystem().getDisplayMetrics());
//        Locale.setDefault(locale);
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
////        AccountKit.initialize(getApplicationContext(), new AccountKit.InitializeCallback() {
////            @Override
////            public void onInitialized() {
////
////            }
////        });
//
//
//        mInstance = this;
//        this.preferences = PreferenceManager.getDefaultSharedPreferences(mInstance);
////        if (!UtilityParent.isEmptyString(UtilityParent.getStringShared(UtilityParent.LANGUAGE))) {
//////            UtilityParent.setStringShared(UtilityParent.LANGUAGE, Locale.getDefault().getLanguage().toLowerCase());
////            UtilityParent.setLocale(UtilityParent.getStringShared(UtilityParent.LANGUAGE), this);
////        }
//
//
////        ReferrerReceiver.setFirstLaunch(this);
//
////        UtilityParent.setStringShared(UtilityParent.SECREET_TOKEN, "fdb9739fbe763fb3f6bb0570eaa4657b");
//
//
////        if (MainFragmentActivity.SOCKET_API) {
////            PhoenixPlug.getInstance().initializeSocket(new Callable<Void>() {
////
////                public Void call() throws Exception {
////                    // socket is opened now:
////                    PhoenixPlug.getInstance().socket.onOpen(new ISocketOpenCallback() {
////                        @Override
////                        public void onOpen() throws Exception {
////
////                        }
////                    });
////
////
////                    try {
////                        socketConnected();
////                    } catch (Exception e) {
////
////                    }
////                    return null;
////                }
////            }, this);
////
////        }
//
//    }
//
//
//    public static synchronized ParentApplicationV2 getInstance() {
//        return mInstance;
//    }
//
//    public SharedPreferences getPreferences() {
//        return this.preferences;
//    }
//
//    public static synchronized ParentApplicationV2 getInstanceVolly() {
//        return getInstance();
//    }
//
//    private RequestQueue mRequestQueue;
//
//    public <T> void addToRequestQueue(Request<T> req, String tag) {
//        // set the default tag if tag is empty
//        req.setTag(TextUtils.isEmpty(tag) ? LOG_TAG : tag);
//        getRequestQueue().add(req);
//    }
//
//    public RequestQueue getRequestQueue() {
//        if (mRequestQueue == null) {
//            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
//        }
//
//        return mRequestQueue;
//    }
//
//    public static boolean isActivityVisible() {
//        return activityVisible;
//    }
//
//    public static void activityResumed() {
//        activityVisible = true;
//    }
//
//    public static void activityPaused() {
//        activityVisible = false;
//    }
//
//    private static boolean activityVisible = false;
//
//
////    @Override
////    protected void attachBaseContext(Context base) {
////        super.attachBaseContext(localizationDelegate.attachBaseContext(base));
////    }
////
////    @Override
////    public void onConfigurationChanged(Configuration newConfig) {
////        super.onConfigurationChanged(newConfig);
////        localizationDelegate.onConfigurationChanged(this);
////    }
////
////    @Override
////    public Context getApplicationContext() {
////        return localizationDelegate.getApplicationContext(super.getApplicationContext());
////    }
//
//
//    private static SentryClient your_dsn;
//
//
//    private static String volleyErrorToString(VolleyError volleyError) {
//        String volleyError_StackTrace;
//        try {
//            volleyError_StackTrace = Arrays.toString(volleyError.getStackTrace());
//        } catch (Exception e) {
//            volleyError_StackTrace = "volleyError";
//        }
//        return volleyError_StackTrace;
//    }
//
//    private static String exceptionToString(Exception exception) {
//        String exception_StackTrace;
//        try {
//            exception_StackTrace = Arrays.toString(exception.getStackTrace());
//        } catch (Exception e) {
//            exception_StackTrace = "volleyError";
//        }
//        return exception_StackTrace;
//    }
//
//    private static String apiRequestToString(ApiRequest apiRequest) {
//        String apiRequest_api_name;
//        try {
//            apiRequest_api_name = "api_url is :" + apiRequest.getUrl_path();
//            apiRequest_api_name += "api_name is :" + apiRequest.getEnumNameApi();
//        } catch (Exception e) {
//            apiRequest_api_name = "nodata";
//        }
//        return apiRequest_api_name;
//    }
//
//    private static String apiRequestToString(JSONObject jSONObject) {
//        String jSONObjectString = "";
//        try {
//            jSONObjectString = jSONObject.toString();
//        } catch (Exception e) {
//            jSONObjectString = "nodata";
//        }
//        return jSONObjectString;
//    }
//
//    public static void logEvents(Object api_object, String class_and_method, Object... exceptions) {
////        String api_name = "no data";
////        if (api_object != null) {
////            if (api_object instanceof String)
////                api_name = api_object.toString();
////            if (api_object instanceof ApiRequest)
////                api_name = apiRequestToString((ApiRequest) api_object);
////        }
////        class_and_method = class_and_method == null ? "null" : class_and_method;
////
////        try {
////
////            if (your_dsn == null) {
////                your_dsn = Sentry.init(
////                        Constants.Urls.LOG_URL,
////                        new AndroidSentryClientFactory(mInstance)
////                );
////            }
////
////            JsonObject jsonLog = new JsonObject();
////            LogInData logInData = MyGson.getGson().fromJson(UtilityParent.getStringShared(UtilityParent.LOG_IN_DATA), LogInData.class);
////
////            jsonLog.addProperty("TIME", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a", Locale.US).format(new Date()));
////            jsonLog.addProperty("AUTH", UtilityParent.getStringSharedForFireBase(UtilityParent.AUTH));
////            jsonLog.addProperty("LANGUAGE", UtilityParent.getStringSharedForFireBase(UtilityParent.LANGUAGE));
////            jsonLog.addProperty("PIN_Code", UtilityParent.getStringSharedForFireBase(UtilityParent.PIN_Code));
////            jsonLog.addProperty("LOG_IN_DATA", logInData.toString());
////            jsonLog.addProperty("PARENT_ID", UtilityParent.getStringSharedForFireBase(UtilityParent.PARENT_ID));
////            jsonLog.addProperty("PARENT_NAME", UtilityParent.getStringSharedForFireBase(UtilityParent.PARENT_NAME));
////            jsonLog.addProperty("MOBILE_NUMBER", UtilityParent.getStringSharedForFireBase(UtilityParent.MOBILE_NUMBER));
////            jsonLog.addProperty("PARENT_NAME", UtilityParent.getStringSharedForFireBase(UtilityParent.PARENT_NAME));
////
////            jsonLog.addProperty("class_and_method", class_and_method);
////            jsonLog.addProperty("api_name", api_name);
////            jsonLog.add("Device Info", getDeviceinfo());
////
////            if (exceptions!=null){
////
////                if (exceptions != null) {
////                    for (int i = 0; i < exceptions.length; i++) {
////                        if (exceptions[i] != null) {
////                            if (exceptions[i] instanceof String)
////                                jsonLog.addProperty("exception_data_" + i, (String) exceptions[i]);
////                            if (exceptions[i] instanceof VolleyError)
////                                jsonLog.addProperty("exception_data_" + i, volleyErrorToString((VolleyError) exceptions[i]));
////                            if (exceptions[i] instanceof Exception)
////                                jsonLog.addProperty("exception_data_" + i, exceptionToString((Exception) exceptions[i]));
////                            if (api_object instanceof ApiRequest)
////                                jsonLog.addProperty("exception_data_" + i, apiRequestToString((ApiRequest) exceptions[i]));
////                            if (api_object instanceof JSONObject)
////                                jsonLog.addProperty("exception_data_" + i, apiRequestToString((JSONObject) exceptions[i]));
////
////                        }
////                    }
////                }
////            }
////
////
////
////
////
////            Sentry.capture(new Exception(jsonLog.toString()));
////        } catch (Exception e) {
////            e.printStackTrace();
////            Sentry.capture(e);
////
////        }
//
//
//    }
//
//
//    private static JsonObject getDeviceinfo() {
//        JsonObject jsonObject = new JsonObject();
//
//        try {
//            jsonObject.addProperty("os_version", System.getProperty("os.version") + " " + android.os.Build.VERSION.INCREMENTAL);
//            jsonObject.addProperty("os_api_level", android.os.Build.VERSION.RELEASE + " " + android.os.Build.VERSION.SDK_INT);
//            jsonObject.addProperty("device", android.os.Build.DEVICE);
//            jsonObject.addProperty("model", android.os.Build.MODEL);
//            jsonObject.addProperty("product", android.os.Build.MODEL);
//
//        } catch (Exception e) {
//            jsonObject.addProperty("exception ", e.getMessage());
//
//        }
//        return jsonObject;
//    }
//
//    public static boolean isNotificationCheckAletred() {
//        return SharedPrefToolsV2.getBoolean(ConstantsV2.SHARED_PREFERENCES_KEYS.NOTIFICATION_CHECK_ALERTED);
//
//    }
//
//    public static boolean setNotificationCheckAletred() {
//        return SharedPrefToolsV2.setValue(ConstantsV2.SHARED_PREFERENCES_KEYS.NOTIFICATION_CHECK_ALERTED, true);
//
//    }
//}
//
