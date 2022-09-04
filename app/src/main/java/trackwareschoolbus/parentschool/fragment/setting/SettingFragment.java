package trackwareschoolbus.parentschool.fragment.setting;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import trackwareschoolbus.parentschool.API.enumApi.EnumMethodApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumTypeHeader;
import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallBack;
import trackwareschoolbus.parentschool.API_new.APIs.ApiController;
import trackwareschoolbus.parentschool.API_new.APIs.Constants;
import trackwareschoolbus.parentschool.API_new.APIs.ListenersAndInterFaces.OnApiComplete;
import trackwareschoolbus.parentschool.MainFragmentActivity;
import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.app.Application;
import trackwareschoolbus.parentschool.basePage.BaseFragment;
import trackwareschoolbus.parentschool.fragment.login.LoginFragment;
import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener;
import trackwareschoolbus.parentschool.utilityParent.UtilDialogs;
import trackwareschoolbus.parentschool.utilityParent.UtilityParent;

/**
 * Created by  on 2/23/2017.
 */

public class SettingFragment extends BaseFragment implements IRestCallBack {

//    ImageView settingBut;
//    ImageView notificationBut;
//    TextView txtTitle;
    LinearLayout llLogOut;
    Bundle bundle;
    SettingPresenter mSettingPresenter;
    SwitchCompat swtBusCheckIn, swtBusNear, swtBusCheckOut;
    public static boolean busChckIn;
    public static boolean busChckOut;
    public static boolean busNear;
    Map<String, Object> mapNotification;
    AppCompatSpinner langSpinner;
//    AppCompatImageView imgBack;
//    LinearLayout mainBar;
    EnumNameApi enumNameApi;
    LinearLayout layoutSetting;
    int settingSent = 0;
    private List<String> companies_list;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        final LoginFragment LoginFragment = new LoginFragment();

        // actionListener();


        mSettingPresenter = new SettingPresenter(getMainActivity());

        CallGetallSetting();

//        imgBack = (AppCompatImageView) view.findViewById(R.id.imgBack);
//
//        imgBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendSetting();
//                MainFragmentActivity.showMyKidsFragment();
//            }
//        });

        llLogOut = (LinearLayout) view.findViewById(R.id.llLogOut);


//        txtTitle = (TextView) view.findViewById(R.id.labTitle);
//        txtTitle.setText(getResources().getString(R.string.setting));
//
//        settingBut = (ImageView) view.findViewById(R.id.imgHome);
//        settingBut.setVisibility(View.INVISIBLE);

//        notificationBut = (AppCompatImageView) view.findViewById(R.id.imgNotification);
//        notificationBut.setImageResource(R.drawable.new_ic_notifcation);


        swtBusCheckIn = (SwitchCompat) view.findViewById(R.id.swtBusCheckIn);
        swtBusCheckOut = (SwitchCompat) view.findViewById(R.id.swtBusCheckOut);
        swtBusNear = (SwitchCompat) view.findViewById(R.id.swtBusNear);
        langSpinner = (AppCompatSpinner) view.findViewById(R.id.langSpinner);

        llLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    ApiController.sendLogout(getActivity(), new OnApiComplete<Object>() {
                        @Override
                        public void onSuccess(Object o) {
//                            Toast.makeText(getActivity(), o.toString(), Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onError(int errorCode, String errorMessage) {
//                            Toast.makeText(getActivity(), "onError", Toast.LENGTH_SHORT).show();

                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();

                }


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        settingSent = 1;
                        LoginFragment.isLogIn = false;
                        UtilityParent.setBooleanShared(UtilityParent.IS_LOgIn, LoginFragment.isLogIn);
                        UtilityParent.setStringShared(UtilityParent.AUTH, null);
                        UtilityParent.setStringShared(UtilityParent.TOKEN, null);
                        UtilityParent.clearSavedlDataHolders();
                        UtilityParent.clearAll();
                        UtilityParent.clearSavedlDataHolders();
                        MainFragmentActivity.showLogInFragment();
                    }
                }, 2000);


            }
        });


//        notificationBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainFragmentActivity.showNotificationFragment();
//            }
//        });


//        settingBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainFragmentActivity.showNotificationFragment();
//            }
//        });

        swtBusCheckIn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    busChckIn = true;
                } else {
                    busChckIn = false;
                }
                //  mapNotification.put("check_in",busChckIn);
            }
        });

        swtBusCheckOut.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    busChckOut = true;
                } else {
                    busChckOut = false;
                }
                // mapNotification.put("check_out",busChckOut);
            }
        });


