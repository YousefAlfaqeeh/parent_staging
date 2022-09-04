package trackwareschoolbus.parentschool.API;


import trackwareschoolbus.parentschool.API.enumApi.EnumTypeHeader;

/**
 * Created by  on 1/5/17.
 */

public class ApiFacade {
    ApiClient thread;
    public void onStringRequest(ApiRequest volleyBean) {
        if (thread == null) {
            thread = new ApiClient(volleyBean);
        }

    }

    public void onStartVolley(ApiRequest volleyBean, EnumTypeHeader enumTypeHeader) {
        try {
        if (thread == null) {
            thread = new ApiClient(volleyBean,enumTypeHeader);
        }
        }catch (Exception e){

        }
    }

}
