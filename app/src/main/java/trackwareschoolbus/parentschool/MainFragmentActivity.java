package trackwareschoolbus.parentschool;
/**/

import static trackwareschoolbus.parentschool.utilityParent.StaticValue.mActivity;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import trackwareschoolbus.parentschool.API_new.APIs.ApiController;
import trackwareschoolbus.parentschool.API_new.APIs.ListenersAndInterFaces.OnApiComplete;
import trackwareschoolbus.parentschool.app.Application;
import trackwareschoolbus.parentschool.basePage.BaseActivity;
import trackwareschoolbus.parentschool.basePage.BaseFragment;
import trackwareschoolbus.parentschool.bean.GCMMessage;
import trackwareschoolbus.parentschool.bean.ImportantNotification;
import trackwareschoolbus.parentschool.bean.NotificationObj;
import trackwareschoolbus.parentschool.bean.StudentPickUpStatusRequest;
import trackwareschoolbus.parentschool.bean.StudentPickUpStatusResponse;
import trackwareschoolbus.parentschool.dataBase.DAO;
import trackwareschoolbus.parentschool.dataBase.OpenHelper;
import trackwareschoolbus.parentschool.fragment.login.LoginFragment;
import trackwareschoolbus.parentschool.fragment.maps.ChangeLocationFragment;
import trackwareschoolbus.parentschool.fragment.maps.KidDetailsFragment;
import trackwareschoolbus.parentschool.fragment.maps.MapParentFragment;
import trackwareschoolbus.parentschool.fragment.mykids.MyKidsFragment_v3;
import trackwareschoolbus.parentschool.fragment.notification.NotificationFragment;
import trackwareschoolbus.parentschool.fragment.qr_code.QRCodeFragment;
import trackwareschoolbus.parentschool.fragment.setting.SettingFragment;
import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener;
import trackwareschoolbus.parentschool.toolsV2.GalleryToolsV2;
import trackwareschoolbus.parentschool.utilityParent.StaticValue;
import trackwareschoolbus.parentschool.utilityParent.UtilDialogs;
import trackwareschoolbus.parentschool.utilityParent.UtilityParent;


public class MainFragmentActivity extends BaseActivity {


    private static final int MY_LOCATION_REQUEST_CODE = 101;
    static FragmentManager fragmentManager;
    static androidx.fragment.app.FragmentTransaction fragmentTransaction;
    public OnActionDoneListener<Intent> onReceiverReceived;
    private final BroadcastReceiver scoolMessage = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            try {
                checkForConfirmPickup();
//                checkNotificationMesseges();
                checkImportantMessages();
                /**/
                getIntent().removeExtra("CONFIRM_PICKUP");
                getIntent().removeExtra("Notification_Message");
                getIntent().removeExtra("SchoolMessageNotification");
                /**/



            } catch (Exception e) {

            }
            try {
                if (onReceiverReceived != null)
                    onReceiverReceived.OnActionDone(intent);
        } catch (Exception e) {

        }


//            checkNotificationMesseges();


        }
    };


