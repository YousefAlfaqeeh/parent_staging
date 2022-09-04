package trackwareschoolbus.parentschool.basePage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import trackwareschoolbus.parentschool.API.ApiFacade;
import trackwareschoolbus.parentschool.API.ApiRequest;
import trackwareschoolbus.parentschool.API.enumApi.EnumMethodApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumTypeHeader;
import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallBack;

/**
 * Created by  on 3/9/2017.
 */

public class BaseDialog extends Dialog {
    protected Activity mActivity;

    public BaseDialog(@NonNull Context context) {
        super(context);
        this.mActivity = (Activity) context;;
    }

    protected void callRestAPI(
            String PATH_URL,
            HashMap<String, Object> params,
            EnumMethodApi verb,
            IRestCallBack restCallBack,
            EnumNameApi enumNameApi,
            Map<String,String> mapHeader,
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
}
