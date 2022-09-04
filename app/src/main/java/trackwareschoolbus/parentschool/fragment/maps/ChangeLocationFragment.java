package trackwareschoolbus.parentschool.fragment.maps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import trackwareschoolbus.parentschool.MainFragmentActivity;
import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.basePage.BaseFragment;
import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener;
import trackwareschoolbus.parentschool.utilityParent.StaticValue;
import trackwareschoolbus.parentschool.utilityParent.UtilityParent;

//import static android.Manifest.permission.ACCESS_FINE_LOCATION;


/**
 * Created by  on 4/2/2017.
 */

public class ChangeLocationFragment extends BaseFragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {


    private GoogleMap mMap;
    MapView mMapView;
    GoogleApiClient mGoogleApiClient;

    Location mLastLocation;
    LocationRequest mLocationRequest;
    static double lat, lng;
//    LinearLayout mainBar;

    Marker mCurrLocationMarker;
    //    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
//    AppCompatImageView imgBack;
//    ImageView imgBar;
//    TextView labTitle;
//    ImageView imgHome;
//    AppCompatImageView imgNotification;
    AppCompatCheckBox checkDropOff;
    AppCompatCheckBox checkPickUp;
    Button btnSaveLocation;
    Button btnCancelChangeLocation;
    MapParentPresenter mapPresenter;
    //    String roundType = "";
    //    String dropOff = null;
//    String pickUp = null;
    LatLng oldLocation;
    private MarkerOptions marker;
    private int studentID;
    private int locationTryingTimes = 0;
    private boolean showDrop, showPic;
    // ChangeLocationDialog changeLocationDialog;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_change_location, container, false);

        Bundle extras = getArguments();
        this.studentID = extras.getInt("studentID");
        showDrop = extras.getBoolean("showDrop");
        showPic = extras.getBoolean("showPic");
        checkPickUp = view.findViewById(R.id.checkPickUp);
        checkDropOff = view.findViewById(R.id.checkDropOff);
        btnCancelChangeLocation = view.findViewById(R.id.btnCancleLocattion);
        btnSaveLocation = view.findViewById(R.id.btnSaveLocattion);
//        mainBar = view.findViewById(R.id.mainBar);
        mMapView = view.findViewById(R.id.mapChangeLocation);
//        imgBar = view.findViewById(R.id.barImage);
//        labTitle = view.findViewById(R.id.labTitle);
//        imgHome = view.findViewById(R.id.imgHome);
//        imgNotification = view.findViewById(R.id.imgNotification);
//        imgBack = view.findViewById(R.id.imgBack);
        /**/
        checkPickUp.setVisibility(showPic ? View.VISIBLE : View.GONE);
        checkDropOff.setVisibility(showDrop ? View.VISIBLE : View.GONE);
        /**/
//        labTitle.setText(getResources().getString(R.string.change_location_screen_title));
//        imgHome.setVisibility(View.INVISIBLE);
//        imgBar.setImageResource(R.drawable.img_change_location);
        /**/
        if (UtilityParent.getStringShared(UtilityParent.LANGUAGE).equals("ar")) {
            checkDropOff.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            checkPickUp.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            btnCancelChangeLocation.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            btnSaveLocation.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
//            mainBar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }


        btnSaveLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String roundType = "";
                if (checkPickUp.isChecked() && checkDropOff.isChecked()) {
                    roundType = "both";
                } else if (checkDropOff.isChecked()) {
                    roundType = "drop-off";
                } else if (checkPickUp.isChecked()) {
                    roundType = "pick-up";
                } else {
                    UtilityParent.showMessage(getMainActivity(), getMainActivity().getString(R.string.select_round_));
                    return;
                }


                mapPresenter = new MapParentPresenter(ChangeLocationFragment.this.getActivity(), roundType);
                mapPresenter.callChangeLocation(roundType, lat, lng, studentID);


            }
        });

        btnCancelChangeLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragmentActivity.showMyKidsFragment();
            }
        });


//        imgBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainFragmentActivity.showMyKidsFragment();
//            }
//        });


