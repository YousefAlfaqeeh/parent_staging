package trackwareschoolbus.parentschool.fragment.maps;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;
import trackwareschoolbus.parentschool.API.enumApi.EnumMethodApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumTypeHeader;
import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallBack;
import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallPhoenix;
import trackwareschoolbus.parentschool.API_new.APIs.Constants;
import trackwareschoolbus.parentschool.MainFragmentActivity;
import trackwareschoolbus.parentschool.Parent;
import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.api_v3.GetKidsResp;
import trackwareschoolbus.parentschool.basePage.BaseDialog;
import trackwareschoolbus.parentschool.basePage.BaseFragment;
import trackwareschoolbus.parentschool.bean.StudentBean;
import trackwareschoolbus.parentschool.fragment.dialog.KidsInfoDialog;
import trackwareschoolbus.parentschool.fragment.dialog.ReconnectingInternetDialog;
import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener;
import trackwareschoolbus.parentschool.utilityParent.UtilityParent;

/**
 * Created by  on 3/7/2017.
 */

public class MapParentFragment extends BaseFragment implements OnMapReadyCallback, IRestCallBack, IRestCallPhoenix {

    public float currentZoom = 17;
    public static ReconnectingInternetDialog dialogInternetReconnecting;

    GoogleMap mMap;
    MapView mMapView;

    MapParentPresenter mMapPresenter;
    ArrayList<Marker> listMarker = new ArrayList<>();

    Marker busMarker;
    CircleImageView imgStudent;
    public String duration;

    GetKidsResp.Student kidBean = new GetKidsResp.Student();

    //    ImageView imgBar;
//    TextView labTitle;
//    ImageView imgHome;
    ImageView imgNotification;
//    public com.nostra13.universalimageloader.core.ImageLoader imageLoader;
//    DisplayImageOptions defaultOptions;
//    ImageLoaderConfiguration config;


    public String timeToArrive;
//    AppCompatImageView imgBack;
    //    LinearLayout mainBar;
    Handler handler;
    private ArrayList<StudentBean> listStudentBean = new ArrayList<>();
    LinearLayout llDone;

    public static Parent parent;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_map, container, false);
        currentZoom = 17;

//        if (MainFragmentActivity.SOCKET_API) {
//            try {
//                PhoenixPlug.getInstance().joinBus(MapParentFragment.this, kidBean.getBusID());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        handler = new Handler(Looper.getMainLooper());

//        mainBar = (LinearLayout) view.findViewById(R.id.mainBar);
        if (UtilityParent.getStringShared(UtilityParent.LANGUAGE).equals("ar")) {

//            mainBar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

        llDone = (LinearLayout) view.findViewById(R.id.llDone);
//        imgBar = (ImageView) view.findViewById(R.id.barImage);
//        imgBar.setImageResource(R.drawable.img_bus);
//
//        labTitle = (TextView) view.findViewById(R.id.labTitle);
//        labTitle.setText(getResources().getString(R.string.bus_location));
//
//        imgHome = (ImageView) view.findViewById(R.id.imgHome);
        imgNotification = (AppCompatImageView) view.findViewById(R.id.imgNotification);

//        imgHome.setVisibility(View.INVISIBLE);

        imgNotification.setImageResource(R.drawable.img_home);
        imgNotification.setVisibility(View.INVISIBLE);
        imgNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragmentActivity.showMyKidsFragment();
            }
        });
        view.findViewById(R.id.imgNotification_count).setVisibility(View.GONE);

        imgStudent = (CircleImageView) view.findViewById(R.id.imgStudentProfile);

        //====================================
//        setImageLoader();

        //===================================


        Bundle extras = getArguments();
        kidBean = (GetKidsResp.Student) extras.getParcelable("KIDBEAN");
        kidBean.getName_();
        Glide.with(getActivity()).load(kidBean.getAvatar()).apply(new RequestOptions().error(R.drawable.img_student).fitCenter()).into(imgStudent);
//        if (kidBean.getAvatar().equals("null")) {
//            imgStudent.setImageResource(R.drawable.img_student);
//        } else {
//            imageLoader.displayImage(kidBean.getAvatar(), imgStudent);
//        }

