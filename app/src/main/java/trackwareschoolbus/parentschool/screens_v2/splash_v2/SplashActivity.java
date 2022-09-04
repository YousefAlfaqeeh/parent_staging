//package trackwareschoolbus.parentschool.screens_v2.splash_v2;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//
//import new_screens.AppConstants;
//import new_screens.activites.HomeActivity;
//import new_tools.DrawableTools;
//import new_tools.SharedPrefTools;
//import trackwareschoolbus.parentschool.FacebookNumberCheckerActivity;
//import trackwareschoolbus.parentschool.R;
//import trackwareschoolbus.parentschool.toolsV2.ConstantsV2;
//import trackwareschoolbus.parentschool.toolsV2.DrawableToolsV2;
//import trackwareschoolbus.parentschool.toolsV2.SharedPrefToolsV2;
//
//public class SplashActivity extends FacebookNumberCheckerActivity implements ConstantsV2.SHARED_PREFERENCES_KEYS {
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        if (SharedPrefToolsV2.getLastUserState().equalsIgnoreCase(LAST_USER_STATE.SELECTING_LANGUAGE)) {
//            showSplashImage();
//            goToHomeScreen(true);
//        } else {
//            goToHomeScreen(false);
//
//        }
////            showSplashImage();
////            goToLanguage();
////        }
////        switch (SharedPrefTools.getLastUserState()) {
////            case LAST_USER_STATE.SELECTING_LANGUAGE:
////
////                break;
////            case LAST_USER_STATE.CONFIRMING_PHONE_NUMBER:
////            case LAST_USER_STATE.LOGING_IN:
////            case LAST_USER_STATE.REGESTERING:
////            case LAST_USER_STATE.SHOWING_TUTORIAL:
////            case LAST_USER_STATE.AT_HOME:
////                goToHomeScreen();
////                break;
////
////        }
//
//
//    }
//
//    public void showSplashImage() {
//        setContentView(R.layout.activity_splash);
//        DrawableToolsV2.loadDrawable( findViewById(R.id.splash_image), R.mipmap.img_splash);
//    }
//
//
//    private void goToHomeScreen(boolean animate) {
//        if (animate)
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
//                    finish();
//                }
//            }, 1000);
//        else {
//            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
//            finish();
//        }
//
//
//    }
//}
