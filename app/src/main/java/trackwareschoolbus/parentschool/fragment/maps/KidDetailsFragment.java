package trackwareschoolbus.parentschool.fragment.maps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import trackwareschoolbus.parentschool.API.enumApi.EnumMethodApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumTypeHeader;
import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallBack;
import trackwareschoolbus.parentschool.API_new.APIs.ApiController;
import trackwareschoolbus.parentschool.API_new.APIs.Constants;
import trackwareschoolbus.parentschool.API_new.APIs.ListenersAndInterFaces.OnApiComplete;
import trackwareschoolbus.parentschool.MainFragmentActivity;
import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.WebViewActivity;
import trackwareschoolbus.parentschool.api_v3.GetKidsResp;
import trackwareschoolbus.parentschool.api_v3.SharedPreferencesHelperV3;
import trackwareschoolbus.parentschool.basePage.BaseFragment;
import trackwareschoolbus.parentschool.bean.FeedbackRequest;
import trackwareschoolbus.parentschool.enums.EnumFragment;
import trackwareschoolbus.parentschool.fragment.dialog.AreYouSureDialog;
import trackwareschoolbus.parentschool.fragment.notification.senderNotification.SendNotificationGCM;
import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener;
import trackwareschoolbus.parentschool.toolsV2.DialogsTools;
import trackwareschoolbus.parentschool.toolsV2.DrawableToolsV2;
import trackwareschoolbus.parentschool.toolsV2.StringToolsV2;
import trackwareschoolbus.parentschool.toolsV2.ViewToolsV2;
import trackwareschoolbus.parentschool.utilityParent.DateTools;
import trackwareschoolbus.parentschool.utilityParent.StaticValue;
import trackwareschoolbus.parentschool.utilityParent.UtilDialogs;
import trackwareschoolbus.parentschool.utilityParent.UtilLocation;
import trackwareschoolbus.parentschool.utilityParent.UtilityParent;


public class KidDetailsFragment extends BaseFragment /*, IRestCallBack,IRestCallPhoenix*/ {


    private View rootView, pickup_view,
            absent_view, call_view, qr_view, no_notification_text, material_progress_bar, student_img_container, feed_view, photo_camera_img, change_location_view;
    //    private MapView mMapView;
//    private GoogleMap googleMap;
    private AppCompatImageView imgNotification, imgBack, student_img;
    private GetKidsResp.Student kidObjectAfterFilter;
    private TextView student_name_txt, school_name_txt, student_grade_txt;
    //    private RecyclerView rcNotification;
    //    private TextView ended_round_txt, no_active_rounds_txt;
    private TextView labSchoolName, labActiveRound, labTimeOTArrive;
    private LinearLayout kid_details_actions_view_root;
//    private View horizontal_progressbar, more_btn;
//    private SwipeRefreshLayout swipe_refresh_layout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.kid_details_fragment, container, false);
        /**/
        kidObjectAfterFilter = (GetKidsResp.Student) getArguments().getParcelable("KIDBEAN");
        findViews();
        initClickListeners();
        refreshContent(savedInstanceState);

//        getMainActivity().registerRefreshReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                refreshContent(savedInstanceState);
//            }
//        });
//        ((TextView) rootView.findViewById(R.id.labTitle)).setText(getResources().getString(R.string.kid_details));

        kid_details_actions_view_root = rootView.findViewById(R.id.kid_details_actions_view_root);
//        checkLocalFeaturesLis();
        checkFeaturesLis();

        if (kidObjectAfterFilter.getShow_absence()){
            absent_view.setVisibility(View.VISIBLE);
        }else {
            absent_view.setVisibility(View.GONE);
        }
        return rootView;

    }