//        imgNotification.setImageResource(R.drawable.img_home);
//        imgNotification.setVisibility(View.VISIBLE);
//        imgNotification.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainFragmentActivity.showMyKidsFragment();
//            }
//        });


        getMainActivity().locationPermission(getMainActivity(), new OnActionDoneListener<Boolean>() {
                    @Override
                    public void OnActionDone(Boolean permissionsAccepted) {
                        if (permissionsAccepted) {
                            try {
                                mMapView.onCreate(savedInstanceState);
                                mMapView.onResume(); // needed to get the map to display immediately
                                MapsInitializer.initialize(getMainActivity());
                                mMapView.getMapAsync(ChangeLocationFragment.this);
                                if (mMap!=null){
                                    mMap.moveCamera(CameraUpdateFactory.newLatLng(oldLocation));
                                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(oldLocation, 15.0f));
                                }


                            } catch (Exception e) {
                                e.printStackTrace();
                                MainFragmentActivity.showMyKidsFragment();
                            }

                        } else {
                            MainFragmentActivity.showMyKidsFragment();
                        }


                    }
                }
        );


        return view;
    }

    @SuppressLint("MissingPermission")
    private LatLng getCurrentLocation(){
        LatLng latLng = null;
        try {
            LocationManager locationManager = (LocationManager)
                    getMainActivity().getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();

            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
             latLng = new LatLng(location.getLatitude(), location.getLongitude());
        }catch (Exception e ){

        }
        return latLng;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
//        gainPermission(new PermissionsHandlerActivity.PermissionsLisiner() {
//
//            @Override
//            public void afterDialogDone(boolean permissionsAccepted) {
//                if (permissionsAccepted) {
        try {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(getCurrentLocation()));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(getCurrentLocation(), 15.0f));
                    initMarker(getCurrentLocation());

                    return true;
                }
            });
            mMap.moveCamera(CameraUpdateFactory.newLatLng(getCurrentLocation()));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(getCurrentLocation(), 15.0f));

            initMarker(getCurrentLocation());
        } catch (Exception e) {
            e.printStackTrace();

        }

//                } else {
//                    MainFragmentActivity.showMyKidsFragment();
//                }

//            }
//        }, PermissionsList.location);


    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(ChangeLocationFragment.this.getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


        mGoogleApiClient.connect();
    }


    public void onStart() {
        super.onStart();
        //  callGetStudentList();
    }

    @Override
    public void onResume() {
        super.onResume();
//        startTimerSpeed();
        //  callGetStudentList();

        if (mMapView != null)
            try {
                mMapView.onResume();
            } catch (Exception e) {

            }

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mMapView != null)
            try {
                mMapView.onPause();
            } catch (Exception e) {

            }


//        stopTimerSpeed();
    }

    @Override
    public void onStop() {
        try {
            if (mGoogleApiClient != null) {
                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            }
            if (mCurrLocationMarker != null) {
                mCurrLocationMarker.remove();
            }

        } catch (Exception e) {

        }
        super.onStop();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMapView != null)
            mMapView.onDestroy();
    }

    @Override
    protected boolean showToolBarNotification() {
        return true;
    }

    @Override
    protected boolean showToolBack() {
        return true;
    }

    @Override
    protected boolean showToolBarSettings() {
        return true;
    }

    @Override
    protected String initToolBarTitle() {
        return getString(R.string.change_location_screen_title);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mMapView != null)
            mMapView.onLowMemory();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {
            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(1000);
            mLocationRequest.setFastestInterval(1000);
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            try {

            } catch (Exception e) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
                if (StaticValue.progressDialog != null) {
                    StaticValue.progressDialog.show();
                }
            }

        } catch (Exception e) {
        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    Marker addedMKarker;

    public void initMarker(LatLng latLng) {
        mMap.clear();
//        if (marker == null) {
        marker = new MarkerOptions();
        marker.position(latLng);
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        marker.title(getString(R.string.current_position));
        marker.draggable(true);
        addedMKarker = mMap.addMarker(marker);
        lat = marker.getPosition().latitude;
        lng = marker.getPosition().longitude;
//        }
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                marker.getPosition();
            }

            @Override
            public void onMarkerDrag(Marker marker) {
                marker.getPosition();
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                marker.getPosition();
                lat = marker.getPosition().latitude;
                lng = marker.getPosition().longitude;
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;


//        if (location.getAccuracy() <= 50.0f) {
//            locationTryingTimes = 0;
        // use your location data here

        oldLocation = new LatLng(location.getLatitude(), location.getLongitude());
        if (oldLocation==null){
            oldLocation=  getCurrentLocation();
        }

        LatLng latLng = new LatLng(oldLocation.latitude, oldLocation.longitude);
        initMarker(latLng);
//
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15.0f));

        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
        try {
            StaticValue.progressDialog.dismiss();
        } catch (Exception e) {

        }
//        } else {
//            if (locationTryingTimes > 7) {
//                StaticValue.progressDialog.dismiss();
//                // show message (can't get Location)
//            } else {
//                locationTryingTimes++;
//            }
//        }


    }

//    public static boolean checkLocationPermission(Activity Activity) {
//        if (ContextCompat.checkSelfPermission(Activity,
//                android.Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Asking user if explanation is needed
//            if (ActivityCompat.shouldShowRequestPermissionRationale(Activity,
//                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {
//
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//
//                //Prompt the user once explanation has been shown
//
//
//
//            } else {
//                // No explanation needed, we can request the permission.
//                ActivityCompat.requestPermissions(Activity,
//                        new String[]{ACCESS_FINE_LOCATION},
//                        MY_PERMISSIONS_REQUEST_LOCATION);
//            }
//            return false;
//        } else {
//            return true;
//        }
//    }


//    private void showHidePick(){
//        if (true){
//            checkPickUp.setVisibility(VolleyLog);
//        }
//        checkPickUp =   view.findViewById(R.id.checkPickUp);
//        checkDropOff =   view.findViewById(R.id.checkDropOff);
//
//    }
}

