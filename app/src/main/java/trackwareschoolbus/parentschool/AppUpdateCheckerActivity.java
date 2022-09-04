package trackwareschoolbus.parentschool;

import trackwareschoolbus.parentschool.utilityParent.UtilDialogs;

public class AppUpdateCheckerActivity extends LocalizationAndFontHandlerActivity {


    @Override
    protected void onStart() {
        super.onStart();
//        checkForUpdate();
    }

    UtilDialogs.MessageYesNoDialog messageYesNoDialog;

//    private void checkForUpdate() {
//
//        ApiController.checkForLatestAppVersion(this, new OnApiComplete<ApplicationVersionJson>() {
//            @Override
//            public void onSuccess(ApplicationVersionJson applicationVersionJson) {
//                try {
//                    /**/
//                    String googlePlayLink = applicationVersionJson.getGooglePlayLink();
//                    String latestVersionName = applicationVersionJson.getLatestVersionName();
//                    int latestVersionCode = applicationVersionJson.getLatestVersionCode();
//                    String updateMessageAr = applicationVersionJson.getUpdateMessageAr();
//                    String updateMessageEn = applicationVersionJson.getUpdateMessageEn();
//                    String updateMessage = getString(R.string.update_available);
//                    /**/
//                    int currentVersionCode = BuildConfig.VERSION_CODE;
//                    String currentversionName = BuildConfig.VERSION_NAME;
//                    /**/
//                    if (UtilityParent.isEmptyString(googlePlayLink, latestVersionName, currentversionName))
//                        return;
//                    /**/
//                    if (currentVersionCode >= latestVersionCode) {
//                        return;
//                    }
//
//
//                    /**/
//                    try {
//                        if (UtilityParent.getStringShared(UtilityParent.LANGUAGE).equalsIgnoreCase("ar") && updateMessageAr != null && !updateMessageAr.trim().equals("")) {
//                            updateMessage = updateMessageAr + "";
//                        } else if (updateMessageEn != null && !updateMessageEn.trim().equals("")) {
//                            updateMessage = updateMessageEn + "";
//                        }
//
//
//                    } catch (Exception e) {
////                        Application.logEvents("checkForLatestAppVersion", "Error on Update Check ", e);
//                        updateMessage = getString(R.string.update_available);
//                    }
//
//                    if (messageYesNoDialog == null || !messageYesNoDialog.isShowing()) {
//                        messageYesNoDialog = UtilDialogs.showYesCancelMessage(AppUpdateCheckerActivity.this)
//                                .setDialogeTitle(R.string.update_available)
//                                .setImageWithColor(R.drawable.ic_system_update_black_36dp, R.color.colorPrimary)
//                                .setYesButtonText(R.string.update_now)
//                                .setCancelButtonText(R.string.maybe_later)
//                                .setYesButtonClickListener(new OnActionDoneListener() {
//                                    @Override
//                                    public void OnActionDone(Object o) {
//                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(googlePlayLink.trim())));
//                                        messageYesNoDialog = null;
//                                    }
//                                });
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    onError(0, Arrays.toString(e.getStackTrace()));
//                }
//
//            }
//
//            @Override
//            public void onError(int errorCode, String errorMessage) {
////                errorMessage = errorMessage == null ? "onError" : errorMessage;
////                Application.logEvents("checkForLatestAppVersion", "Error on Update Check ", errorMessage);
//
//            }
//        });
//    }



}
