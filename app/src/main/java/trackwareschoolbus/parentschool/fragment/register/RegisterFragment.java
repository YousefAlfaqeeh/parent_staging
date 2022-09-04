//package trackwareschoolbus.parentschool.fragment.register;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.appcompat.widget.AppCompatCheckBox;
//
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.CompoundButton;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.android.volley.NetworkResponse;
//import com.android.volley.VolleyError;
//import com.google.gson.JsonObject;
//
//import org.json.JSONObject;
//
//import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
//import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallBack;
//import trackwareschoolbus.parentschool.API_new.APIs.ApiController;
//import trackwareschoolbus.parentschool.API_new.APIs.ListenersAndInterFaces.OnApiComplete;
//import trackwareschoolbus.parentschool.FinishAppActivity;
//import trackwareschoolbus.parentschool.LanguageActivity;
//import trackwareschoolbus.parentschool.MainFragmentActivity;
//import trackwareschoolbus.parentschool.R;
//import trackwareschoolbus.parentschool.SplashActivity;
//import trackwareschoolbus.parentschool.basePage.BaseFragment;
//import trackwareschoolbus.parentschool.bean.RegisterBean;
//import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener;
//import trackwareschoolbus.parentschool.toolsV2.DialogsTools;
//import trackwareschoolbus.parentschool.utilityParent.StaticValue;
//import trackwareschoolbus.parentschool.utilityParent.StringUtil;
//import trackwareschoolbus.parentschool.utilityParent.UtilDialogs;
//import trackwareschoolbus.parentschool.utilityParent.UtilityParent;
//
////import static trackwareschoolbus.parentschool.utilityParent.UtilityParent.SENDER_ID;
//
///**
// * Created by  on 4/11/2017.
// */
//
//public class RegisterFragment extends BaseFragment implements IRestCallBack {
//
//    Activity mActivity;
//
//    Button btnRegister;
//    Button btnSelectLanguge;
//    EditText txtUserName;
//    EditText txtEmail;
//    EditText txtConfirmEmail;
//    EditText txtPin;
//    AppCompatCheckBox tearms_checkBox;
//    RegisterPresenter mPresenter;
//    public static RegisterBean registerBean;
//
//    public static boolean isRegeister = false;
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
//                             Bundle savedInstanceState) {
//        final View view = inflater.inflate(R.layout.fragment_register, container, false);
//        mActivity = getActivity();
//        getMainActivity().showProgressDialoge();
//        btnRegister = (Button) view.findViewById(R.id.btnRegister);
//        txtUserName = (EditText) view.findViewById(R.id.txtUserName);
//        txtEmail = (EditText) view.findViewById(R.id.txtEmail);
//        txtConfirmEmail = (EditText) view.findViewById(R.id.txtConfirmEmail);
//        txtPin = (EditText) view.findViewById(R.id.txtPin);
//        btnSelectLanguge = (Button) view.findViewById(R.id.btnSelectLanguge);
//        tearms_checkBox = (AppCompatCheckBox) view.findViewById(R.id.tearms_checkBox);
//        mPresenter = new RegisterPresenter(mActivity);
//
//        //    mPresenter.callIsRegister();
//        if (UtilityParent.getStringShared(UtilityParent.LANGUAGE).equals("ar")) {
//            btnSelectLanguge.setText(mActivity.getString(R.string.en));
//        } else {
//            btnSelectLanguge.setText(mActivity.getString(R.string.ar));
//        }
//
//
//        btnSelectLanguge.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                btnSelectLanguge.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (UtilityParent.getStringShared(UtilityParent.LANGUAGE).equals("ar")) {
//                            UtilityParent.setStringShared(UtilityParent.LANGUAGE, "en");
//                            UtilityParent.setLocale("en", mActivity, MainFragmentActivity.class);
//                        } else {
//                            UtilityParent.setStringShared(UtilityParent.LANGUAGE, "ar");
//                            UtilityParent.setLocale("ar", mActivity, MainFragmentActivity.class);
//                        }
//
//
//                    }
//                });
//            }
//        });
//
//
//        /**/
////        String value =getString(R.string.tou);
//        ((TextView) view.findViewById(R.id.tearms_tv)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String url = getString(R.string.tearms_of_use_link);
//                new UtilDialogs.WebViewDialog().show(getActivity()).initWebLink(url);
////                String url = getString(R.string.tearms_of_use_link);
////                try {
////                    Intent i = new Intent("android.intent.action.MAIN");
////                    i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
////                    i.addCategory("android.intent.category.LAUNCHER");
////                    i.setData(Uri.parse(url));
////                    startActivity(i);
////                }
////                catch(ActivityNotFoundException e) {
////                    // Chrome is not installed
////                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
////                    startActivity(i);
////                }
//
////                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.tearms_of_use_link)));
////                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                startActivity(intent);
//            }
//        });
////        ((TextView) view.findViewById(R.id.tearms_tv)).setMovementMethod(LinkMovementMethod.getInstance());
//        /**/
//
//        tearms_checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                btnRegister.setEnabled(b);
//            }
//        });
////rest
//
//
//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (UtilityParent.isEmptyString(txtUserName.getText().toString())) {
//                    UtilityParent.showMessage(mActivity, mActivity.getString(R.string.please_enter_name));
//                    UtilityParent.shakeViews(txtUserName);
//
//                    return;
//                }
//                if (UtilityParent.isEmptyString(txtEmail.getText().toString())) {
//                    UtilityParent.showMessage(mActivity, mActivity.getString(R.string.please_enter_email));
//                    UtilityParent.shakeViews(txtEmail);
//                    return;
//                }
//                if (!StringUtil.isValidEmail(txtEmail.getText().toString())) {
//                    UtilityParent.showMessage(mActivity, mActivity.getString(R.string.reg_email_with_wrong_format));
//                    UtilityParent.shakeViews(txtEmail);
//
//                    return;
//                }
//                if (UtilityParent.isEmptyString(txtConfirmEmail.getText().toString())) {
//                    UtilityParent.showMessage(mActivity, mActivity.getString(R.string.please_confirm_email));
//                    UtilityParent.shakeViews(txtConfirmEmail);
//                    return;
//                }
//
//                if (!txtEmail.getText().toString().trim().equalsIgnoreCase(txtConfirmEmail.getText().toString())) {
//                    UtilityParent.showMessage(mActivity, mActivity.getString(R.string.please_confirm_email));
//                    UtilityParent.shakeViews(txtConfirmEmail);
//                    UtilityParent.shakeViews(txtEmail);
//                    return;
//                }
//
//                if (UtilityParent.isEmptyString(txtPin.getText().toString())) {
//                    UtilityParent.showMessage(mActivity, mActivity.getString(R.string.please_enter_pin));
//                    UtilityParent.shakeViews(txtPin);
//                    return;
//                }
//
//
//                UtilityParent.setStringShared(UtilityParent.PIN_Code, txtPin.getText().toString());
//                UtilityParent.setStringShared(UtilityParent.EMAIL, txtEmail.getText().toString());
//
//                if (StaticValue.progressDialog != null) {
//
//                    StaticValue.progressDialog.show();
//                }
//
//                registerBean = new RegisterBean();
//                registerBean.setEmail(txtEmail.getText().toString());
//                registerBean.setPin(txtPin.getText().toString());
////                    registerBean.setSecretToken(UtilityParent.getStringShared(UtilityParent.MOBILE_NUMBER));
//                registerBean.setMobile_number(UtilityParent.getStringShared(UtilityParent.MOBILE_NUMBER));
//                registerBean.setSecretToken("");
//
//
////                    registerBean.setSecretToken(ReferrerReceiver.getReferrerKey(mActivity, "secret_token"));
//                registerBean.setUserName(txtUserName.getText().toString());
//                mPresenter.callRegister(registerBean, EnumNameApi.REGISTER);
//
//
//            }
//        });
//
//
////        txtUserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
////            @Override
////            public void onFocusChange(View v, boolean hasFocus) {
////                if (hasFocus) {
////                    txtUserName.setBackgroundResource(R.drawable.edittext_background_whith_border);
////                } else {
////                    txtUserName.setBackgroundResource(R.drawable.edittext_background_whith_out_border);
////                }
////            }
////        });
////        txtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
////            @Override
////            public void onFocusChange(View v, boolean hasFocus) {
////                if (hasFocus) {
////                    txtEmail.setBackgroundResource(R.drawable.edittext_background_whith_border);
////                } else {
////                    txtEmail.setBackgroundResource(R.drawable.edittext_background_whith_out_border);
////                }
////            }
////        });
////        txtConfirmEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
////            @Override
////            public void onFocusChange(View v, boolean hasFocus) {
////                if (hasFocus) {
////                    txtConfirmEmail.setBackgroundResource(R.drawable.edittext_background_whith_border);
////                } else {
////                    txtConfirmEmail.setBackgroundResource(R.drawable.edittext_background_whith_out_border);
////                }
////            }
////        });
////        txtPin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
////            @Override
////            public void onFocusChange(View v, boolean hasFocus) {
////                if (hasFocus) {
////                    txtPin.setBackgroundResource(R.drawable.edittext_background_whith_border);
////                } else {
////                    txtPin.setBackgroundResource(R.drawable.edittext_background_whith_out_border);
////                }
////            }
////        });
//
//        if (getActivity().getIntent().hasExtra("saveddata")) {
//            try {
//                JSONObject response = new JSONObject(getActivity().getIntent().getStringExtra("saveddata"));
//                checkAPIData(response);
//            } catch (Exception e) {
//                callFacebookScreen(view);
//            }
//
//        } else {
//            callFacebookScreen(view);
//        }
//
//
//        return view;
//    }
//
//    DialogsTools.CheckPhoneNumberDialog show;
//
//    private void callFacebookScreen(View view) {
//        getMainActivity().showProgressDialoge();
//        if (UtilityParent.getStringShared(UtilityParent.LANGUAGE_SELECTED).equals("")) {
//            mActivity.startActivity(new Intent(mActivity, LanguageActivity.class));
//            return;
//        }
//
//        view.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                getMainActivity().hideProgressDialoge();
//                show = new DialogsTools.CheckPhoneNumberDialog().show(getMainActivity(), new OnActionDoneListener<DialogsTools.CheckPhoneNumberDialog>() {
//                    @Override
//                    public void OnActionDone(DialogsTools.CheckPhoneNumberDialog checkPhoneNumberDialog) {
//                        if (checkPhoneNumberDialog == null) { // if no data from facebook screen.
////                            getMainActivity().finish();
//                            ((MainFragmentActivity) getActivity()).closeApplication();
//                        } else {
//                            show.dismiss();
//                            isParentRegistered(checkPhoneNumberDialog.phone_number, checkPhoneNumberDialog.phone_code);
//                        }
//                    }
//                });
//
////                ((MainFragmentActivity) getActivity()).showFacebookCheckScreen(new OnActionDoneListener<JSONObject>() {
////                    @Override
////                    public void OnActionDone(JSONObject response) {
////                        if (response == null) { // if no data from facebook screen
////                            ((MainFragmentActivity) getActivity()).closeApplication();
////                        } else {
////                            checkAPIData(response);
////                        }
////
////
////                    }
////                });
//
//            }
//        }, 300);
//        Intent intent = new Intent(mActivity, SplashActivity.class);
//        mActivity.startActivity(intent);
//
//
//    }
//
//    private void isParentRegistered(final String phone_number, final String phone_code) {
//        getMainActivity().showProgressDialoge();
//        ApiController.isParentPhoneNumberRegistered(getMainActivity(), /*"44fcd2f9bb1d8c24c269084b26a5ebd8",*/ phone_code, phone_number, new OnApiComplete<JsonObject>() {
//            @Override
//            public void onSuccess(JsonObject jsonObject) {
//                try {
////                        boolean is_register = jsonObject.get("parent_registered").getAsBoolean();
////                        RegisterFragment.isRegeister = is_register;
////                    UtilityParent.setStringShared(UtilityParent.MOBILE_NUMBER, phone_number);
//
//                    if (jsonObject == null) { // if no data from facebook screen
//                        ((MainFragmentActivity) getActivity()).closeApplication();
//                    } else {
//                        checkAPIData(new JSONObject(jsonObject.toString()));
//                    }
//
////                        /**/
////                        if (is_register) {
////                            String pin = jsonObject.get("pin").getAsString();
////                            String name = jsonObject.get("name").getAsString();
////                            String email = jsonObject.get("email").getAsString();
////                        /**/
////                            UtilityParent.setStringShared(UtilityParent.PIN_Code, pin);
////                            UtilityParent.setStringShared(UtilityParent.PARENT_NAME, name);
////                            UtilityParent.setStringShared(UtilityParent.EMAIL, email);
////                            // go to login
////                            if (onFacebookScreenDone!=null)
////                                onFacebookScreenDone.OnActionDone("login");
////                        } else {
//                    // go to register
////                    if (onFacebookScreenDone != null)
////                        onFacebookScreenDone.OnActionDone(new JSONObject(jsonObject.toString()));
//
////                        }
//
//                } catch (Exception e) {
//                    onFaceBookScreenError("error");
//                }
//
//                getMainActivity().hideProgressDialoge();
//
//            }
//
//            @Override
//            public void onError(int errorCode, String errorMessage) {
//                onFaceBookScreenError(errorMessage);
//            }
//        });
//
//    }
//
//    private void onFaceBookScreenError(String errorMessage) {
//        closeApplication();
//        getMainActivity().hideProgressDialoge();
//    }
//
//    public void closeApplication() {
//
//        getActivity().finish();
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//
//    }
//
//    private void checkAPIData(JSONObject response) {
//
//        getActivity().getIntent().putExtra("saveddata", response.toString());
//
//        try {
//
//            if (response.optBoolean("parent_registered",false)) {
//                RegisterFragment.isRegeister = true;
//
//                try {
//                    UtilityParent.setBooleanShared(UtilityParent.IS_REGISTER, RegisterFragment.isRegeister);
//
//                    UtilityParent.setStringShared(UtilityParent.PIN_Code, response.optString("pin"));
//                    UtilityParent.setStringShared(UtilityParent.EMAIL, response.optString("email"));
//                    UtilityParent.setStringShared(UtilityParent.PARENT_NAME, response.optString("name"));
//                    UtilityParent.setStringShared(UtilityParent.MOBILE_NUMBER, response.optString("mobile_number"));
//
////                                    UtilityParent.setStringShared("secret_token", "1336b57f542e147f6f8ed25fa12352f3");
//
//
//                } catch (Exception e) {
////                    UtilityParent.showMessageDialog(mActivity, "Error Parent register", e.getMessage());
//                    UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeMessage(R.string.api_send_error);
//                }
//                MainFragmentActivity.showLogInFragment();
////                    MainFragmentActivity.showLogInFragment();
////                    new SendDialog(mActivity, mActivity.getString(R.string.is_register), EnumFragment.REGISTER).show();
//                if (StaticValue.progressDialog != null) {
//
//                    StaticValue.progressDialog.dismiss();
//                }
//            } else if (response.toString().contains("status") && response.getString("status").equals("Missing secret_token")) {
////                UtilityParent.showMessageDialog(mActivity, "", response.getString("status"));
//                UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeMessage(R.string.api_send_error);
//                if (StaticValue.progressDialog != null) {
//
//                    StaticValue.progressDialog.dismiss();
//                }
//            } else if (response.toString().contains("Parent not found") || response.toString().contains("Contact your school")||response.toString().contains("Invalid mobile number format")) {
////                UtilityParent.showMessageDialog(mActivity, mActivity.getString(R.string.register_fail), mActivity.getString(R.string.register_fail_message));
//                UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeTitle(R.string.register_fail).setDialogeMessage(R.string.register_fail_message).setYesButtonClickListener(new OnActionDoneListener() {
//                    @Override
//                    public void OnActionDone(Object o) {
//                        getActivity().finish();
//                    }
//                });
//            }
//
//            if (!response.optString("mobile_number","").equals("")){
//                UtilityParent.setStringShared(UtilityParent.MOBILE_NUMBER, response.optString("mobile_number"));
//
//            }
//            //else -  nothing to do (will go to register)
//
//
//        } catch (Exception e) {
//            getActivity().finish();
//            e.printStackTrace();
////                            UtilityParent.showMessageDialog(mActivity, "Error onRestCallBack", e.getMessage());
////                            if (StaticValue.progressDialog != null) {
////
////                                StaticValue.progressDialog.dismiss();
////                            }
//
//        }
//
//        try {
//            getMainActivity().hideProgressDialoge();
//        } catch (Exception e) {
//        }
//
//
//    }
//
//    //    public void callIsRegister() {
////        String value = ReferrerReceiver.getReferrerKey(mActivity,"secret_token");
////        if (UtilityParent.isEmptyString(value)){
////            value =  UtilityParent.getStringShared(UtilityParent.SECREET_TOKEN);
////            if (UtilityParent.isEmptyString(value)){
////                value = UtilityParent.getStringShared("SECREETSECREET");
////            }
////        }
////
//////        value = "28069e694836d7e2536c05659b665774";
//////        UtilityParent.showMessageDialog(mActivity,"Secreet",value);
////        if (StaticValue.progressDialog != null) {
////
////            StaticValue.progressDialog.show();
////        }
////        final String finalValue = value;
////        callRestAPI(PathUrl.MAIN_URL + PathUrl.PARENT_IS_REGISTER,
////                new HashMap<String, Object>() {{
////                    put("secret_token", finalValue);
////                }},
////                EnumMethodApi.POST,
////                RegisterFragment.this,
////                EnumNameApi.PARENT_IS_REGISTER,
////                UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true),
////                EnumTypeHeader.JSON
////        );
////    }
////    public void sendNotificationToUser() {
////        FirebaseMessaging fm = FirebaseMessaging.getInstance();
////        fm.send(new RemoteMessage.Builder("fZ9i0vSKfoU:APA91bH0kBdULb6ebwW8qI7NIBkjnqTslbIJaEmOIlTGbRJf58yFre-wxDlOz-oxyPYHA3G7Owyr5ozILEnD0x4U9uy_-61FVcZr417A8yl_y-JlxbsBdIaZ-6U2RhtIijYCh5a_LSX_\n" +
////                "\n" + "@gcm.googleapis.com")
////                .setMessageId("777")
////                .addData("my_message", "Hello World")
////                .addData("my_action", "SAY_HELLO")
////                .build());
////    }
//    @Override
//    public void onRestCallBack(String response, EnumNameApi nameApiEnum) {
//        if (StaticValue.progressDialog != null) {
//            StaticValue.progressDialog.dismiss();
//        }
//    }
//
//    @Override
//    public void onRestCallBack(JSONObject response, EnumNameApi nameApiEnum) {
//        if (nameApiEnum == EnumNameApi.PARENT_IS_REGISTER) {
//
//
//            try {
//
//                if (response.toString().contains("parent_registered") && response.getString("parent_registered").equals("true")) {
//                    RegisterFragment.isRegeister = true;
//
//                    try {
//                        UtilityParent.setBooleanShared(UtilityParent.IS_REGISTER, RegisterFragment.isRegeister);
//
//                        UtilityParent.setStringShared(UtilityParent.PIN_Code, response.getString("pin"));
//                        UtilityParent.setStringShared(UtilityParent.EMAIL, response.getString("email"));
//                    } catch (Exception e) {
////                        UtilityParent.showMessageDialog(mActivity, "Error Parent register", e.getMessage());
//                        UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeMessage(R.string.api_send_error);
//                    }
//                    MainFragmentActivity.showLogInFragment();
////                    MainFragmentActivity.showLogInFragment();
////                    new SendDialog(mActivity, mActivity.getString(R.string.is_register), EnumFragment.REGISTER).show();
//                    if (StaticValue.progressDialog != null) {
//
//                        StaticValue.progressDialog.dismiss();
//                    }
//                } else if (response.toString().contains("status") && response.getString("status").equals("Missing secret_token")) {
////                    UtilityParent.showMessageDialog(mActivity, "", response.getString("status"));
//                    UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeMessage(R.string.api_send_error);
//                    if (StaticValue.progressDialog != null) {
//
//                        StaticValue.progressDialog.dismiss();
//                    }
//                } else if (response.toString().contains("Parent not found")) {
//
////                    UtilityParent.showMessageDialog(mActivity, mActivity.getString(R.string.register_fail), mActivity.getString(R.string.register_fail_message));
//                    UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeTitle(R.string.register_fail).setDialogeMessage(R.string.register_fail_message);
//                }
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
////                UtilityParent.showMessageDialog(mActivity, "Error onRestCallBack", e.getMessage());
//                UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeMessage(R.string.api_send_error);
//                if (StaticValue.progressDialog != null) {
//
//                    StaticValue.progressDialog.dismiss();
//                }
//
//            }
//
//        }
//        try {
//            getMainActivity().hideProgressDialoge();
//        } catch (Exception e) {
//        }
//    }
//
//    @Override
//    public void onRestCallBack(VolleyError volleyError, EnumNameApi nameApiEnum) {
//        if (StaticValue.progressDialog != null) {
//
//            StaticValue.progressDialog.dismiss();
//        }
//    }
//
//    @Override
//    public void onRestCallBack(NetworkResponse networkResponse, EnumNameApi nameApiEnum) {
//
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        getView().setFocusableInTouchMode(true);
//        getView().requestFocus();
//        getView().setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
////                    Toast.makeText(getActivity(),"efsf",Toast.LENGTH_LONG).show();
////                    getActivity().finish();
//                    FinishAppActivity.finishAppActivity(getActivity());
//
//                    return true;
//                }
//                return false;
//            }
//        });
//
//    }
//}
