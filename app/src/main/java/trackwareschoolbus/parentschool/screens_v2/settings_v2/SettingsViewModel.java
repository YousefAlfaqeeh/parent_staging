package trackwareschoolbus.parentschool.screens_v2.settings_v2;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import trackwareschoolbus.parentschool.API_V2.ConnectionFactory;
import trackwareschoolbus.parentschool.screens_v2.BaseViewModelV2;
import trackwareschoolbus.parentschool.screens_v2.settings_v2.request_and_response.GetSettingsResponse;
import trackwareschoolbus.parentschool.screens_v2.settings_v2.request_and_response.SendSettingsRequest;


public class SettingsViewModel extends BaseViewModelV2 {


    public SettingsViewModel getSetting(SingleObserver<GetSettingsResponse> singleObserver) {

        ConnectionFactory.getInstance().getSetting()
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<GetSettingsResponse, GetSettingsResponse>() {
                    @Override
                    public GetSettingsResponse apply(GetSettingsResponse response) throws Exception {
                        try {


                        } catch (Exception e) {

                        }


                        return response;
                    }
                })
                .subscribe(singleObserver);
        return this;
    }


    public SettingsViewModel sendSetting(SendSettingsRequest sendSettingsRequest, SingleObserver<Object> singleObserver) {

        ConnectionFactory.getInstance().sendSetting(sendSettingsRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object response) throws Exception {
                        try {


                        } catch (Exception e) {

                        }


                        return response;
                    }
                })
                .subscribe(singleObserver);
        return this;
    }


}