//    private void checkLocalFeaturesLis() {
//
//        View call_view = ViewToolsV2.inflaterView(kid_details_actions_view_root.getContext(), R.layout.features_view);
//
//
//        for (int i = 0; i < features.size(); i++) {
//            ImageView feature_icon = features_view.findViewById(R.id.feature_icon);
//            TextView feature_text = features_view.findViewById(R.id.feature_text);
//            feature_text.setText(features.get(i).getName());
//            /////
//            GetKidsResp.Student.FeaturesListItem featureItem = features.get(i);
//
//            if (!featureItem.getIcon().isEmpty()) {
//                Glide.with(getActivity()).load(featureItem.getIcon()).apply(new RequestOptions().error(R.drawable.school_outline).fitCenter()).into(feature_icon);
//            }
//            features_view.setOnClickListener(v -> {
//
//                Intent intent = new Intent(mActivity, WebViewActivity.class);
//                intent.putExtra("KIDBEAN", kidObjectAfterFilter);
//                intent.putExtra("SELECTED_FEATURE", featureItem);
//                startActivity(intent);
//            });
//            kid_details_actions_view_root.addView(features_view);
//
//        }
//
//    }

    private void checkFeaturesLis() {

        List<GetKidsResp.Student.FeaturesListItem> features = kidObjectAfterFilter.getFeatures();
        for (int i = 0; i < features.size(); i++) {
            View features_view = ViewToolsV2.inflaterView(kid_details_actions_view_root.getContext(), R.layout.features_view);
            ImageView feature_icon = features_view.findViewById(R.id.feature_icon);
            TextView feature_text = features_view.findViewById(R.id.feature_text);
            if (SharedPreferencesHelperV3.INSTANCE.getCurrentAppLanguage().equals("en")){
                feature_text.setText(features.get(i).getName());
            }else {

                feature_text.setText(features.get(i).getNameAr());
            }

            /////
            GetKidsResp.Student.FeaturesListItem featureItem = features.get(i);

//            if (featureItem.getIcon().contains("svg") || featureItem.getIcon().contains("SVG")|| featureItem.getIcon().contains("Svg")) {
//                GlideToVectorYou.justLoadImage(KidDetailsFragment.this.requireActivity(),new Uri.Builder().path(featureItem.getIcon()).build(), feature_icon);
//
//            }else {
            Glide.with(KidDetailsFragment.this).load(featureItem.getIcon()).apply(new RequestOptions().error(R.drawable.school_outline)).into(feature_icon);

//            }
            features_view.setOnClickListener(v -> {

                Intent intent = new Intent(getMainActivity(), WebViewActivity.class);
                intent.putExtra("KIDBEAN", kidObjectAfterFilter);
                intent.putExtra("SELECTED_FEATURE", featureItem);
                intent.putExtra("PAGE_TITLE", feature_text.getText().toString());
                intent.putExtra("PAGE_ICON_URL", featureItem.getIcon());
                startActivity(intent);


                ///////////


//                CustomTabsIntent.Builder customTabsIntentBuilder = new CustomTabsIntent.Builder();
//                customTabsIntentBuilder.setShowTitle(false);
//                customTabsIntentBuilder.addMenuItem("",null);
//                customTabsIntentBuilder.setUrlBarHidingEnabled(true);
//                CustomTabsIntent customTabsIntent = customTabsIntentBuilder.build();
//
//                Bundle headers = new Bundle();
//
//                headers.putString("X-Openerp-Session-Id", kidObjectAfterFilter.getSessionId());
//                customTabsIntent.intent.putExtra(Browser.EXTRA_HEADERS, headers);
//                customTabsIntent.launchUrl(this.getMainActivity(), Uri.parse(featureItem.getUrlWithLangAndDB(kidObjectAfterFilter.getSchoolsListItem().getDbName())));


                ///////////////////////////


//                String PACKAGE_NAME = "com.android.chrome";
//                Bitmap imgBackIcon = BitmapFactory.decodeResource(getMainActivity().getResources(),
//                        R.drawable.img_back);
//
//
//                Uri uri = Uri.parse(featureItem.getUrlWithLangAndDB(kidObjectAfterFilter.getSchoolsListItem().getDbName()));
//                CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
//                intentBuilder.setShowTitle(false);
//                intentBuilder.setUrlBarHidingEnabled(true);
//                CustomTabColorSchemeParams params = new CustomTabColorSchemeParams.Builder()
//                        .setNavigationBarColor(ContextCompat.getColor(getMainActivity(), R.color.white))
//                        .setToolbarColor(ContextCompat.getColor(getMainActivity(), R.color.white))
//                        .setSecondaryToolbarColor(ContextCompat.getColor(getMainActivity(), R.color.white))
//                        .build();
//                intentBuilder.setColorSchemeParams(CustomTabsIntent.COLOR_SCHEME_DARK, params);
//
//                intentBuilder.setCloseButtonIcon(imgBackIcon);
//                intentBuilder.setStartAnimations(getMainActivity(), R.anim.fade_in, R.anim.fade_out);
//                intentBuilder.setExitAnimations(getMainActivity(), android.R.anim.slide_in_left, android.R.anim.slide_out_right);
//                CustomTabsIntent customTabsIntent = intentBuilder.build();
//                customTabsIntent.intent.setPackage(PACKAGE_NAME);
//
////                customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//
//                customTabsIntent.launchUrl(getMainActivity(), uri);


                //////////////////
//                CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
////                        .addMenuItem("", null)
//                        .setToolbarColor(this.getResources().getColor(R.color.white, getMainActivity().getTheme()))
//                        .setShowTitle(false)
//                        .enableUrlBarHiding()
//                        .setCloseButtonIcon(imgBackIcon)
//                        .build();
////                customTabsIntent.intent.setPackage(PACKAGE_NAME);
//
//// This is optional but recommended
//                CustomTabsHelper.Companion.addKeepAliveExtra(getMainActivity(), customTabsIntent.intent);
//                Bundle headers = new Bundle();
////                headers.putString("X-Openerp-Session-Id", kidObjectAfterFilter.getSessionId());
//                headers.putString("session_id", kidObjectAfterFilter.getSessionId());
//                customTabsIntent.intent.putExtra(Browser.EXTRA_HEADERS, headers);
//// This is where the magic happens...
//                CustomTabsHelper.Companion.openCustomTab(getMainActivity(), customTabsIntent,
//                        Uri.parse(featureItem.getUrlWithLangAndDB(kidObjectAfterFilter.getSchoolsListItem().getDbName())),
//                        new WebViewFallback());


            });
            kid_details_actions_view_root.addView(features_view);

        }

    }

    private void refreshContent(Bundle savedInstanceState) {
        initStudentObject();
//        initToolBarButtons();
//        initMap(savedInstanceState);
        callKidsAPI();
//        getFirstTwoKidNotifications();

    }


    private void findViews() {
//        mMapView = rootView.findViewById(R.id.mapKidsLocation);
        imgNotification = rootView.findViewById(R.id.imgNotification);
        imgBack = rootView.findViewById(R.id.imgBack);
//        no_active_rounds_txt = rootView.findViewById(R.id.no_active_rounds_txt);
        pickup_view = rootView.findViewById(R.id.pickup_view);
        absent_view = rootView.findViewById(R.id.absent_view);
        call_view = rootView.findViewById(R.id.call_view);
        qr_view = rootView.findViewById(R.id.qr_view);
        student_name_txt = rootView.findViewById(R.id.student_name_txt);
        school_name_txt = rootView.findViewById(R.id.school_name_txt);
        student_grade_txt = rootView.findViewById(R.id.student_grade_txt);
        student_img = rootView.findViewById(R.id.student_img);
//        rcNotification = rootView.findViewById(R.id.rcNotification);
        no_notification_text = rootView.findViewById(R.id.no_notification_text);
        material_progress_bar = rootView.findViewById(R.id.material_progress_bar);
        change_location_view = rootView.findViewById(R.id.change_location_view);
//        ended_round_txt = rootView.findViewById(R.id.ended_round_txt);
//        no_active_rounds_txt = rootView.findViewById(R.id.no_active_rounds_txt);

        labSchoolName = rootView.findViewById(R.id.labSchoolName);
        labActiveRound = rootView.findViewById(R.id.labActiveRound);
        labTimeOTArrive = rootView.findViewById(R.id.labTimeOTArrive);
        student_img_container = rootView.findViewById(R.id.student_img_container);
//        horizontal_progressbar = rootView.findViewById(R.id.horizontal_progressbar);
//        swipe_refresh_layout = rootView.findViewById(R.id.swipe_refresh_layout);
        feed_view = rootView.findViewById(R.id.feed_view);
//        school_web_view_contain = rootView.findViewById(R.id.school_web_view_contain);
        photo_camera_img = rootView.findViewById(R.id.photo_camera_img);
//        more_btn = rootView.findViewById(R.id.more_btn);
//        llDone= rootView.findViewById(R.id.llDone);
    }


    private void initClickListeners() {

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {


                    case R.id.change_location_view:
                        if (!UtilLocation.islocationAvailable(getMainActivity())) {
                            UtilDialogs.showNoGPSDialog(getMainActivity());
                        } else
                            new AreYouSureDialog(getMainActivity(), kidObjectAfterFilter).show();
                        break;

                    case R.id.feed_view:
                        showFeedDialog();
                        break;

                    case R.id.pickup_view:
                        showCheckDistance(new OnActionDoneListener<Boolean>() {
                            @Override
                            public void OnActionDone(Boolean aBoolean) {
//                                if (aBoolean) {
                                    showPinAndDatePickerDialogForPickup();
//                                }

                            }
                        });

                        break;
                    case R.id.absent_view:
//                new ConfermPinDialog(getMainActivity(), EnumFragment.MYKIDS, kidObjectAfterFilter, kidObjectAfterFilter.getName()).show();
                        showPinAndDatePickerDialogForAbsent();

//                checkPin(new OnActionDoneListener<Boolean>() {
//                    @Override
//                    public void OnActionDone(Boolean aBoolean) {
//                        if (!aBoolean)
//                            return;
//
//
//                    }
//                });
                        break;
                    case R.id.call_view:
                        showCallDialog();
                        break;
                    case R.id.qr_view:
                        checkCameraBeforeQR(kidObjectAfterFilter);
                        break;


                    case R.id.student_img_container:
                        getMainActivity().openGalary(new OnActionDoneListener<Uri>() {
                            @Override
                            public void OnActionDone(Uri uri) {
                                callUploadStudentImage(uri);
                            }
                        });
                        break;

                    case R.id.more_btn:
                        getMainActivity().showNotificationFragment();
                        break;


                }
            }
        };
        pickup_view.setOnClickListener(onClickListener);
        absent_view.setOnClickListener(onClickListener);
        call_view.setOnClickListener(onClickListener);
        qr_view.setOnClickListener(onClickListener);
        feed_view.setOnClickListener(onClickListener);
        student_img_container.setOnClickListener(onClickListener);