//    public void checkNotificationMesseges() {
//        if ((getFragmentManager().findFragmentById(R.id.container_fragmnet) instanceof NotificationFragment) || (getFragmentManager().findFragmentById(R.id.container_fragmnet) instanceof MapParentFragment))
//            return;
//        try {
//            AppCompatButton viewById =  getFragmentManager().findFragmentById(R.id.container_fragmnet).getView().findViewById(R.id.imgNotification_count);
//            if (viewById == null) {
//                return;
//            }
//
//            int notCount = DAO.haseNotification(OpenHelper.getDatabase(MainFragmentActivity.this));
//            if (notCount > 0) {
//                viewById.setVisibility(View.VISIBLE);
//                viewById.setText(notCount > 99 ? "99+" : notCount + "");
//            } else {
//                viewById.setVisibility(View.GONE);
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }
//    }

    UtilDialogs.MessageYesNoDialog messageYesNoDialog;

    public void checkImportantMessages() {
        if (messageYesNoDialog != null)
            return;

        ImportantNotification importantNotification = DAO.ImportantNotificationTable.getOne(OpenHelper.getDatabase(MainFragmentActivity.this));
        /**/
        if (importantNotification != null && importantNotification.getType().equals(ImportantNotification.SCHOOL) && importantNotification.getMessage() != null) {
            NotificationObj message = (NotificationObj) importantNotification.getMessage();
            String id = importantNotification.getId();
            messageYesNoDialog = UtilDialogs.showGeneralMessageDialog(MainFragmentActivity.this)
                    .setDialogeTitle(message.getTitle())
                    .setDialogeMessage(message.getMessage())
                    .setImageWithColor(R.drawable.admin_msg, 0)
                    .setYesButtonClickListener(new OnActionDoneListener<UtilDialogs.MessageYesNoDialog>() {
                        @Override
                        public void OnActionDone(UtilDialogs.MessageYesNoDialog action) {
                            DAO.ImportantNotificationTable.delete(OpenHelper.getDatabase(MainFragmentActivity.this), id);
                            DAO.deleteNotification(OpenHelper.getDatabase(MainFragmentActivity.this), Integer.parseInt(id), Integer.parseInt(id));
                            messageYesNoDialog.dismiss();
                            messageYesNoDialog = null;
                            checkImportantMessages();
//                            checkNotificationMesseges();
                        }
                    });
        } else if (importantNotification != null && importantNotification.getType().equals(ImportantNotification.DRIVER)) {
            NotificationObj message = (NotificationObj) importantNotification.getMessage();
            String id = importantNotification.getId();
            messageYesNoDialog = UtilDialogs.showGeneralMessageDialog(MainFragmentActivity.this).setDialogeTitle(message.getTitle()).
                    setDialogeMessage(message.getMessage()).setImageWithColor(R.drawable.msg_driver, 0).
                    setYesButtonClickListener(new OnActionDoneListener<UtilDialogs.MessageYesNoDialog>() {
                        @Override
                        public void OnActionDone(UtilDialogs.MessageYesNoDialog Action) {
                            DAO.ImportantNotificationTable.delete(OpenHelper.getDatabase(MainFragmentActivity.this), id);
                            DAO.deleteNotification(OpenHelper.getDatabase(MainFragmentActivity.this), Integer.parseInt(id), Integer.parseInt(id));
                            messageYesNoDialog.dismiss();
                            messageYesNoDialog = null;
                            checkImportantMessages();
//                            checkNotificationMesseges();
                        }
                    });
            ;

        }


    }

    @Override
    public void onResume() {

        refreshFireBase();


        super.onResume();
        Application.activityResumed();

    }


    public void refreshFireBase(){
        try {
            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if (!task.isSuccessful()) {
                                Log.v("FirebaseInstanceId", "getInstanceId failed", task.getException());
                                return;
                            }

                            // Get new Instance ID token
                            String token = task.getResult().getToken();
                            UtilityParent.setStringShared(UtilityParent.TOKEN, token);

                        }
                    });
        }catch (Exception e){

        }

    }
    @Override
    protected void onPause() {
        super.onPause();

        Application.activityPaused();

    }


    LocationManager systemService;
    Timer timerPhoenix;
//    TimerTask timerTaskPhoenix;

//    public void startTimerPhoenix() {
//        //set a new Timer
//        timerPhoenix = new Timer();
//
//        try {
//            timerOpenPhoenix();
//        } catch (OutOfMemoryError outOfMemoryError) {
//
//        }
//
//
//        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
//        timerPhoenix.schedule(timerTaskPhoenix, 3 * 1000, 5 * 1000); //
//    }

//    private void timerOpenPhoenix() {
//
//        timerTaskPhoenix = new TimerTask() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        initLocationService();
//                    }
//                });
//            }
//        };
//
//    }

    public void stopTimerPhoenix() {
        //stop the timer, if it's not already null
        if (timerPhoenix != null) {
            timerPhoenix.cancel();
            timerPhoenix = null;
        }
    }

