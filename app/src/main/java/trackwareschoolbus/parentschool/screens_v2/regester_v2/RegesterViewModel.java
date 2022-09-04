//package trackwareschoolbus.parentschool.screens_v2.regester_v2;
//
//import com.google.gson.JsonObject;
//
//import io.reactivex.SingleObserver;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.functions.Function;
//import trackwareschoolbus.parentschool.API_V2.ConnectionFactory;
//import trackwareschoolbus.parentschool.screens_v2.BaseViewModelV2;
//import trackwareschoolbus.parentschool.screens_v2.regester_v2.request_and_response.RegisterRequest;
//
//
//public class RegesterViewModel extends BaseViewModelV2 {
//
//
//    public RegesterViewModel call(RegisterRequest registerRequest, SingleObserver<String> singleObserver) {
//
//        ConnectionFactory.getInstance().register(registerRequest)
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(new Function<JsonObject, String>() {
//                    @Override
//                    public String apply(JsonObject response) throws Exception {
//                        String states;
//                        try {
//                            states = response.get("status").getAsString();
//                            if (!(states.equalsIgnoreCase("ok")
//                                    ||
//                                    states.equalsIgnoreCase("Parent already registered")
//                                    ||
//                                    states.equalsIgnoreCase("Parent not found"))) {
//                                states = "Error";
//                            }
////                            } else if (states.equalsIgnoreCase("Parent already registered")) {
////
////                            } else if (states.equalsIgnoreCase("Parent not found")) {
////
////                            }
//                        } catch (Exception e) {
//                            states = "Error";
//                        }
//
//
////                        | || response.getString("status").contains("Parent already registered")) {
////                            RegisterFragment.isRegeister = true;
////                            UtilityParent.setBooleanShared(UtilityParent.IS_REGISTER, RegisterFragment.isRegeister);
////                            UtilDialogs.MessageYesNoDialog successRegistered = UtilDialogs.showSuccessMessage(mActivity).setDialogeTitle(R.string.sent);
////                            new Handler().postDelayed(new Runnable() {
////                                @Override
////                                public void run() {
////                                    try {
////                                        successRegistered.dismiss();
////                                        MainFragmentActivity.showLogInFragment();
////                                    } catch (Exception e) {
////                                        e.printStackTrace();
////                                    }
////
////                                }
////                            }, 1000);
////
////
////                        } else {
////                            String value = response.getJSONArray("secret_token").toString();
//////                        UtilityParent.showMessageDialog(mActivity,mActivity.getString(R.string.error),value);
////                            new DialogRegister(mActivity);
////                        }
//
//
//                        return states;
//                    }
//                })
//                .subscribe(singleObserver);
//        return this;
//    }
//
//
//}