//        more_btn.setOnClickListener(onClickListener);
        change_location_view.setOnClickListener(onClickListener);

//        school_web_view_contain.setOnClickListener(v -> {
//            Intent intent = new Intent(mActivity, WebViewActivity.class);
//            intent.putExtra("KIDBEAN", kidObjectAfterFilter);
//            startActivity(intent);
//        });

//        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                refreshContent(null);
//                if (swipe_refresh_layout.isRefreshing()) {
//                    swipe_refresh_layout.setRefreshing(false);
//                }
//            }
//        });
    }

    private void initStudentObject() {
        student_name_txt.setText(kidObjectAfterFilter.getName_());
        school_name_txt.setText(kidObjectAfterFilter.getSchoolName());
        student_grade_txt.setText(kidObjectAfterFilter.getStudentGrade());
        DrawableToolsV2.loadDrawable(student_img, kidObjectAfterFilter.getAvatar(), DrawableToolsV2.GLIDE_SCALE_CIRCLE_CROP);
        labSchoolName.setText(kidObjectAfterFilter.getSchoolName());
        labActiveRound.setText("....");
        labTimeOTArrive.setText("....");
        /**/
        photo_camera_img.setVisibility(kidObjectAfterFilter.getAllowUploadStudentsImages() ? View.VISIBLE : View.INVISIBLE);
        student_img_container.setClickable(kidObjectAfterFilter.getAllowUploadStudentsImages());
        /**/
        boolean pickupParent = kidObjectAfterFilter.getPickupByParent();
        boolean dropOffParent = kidObjectAfterFilter.getDropOffByParent();
        boolean canChangeLocation = kidObjectAfterFilter.getChangeLocation();
        ;


        if (canChangeLocation) {
            if (pickupParent && dropOffParent) {
                change_location_view.setVisibility(View.GONE);
            } else {
                change_location_view.setVisibility(View.VISIBLE);
            }
        } else {
            change_location_view.setVisibility(View.GONE);

        }

//        pickupParent = true;
//                dropOffParent= true;

//        if (pickupParent || dropOffParent) {// pickup Visibility
            pickup_view.setVisibility(View.VISIBLE);
//        } else {
//            pickup_view.setVisibility(View.GONE);
//        }


        if (kidObjectAfterFilter.getShowAddBusCard()) {
            qr_view.setVisibility(View.VISIBLE);
        } else {
            qr_view.setVisibility(View.GONE);

        }

    }