//        imgBack = (AppCompatImageView) view.findViewById(R.id.imgBack);
//
//        imgBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainFragmentActivity.showMyKidsFragment();
//            }
//        });


        mMapView = (MapView) view.findViewById(R.id.mapKidsLocation);

        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately
        try {
            MapsInitializer.initialize(MapParentFragment.this.getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }


        mMapView.getMapAsync(this);


        imgStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String studentName = kidBean.getName_();
                String driver_name = kidBean.getDriverName();
                String attendance_name = kidBean.getAssistantName();
                String active_round_now = kidBean.getRoundType();

                new KidsInfoDialog(MapParentFragment.this.getActivity(), studentName, driver_name, attendance_name, active_round_now).show();
            }
        });


        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        listMarker.clear();
        bussMarkerAdded = false;
        studMarkerAdded = false;
        kidsMarkerAdded = false;
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);


        mMapPresenter = new MapParentPresenter(MapParentFragment.this.getActivity(), this);
        mMapPresenter.callGetStudentList(kidBean.getRoundId());


        addKidsPoint(kidBean);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (listMarker != null && listMarker.size() > 0) {
//                    SetZoom(listMarker);
                }
            }
        }, 500);


        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                if (cameraPosition.zoom != currentZoom) {
                    currentZoom = cameraPosition.zoom;  // here you get zoom level
                }
            }
        });
        mMap.addMarker(new MarkerOptions().position(new LatLng(kidBean.getschoolLatD(), kidBean.getschoolLngD()))
                .icon(BitmapDescriptorFactory.fromBitmap(mMapPresenter.MarkerSchoolWithOptionsBitmap())));

    }


    BaseDialog dialog;
    int numDialogView = 0;


    @Override
    public void sendSocketMessage(String response, int usedId) {

    }

    @Override
    public void receivedSocketMessage(String response, int usedId) {

    }

    boolean bussMarkerAdded = false;
    boolean studMarkerAdded = false;
    boolean kidsMarkerAdded = false;
    Timer timerPhoenix;
    TimerTask timerTaskPhoenix;

    final Handler handlerPhoenix = new Handler();

    public void startTimerPhoenix() {
        //set a new Timer
        timerPhoenix = new Timer();

        //initialize the TimerTask's job
        try {
            timerOpenPhoenix();
        } catch (OutOfMemoryError outOfMemoryError) {

        }


        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timerPhoenix.schedule(timerTaskPhoenix, 2 * 1000, 5 * 1000); //
    }

    public void stopTimerPhoenix() {
        //stop the timer, if it's not already null
        if (timerPhoenix != null) {
            timerPhoenix.cancel();
            timerPhoenix = null;
        }
    }


    public void timerOpenPhoenix() {


        timerTaskPhoenix = new TimerTask() {
            public void run() {


                //use a handler to run a toast that shows the current timestamp
                handlerPhoenix.post(new Runnable() {
                    @Override
                    public void run() {
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {


                                callRestAPI(Constants.Urls.NOTIFY_URL + Constants.Urls.ROUND_STATUS + "?round_id=" + kidBean.getRoundId()+"&student_id="+kidBean.getId()
                                        ,
                                        new HashMap()
                                        ,
                                        EnumMethodApi.GET
                                        ,
                                        MapParentFragment.this
                                        ,
                                        EnumNameApi.ROUND_STATUS
                                        ,
                                        UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true)
                                        ,
                                        EnumTypeHeader.JSON
                                );
                                callRestAPI(Constants.Urls.IS_STUDENT_SERVED
                                        ,
                                        new HashMap() {{
                                            put("round_id", kidBean.getRoundId());
                                            put("student_id", kidBean.getId());
                                        }}
                                        ,
                                        EnumMethodApi.POST
                                        ,
                                        MapParentFragment.this
                                        ,
                                        EnumNameApi.IS_STUDENT_SERVED
                                        ,
                                        UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true)
                                        ,
                                        EnumTypeHeader.JSON
                                );


                                callRestAPI(Constants.Urls.NOTIFY_URL + Constants.Urls.BUS_LOCATION + "?bus_id=" + kidBean.getBusId()
                                        ,
                                        new HashMap()
                                        ,
                                        EnumMethodApi.GET
                                        ,
                                        MapParentFragment.this
                                        ,
                                        EnumNameApi.BUS_LOCATION
                                        ,
                                        UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true)
                                        ,
                                        EnumTypeHeader.JSON
                                );

                            }
                        });

                        thread.start();
                    }
                });


            }
        };
    }

    LatLng mainLatLng;

    //    @Override
    public void newLocation(final double lat, final double lng, final int usedId) {
        MapParentFragment.this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainLatLng = new LatLng(lat, lng);

                if (busMarker != null) {
                    busMarker.setPosition(mainLatLng);
//
//                    CameraPosition cameraPosition = new CameraPosition.Builder()
//                            .target(mainLatLng)      // Sets the center of the map to Mountain View
//                            .zoom(currentZoom)                   // Sets the zoom
////                                    .bearing(0)                // Sets the orientation of the camera to east
//                            .tilt(70)                   // Sets the tilt of the camera to 30 degrees
//                            .build();                   // Creates a CameraPosition from the builder
//                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                } else {
                    busMarker = mMap.addMarker(new MarkerOptions().position(mainLatLng).icon(BitmapDescriptorFactory.fromBitmap(mMapPresenter.MarkerWithOptionsBitmap())));
//

                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(mainLatLng)      // Sets the center of the map to Mountain View
                            .zoom(17)                   // Sets the zoom
//                                    .bearing(0)                // Sets the orientation of the camera to east
                            .tilt(70)                   // Sets the tilt of the camera to 30 degrees
                            .build();                   // Creates a CameraPosition from the builder
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
//                if (kidBean.getRoundType())
//                if (busMarker != null) {
//                    busMarker.setPosition(new LatLng(lat, lng));
//
//                } else {
//
//                    markerView = ((LayoutInflater) MapParentFragment.this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.map_bus_marker, null);
//
//                    busMarker = mMap.addMarker(new MarkerOptions()
//
//                            .position(new LatLng(lat, lng))
//                            .icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(MapParentFragment.this.getActivity(), markerView))
//
//                            ));
//                    if (!bussMarkerAdded) {
//                        listMarker.add(busMarker);
//                        bussMarkerAdded = true;
//                        SetZoom(listMarker);
//                    }
//
//
////                    LatLng latLng = new LatLng(kidBean.getLatitude(), kidBean.getLongitude());
////
////
////                    String PARMS = "origin=" + latLng.latitude + "," + latLng.longitude
////                            + "&destination=" + lat + "," + lng
////                            + "&language=" + getResources().getConfiguration().locale.getDefault().getLanguage()
////                            + "&sensor=false&units=metric&mode=" + "walking"
////                            + "&alternatives=true"//+"&key=AIzaSyBH8tFWNeJHVfHOlmR3s95gXg_oFc-c_hA";
////                            ;
////                    callRestAPI(PathUrl.GOOGLE_DISTANCE_TIME + PARMS
////                            ,
////                            new HashMap<String, Object>()
////                            ,
////                            EnumMethodApi.GET
////                            ,
////                            MapParentFragment.this
////                            ,
////                            EnumNameApi.GOOGLE_DISTANCE_TIME
////                            ,
////                            UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, false)
////                            ,
////                            EnumTypeHeader.JSON
////
////                    );
//
//
//                }
            }
        });

    }

    @Override
    public void errorConnect(String response) {

    }

    @Override
    public void ignoreConnect(String response) {

    }

    @Override
    public void closeConnect(String response) {

    }

    @Override
    public void socketConnected(int busId) {

    }

    @Override
    public void onReadyToPushMessage() {
//        PhoenixPlug.getInstance().receivedMessage(this);
    }


    public void setPoint(final ArrayList<StudentBean> listStudentBean, boolean value) {

        this.listStudentBean = listStudentBean;
        if (value) {
            for (int i = 0; i < listStudentBean.size(); i++) {
                StudentBean studentBean = listStudentBean.get(i);
                addPoint(studentBean);
            }
        }
        studMarkerAdded = true;
//        SetZoom(listMarker);

    }


    public void addKidsPoint(final GetKidsResp.Student kidBean) {
//        new LatLng(kidBean.getLatitude(), kidBean.getLongitude())


//
//        final String studentName_ = kidBean.getRouteOrder() + "";
////
//        final TextView labStudentNaame = (TextView) addKidsPointMarker.findViewById(R.id.labRoutNumberStudent);
//        final CircleImageView imgStudent = (CircleImageView) addKidsPointMarker.findViewById(R.id.imgMarkerStudent);
//         Glide.with(getActivity()).load(kidBean.getAvatar()).error(R.drawable.img_student).fitCenter().listener(new RequestListener<String, GlideDrawable>() {
//             @Override
//             public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//
//                 if (getActivity() != null) {
//                     labStudentNaame.setText(studentName_);
//                     Marker marker = mMap.addMarker(new MarkerOptions().position(latLong));
//                     marker.setIcon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getActivity(), addKidsPointMarker)));
//
//                     if (!kidsMarkerAdded) {
//                         listMarker.add(marker);
//                         kidsMarkerAdded = true;
//                         SetZoom(listMarker);
//                     }
//                 }
//
//
//                 return false;
//             }
//
//             @Override
//             public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                 if (getActivity() != null) {
//                     imgStudent.setImageDrawable(resource);
//                     labStudentNaame.setText(studentName_);
//                     Marker marker = mMap.addMarker(new MarkerOptions().position(latLong));
//                     marker.setIcon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getActivity(), addKidsPointMarker)));
//
//                     if (!kidsMarkerAdded) {
//                         listMarker.add(marker);
//                         kidsMarkerAdded = true;
//                         SetZoom(listMarker);
//                     }
//                 }
//                 return false;
//             }
//         }).into(imgStudent);
        try {
            if (getActivity() == null) {
                return;
            }


            View addKidsPointMarker = LayoutInflater.from(getMainActivity()).inflate(R.layout.map_student_marker, null, false);
//            addKidsPointMarker.setScaleX(R.integer.scale_ar_en);
//            final View addKidsPointMarker = ((LayoutInflater) getMainActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.map_student_marker, null);
            kidBean.loadImageInToImageView(getMainActivity(), addKidsPointMarker, new OnActionDoneListener<View>() {
                @Override
                public void OnActionDone(View kidPointMarker) {
                    Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(kidBean.gettargetLatD(), kidBean.gettargetLngD())));
                    marker.setIcon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getActivity(), kidPointMarker)));

                    if (!kidsMarkerAdded) {
                        listMarker.add(marker);
                        kidsMarkerAdded = true;
                        SetZoom(listMarker);
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void SetZoom(ArrayList<Marker> markers) {


//        Log.e(TAG, "Markers size: " + markers.size());
        if ((markers.size() == 0) && (!kidsMarkerAdded || !studMarkerAdded || !bussMarkerAdded)) {
            return;
        }
        if (markers.size() == 1) {
            Marker marker = markers.get(0);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15));
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        } else {
            LatLngBounds.Builder b = new LatLngBounds.Builder();
            for (Marker m : markers) {
                b.include(m.getPosition());
            }
            LatLngBounds bounds = b.build();
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 400, 400, 5);
            mMap.animateCamera(cu);
        }
        mMapView.onResume();

    }

    public void addPoint(final StudentBean studentBean) {

        if (getActivity() == null) {
            return;
        }
        if (studentBean.getId() == kidBean.getId()) {
            return;
        }
        final View addPointmarker = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.student_rounds_marker, null);
        final TextView labStudentOrder = (TextView) addPointmarker.findViewById(R.id.labStudentOrderNumber);

        labStudentOrder.setText(studentBean.getRouteOrder() + "");
        Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(studentBean.getLatitude(), studentBean.getLongitude())));
        marker.setIcon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getActivity(), addPointmarker)));
        if (!studMarkerAdded) {
            listMarker.add(marker);

        }
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
//        if (MainFragmentActivity.SOCKET_API) {
//            startTimerSpeed();
//        }else {
        startTimerPhoenix();