//        UtilityParent.getStringShared(UtilityParent.LANGUAGE).equals("ar")

        int current_language_index = Arrays.asList(getResources().getStringArray(R.array.languages_list_keys)).indexOf(UtilityParent.getStringShared(UtilityParent.LANGUAGE));
        langSpinner.setSelection(current_language_index == -1 ? 0 : current_language_index, false);


        langSpinner.postDelayed(new Runnable() {
            @Override
            public void run() {
                langSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (!UtilityParent.getStringShared(UtilityParent.LANGUAGE).equals(getResources().getStringArray(R.array.languages_list_keys)[i])) {
                            UtilDialogs.showYesCancelMessage(getMainActivity()).setDialogeMessage(R.string.change_lang_dialog).hideMainImage().setYesButtonClickListener(new OnActionDoneListener<UtilDialogs.MessageYesNoDialog>() {
                                @Override
                                public void OnActionDone(UtilDialogs.MessageYesNoDialog Action) {
                                    sendSetting();
                                    getMainActivity().setAppLanguage(getResources().getStringArray(R.array.languages_list_keys)[i]);
                                }
                            }).setCancelButtonClickListener(new OnActionDoneListener<UtilDialogs.MessageYesNoDialog>() {
                                @Override
                                public void OnActionDone(UtilDialogs.MessageYesNoDialog Action) {
                                    int current_language_index = Arrays.asList(getResources().getStringArray(R.array.languages_list_keys)).indexOf(UtilityParent.getStringShared(UtilityParent.LANGUAGE));
                                    langSpinner.setSelection(current_language_index == -1 ? 0 : current_language_index, false);
                                }
                            });

                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        }, 100);


        swtBusNear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    busNear = true;
                } else {
                    busNear = false;
                }
                // mapNotification.put("nearby",busNear);
            }
        });
        layoutSetting = (LinearLayout) view.findViewById(R.id.layOutSetting);
//        mainBar = (LinearLayout) view.findViewById(R.id.mainBar);
        if (UtilityParent.getStringShared(UtilityParent.LANGUAGE).equals("ar")) {

//            mainBar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            layoutSetting.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        return view;
    }


    public void CallGetallSetting() {

        callRestAPI(Constants.Urls.GET_SETTING,
                new HashMap() {{

                }},
                EnumMethodApi.GET,
                SettingFragment.this,
                EnumNameApi.GET_SETTING,
                UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true)
                ,
                EnumTypeHeader.JSON
        );
    }

    public void getAllSetting(JSONObject response) {
        mapNotification = new HashMap<String, Object>();
        try {
            /* JSONArray jaNotification = response.getJSONArray("notifications");*/
            // JSONObject joNotificatin=new JSONObject(response);
            JSONObject joNotifcations = response.getJSONObject("notifications");
            /* JSONObject joNotification = jaNotification.getJSONObject(i);*/

            if (joNotifcations.getString("check_in").equals("true")) {
                busChckIn = true;
                swtBusCheckIn.setChecked(busChckIn);
            } else {
                busChckIn = false;
                swtBusCheckIn.setChecked(busChckIn);
            }

            if (joNotifcations.getString("check_out").equals("true")) {
                busChckOut = true;
                swtBusCheckOut.setChecked(busChckOut);
            } else {
                busChckOut = false;
                swtBusCheckOut.setChecked(busChckOut);
            }

            if (joNotifcations.getString("nearby").equals("true")) {
                busNear = true;
                swtBusNear.setChecked(busNear);
            } else {
                busNear = false;
                swtBusNear.setChecked(busNear);
            }


        } catch (Exception e) {
            Application.logEvents("getAllSetting", "SettingFragment - onRestCallBack  ", e);

//            UtilityParent.showMessageDialog(mActivity, "fail", e.getMessage());
//            UtilDialogs.showGeneralErrorDialog(getMainActivity()).setDialogeMessage(R.string.api_send_error);
        }
    }


    public void onStop() {
        super.onStop();

        if (!(settingSent == 1)) {
            sendSetting();
        }

    }

    @Override
    protected boolean showToolBarNotification() {
        return false;
    }

    @Override
    protected boolean showToolBack() {
        return true;
    }

    @Override
    protected boolean showToolBarSettings() {
        return false;
    }



    @Override
    protected String initToolBarTitle() {
        return null;
    }


    @Override
    public void onRestCallBack(String response, EnumNameApi nameApiEnum) {
        if (nameApiEnum == EnumNameApi.GET_SETTING) {
            //getAllSetting(response);
        }
    }

    @Override
    public void onRestCallBack(JSONObject response, EnumNameApi nameApiEnum) {
        if (nameApiEnum == EnumNameApi.GET_SETTING) {
            try {
                getAllSetting(response);
            } catch (Exception e) {
                Application.logEvents(nameApiEnum.name(), "SettingFragment - onRestCallBack  ", e);
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onRestCallBack(VolleyError volleyError, EnumNameApi nameApiEnum) {
//        UtilityParent.showMessageDialog(mActivity, mActivity.getString(R.string.error_api), volleyError.getMessage());
//        UtilDialogs.showGeneralErrorDialog(getMainActivity()).setDialogeMessage(R.string.api_send_error);
        Application.logEvents(nameApiEnum.name(), "SettingFragment - onRestCallBack  ", volleyError);

    }

    @Override
    public void onRestCallBack(NetworkResponse networkResponse, EnumNameApi nameApiEnum) {

    }

    public void sendSetting() {
        mapNotification = new HashMap<String, Object>() {{
            put("check_in", busChckIn);
            put("check_out", busChckOut);
            put("nearby", busNear);
            put("locale", UtilityParent.getStringShared(UtilityParent.LANGUAGE));
        }};
        mSettingPresenter.callSetSetting(UtilityParent.getDateFormat("yyyy/MM/dd HH:mm:ss"), 13, 3, new JSONObject(mapNotification));

    }


}
