//package trackwareschoolbus.parentschool.toolsV2;
//
//import android.app.Activity;
//import android.content.Intent;
//
//import com.facebook.accountkit.AccountKitLoginResult;
//import com.facebook.accountkit.ui.AccountKitActivity;
//import com.facebook.accountkit.ui.AccountKitConfiguration;
//import com.facebook.accountkit.ui.LoginType;
//import com.google.gson.JsonObject;
//
//import trackwareschoolbus.parentschool.API_new.APIs.ApiController;
//import trackwareschoolbus.parentschool.API_new.APIs.ListenersAndInterFaces.OnApiComplete;
//import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener;
//
//public class FacebookApisToolsV2 {
//
//    private static final int FACEBOOOK_ACCOUNT_KIT_ACTIVITY_REQUESTCODE = 10101;
//
//    public static void showFacebookNumberCheckerScreen(Activity activity) {
//        final Intent intent = new Intent(activity, AccountKitActivity.class);
//        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder = new AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.PHONE, AccountKitActivity.ResponseType.TOKEN);
//        configurationBuilder.setReceiveSMS(true);
//        configurationBuilder.setReadPhoneStateEnabled(true);
//        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configurationBuilder.build());
//        activity.startActivityForResult(intent, FACEBOOOK_ACCOUNT_KIT_ACTIVITY_REQUESTCODE);
//    }
//
//
//    public static void checkFacebookOnActivityResult(final int requestCode, final Intent resultData, OnActionDoneListener<String> afterCheck) {
//        String accountKitAccessToken=null;
//        try {
//            if (requestCode == FACEBOOOK_ACCOUNT_KIT_ACTIVITY_REQUESTCODE) {
//                AccountKitLoginResult loginResult = resultData.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
//                if (loginResult.getError() != null || loginResult.getAccessToken() == null  || loginResult.getAccessToken().getToken().equalsIgnoreCase("")) {
//                    accountKitAccessToken = "Error";
//                } else if (loginResult.wasCancelled()) {
//                    accountKitAccessToken = "Error";
//                } else {
//                    accountKitAccessToken=loginResult.getAccessToken().getToken();
//                }
//            }
//        } catch (Exception e) {
//            accountKitAccessToken = "Error";
//
//        }
//        afterCheck.OnActionDone(accountKitAccessToken);
//
//
//    }
//
//
//}