//    @SuppressLint("MissingPermission")
//    private void initLocationService() {
//        try {
//
//
//            locationPermission(this, new OnActionDoneListener<Boolean>() {
//                @Override
//                public void OnActionDone(Boolean permissionsAccepted) {
//                    if (permissionsAccepted) {
//                        if (systemService == null) {
//                            Criteria criteria = new Criteria();
//                            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
//
//                            final long location_TIME = 5000;
//
//
//                            systemService = (LocationManager) getSystemService(LOCATION_SERVICE);
//                            /**/
//                            systemService.requestLocationUpdates(LocationManager.GPS_PROVIDER, location_TIME,
//                                    location_TIME, new LocationListener() {
//                                        @Override
//                                        public void onLocationChanged(Location location) {
//                                            StaticValue.latitudeMain = location.getLatitude();
//                                            StaticValue.longitudeMain = location.getLongitude();
//                                        }
//
//                                        @Override
//                                        public void onStatusChanged(String provider, int status, Bundle extras) {
//
//                                        }
//
//                                        @Override
//                                        public void onProviderEnabled(String provider) {
//
//                                        }
//
//                                        @Override
//                                        public void onProviderDisabled(String provider) {
//
//                                        }
//                                    });
//                        }
//
//
//                        if (StaticValue.latitudeMain == 0 || StaticValue.longitudeMain == 0) {
//
//
//                            LocationServices.getFusedLocationProviderClient(mActivity).getLastLocation().addOnSuccessListener(mActivity, new OnSuccessListener<Location>() {
//                                @Override
//                                public void onSuccess(Location location) {
//                                    // Got last known location. In some rare situations this can be null.
//                                    if (location != null) {
//                                        StaticValue.latitudeMain = location.getLatitude();
//                                        StaticValue.longitudeMain = location.getLongitude();
//                                    }
//                                }
//                            });
//                            return;
//                        }
//                    }
//                }
//            });
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;


        NotificationManager nManager = ((NotificationManager) getApplication().getSystemService(Context.NOTIFICATION_SERVICE));
        nManager.cancelAll();


        StaticValue.progressDialog = ProgressDialog.show(mActivity, "",
                getString(R.string.waiting), true);
        StaticValue.progressDialog.dismiss();


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();


        try {
//            boolean isFromnotification = getIntent().hasExtra("fragment") && getIntent().getExtras().getInt("fragment") == 1;
//            getSupportFragmentManager().popBackStack(null, getFragmentManager().POP_BACK_STACK_INCLUSIVE);


//            if (UtilityParent.getBooleanShared(UtilityParent.IS_LOgIn) && isFromnotification) {
//                showNotificationFragment();
//            }

//            else
            if (UtilityParent.getSavedlDataHolders().size() > 0) {
                showMyKidsFragment();
            } else {
                showLogInFragment();

            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (getIntent().hasExtra("From_Notification")) {
                            Bundle bundle = new Bundle();
                            GCMMessage gcmMessage = getIntent().getParcelableExtra("GCMMessage");
                            getIntent().removeExtra("From_Notification");
//                            if (gcmMessage != null) {
//                                bundle.putParcelable("GCMMessage", gcmMessage);
//                                showChatScreen(gcmMessage, false);
//                            }


                        } else if (getIntent().hasExtra("SchoolMessageNotification")) {
                            NotificationObj schoolMessage = getIntent().getParcelableExtra("SchoolMessage");
                            getIntent().removeExtra("SchoolMessageNotification");
//                                UtilDialogs.showGeneralMessageDialog(MainFragmentActivity.this).setDialogeTitle(schoolMessage.getCleanTitle()).setDialogeMessage(schoolMessage.getMessage()).setImageWithColor(R.drawable.admin_msg, 0);
                            checkImportantMessages();

                        } else if (getIntent().hasExtra("DriverMessageNotification")) {
                            NotificationObj driver_message = getIntent().getParcelableExtra("DriverMessage");
                            getIntent().removeExtra("DriverMessageNotification");
                            checkImportantMessages();
//                                UtilDialogs.showGeneralMessageDialog(MainFragmentActivity.this).setDialogeTitle(driver_message.getTitle()).setDialogeMessage(driver_message.getMessage()).setImageWithColor(R.drawable.msg_driver, 0);

                        } else if (getIntent().hasExtra("CONFIRM_PICKUP")) {
                            getIntent().removeExtra("CONFIRM_PICKUP");

                            checkForConfirmPickup();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }, 500);


//            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
//            startTimerPhoenix();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

//        getSupportFragmentManager().addOnBackStackChangedListener(
//                new FragmentManager.OnBackStackChangedListener() {
//                    public void onBackStackChanged() {
//
//                        // Your Code Here
//
//                    }
//                });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.v("onBackPressed", getSupportFragmentManager().getBackStackEntryCount() + "");
        if (getSupportFragmentManager().getBackStackEntryCount() <= 0) {
            finish();
        }
    }

    UtilDialogs.MessageYesNoDialog confirmPickupDialog;

    public void checkForConfirmPickup() {
        if (confirmPickupDialog == null || !confirmPickupDialog.isShowing()) {
            ApiController.getStudentPickUpStatus(this, new OnApiComplete<ArrayList<StudentPickUpStatusResponse>>() {
                @Override
                public void onSuccess(ArrayList<StudentPickUpStatusResponse> studentPickUpStatusResponses) {

                    showConfirmPickupDialogIfNeeded(studentPickUpStatusResponses);

                }

                @Override
                public void onError(int errorCode, String errorMessage) {
//                    UtilityParent.showMessage(MainFragmentActivity.this, MainFragmentActivity.this.getString(R.string.api_send_error));
                    checkForConfirmPickup();
                }
            });
        }


    }


    public void sendConfirmPickup(boolean confirm, int studentId) {
        ApiController.sendStudentPickUpStatus(this, new StudentPickUpStatusRequest().setStatus(confirm ? "yes" : "no").setStudentId(studentId), new OnApiComplete<ArrayList<StudentPickUpStatusResponse>>() {
            @Override
            public void onSuccess(ArrayList<StudentPickUpStatusResponse> studentPickUpStatusResponses) {
                showConfirmPickupDialogIfNeeded(studentPickUpStatusResponses);
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
//                UtilityParent.showMessage(MainFragmentActivity.this, MainFragmentActivity.this.getString(R.string.api_send_error));
//                checkForConfirmPickup();
            }
        });

    }


    private void showConfirmPickupDialogIfNeeded(ArrayList<StudentPickUpStatusResponse> studentPickUpStatusResponses) {
        try {
            if (studentPickUpStatusResponses == null || studentPickUpStatusResponses.size() == 0)
                return;

            confirmPickupDialog = UtilDialogs.showGeneralMessageDialog(MainFragmentActivity.this)
                    .setDialogeTitle(studentPickUpStatusResponses.get(0).getMessage())
//                            .setDialogeMessage(message.getMessage())
                    .setImageWithColor(R.drawable.pick_up_outline, 0)
                    .setYesButtonText(R.string.yes)
                    .setCancelButtonText(R.string.no)
                    .setYesButtonClickListener(new OnActionDoneListener<UtilDialogs.MessageYesNoDialog>() {
                        @Override
                        public void OnActionDone(UtilDialogs.MessageYesNoDialog action) {
                            confirmPickupDialog.dismiss();
                            confirmPickupDialog = null;
                            sendConfirmPickup(true, studentPickUpStatusResponses.get(0).getStudentId());
//                                    checkForConfirmPickup();
                        }
                    }).setCancelButtonClickListener(new OnActionDoneListener<UtilDialogs.MessageYesNoDialog>() {
                        @Override
                        public void OnActionDone(UtilDialogs.MessageYesNoDialog action) {
                            confirmPickupDialog.dismiss();
                            confirmPickupDialog = null;
                            sendConfirmPickup(false, studentPickUpStatusResponses.get(0).getStudentId());
//                                    checkForConfirmPickup();

                        }
                    });
        } catch (Exception e) {

        }

    }

    public static void showLogInFragment() {
        BaseFragment logInFragment = new LoginFragment();
        fragmentManager.popBackStackImmediate(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
        replaceFragmentGeneric(logInFragment);

    }

//    public static void showTeacherList(GetKidsResp.Student kidBean) {
//        BaseFragment teacherFragment = new TeacherFragment();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("kidBean", kidBean);
//        teacherFragment.setArguments(bundle);
//        replaceFragmentGeneric(teacherFragment);
//
//    }

//    public static void showChatScreen(GCMMessage gcmessage, Boolean deleted) {
//        ChatFragment chatFragment = new ChatFragment();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("GCMMessage", gcmessage);
//        bundle.putBoolean("deleted", deleted);
//        chatFragment.setArguments(bundle);
//        replaceFragmentGeneric(chatFragment);
//
//    }


    public static void showSettingFragment() {
        BaseFragment settingFragment = new SettingFragment();
        replaceFragmentGeneric(settingFragment);
    }

    public static void showNotificationFragment() {
        try {
            NotificationFragment notificationFragment = new NotificationFragment();
            replaceFragmentGeneric(notificationFragment);
        }catch (Exception e){

        }

    }

    public static void showLoginFragment() {
        LoginFragment calledFragment = new LoginFragment();
        MainFragmentActivity.replaceFragmentGeneric(calledFragment);
    }


    public static void showMyKidsFragment() {
        MyKidsFragment_v3 myKidsFragment = MyKidsFragment_v3.newInstance();
        replaceFragmentGeneric(myKidsFragment);
    }


    public static void showKidDetails(Bundle bundle) {
        KidDetailsFragment mapFragment = new KidDetailsFragment();
        mapFragment.setArguments(bundle);
        replaceFragmentGeneric(mapFragment);
    }

    public static void showMapFragment(Bundle bundle) {
        MapParentFragment mapFragment = new MapParentFragment();
        mapFragment.setArguments(bundle);
        replaceFragmentGeneric(mapFragment);
    }


    public static void showQrCode(int studentID) {
        QRCodeFragment qrCodeFragment = new QRCodeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("studentID", studentID);
        qrCodeFragment.setArguments(bundle);
        replaceFragmentGeneric(qrCodeFragment);
    }

    public static void showChangeLocationFragment(int studentID, boolean showPic,
                                                  boolean showDrop) {
        ChangeLocationFragment changeLocationFragment = new ChangeLocationFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("studentID", studentID);
        bundle.putBoolean("showPic", showPic);
        bundle.putBoolean("showDrop", showDrop);
        changeLocationFragment.setArguments(bundle);
        replaceFragmentGeneric(changeLocationFragment);
    }

    public static void replaceFragmentGeneric(final BaseFragment baseFragment) {

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.replace(R.id.container_fragmnet, baseFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();

    }


    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(scoolMessage, new IntentFilter("Notification_Message"));

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(scoolMessage);

    }


//    private BroadcastReceiver messageBroadcastReceiver;

//    public void registerMessageReceiver(BroadcastReceiver messageBroadcastReceiver) {
//        unRegisterMessageReceiver();
//        this.messageBroadcastReceiver = messageBroadcastReceiver;
//        registerReceiver(messageBroadcastReceiver, new IntentFilter("GCMMessage"));
//    }

    public void unRegisterMessageReceiver() {
        try {
            this.onReceiverReceived=null;
        } catch (Exception e) {
        }
    }

    /////////////
//    private BroadcastReceiver refreshBroadcastReceiver;

    public void registerRefreshReceiver(OnActionDoneListener<Intent> _onReceiverReceived) {
        this.onReceiverReceived = _onReceiverReceived;
    }


    private OnActionDoneListener<Uri> afterImageSelectedListener;

    public void openGalary(OnActionDoneListener<Uri> onImagesPicked) {
        GalleryToolsV2.openGalary(this, getString(R.string.pick_image_title));
        afterImageSelectedListener = onImagesPicked;
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (afterImageSelectedListener != null) {
            GalleryToolsV2.handleOnActivityResult(this, requestCode, resultCode, data, afterImageSelectedListener);
            afterImageSelectedListener = null;
        }

    }

//    public static void showKidScreenFragment(Bundle bundle) {
//        KidDetailsFragment mapFragment = new KidDetailsFragment();
//        if (bundle != null)
//            mapFragment.setArguments(bundle);
//        replaceFragmentGeneric(mapFragment);
//    }

}