//    private void initToolBarButtons() {
//        ((TextView) rootView.findViewById(R.id.labTitle)).setText(getResources().getString(R.string.bus_location));
//        imgBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainFragmentActivity.showMyKidsFragment();
//            }
//        });
//
//
//        DrawableToolsV2.loadDrawable(rootView.findViewById(R.id.imgHome), R.drawable.setting).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainFragmentActivity.showSettingFragment();
//
//
//            }
//        });
//
//        DrawableToolsV2.loadDrawable(rootView.findViewById(R.id.imgNotification), R.drawable.new_ic_notifcation).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainFragmentActivity.showNotificationFragment();
//            }
//        });
//
//
//    }

//    private void initMap(Bundle savedInstanceState) {
//        try {
////            if (kidObjectAfterFilter.isKidActiveNowInRound) {
//            /**/
////                no_active_rounds_txt.setVisibility(View.GONE);
//            mMapView.setVisibility(View.VISIBLE);
//            /**/
//            mMapView.onCreate(savedInstanceState);
//            mMapView.onResume();
//            MapsInitializer.initialize(getActivity());
//            mMapView.getMapAsync(new OnMapReadyCallback() {
//                @Override
//                public void onMapReady(GoogleMap gm) {
//                    googleMap = gm;
//                    googleMap.getUiSettings().setMapToolbarEnabled(true);
//                    googleMap.getUiSettings().setZoomControlsEnabled(true);
//                    googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
//                        @Override
//                        public void onCameraMove() {
////                                googleMap.getCameraPosition().zoom
//                        }
//                    });
//                }
//            });
////            } else {
////                no_active_rounds_txt.setVisibility(View.VISIBLE);
////                mMapView.setVisibility(View.GONE);
////            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }


    private void showKidsInfoDialog() {
//        String studentName= kidObjectAfterFilter.kidFullname();
//        String driver_name=kidObjectAfterFilter.driverName();
//        String attendance_name=kidObjectAfterFilter.assistantName();
//        String active_round_now=kidObjectAfterFilter.();


//        new KidsInfoDialog(getActivity(), studentName, driver_name,attendance_name, active_round_now).show();}
    }


//    public void getFirstTwoKidNotifications() {
//        showNotificationsLoading();
//        ApiController.getKidsHistory(getMainActivity(), null, null, kidObjectAfterFilter.getId(), new OnApiComplete<JsonObject>() {
//            @Override
//            public void onSuccess(JsonObject response) {
//                try {
//                    JsonArray notificationsJsonArray = response.getAsJsonArray("notifications");
//
////                    JSONArray ja = response.getJSONArray("notifications");
//
////                    ArrayList<NotificationBean> listNotificationBean = MyGson.getGson().fromJson(response.getJSONArray("notifications").toString(), new TypeToken<ArrayList<NotificationBean>>() {
////                    }.getType());
//
//
//                    ArrayList<NotificationBean> notificationBeanList = MyGson.getGson().fromJson(notificationsJsonArray, new TypeToken<ArrayList<NotificationBean>>() {
//                    }.getType());
//
//                    if (notificationBeanList.size() == 0) {
//                        showNoNotificationText();
//                    } else {
//                        List<NotificationBean> temp = new ArrayList<>();
//                        temp.add(notificationBeanList.get(0));
//
//                        if (notificationBeanList.size() >= 2)
//                            temp.add(notificationBeanList.get(1));
//
//                        showNoNotificationText();
//                        notificationBeanList.clear();
//                        notificationBeanList.addAll(temp);
//                        initNotificationAdapter(notificationBeanList);
//                        showNotificationsList();
//
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    showNoNotificationText();
//                }
//
//            }
//
//            @Override
//            public void onError(int errorCode, String errorMessage) {
//                showNoNotificationText();
//            }
//        });
//    }

