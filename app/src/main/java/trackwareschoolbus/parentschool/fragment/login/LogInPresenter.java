//package trackwareschoolbus.parentschool.fragment.login;
//
//import android.app.Activity;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.android.volley.NetworkResponse;
//import com.android.volley.VolleyError;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.iid.InstanceIdResult;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//
//import trackwareschoolbus.parentschool.API.enumApi.EnumMethodApi;
//import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
//import trackwareschoolbus.parentschool.API.enumApi.EnumTypeHeader;
//import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallBack;
//import trackwareschoolbus.parentschool.API_new.APIs.Constants;
//import trackwareschoolbus.parentschool.MainFragmentActivity;
//import trackwareschoolbus.parentschool.R;
//import trackwareschoolbus.parentschool.app.Application;
//import trackwareschoolbus.parentschool.basePage.BasePresenter;
//import trackwareschoolbus.parentschool.utilityParent.StaticValue;
//import trackwareschoolbus.parentschool.utilityParent.UtilDialogs;
//import trackwareschoolbus.parentschool.utilityParent.UtilityParent;
//
//
//public class LogInPresenter extends BasePresenter implements IRestCallBack {
//
//
//    Activity mActivity;
//
//    public LogInPresenter(Activity mActivity) {
//        this.mActivity = mActivity;
//    }
//
//    public void callLOgIn() {
////        String token = FirebaseInstanceId.getInstance().getToken();
//        FirebaseInstanceId.getInstance().getInstanceId()
//                .addOnCompleteListener(task -> {
//                    if (!task.isSuccessful()) {
//                        Log.v("FirebaseInstanceId", "getInstanceId failed", task.getException());
//                        return;
//                    }
//
//                    // Get new Instance ID token
//                    String token = task.getResult().getToken();
//                    UtilityParent.setStringShared(UtilityParent.TOKEN, token);
//                    /**/
//                    callRestAPI(Constants.Urls.LOG_IN,
//                            new HashMap() {{   //0d6823a04497bbe78e6a4d0919ecbe49
////                    put("secret_token", "1336b57f542e147f6f8ed25fa12352f3");
//
//                                put("mobile_token", UtilityParent.getStringShared(UtilityParent.TOKEN));
//                                put("version_number", Constants.Values.VERSION_NUMBER);
//                                put("platform", Constants.Values.PLATFORM);
//                                put("parent_pin", UtilityParent.getStringShared(UtilityParent.PIN_Code));
//                                put("mobile_number", UtilityParent.getStringShared(UtilityParent.MOBILE_NUMBER));
//
//                            }},
//                            EnumMethodApi.POST,
//                            LogInPresenter.this,
//                            EnumNameApi.LOG_IN,
//                            UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true),
//                            EnumTypeHeader.JSON
//                    );
//
//                });
//
//
//
//    }
//
//    public void logInResponse(JSONObject response) {
//
//      /*  try {
//            JSONObject joLogIn=new JSONObject(response);
//            joLogIn.getString("school_phone");
//            joLogIn.getString("school_name");
//            joLogIn.getString("school_id");
//            joLogIn.getString("nearby_distance");
//            UtilityParent.setStringShared(UtilityParent.AUTH,joLogIn.getString("Authorization"));
//
//            JSONArray jaGeofenses=joLogIn.getJSONArray("geofenses");
//            for (int i=0;i<jaGeofenses.length();i++){
//               JSONObject joGeofenses=jaGeofenses.getJSONObject(i);
//                joGeofenses.getString("id");
//                joGeofenses.getString("name");
//                joGeofenses.getString("shape_type");
//            }
//
//
//        }catch (JSONException e){
//            UtilityParent.showMessageDialog(mActivity,"fail",e.getMessage());
//        }*/
//    }
//
//    public void callForgetPassword() {
////        String value = ReferrerReceiver.getReferrerKey(mActivity,"secret_token");
////        if (UtilityParent.isEmptyString(value)){
////            value =  UtilityParent.getStringShared(UtilityParent.SECREET_TOKEN);
////        }
////        final String finalValue = value;
//        callRestAPI(Constants.Urls.FORGET_PASSWORD,
//                new HashMap<String, Object>() {{
////                    put("email",UtilityParent.getStringShared(UtilityParent.EMAIL));
////                    put("secret_token", "1336b57f542e147f6f8ed25fa12352f3");
//                    put("user_type", "parent");
////                    put("pin",UtilityParent.getStringShared(UtilityParent.PIN_Code));
//                    put("mobile", UtilityParent.getStringShared(UtilityParent.MOBILE_NUMBER));
//                    //  put("mobile_number","962795568031");
//                }},
//                EnumMethodApi.POST,
//                LogInPresenter.this,
//                EnumNameApi.FORGET_PASSWORD,
//                UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true),
//                EnumTypeHeader.JSON
//        );
//    }
//
//
//    @Override
//    public void onRestCallBack(String response, EnumNameApi nameApiEnum) {
//
//        if (nameApiEnum == EnumNameApi.LOG_IN) {
//            // logInResponse(response);
//        }
//
//    }
//
//
//    @Override
//    public void onRestCallBack(JSONObject response, EnumNameApi nameApiEnum) {
//        UtilityParent.setStringShared("", response.toString());
//
//
//        try {
//            if (nameApiEnum == EnumNameApi.LOG_IN) {
//                if (!UtilityParent.isEmptyString(response + "") && response.toString().contains("notifications_text")) {
//                    LoginFragment.isLogIn = true;
//                    UtilityParent.setBooleanShared(UtilityParent.IS_LOgIn, LoginFragment.isLogIn);
//                    MainFragmentActivity.showMyKidsFragment();
//
//
//                    UtilityParent.setStringShared(UtilityParent.LOG_IN_DATA, response.toString());
//                    JSONArray joNotificationText = response.getJSONArray("notifications_text");
//                    for (int i = 0; i < joNotificationText.length(); i++) {
//                        JSONObject jaNotificationText = joNotificationText.getJSONObject(i);
//                        jaNotificationText.getString("type");
//                    }
//                } else {
//                    String status = response.getString("status");
//                    UtilDialogs.showGeneralErrorDialog(StaticValue.mActivity).setDialogeMessage(R.string.api_send_error);
////                    UtilityParent.showMessageDialog(mActivity, mActivity.getString(R.string.error), status);
//                }
//
//            } else if (nameApiEnum == EnumNameApi.FORGET_PASSWORD) {
//                if (response.getString("status").equalsIgnoreCase("a message was sent to your email")) {
//
////                    new SendDialog(mActivity, mActivity.getString(R.string.send_forget_password), EnumFragment.FORGET_PASSWORD).show();
//                    UtilDialogs.showSuccessMessage(mActivity).setDialogeTitle(R.string.send_forget_password);
//                } else {
//                    UtilDialogs.showGeneralErrorDialog(StaticValue.mActivity);
////                    UtilityParent.showMessage(mActivity, "Email incorrect");
////                    UtilDialogs.showGeneralErrorDialog(mActivity);
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Application.logEvents(nameApiEnum.name(), "LogInPresenter - onRestCallBack  ",e,response);
//
//        }
//
//    }
//
//    @Override
//    public void onRestCallBack(VolleyError volleyError, EnumNameApi nameApiEnum) {
//        Application.logEvents(nameApiEnum.name(), "LogInPresenter - onRestCallBack  ",volleyError);
//
//
//        if (nameApiEnum == EnumNameApi.LOG_IN) {
////            UtilityParent.showMessageDialog(mActivity, mActivity.getString(R.string.error_api), volleyError.getMessage());
//            UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeMessage(R.string.api_send_error);
//
//        } else if (nameApiEnum == EnumNameApi.FORGET_PASSWORD) {
//            UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeMessage(R.string.api_send_error);
//
////            UtilityParent.showMessageDialog(mActivity, mActivity.getString(R.string.error_api), volleyError.getMessage());
//        }
//
//    }
//
//
//    @Override
//    public void onRestCallBack(NetworkResponse networkResponse, EnumNameApi nameApiEnum) {
//        if (nameApiEnum == EnumNameApi.LOG_IN) {
//
//            UtilityParent.setStringShared(UtilityParent.AUTH, networkResponse.headers.get("x-amzn-Remapped-Authorization"));
//
//
//        }
//
//    }
//
//
//}
