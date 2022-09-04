//package trackwareschoolbus.parentschool.fragment.register;
//
//import android.app.Activity;
//import android.os.Handler;
//
//import com.android.volley.NetworkResponse;
//import com.android.volley.VolleyError;
//
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
//import trackwareschoolbus.parentschool.bean.RegisterBean;
//import trackwareschoolbus.parentschool.utilityParent.UtilDialogs;
//import trackwareschoolbus.parentschool.utilityParent.UtilityParent;
//
///**
// * Created by  on 4/11/2017.
// */
//
//public class RegisterPresenter extends BasePresenter implements IRestCallBack {
//
//    Activity mActivity;
//
//    public RegisterPresenter(Activity mActivity) {
//        this.mActivity = mActivity;
//    }
//
//
//    public void callRegister(final RegisterBean registerBean, EnumNameApi register) {
//
//        callRestAPI(Constants.Urls.REGISTER,
//                new HashMap<String, Object>() {{
//                    // "68692a58e98f6edb4c7e228025e1d3ad"
//                    put("mobile_number", UtilityParent.getStringShared(UtilityParent.MOBILE_NUMBER));
////                    put("secret_token", "");
////                    put("secret_token", registerBean.getSecretToken());
//                    put("name", registerBean.getUserName());
//                    put("email", registerBean.getEmail());
//                    put("pin", registerBean.getPin());
//                }},
//                EnumMethodApi.POST,
//                RegisterPresenter.this,
//                register,
//                UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true),
//                EnumTypeHeader.JSON
//        );
//    }
//
//
//    @Override
//    public void onRestCallBack(String response, EnumNameApi nameApiEnum) {
//
//
//    }
//
//    @Override
//    public void onRestCallBack(JSONObject response, EnumNameApi nameApiEnum) {
//
//        try {
//            if (nameApiEnum == EnumNameApi.REGISTER) {
////                    if (response.toString().contains("status") ) {
//                if (response.toString().contains("secret_token")) {
//                    String value = response.getJSONArray("secret_token").toString();
////                        UtilityParent.showMessageDialog(mActivity,mActivity.getString(R.string.error),value);
//                    new DialogRegister(mActivity);
//                    return;
//                }
//                if (response.getString("status").contains("ok") || response.getString("status").contains("Parent already registered")) {
//                    RegisterFragment.isRegeister = true;
//                    UtilityParent.setBooleanShared(UtilityParent.IS_REGISTER, RegisterFragment.isRegeister);
//                    UtilDialogs.MessageYesNoDialog successRegistered = UtilDialogs.showSuccessMessage(mActivity).setDialogeTitle(R.string.sent);
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                successRegistered.dismiss();
//                                MainFragmentActivity.showLogInFragment();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    }, 1000);
//
//
//                } else {
//                    String value = response.getJSONArray("secret_token").toString();
////                        UtilityParent.showMessageDialog(mActivity,mActivity.getString(R.string.error),value);
//                    new DialogRegister(mActivity);
//                }
////                    }
//            } else if (nameApiEnum == EnumNameApi.REGISTER_DIALOG) {
////                    if (response.toString().contains("status")) {
//                if (response.getString("status").contains("ok") || response.getString("status").contains("Parent already registered")) {
//                    RegisterFragment.isRegeister = true;
//                    UtilDialogs.MessageYesNoDialog successRegistered = UtilDialogs.showSuccessMessage(mActivity).setDialogeTitle(R.string.sent);
//                    UtilityParent.setBooleanShared(UtilityParent.IS_REGISTER, RegisterFragment.isRegeister);
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                successRegistered.dismiss();
//                                MainFragmentActivity.showLogInFragment();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                Application.logEvents("REGISTER_DIALOG", "RegisterPresenter - onRestCallBack  ", e);
//                            }
//
//                        }
//                    }, 1000);
//                } else if (response.getString("status").trim().equalsIgnoreCase("Contact your school".trim())) {
//                    UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeMessage(R.string.register_fail_message);
//                } else {
//                    if (response.toString().contains("Parent not found")) {
////                        UtilityParent.showMessageDialog(mActivity, mActivity.getString(R.string.error), "Parent not found");
//                        UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeMessage(R.string.api_send_error);
//                    }
//                    String value = response.getJSONArray("secret_token").toString();
////                    UtilityParent.showMessageDialog(mActivity, mActivity.getString(R.string.error), value);
//                    UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeMessage(R.string.api_send_error);
////                        new DialogRegister(mActivity);
//                }
////                }
//            }
//                    /*else  if(nameApiEnum==EnumNameApi.PARENT_IS_REGISTER){
//
//
//                        if (response.getString("parent_registered").equals("false")){
//
//
//
//                        }else if (response.getString("parent_registered").equals("true")){
//                            RegisterFragment.isRegeister = true;
//
//                            UtilityParent.setBooleanShared(UtilityParent.IS_REGISTER, RegisterFragment.isRegeister);
//
//                            UtilityParent.setStringShared(UtilityParent.PIN_Code,response.getString("pin"));
//                            UtilityParent.setStringShared(UtilityParent.EMAIL,response.getString("email"));
//                            new SendDialog(mActivity,mActivity.getString(R.string.is_register),EnumFragment.REGISTER).show();
//
//                        }else if (response.getString("status").equals("Missing secret_token")){
//                            UtilityParent.showMessageDialog(mActivity,"",response.getString("status"));
//                        }
//
//
//                }*/
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Application.logEvents(nameApiEnum.name(), "RegisterPresenter - onRestCallBack  ", e);
//
//        }
//
//
//    }
//
//    @Override
//    public void onRestCallBack(VolleyError volleyError, EnumNameApi nameApiEnum) {
//        try {
//            Application.logEvents(nameApiEnum.name(), "RegisterPresenter - onRestCallBack  ", volleyError);
//        } catch (Exception e) {
//            Application.logEvents(nameApiEnum.name(), "RegisterPresenter - onRestCallBack  ", e);
//        }
//
//
//        if (nameApiEnum == EnumNameApi.REGISTER) {
////            UtilityParent.showMessageDialog(mActivity, mActivity.getString(R.string.error_api), volleyError.getMessage());
//            UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeMessage(R.string.api_send_error);
//            // MainActivity.showLogInFragment();
//        }
////        else if (nameApiEnum == EnumNameApi.PARENT_IS_REGISTER) {
//////            UtilityParent.showMessageDialog(mActivity, mActivity.getString(R.string.error_api), volleyError.getMessage());
////            UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeMessage(R.string.api_send_error);
////            // MainActivity.showLogInFragment();
////        }
//        else {
////            UtilityParent.showMessageDialog(mActivity, mActivity.getString(R.string.error_api), volleyError.getMessage());
//            UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeMessage(R.string.api_send_error);
//        }
//    }
//
//    @Override
//    public void onRestCallBack(NetworkResponse networkResponse, EnumNameApi nameApiEnum) {
//
//
//    }
//
//
//}