//    private void initNotificationAdapter(ArrayList<NotificationBean> notificationBeanList) {
//        BaseRecyclerViewAdapter_V2 notificationAdapter = new BaseRecyclerViewAdapter_V2<NotificationBean, NotificationHolderForKidsDetails>(rcNotification, BaseRecyclerViewAdapter_V2.RecyclerViewType.Linear) {
//            @Override
//            public NotificationHolderForKidsDetails cViewHolder(ViewGroup viewGroup, int i, LayoutInflater layoutInflater) {
//                return new NotificationHolderForKidsDetails(layoutInflater.inflate(R.layout.list_notification_for_kids_details, viewGroup, false));
//            }
//
//            @Override
//            public void bViewHolder(NotificationHolderForKidsDetails holder, int position, NotificationBean notificationBean) {
//
////        String coloredStudentName = "<font color='#B01500'>" + notificationBean.getStudentName() + "</font>";
////        Spanned spanned = Html.fromHtml(notificationBean.getTitleAndText());
////                if (position == 0) {
////                    holder.top_line.setVisibility(View.VISIBLE);
////                    holder.bottom_line.setVisibility(View.GONE);
////                } else if (position == 1) {
////                    holder.top_line.setVisibility(View.VISIBLE);
////                    holder.bottom_line.setVisibility(View.GONE);
////                } else {
////                    holder.top_line.setVisibility(View.GONE);
////                    holder.bottom_line.setVisibility(View.GONE);
////                }
//
//
//                holder.labStudentName.setText(notificationBean.getTitleAndText());
//                Glide.with(holder.imgStudent.getContext()).load(notificationBean.getAvatar()).apply(new RequestOptions().fitCenter().error(R.drawable.notification_icon_msg_emergency)).into(holder.imgStudent);
//
//                try {
//                    long dateLong = DateTools.Formats.DATE_FORMAT_GMT.parse(notificationBean.getDateTime()).getTime();
//
//                    holder.labStudentTime.setText("");
//                    if (DateUtils.isToday(dateLong)) {
//                        holder.labStudentTime.append(getMainActivity().getString(R.string.today));
//                        holder.labStudentTime.append("\n");
//                        holder.labStudentTime.append(getMainActivity().getString(R.string.at_time));
//                        holder.labStudentTime.append(" ");
//                        holder.labStudentTime.append(DateTools.Formats.TIMEONLY_FORMAT_12H_LOCAL.format(dateLong));
//                    } else {
//                        holder.labStudentTime.append(DateTools.Formats.DATEONLY_FORMAT_LOCAL.format(dateLong));
//                        holder.labStudentTime.append("\n");
//                        holder.labStudentTime.append(mActivity.getString(R.string.at_time));
//                        holder.labStudentTime.append(" ");
//                        holder.labStudentTime.append(DateTools.Formats.TIMEONLY_FORMAT_12H_LOCAL.format(dateLong));
//                    }
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                    holder.labStudentTime.setVisibility(View.GONE);
//                }
//
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                Spanned spanned = Html.fromHtml(notificationBean.getTitleAndTextForDialog());
//                        UtilDialogs.showGeneralMessageDialog(getMainActivity()).setDialogeTitle(notificationBean.getNotificationsTitle()).setDialogeMessage(notificationBean.getNotificationsText()).loadImageFromURL(notificationBean.getAvatar());
//                    }
//                });
//
//
//
//                /**/
//
//            }
//        }.withFadeAnimation();
//
//        notificationAdapter.addAll(notificationBeanList);
//    }

//    private void showNoNotificationText() {
//        no_notification_text.setVisibility(View.VISIBLE);
////        rcNotification.setVisibility(View.GONE);
//        material_progress_bar.setVisibility(View.GONE);
//        more_btn.setVisibility(View.INVISIBLE);
//    }
//
//    private void showNotificationsLoading() {
//        no_notification_text.setVisibility(View.GONE);
//        rcNotification.setVisibility(View.GONE);
//        material_progress_bar.setVisibility(View.VISIBLE);
//        more_btn.setVisibility(View.INVISIBLE);
//
//    }

