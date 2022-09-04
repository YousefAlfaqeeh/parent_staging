package trackwareschoolbus.parentschool.basePage;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GoogleApiAvailability;

import java.util.HashMap;
import java.util.Map;

import trackwareschoolbus.parentschool.API.ApiFacade;
import trackwareschoolbus.parentschool.API.ApiRequest;
import trackwareschoolbus.parentschool.API.enumApi.EnumMethodApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumTypeHeader;
import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallBack;

import static android.content.ContentValues.TAG;

/**
 * Created by  on 3/22/2017.
 */

public class BasePresenter {


    /*protected int parse(String ... str){
        for (int i = 0; i < str.length ; i++) {

        }


    }
*/


    protected void callRestAPI(
            String PATH_URL,
            HashMap params,
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
