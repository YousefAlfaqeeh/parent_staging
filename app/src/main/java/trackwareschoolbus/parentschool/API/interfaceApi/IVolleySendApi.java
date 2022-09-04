package trackwareschoolbus.parentschool.API.interfaceApi;


import trackwareschoolbus.parentschool.API.ApiRequest;

/**
 * Created by  on 12/5/2016.
 */

public interface IVolleySendApi {


    void sendVolley(ApiRequest volleyBean);
    void sendVolley(ApiRequest volleyBean, boolean value);
    void sendVolleyJson(ApiRequest volleyBean) throws Exception;

}
