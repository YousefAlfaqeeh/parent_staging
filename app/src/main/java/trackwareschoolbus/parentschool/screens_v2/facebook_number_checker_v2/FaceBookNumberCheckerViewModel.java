//package trackwareschoolbus.parentschool.screens_v2.facebook_number_checker_v2;
//
//import com.google.gson.JsonObject;
//
//import io.reactivex.SingleObserver;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.functions.Function;
//import trackwareschoolbus.parentschool.API_V2.ConnectionFactory;
//import trackwareschoolbus.parentschool.screens_v2.BaseViewModelV2;
//import trackwareschoolbus.parentschool.screens_v2.regester_v2.request_and_response.RegesterResponse;
//import trackwareschoolbus.parentschool.toolsV2.ConstantsV2;
//import trackwareschoolbus.parentschool.toolsV2.StringToolsV2;
//
//
//public class FaceBookNumberCheckerViewModel extends BaseViewModelV2 {
//
//
//    public FaceBookNumberCheckerViewModel callGetPhoneFromFacebookAccountKit(String access_token, SingleObserver<String> singleObserver) {
//
//        ConnectionFactory.getInstance().getPhoneFromFacebookAccountKit(ConstantsV2.URLS.FACEBOOK_ACCOUNT_KIT_BASE_URL, access_token)
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(new Function<JsonObject, String>() {
//                    @Override
//                    public String apply(JsonObject jsonObject) throws Exception {
//                        String phone_number;
//                        try {
//                            phone_number = jsonObject.get("phone").getAsJsonObject().get("number").getAsString();
//                            if (StringToolsV2.isEmptyString(phone_number)) {
//                                phone_number = "Error";
//                            }
//                        } catch (Error e) {
//                            phone_number = "Error";
//                        }
//
//                        return phone_number;
//                    }
//                })
//                .subscribe(singleObserver);
//        return this;
//    }
//
//
//    public FaceBookNumberCheckerViewModel callIsParentRegistered(IsParentRegesteredRequest isParentRegesteredRequest, SingleObserver<RegesterResponse> singleObserver) {
//
//        ConnectionFactory.getInstance().isParentRegistered(isParentRegesteredRequest).observeOn(AndroidSchedulers.mainThread())
//                .map(new Function<JsonObject, RegesterResponse>() {
//                    @Override
//                    public RegesterResponse apply(JsonObject object) throws Exception {
//                        RegesterResponse regesterResponse = new RegesterResponse();
//                        if (object.get("parent_registered") == null) {
//                            regesterResponse.status = RegesterResponse.RegisterStatus.NotExist;
//                            regesterResponse.message_ar = object.get("message_ar").getAsString();
//                            regesterResponse.message_en = object.get("message_en").getAsString();
//                        } else if (object.get("parent_registered").getAsBoolean()) {
//                            regesterResponse.status = RegesterResponse.RegisterStatus.Registered;
//                            regesterResponse.pin = object.get("pin").getAsString();
//                            regesterResponse.name = object.get("name").getAsString();
//                            regesterResponse.email = object.get("email").getAsString();
//
//                        } else {
//                            regesterResponse.status = RegesterResponse.RegisterStatus.NotRegistered;
//                        }
//
//                        return regesterResponse;
//                    }
//                })
//                .subscribe(singleObserver);
//        return this;
//    }
//
//
////    {
////        "parent_registered": null,
////            "message_en": "Contact your school",
////            "message_ar": "Contact your school"
////    }
////
////
////    {
////        "parent_registered": false
////    }
////
////
////    {
////        "parent_registered": true,
////            "pin": "1234",
////            "name": "abdallah",
////            "email": "a.khutaba@trackware.com"
////    }
//
//
//}
