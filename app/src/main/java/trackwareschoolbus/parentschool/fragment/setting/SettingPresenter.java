package trackwareschoolbus.parentschool.fragment.setting;

import android.app.Activity;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;

import trackwareschoolbus.parentschool.API.enumApi.EnumMethodApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumTypeHeader;
import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallBack;
import trackwareschoolbus.parentschool.API_new.APIs.Constants;
import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.app.Application;
import trackwareschoolbus.parentschool.basePage.BasePresenter;
import trackwareschoolbus.parentschool.utilityParent.UtilDialogs;
import trackwareschoolbus.parentschool.utilityParent.UtilityParent;

/**
 * Created by  on 3/22/2017.
 */

public class SettingPresenter extends BasePresenter implements IRestCallBack {


    Activity mActivity;
    EnumNameApi enumNameApi;

    public SettingPresenter(Activity mActivity) {
        this.mActivity = mActivity;
    }


    public void callSetSetting(final String dateTime, final float lat, final float lng, final JSONObject setting) {

//        String value = ReferrerReceiver.getReferrerKey(mActivity,"secret_token");
//        if (UtilityParent.isEmptyString(value)){
//            value =  UtilityParent.getStringShared(UtilityParent.SECREET_TOKEN);
//        }
//        final String finalValue = value;

        HashMap<String, Object> mapNotification = new HashMap<String, Object>() {{
            put("notifications", setting);
        }};
        final JSONObject joNotification = new JSONObject(mapNotification);
        HashMap mapData = new HashMap() {{

            put("mobile", UtilityParent.getStringShared(UtilityParent.MOBILE_NUMBER));
//            put("secret_token", finalValue);
            put("secret_token", "");
            put("settings", joNotification);
        }};

        callRestAPI(Constants.Urls.SET_SETTING,
                mapData,
                EnumMethodApi.POST,
                SettingPresenter.this,
                EnumNameApi.POST_SETTING,
                UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true),
                EnumTypeHeader.JSON
        );

    }


    @Override
    public void onRestCallBack(String response, EnumNameApi nameApiEnum) {

    }

    @Override
    public void onRestCallBack(JSONObject response, EnumNameApi nameApiEnum) {


    }

    @Override
    public void onRestCallBack(VolleyError volleyError, EnumNameApi nameApiEnum) {
        Application.logEvents(nameApiEnum.name(), "SettingPresenter - onRestCallBack  ",volleyError);

//        UtilityParent.showMessageDialog(mActivity, mActivity.getString(R.string.error_api), volleyError.getMessage());
        UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeMessage(R.string.api_send_error);
    }

    @Override
    public void onRestCallBack(NetworkResponse networkResponse, EnumNameApi nameApiEnum) {

    }
}
