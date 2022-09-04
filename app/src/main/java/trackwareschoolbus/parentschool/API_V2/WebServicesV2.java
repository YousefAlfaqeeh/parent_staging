package trackwareschoolbus.parentschool.API_V2;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;

import io.reactivex.Single;
import me.linshen.retrofit2.adapter.ApiResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import trackwareschoolbus.parentschool.API_new.APIs.Constants;
import trackwareschoolbus.parentschool.api_v3.GetKidsResp;
import trackwareschoolbus.parentschool.api_v3.LogInReq;
import trackwareschoolbus.parentschool.api_v3.LogInRes;
import trackwareschoolbus.parentschool.api_v3.SchoolsListItem;
import trackwareschoolbus.parentschool.screens_v2.settings_v2.request_and_response.GetSettingsResponse;
import trackwareschoolbus.parentschool.screens_v2.settings_v2.request_and_response.SendSettingsRequest;
import trackwareschoolbus.parentschool.toolsV2.ConstantsV2;

/**
 * Created by  on 2/12/2018.
 */

public interface WebServicesV2 {


    @POST(ConstantsV2.URLS.LOG_IN)
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    LiveData<ApiResponse<LogInRes>> logIn(@Body LogInReq logInReq);


    @GET(Constants.SCHOOLS_LIST)
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    LiveData<ApiResponse<ArrayList<SchoolsListItem>>> schoolsList();


//    @POST(ConstantsV2.URLS.REGISTER)
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    Single<JsonObject> register(@Body RegisterRequest logInRequest);


    @POST(ConstantsV2.URLS.GTE_KIDS_LIST)
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    LiveData<ApiResponse<GetKidsResp>> getKidsListForAllKids();

    @POST(ConstantsV2.URLS.GTE_KIDS_LIST)
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    LiveData<ApiResponse<GetKidsResp>> getKidsListForAllKids(@Header("Authorization") String authorization);


//    @POST(ConstantsV2.URLS.PARENT_NOTIFICATION)
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    Single<ChangeLocationResponse> changeLocation(@Body ChangeLocationRequest changeLocationRequest);
//
//    @GET
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    Single<JsonObject> getPhoneFromFacebookAccountKit(@Url String url, @Query("access_token") String access_token);
//
//    @POST(ConstantsV2.URLS.PARENTS_KIDS_HISTORY)
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    Single<ArrayList<GetNotificationsResponse>> getNotifications(@Body GetNotificationsRequest getNotificationsRequest);
//
//
//    @POST(ConstantsV2.URLS.FORGET_PASSWORD)
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    Single<ForgetPinResponse> callForgetPin(@Body ForgetPinRequest changeLocationRequest);
//
//
//    /**/
//    /**/
//    @POST(ConstantsV2.URLS.GTE_KIDS_LIST)
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    Single<KidsListResponse> getKidsListForSingleKid(KidsListRequest kidsListRequest);
//
//    @GET(ConstantsV2.URLS.ROUND_STATUS)
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    Single<RoundStatusResponse> getRoundStatus(@Path("round_id") int round_id);
//
//    @GET(ConstantsV2.URLS.BUS_LOCATION)
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    Single<BusLocationResponse> getBusLocation(@Path("bus_id") int bus_id);
//
//    @POST(ConstantsV2.URLS.IS_STUDENT_SERVED)
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    Single<IsStudentSrvedResponse> isStudentServed(@Body IsStudentSrvedRequest isStudentSrvedRequest);
//
//    /**/
//    /**/
    @GET(ConstantsV2.URLS.GET_AND_SEND_SETTING)
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Single<GetSettingsResponse> getSetting();
//
    @POST(ConstantsV2.URLS.GET_AND_SEND_SETTING)
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Single<Object> sendSetting(@Body SendSettingsRequest sendSettingsRequest);
//
//
//    @POST(ConstantsV2.URLS.PARENT_IS_REGISTER)
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    Single<JsonObject> isParentRegistered(@Body IsParentRegesteredRequest sendSettingsRequest);


}