//        }


        try {
            mMapView.onResume();

            getView().setFocusableInTouchMode(true);
            getView().requestFocus();
            getView().setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                        MainFragmentActivity.showMyKidsFragment();
                        return true;
                    }
                    return false;
                }
            });
        } catch (Exception e) {

        }


    }

    @Override
    public void onPause() {

        handler.removeMessages(0);
        handler.removeMessages(1);
        mMapView.onPause();
        stopTimerPhoenix();
//        if (MainFragmentActivity.SOCKET_API) {
//            stopTimerSpeed();
//        }else {
        stopTimerPhoenix();
//        }
//        stopTimerSpeed();

        super.onPause();
    }

    @Override
    public void onDestroy() {
        try {
            if (mMapView != null) {
                mMapView.onDestroy();
            }

            super.onDestroy();
        } catch (Exception e) {


        }


    }

    @Override
    protected boolean showToolBarNotification() {
        return false;
    }

    @Override
    protected boolean showToolBack() {
        return true;
    }

    @Override
    protected boolean showToolBarSettings() {
        return false;
    }

    @Override
    protected String initToolBarTitle() {
        return getString(R.string.bus_location);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

//    Timer timerSpeed;
//    TimerTask timerTaskSpeed;

    final Handler handlerSpeed = new Handler();

//    public void startTimerSpeed() {
//        //set a new Timer
//        timerSpeed = new Timer();
//
//        //initialize the TimerTask's job
//
//        timerSpeed();
//
//        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
//        timerSpeed.schedule(timerTaskSpeed, 30 * 100, 40 * 100); //
//    }


    private float bearing(LatLng latLng1, LatLng latLng2) {
        double lat1 = latLng1.latitude;
        double lng1 = latLng1.longitude;
        double lat2 = latLng2.latitude;
        double lng2 = latLng2.longitude;

        double dLon = (lng2 - lng1);
        double y = Math.sin(dLon) * Math.cos(lat2);
        double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(dLon);
        double brng = Math.toDegrees((Math.atan2(y, x)));
        brng = (360 - ((brng + 360) % 360));
        return (float) brng;
    }

//    public void timerSpeed() {
//
//
//        timerTaskSpeed = new TimerTask() {
//            public void run() {
//
//                //use a handler to run a toast that shows the current timestamp
//                handlerSpeed.post(new Runnable() {
//                    public void run() {
//                        Thread thread = new Thread(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                try {
//
//
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
//                        try {
//                            thread.start();
//                        } catch (Exception | OutOfMemoryError e) {
//
//                        }
//
//                    }
//                });
//            }
//        };
//
//
//    }


//    public void stopTimerSpeed() {
//        //stop the timer, if it's not already null
//        if (timerSpeed != null) {
//            timerSpeed.cancel();
//            timerSpeed = null;
//        }
//    }


    public static Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }


