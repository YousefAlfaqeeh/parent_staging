package trackwareschoolbus.parentschool;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.util.ArrayList;

import trackwareschoolbus.parentschool.basePage.BaseActivity;
import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener;

public class PermissionsHandlerActivity extends AppCompatActivity {

//    public boolean isCameraPermissionsShowing;
//    public boolean isLocationPermissionsShowing;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        isCameraPermissionsShowing = false;
//        isLocationPermissionsShowing = false;
    }


    public void cameraPermission(BaseActivity activity, OnActionDoneListener<Boolean> onActionDoneListener) {
        try {


//            Dexter.withActivity(activity)
//                    .withPermission(Manifest.permission.CAMERA)
//                    .withListener(new PermissionListener() {
//                        @Override
//                        public void onPermissionGranted(PermissionGrantedResponse response) {
//                            if (onActionDoneListener != null)
//                                onActionDoneListener.OnActionDone(true);
//                        }
//
//                        @Override
//                        public void onPermissionDenied(PermissionDeniedResponse response) {
//                            if (onActionDoneListener != null)
//                                onActionDoneListener.OnActionDone(false);
//                        }
//
//                        @Override
//                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
//
//                        }
//                    })
//                    .check();

//            if (!isCameraPermissionsShowing) {
            Permissions.check(activity, Manifest.permission.CAMERA, null,
                    new PermissionHandler() {

                        @Override
                        public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                            super.onDenied(context, deniedPermissions);
//                                isCameraPermissionsShowing=false;
                            if (onActionDoneListener != null)
                                onActionDoneListener.OnActionDone(false);
                        }

                        @Override
                        public void onGranted() {
//                                isCameraPermissionsShowing=false;
                            if (onActionDoneListener != null)
                                onActionDoneListener.OnActionDone(true);

                        }
                    });
//                isCameraPermissionsShowing = true;
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void locationPermission(BaseActivity activity, OnActionDoneListener<Boolean> onActionDoneListener) {
        try {

//            Dexter.withActivity(activity)
//                    .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
//                    .withListener(new PermissionListener() {
//                        @Override
//                        public void onPermissionGranted(PermissionGrantedResponse response) {
//                            if (onActionDoneListener != null)
//                                onActionDoneListener.OnActionDone(true);
//                        }
//
//                        @Override
//                        public void onPermissionDenied(PermissionDeniedResponse response) {
//                            if (onActionDoneListener != null)
//                                onActionDoneListener.OnActionDone(false);
//                        }
//
//                        @Override
//                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
//
//                        }
//                    })
//                    .check();

//            if (!isLocationPermissionsShowing) {
            Permissions.check(activity, Manifest.permission.ACCESS_FINE_LOCATION, null,
                    new PermissionHandler() {

                        @Override
                        public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                            super.onDenied(context, deniedPermissions);
//                                isLocationPermissionsShowing = false;
                            if (onActionDoneListener != null)
                                onActionDoneListener.OnActionDone(false);
                        }

                        @Override
                        public void onGranted() {
//                                isLocationPermissionsShowing = false;
                            if (onActionDoneListener != null)
                                onActionDoneListener.OnActionDone(true);

                        }
                    });
//                isLocationPermissionsShowing = true;
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//
//
//    public void cameraPermission(Context context, OnActionDoneListener<Boolean> onActionDoneListener) {
//        try {
//
//            Permissions.check(context, Manifest.permission.CAMERA, null,
//                    new PermissionHandler() {
//                        @Override
//                        public void onDenied(Context context, ArrayList<String> deniedPermissions) {
//                            super.onDenied(context, deniedPermissions);
//                            if (onActionDoneListener != null)
//                                onActionDoneListener.OnActionDone(false);
//                        }
//
//                        @Override
//                        public void onGranted() {
//                            if (onActionDoneListener != null)
//                                onActionDoneListener.OnActionDone(true);
//
//                        }
//                    });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void locationPermission(Context context, OnActionDoneListener<Boolean> onActionDoneListener) {
//        try {
//            Permissions.check(context, Manifest.permission.ACCESS_FINE_LOCATION, null,
//                    new PermissionHandler() {
//                        @Override
//                        public void onDenied(Context context, ArrayList<String> deniedPermissions) {
//                            super.onDenied(context, deniedPermissions);
//                            if (onActionDoneListener != null)
//                                onActionDoneListener.OnActionDone(false);
//                        }
//
//                        @Override
//                        public void onGranted() {
//                            if (onActionDoneListener != null)
//                                onActionDoneListener.OnActionDone(true);
//
//                        }
//                    });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public void batteryOptimizationsPermission(OnActionDoneListener<Boolean> onActionDoneListener) {
        try {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Permissions.check(this, Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS, null,
                        new PermissionHandler() {

                            @Override
                            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                                super.onDenied(context, deniedPermissions);

                                if (onActionDoneListener != null)
                                    onActionDoneListener.OnActionDone(false);
                            }

                            @Override
                            public void onGranted() {

                                if (onActionDoneListener != null)
                                    onActionDoneListener.OnActionDone(true);

                            }
                        });
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