//    private void showNotificationsList() {
//        no_notification_text.setVisibility(View.GONE);
//        rcNotification.setVisibility(View.VISIBLE);
//        material_progress_bar.setVisibility(View.GONE);
//        more_btn.setVisibility(View.VISIBLE);
//
//    }

    public void checkCameraBeforeQR(GetKidsResp.Student kidBean) {
        getMainActivity().cameraPermission(getMainActivity(), new OnActionDoneListener<Boolean>() {
            @Override
            public void OnActionDone(Boolean permissionsAccepted) {
                if (permissionsAccepted) {
                    new AreYouSureDialog(getMainActivity(), EnumFragment.QR_CODE, kidBean.getId()).show();
                }

            }
        });
    }


    public void showCheckDistance(OnActionDoneListener<Boolean> afterCheckDistance) {
        getMainActivity().locationPermission(getMainActivity(), new OnActionDoneListener<Boolean>() {
            @Override
            public void OnActionDone(Boolean permissionsAccepted) {
                if (permissionsAccepted) {
                    if (!UtilLocation.islocationAvailable(getMainActivity())) {
                        UtilDialogs.showNoGPSDialog(getMainActivity());
                        return;
                    }
                    boolean distance_ok = false;
                    if (kidObjectAfterFilter.getPickupRequestDistance() < 0) {
                        distance_ok = true;
                    } else {
                        double distance = UtilityParent.distance(StaticValue.latitudeMain, StaticValue.longitudeMain, Double.valueOf(kidObjectAfterFilter.getschoolLatD()), Double.valueOf(kidObjectAfterFilter.getschoolLngD()), "M");
                        distance_ok = (distance <= kidObjectAfterFilter.getPickupRequestDistance());
                    }
                    if (!distance_ok) {
                        UtilDialogs.showGeneralMessageDialog(getMainActivity()).setDialogeMessage(getMainActivity().getString(R.string.allow_distance).replace("___", kidObjectAfterFilter.getPickupRequestDistance() + ""));
                        if (StaticValue.progressDialog != null) {
                            StaticValue.progressDialog.dismiss();
                        }
                        afterCheckDistance.OnActionDone(false);
                        return;
                    } else {
                        afterCheckDistance.OnActionDone(true);
                        return;
                    }
                }
            }
        });
    }

    private void callUploadStudentImage(Uri uri) {

        String base64StringImage = DrawableToolsV2.UriToBase64String(getMainActivity(), uri);
        if (base64StringImage == null) {
            UtilDialogs.showGeneralErrorDialog(getMainActivity()).setDialogeMessage(R.string.api_send_error);
            return;
        }
        showLoadingDialog(true);

        ApiController.uploadStudentImage(getMainActivity(), kidObjectAfterFilter.getId(), kidObjectAfterFilter.getSchoolId(), base64StringImage, new OnApiComplete<JsonObject>() {
            @Override
            public void onSuccess(JsonObject o) {

                Glide.with(student_img)
                        .load(uri)
                        .apply(RequestOptions.circleCropTransform())
                        .into(student_img);
                showLoadingDialog(false);
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                showLoadingDialog(false);
                UtilDialogs.showGeneralErrorDialog(getMainActivity()).setDialogeMessage(R.string.api_send_error);
            }
        });


    }


    private void callPickUpAPI() {
        try {
            if (StaticValue.progressDialog != null) {
                StaticValue.progressDialog.show();
            }
        }catch (Exception ignored){}

        callRestAPI(Constants.Urls.PRE_ARRIVE,
                new HashMap() {{
                    put("school_id", kidObjectAfterFilter.getSchoolId());
                    put("student_id", kidObjectAfterFilter.getId());
                    put("locale", UtilityParent.getStringShared(UtilityParent.LANGUAGE));
                }},
                EnumMethodApi.POST,
                new IRestCallBack() {
                    @Override
                    public void onRestCallBack(String response, EnumNameApi nameApiEnum) {
                        if (StaticValue.progressDialog != null) {
                            StaticValue.progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onRestCallBack(JSONObject response, EnumNameApi nameApiEnum) {
                        if (StaticValue.progressDialog != null) {
                            StaticValue.progressDialog.dismiss();
                        }
                        if (!UtilityParent.isEmptyString(response + "")) {
                            try {
                                String value = response.getString("status");
                                UtilDialogs.showNoImageMessage(getMainActivity()).setDialogeTitle(value);
                            } catch (JSONException e) {
                                e.printStackTrace();
//                                UtilDialogs.showGeneralErrorDialog(getMainActivity());
                            }
                        }
//                        if (StaticValue.progressDialog != null) {
//                            StaticValue.progressDialog.dismiss();
//                        }


                    }

                    @Override
                    public void onRestCallBack(VolleyError volleyError, EnumNameApi nameApiEnum) {
                        if (StaticValue.progressDialog != null) {
                            StaticValue.progressDialog.dismiss();
                        }
//                        UtilDialogs.showGeneralErrorDialog(getMainActivity()).setDialogeMessage(R.string.api_send_error);
                    }

                    @Override
                    public void onRestCallBack(NetworkResponse networkResponse, EnumNameApi nameApiEnum) {
                        if (StaticValue.progressDialog != null) {
                            StaticValue.progressDialog.dismiss();
                        }
                    }
                },
                EnumNameApi.PRE_ARRIVE,
                UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true),
                EnumTypeHeader.JSON
        );
    }
//    private void showPickUp(String  school_Id,String kid_id) {
//        getMainActivity().locationPermission(getMainActivity(), new OnActionDoneListener<Boolean>() {
//            @Override
//            public void OnActionDone(Boolean permissionsAccepted) {
//                if (permissionsAccepted) {
//                    if (!UtilLocation.islocationAvailable(getMainActivity())) {
//                        UtilDialogs.showNoGPSDialog(getMainActivity());
//                        return;
//                    }
//
//
////                    new ConfermPinDialog(getMainActivity(), new OnActionDoneListener<ConfermPinDialog>() {
////                        @Override
////                        public void OnActionDone(ConfermPinDialog action) {
//                            AlertDialog.Builder builder = new AlertDialog.Builder(getMainActivity());
//                            builder.setTitle(getMainActivity().getString(R.string.pic_up_list));
//                            builder.setMessage(getMainActivity().getString(R.string.are_you_sure_pickup));
//                            builder.setPositiveButton(getMainActivity().getString(R.string.yes), new DialogInterface.OnClickListener() {
//                                @SuppressLint("MissingPermission")
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    try {
//                                        if (StaticValue.progressDialog != null) {
//                                            StaticValue.progressDialog.show();
//                                        }
//                                    } catch (Exception e) {
//
//                                    }
//
//
//                                    double distance = UtilityParent.distance(StaticValue.latitudeMain, StaticValue.longitudeMain, kidObjectAfterFilter.getschoolLatD(), kidObjectAfterFilter.getschoolLngD(), "M");
//
//
//                                    if (distance >= 500) {
//
////                                    UtilityParent.showMessageDialog(getMainActivity(), getMainActivity().getString(R.string.failed), getMainActivity().getString(R.string.allow_distance));
//                                        UtilDialogs.showGeneralErrorDialog(getMainActivity()).setDialogeMessage(R.string.allow_distance);
//                                        if (StaticValue.progressDialog != null) {
//                                            StaticValue.progressDialog.dismiss();
//                                        }
//                                        return;
//                                    }
//
//                                    callRestAPI(Constants.Urls.PRE_ARRIVE,
//                                            new HashMap() {{
//                                                put("school_id", school_Id);
//                                                put("student_id", kid_id);
//                                                put("locale", UtilityParent.getStringShared(UtilityParent.LANGUAGE));
//                                            }},
//                                            EnumMethodApi.POST,
//                                            new IRestCallBack() {
//                                                @Override
//                                                public void onRestCallBack(String response, EnumNameApi nameApiEnum) {
//                                                }
//
//                                                @Override
//                                                public void onRestCallBack(JSONObject response, EnumNameApi nameApiEnum) {
//                                                    if (!UtilityParent.isEmptyString(response + "")) {
//                                                        try {
//                                                            String value = response.getString("status");
//                                                            UtilDialogs.showNoImageMessage(getMainActivity()).setDialogeTitle(value);
//                                                        } catch (JSONException e) {
//                                                            e.printStackTrace();
//                                                            UtilDialogs.showGeneralErrorDialog(getMainActivity());
//                                                        }
//                                                    }
//                                                    if (StaticValue.progressDialog != null) {
//                                                        StaticValue.progressDialog.dismiss();
//                                                    }
//
//
//                                                }
//
//                                                @Override
//                                                public void onRestCallBack(VolleyError volleyError, EnumNameApi nameApiEnum) {
//                                                    UtilDialogs.showGeneralErrorDialog(getMainActivity()).setDialogeMessage(R.string.api_send_error);
//                                                }
//
//                                                @Override
//                                                public void onRestCallBack(NetworkResponse networkResponse, EnumNameApi nameApiEnum) {
//
//                                                }
//                                            },
//                                            EnumNameApi.PRE_ARRIVE,
//                                            UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true),
//                                            EnumTypeHeader.JSON
//                                    );
//
//
//                                }
//                            });
//                            builder.setNegativeButton(getMainActivity().getString(R.string.no), new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                    if (StaticValue.progressDialog != null) {
//                                        StaticValue.progressDialog.dismiss();
//                                    }
//                                }
//                            });
//                            Dialog alertDialog = builder.create();
//                            alertDialog.setCanceledOnTouchOutside(false);
//                            alertDialog.show();
////                        }
////                    }).show();
//
//
//                }
//            }
//        });
//
//
//    }

    private void showPinAndDatePickerDialogForPickup() {
        checkPin(new OnActionDoneListener<Boolean>() {
            @Override
            public void OnActionDone(Boolean aBoolean) {
                try {
                    if (aBoolean) {
                        callPickUpAPI();
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
        });
    }

    private void showPinAndDatePickerDialogForAbsent() {
        checkPin(new OnActionDoneListener<Boolean>() {
            @Override
            public void OnActionDone(Boolean aBoolean) {
                try {
                    if (aBoolean) {
                        DialogsTools.showDatePickerDialog(getMainActivity(), new OnActionDoneListener<Integer>() {
                            @Override
                            public void OnActionDone(final Integer noDays) {


                                new UtilDialogs.MultichoicesDialog().show(getMainActivity()).setYesButtonClickListener(new OnActionDoneListener<Integer>() {
                                    @Override
                                    public void OnActionDone(Integer selectedChoice) {
                                        if (StaticValue.progressDialog != null) {
                                            StaticValue.progressDialog.show();
                                        }
                                        final String target_rounds;
                                        if (selectedChoice == 1)
                                            target_rounds = "both";
                                        else if (selectedChoice == 2)
                                            target_rounds = "pick";
                                        else
                                            target_rounds = "";


                                        callRestAPI(Constants.Urls.PARENT_NOTIFICATION,
                                                new HashMap() {{
                                                    put("name", "childs_attendance");
                                                    put("absent", "true");
                                                    put("lat", kidObjectAfterFilter.gettargetLatD());
                                                    put("long", kidObjectAfterFilter.gettargetLngD());
                                                    put("student_id", kidObjectAfterFilter.getId());
                                                    put("when", noDays);
                                                    put("target_rounds", target_rounds);


                                                }},
                                                EnumMethodApi.POST,
                                                new IRestCallBack() {
                                                    @Override
                                                    public void onRestCallBack(String response, EnumNameApi nameApiEnum) {
                                                        if (StaticValue.progressDialog != null) {
                                                            StaticValue.progressDialog.hide();
                                                        }
                                                        UtilityParent.showMessage(getMainActivity(), response.toString());

                                                    }

                                                    @Override
                                                    public void onRestCallBack(JSONObject response, EnumNameApi nameApiEnum) {
                                                        try {
                                                            if (StaticValue.progressDialog != null) {
                                                                StaticValue.progressDialog.hide();
                                                            }

                                                            String status = response.optString("status", "");
                                                            if (status.equalsIgnoreCase("ok")) {
                                                                /**/
                                                                UtilDialogs.showSuccessMessage(getMainActivity()).setDialogeTitle(R.string.absent_send_succesfill);
                                                                JSONObject message = new JSONObject(

                                                                        new HashMap() {{
                                                                            put("status", "absent");
                                                                            put("student_id", kidObjectAfterFilter.getId());
                                                                            put("student_name", kidObjectAfterFilter.getName_());
                                                                            put("round_id", kidObjectAfterFilter.getRoundId());
                                                                            put("date_time", DateTools.Formats.DATEONLY_FORMAT_GMT.format(new Date()));

                                                                        }}

                                                                );
                                                                new SendNotificationGCM().sendNotification(kidObjectAfterFilter.getDriverMobileToken(), message.toString());
                                                                /**/
                                                            } else if (status.equals("")) {
                                                                UtilityParent.showMessage(getMainActivity(), getMainActivity().getString(R.string.error_api), response.toString());

                                                            } else {
                                                                UtilDialogs.showSuccessMessage(getMainActivity()).setDialogeTitle(status);
                                                            }
                                                        } catch (Exception e) {
                                                            UtilityParent.showMessage(getMainActivity(), getMainActivity().getString(R.string.error_api), response.toString());
                                                            e.printStackTrace();
                                                        }


                                                    }

                                                    @Override
                                                    public void onRestCallBack(VolleyError volleyError, EnumNameApi nameApiEnum) {
                                                        if (StaticValue.progressDialog != null) {
                                                            StaticValue.progressDialog.hide();
                                                        }
                                                        UtilityParent.showMessage(getMainActivity(), getMainActivity().getString(R.string.error_api));

                                                    }

                                                    @Override
                                                    public void onRestCallBack(NetworkResponse networkResponse, EnumNameApi nameApiEnum) {
//                                                        if (StaticValue.progressDialog != null) {
//                                                            StaticValue.progressDialog.hide();
//                                                        }
                                                    }
                                                },
                                                EnumNameApi.ABSENT,
                                                UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true),
                                                EnumTypeHeader.JSON
                                        );


                                    }
                                });


                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
        });
    }


    public void showCallDialog() {
        ArrayList<GetKidsResp.Student.MobileNumber> mobileNumbers = removeEmptyMobileNumbers();
        if (mobileNumbers != null)
            new DialogsTools.CallNumbersDialog().show(getMainActivity()).initMobileNumbersAdaptert(mobileNumbers);

    }

    private ArrayList<GetKidsResp.Student.MobileNumber> removeEmptyMobileNumbers() {
        try {
            ArrayList<GetKidsResp.Student.MobileNumber> tempMobileNumbers = new ArrayList<>();
            for (GetKidsResp.Student.MobileNumber mobileNumber : kidObjectAfterFilter.getMobileNumbers()) {
                if (!StringToolsV2.isEmptyStrings(mobileNumber.getMobile(), mobileNumber.getName())) {
                    tempMobileNumbers.add(mobileNumber);
                }
            }
            if (tempMobileNumbers.size() == 0)
                return null;

            return tempMobileNumbers;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }

    }

    private void callKidsAPI() {
//        showLoading();
//        horizontal_progressbar.setVisibility(View.VISIBLE);

//        ApiController.geSingleKidList(getMainActivity(), new KidsListRequest().withKidId(kidObjectAfterFilter.id), new OnApiComplete<KidsListResponse>() {
//            @Override
//            public void onSuccess(KidsListResponse kidsListResponse) {
////                hideLoading();
//                horizontal_progressbar.setVisibility(View.GONE);
//                ArrayList<KidBean> kidObjectAfterFilters = KidsListTools.ListFilter.filterAllKidslist(kidsListResponse);
//                if (kidObjectAfterFilters.size() == 0)
//                    onError(0, null);
//                else {
//                    kidObjectAfterFilter = kidObjectAfterFilters.get(0);
//                }
//
//
//            }
//
//            @Override
//            public void onError(int errorCode, String errorMessage) {
////                hideLoading();
//                horizontal_progressbar.setVisibility(View.GONE);
//
//                UtilDialogs.showGeneralErrorDialog(getMainActivity()).setDialogeMessage(R.string.api_send_error);
//
//            }
//        });


//        HomeKidsListViewModel.callAllKids(new SingleObserver<ArrayList<KidObjectAfterFilter>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                swipeRefreshLayout.setRefreshing(true);
//            }
//
//            @Override
//            public void onSuccess(ArrayList<KidObjectAfterFilter> kidObjectAfterFilters) {
//                /**/
//                swipeRefreshLayout.setRefreshing(false);
//                /**/
//                if (kidObjectAfterFilters == null) {
//                    onError(new Exception());
//                } else if (kidObjectAfterFilters.size() == 0) {
//                    showNoKids(true);
//                } else {
//
//                    showNoKids(false);
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                UtilDialogs.showGeneralErrorDialog(getMainActivity()).setDialogeMessage(R.string.api_send_error);
//                swipeRefreshLayout.setRefreshing(false);
////                showError();
//            }
//        });

    }

    @Override
    public void onResume() {
        super.onResume();
        try {
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
        return getString(R.string.kid_details);
    }


    private void showFeedDialog() {
        new DialogsTools.FeedBackDialog().show(getMainActivity()).setOnSubmintListener(new OnActionDoneListener<FeedbackRequest>() {
            @Override
            public void OnActionDone(FeedbackRequest feedbackRequest) {
                showLoadingDialog(true);
                feedbackRequest.setSchoolId(kidObjectAfterFilter.getSchoolId()).setStudentId(kidObjectAfterFilter.getId());
                ApiController.sendFeedBack(getMainActivity(), feedbackRequest, new OnApiComplete<Object>() {
                    @Override
                    public void onSuccess(Object object) {
                        try {
                            showLoadingDialog(false);
                            UtilDialogs.showSuccessMessage(getMainActivity()).setDialogeTitle(R.string.your_feedback_was_sent_successfully);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(int errorCode, String errorMessage) {
                        try {
                            showLoadingDialog(false);
                            UtilDialogs.showGeneralErrorDialog(getMainActivity()).setDialogeMessage(R.string.api_send_error);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }


    private void showLoadingDialog(boolean show) {
        try {
            if (show)
                StaticValue.progressDialog.show();
            else
                StaticValue.progressDialog.dismiss();
        } catch (Exception e) {

        }
    }


}