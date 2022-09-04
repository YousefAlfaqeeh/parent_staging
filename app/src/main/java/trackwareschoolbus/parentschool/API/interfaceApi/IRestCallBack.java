package trackwareschoolbus.parentschool.API.interfaceApi;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;


/**
 * Created by  on 1/8/17.
 */

public interface IRestCallBack {

    void onRestCallBack(String response, EnumNameApi nameApiEnum);
    void onRestCallBack(JSONObject response, EnumNameApi nameApiEnum);
    void onRestCallBack(VolleyError volleyError, EnumNameApi nameApiEnum);
    void onRestCallBack(NetworkResponse networkResponse, EnumNameApi nameApiEnum);
}