//    public void setImageLoader() {
//
//        imageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
//        defaultOptions = new DisplayImageOptions.Builder()
//                .cacheOnDisc(true).cacheInMemory(true)
//                .imageScaleType(ImageScaleType.EXACTLY)
//                .displayer(new FadeInBitmapDisplayer(300)).build();
//
//        config = new ImageLoaderConfiguration.Builder(Application.getInstance().getApplicationContext())
//                .defaultDisplayImageOptions(defaultOptions)
//                .memoryCache(new WeakMemoryCache())
//                .discCacheSize(100 * 1024 * 1024).build();
//        imageLoader.init(config);
//
//
//    }

    @Override
    public void onRestCallBack(String response, EnumNameApi nameApiEnum) {


        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(response);
            JSONArray parentArray = jsonObj.getJSONArray("routes");
            final JSONArray legArray = parentArray.getJSONObject(0).getJSONArray("legs");

            //Distance
            JSONObject distanceObj = legArray.getJSONObject(0).getJSONObject("distance");
//            int distance = distanceObj.getInt("value"); //Value of distance
            String distance = distanceObj.getString("text");

            //duration

            JSONObject durationObj = legArray.optJSONObject(0).optJSONObject("duration");
//            int distance = distanceObj.getInt("value"); //Value of distance

            duration = distanceObj.optString("text","") + "/" + durationObj.optString("text","");
            /*labTimeArive.setText(duration + "/ " + distance);*/


            timeToArrive = duration + "/ " + distance;

            String[] parts = distance.split(" ");
            String part1 = parts[0]; // 004
//            String part2 = parts[1]; // 034556
            if (!UtilityParent.isEmptyString(part1))
                if (Double.parseDouble(part1) <= 7.0) {
                    if (dialog == null) {
                        /*  dialog = new SendDialog(getActivity(), EnumFragment.MAPS);*/
                    }
                    if (!dialog.isShowing()) {

                        if (numDialogView < 1) {
                            dialog.show();
                            numDialogView = 1;
                        }
                    }
                }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onRestCallBack(JSONObject response, EnumNameApi nameApiEnum) {

        try {
            if (nameApiEnum == EnumNameApi.BUS_LOCATION) {

                try {
                    if (dialogInternetReconnecting != null) {
                        dialogInternetReconnecting.dismiss();
                    }

                    newLocation(response.getDouble("lat"), response.getDouble("long"), 0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else if (nameApiEnum == EnumNameApi.ROUND_STATUS) {
                try {
                    JSONArray jaStatus = response.getJSONArray("students");
                    if (jaStatus.length() == 0) {
                        setPoint(listStudentBean, true);
                    }
                    for (int i = 0; i < jaStatus.length(); i++) {
                        JSONObject joStatus = jaStatus.getJSONObject(i);
                        int id = joStatus.getInt("id");

                        if (id == kidBean.getId()) {
                            mMap.clear();
                            mMap.addMarker(new MarkerOptions().position(new LatLng(kidBean.getschoolLatD(), kidBean.getschoolLngD()))
                                    .icon(BitmapDescriptorFactory.fromBitmap(mMapPresenter.MarkerSchoolWithOptionsBitmap())));
                            busMarker = mMap.addMarker(new MarkerOptions().position(mainLatLng).icon(BitmapDescriptorFactory.fromBitmap(mMapPresenter.MarkerWithOptionsBitmap())));
//

//                                    CameraPosition cameraPosition = new CameraPosition.Builder()
//                                            .target(mainLatLng)      // Sets the center of the map to Mountain View
//                                            .zoom(currentZoom)                   // Sets the zoom
////                                    .bearing(0)                // Sets the orientation of the camera to east
//                                            .tilt(70)                   // Sets the tilt of the camera to 30 degrees
//                                            .build();                   // Creates a CameraPosition from the builder
//                                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            setPoint(listStudentBean, true);
                        }
                        if (listStudentBean != null) {
                            for (int no = 0; no < listStudentBean.size(); no++) {
                                StudentBean studentBean = listStudentBean.get(no);
                                if (id == studentBean.getId()) {
                                    listStudentBean.remove(studentBean);
                                    mMap.clear();
                                    mMap.addMarker(new MarkerOptions().position(new LatLng(kidBean.getschoolLatD(), kidBean.getschoolLngD()))
                                            .icon(BitmapDescriptorFactory.fromBitmap(mMapPresenter.MarkerSchoolWithOptionsBitmap())));
                                    busMarker = mMap.addMarker(new MarkerOptions().position(mainLatLng).icon(BitmapDescriptorFactory.fromBitmap(mMapPresenter.MarkerWithOptionsBitmap())));
//

//                                    CameraPosition cameraPosition = new CameraPosition.Builder()
//                                            .target(mainLatLng)      // Sets the center of the map to Mountain View
//                                            .zoom(currentZoom)                   // Sets the zoom
////                                    .bearing(0)                // Sets the orientation of the camera to east
//                                            .tilt(70)                   // Sets the tilt of the camera to 30 degrees
//                                            .build();                   // Creates a CameraPosition from the builder
//                                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                                    setPoint(listStudentBean, true);
                                }
                            }
                        }
//                        String status = joStatus.getString("status");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else if (nameApiEnum == EnumNameApi.IS_STUDENT_SERVED) {
                System.err.println(response);
                boolean value = response.getBoolean("result");
                if (value) {
                    llDone.setVisibility(View.VISIBLE);
                    stopTimerPhoenix();
                    mMap.clear();
                }
            }
        } catch (IllegalArgumentException e) {

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRestCallBack(VolleyError volleyError, EnumNameApi nameApiEnum) {
      try {
        if (nameApiEnum == EnumNameApi.BUS_LOCATION) {
            if (dialogInternetReconnecting == null) {
                dialogInternetReconnecting = new ReconnectingInternetDialog(getMainActivity());
            }
            dialogInternetReconnecting.show();
        }
        }catch (Exception e){

        }
    }

    @Override
    public void onRestCallBack(NetworkResponse networkResponse, EnumNameApi nameApiEnum) {

    }

}


