package trackwareschoolbus.parentschool.fragment.notification;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.gson.reflect.TypeToken;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import trackwareschoolbus.parentschool.API.enumApi.EnumMethodApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumTypeHeader;
import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallBack;
import trackwareschoolbus.parentschool.API_new.APIs.Constants;
import trackwareschoolbus.parentschool.MainFragmentActivity;
import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.api_v3.LogInRes;
import trackwareschoolbus.parentschool.api_v3.SchoolDataHolder;
import trackwareschoolbus.parentschool.app.Application;
import trackwareschoolbus.parentschool.basePage.BaseFragment;
import trackwareschoolbus.parentschool.bean.NotificationBean;
import trackwareschoolbus.parentschool.dataBase.DAO;
import trackwareschoolbus.parentschool.utilityParent.DateTools;
import trackwareschoolbus.parentschool.utilityParent.MyGson;
import trackwareschoolbus.parentschool.utilityParent.UtilDialogs;
import trackwareschoolbus.parentschool.utilityParent.UtilityParent;

/**
 * Created by  on 2/23/2017.
 */

public class NotificationFragment extends BaseFragment implements IRestCallBack {

    RecyclerView _rcNotification;
//    TextView labTitle;
//    ImageView imgTitle;
//    ImageView imgHome;
//    AppCompatImageView imgNotification;

    Activity mActivity;
    Button btnDateFrom, btnDateTo;
    View imgSearch;
    Bundle bundle;
//    AppCompatImageView imgBack;
    List<NotificationBean> listNotificationBeen;
    TextView labNoNotification;
    NotificationBean notificationBean = new NotificationBean();
    /*private DatePickerDialog btnDateFromPickerDialog;
    private DatePickerDialog btnDateToPickerDialog;*/
    LinearLayout layoutSearch;
    //    private SimpleDateFormat dateFormatter;
    LogInRes.NotificationsText.Action actions_dropOff;
    LogInRes.NotificationsText.Action actions_pickUp;
    private SwipeRefreshLayout swipeRefreshLayout;
    NotificationAdapter notificationAdapter;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            View view = inflater.inflate(R.layout.fragmnet_notification, container, false);
            DAO.deleteNotification(getDatabase(), -101, -101);
            view.findViewById(R.id.imgNotification_count).setVisibility(View.GONE);
            if (UtilityParent.getSavedlDataHolders()!=null && UtilityParent.getSavedlDataHolders().get(0)!=null && UtilityParent.getSavedlDataHolders().get(0).getLogInRes()!=null) {
                LogInRes resp = UtilityParent.getSavedlDataHolders().get(0).getLogInRes();
                actions_dropOff = resp.getNotificationsText().get(0).getActions().get(0); //drop-off
                actions_pickUp = resp.getNotificationsText().get(1).getActions().get(0); //pick-up
            }

            mActivity = NotificationFragment.this.getActivity();
            _rcNotification = (RecyclerView) view.findViewById(R.id.rcNotification);
//        LinearLayoutManager  layoutManager = new LinearLayoutManager(NotificationFragment.this.getActivity());
            _rcNotification.setLayoutManager(new LinearLayoutManager(NotificationFragment.this.getActivity()));

//        _rcNotification.setLayoutManager(layoutManager);

            if (UtilityParent.getStringShared(UtilityParent.LANGUAGE).equals("ar")) {
                _rcNotification.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
            /* Bundle bundle = getArguments();*/
            /* notificationBean=(NotificationBean) bundle.getSerializable("notifications");*/

            listNotificationBeen = new ArrayList<NotificationBean>();
            listNotificationBeen.add(notificationBean);
            /* setListAdapter(listNotificationBeen);*/
//        imgBack = (AppCompatImageView) view.findViewById(R.id.imgBack);
//
//        imgBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainFragmentActivity.showMyKidsFragment();
//            }
//        });


            // mNotificationPresenter.callNotification();

            /**/
            btnDateFrom = view.findViewById(R.id.btnDateFrom);
            imgSearch = view.findViewById(R.id.imgSearch);
            btnDateTo = view.findViewById(R.id.btnDateTo);
            labNoNotification = view.findViewById(R.id.labNoNotification);

            swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    labNoNotification.setText("");
                    callNotification(btnDateFrom.getText().toString(), btnDateTo.getText().toString());
                }
            });

//        labNoNotification.setText(mNotificationPresenter.returnNotification());


//        labTitle = (TextView) view.findViewById(R.id.labTitle);
//        labTitle.setText(getResources().getString(R.string.notification));
//        imgTitle = (ImageView) view.findViewById(R.id.barImage);
//        imgTitle.setImageResource(R.drawable.img_notifications_title);


//        imgHome = (ImageView) view.findViewById(R.id.imgHome);
//        imgHome.setImageResource(R.drawable.setting);
//        imgHome.setVisibility(View.INVISIBLE);
//        imgHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                /* MainFragmentActivity.showSettingFragment();*/
//                MainFragmentActivity.showSettingFragment();
//            }
//        });

//        imgNotification = (AppCompatImageView) view.findViewById(R.id.imgNotification);
//        imgNotification.setImageResource(R.drawable.setting);
//        imgNotification.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        /*  MainFragmentActivity.showSettingFragment();*/
//                        MainFragmentActivity.showSettingFragment();
//                    }
//                });


//        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            btnDateFrom.setInputType(InputType.TYPE_NULL);
            btnDateFrom.requestFocus();
            btnDateTo.setInputType(InputType.TYPE_NULL);

//        mainBar = (LinearLayout) view.findViewById(R.id.mainBar);
            layoutSearch = (LinearLayout) view.findViewById(R.id.layOutSearch);
            if (UtilityParent.getStringShared(UtilityParent.LANGUAGE).equals("ar")) {

//            mainBar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                layoutSearch.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }

            listNotificationBeen = new ArrayList<>();
            if (UtilityParent.getStringShared(UtilityParent.LANGUAGE).equals("ar")) {

            }

            imgSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (btnDateTo.getText().toString().trim().equals("") && btnDateFrom.getText().toString().trim().equals("")) {
                        UtilityParent.showMessage(mActivity, mActivity.getString(R.string.fill_field));
                        UtilityParent.shakeViews(btnDateFrom, btnDateTo);
                        return;
                    } else if (btnDateFrom.getText().toString().trim().equals("")) {
                        UtilityParent.showMessage(mActivity, mActivity.getString(R.string.enter_from_date));
                        UtilityParent.shakeViews(btnDateFrom);
                        return;
                    } else if (btnDateTo.getText().toString().trim().equals("")) {
                        UtilityParent.showMessage(mActivity, mActivity.getString(R.string.enter_to_date));
                        UtilityParent.shakeViews(btnDateTo);
                        return;
                    }
                    search();
                }
            });

            btnDateTo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (btnDateFrom.getText().toString().equals("")) {
                        return;
                    }
                    /**/
                    Calendar dateFrom = Calendar.getInstance();
                    /**/
                    try {
                        dateFrom.setTime(DateTools.Formats.DATEONLY_FORMAT_LOCAL.parse(btnDateFrom.getText().toString()));
                    } catch (ParseException e) {
                        dateFrom = null;
                    }
                    /**/
                    setDateTimeFields(dateFrom, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                            Calendar newDate = Calendar.getInstance();
                            newDate.set(year, monthOfYear, dayOfMonth);
                            btnDateTo.setText(DateTools.Formats.DATEONLY_FORMAT_LOCAL.format(newDate.getTime()));
                        }
                    });


                }
            });


            btnDateFrom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    setDateTimeFields(null, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                            Calendar newDate = Calendar.getInstance();
                            newDate.set(year, monthOfYear, dayOfMonth);
                            btnDateFrom.setText(DateTools.Formats.DATEONLY_FORMAT_LOCAL.format(newDate.getTime()));
                        }
                    });


                }
            });
       /* setListAdapter(
                listNotificationBeen
        );*/
            notificationAdapter = new NotificationAdapter(getMainActivity());
            _rcNotification.setAdapter(notificationAdapter);
            callNotification(btnDateFrom.getText().toString(), btnDateTo.getText().toString());

            return view;
        }catch (Exception e){
//            getMainActivity().onBackPressed();
            return null;
        }

    }

//    List<NotificationBean> listNotificationSearch;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void search() {
        String fromDate = btnDateFrom.getText().toString();
        String toDate = btnDateTo.getText().toString();
        if (UtilityParent.isEmptyString(fromDate)) {
            UtilityParent.showMessage(mActivity, getString(R.string.enter_from_date));
            return;
        }
        if (UtilityParent.isEmptyString(toDate)) {
            UtilityParent.showMessage(mActivity, getString(R.string.enter_to_date));
            return;
        }
        callNotification(btnDateFrom.getText().toString(), btnDateTo.getText().toString());


      /*  listNotificationSearch = new ArrayList<>();
        for (NotificationBean notificationBean : listNotificationBeen) {

            if (UtilityParent.beforaDate(fromDate, notificationBean.getFromDate()) && UtilityParent.afterDate(toDate, notificationBean.getFromDate())) {
                listNotificationSearch.add(notificationBean);
            }*/
    }

//        if (listNotificationSearch.size()>0)
    /*setListAdapter(listNotificationSearch);*/

//    public void setDateTimeField() {
//        Calendar calendar = Calendar.getInstance();
//        btnDateFromPickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                Calendar newDate = Calendar.getInstance();
//                newDate.set(year, month, dayOfMonth);
//                btnDateFrom.setText(dateFormatter.format(newDate.getTime()));
//            }
//        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(calendar.DAY_OF_MONTH));
////        btnDateToPickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
////            @Override
////            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
////                Calendar newDate = Calendar.getInstance();
////                newDate.set(year, month, dayOfMonth);
////                btnDateTo.setText(dateFormatter.format(newDate.getTime()));
////            }
////        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
//    }


    public void setDateTimeFields(Calendar minCalendar, DatePickerDialog.OnDateSetListener callBack) {
        Calendar now = Calendar.getInstance(Locale.getDefault());
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                callBack,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        if (minCalendar != null) {
            dpd.setMinDate(minCalendar);

        }
        dpd.show(getActivity().getSupportFragmentManager(), "Datepickerdialog");

        //////////////////
//
//        new MaterialDialog( ).daBuilder(getActivity())
//                .title(R.string.enter_from_date)
//                .positiveText(android.R.string.ok)
//                .negativeText(android.R.string.cancel)
//                .
//                .show();
//
//
//        Calendar  calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR,2000);
//        btnDateToPickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                Calendar newDate = Calendar.getInstance();
//                newDate.set(year, month, dayOfMonth);
//                btnDateTo.setText(dateFormatter.format(newDate.getTime()));
//            }
//        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setListAdapter(List<NotificationBean> listNotificationBean) {
//        _rcNotification = (RecyclerView) mActivity.findViewById(R.id._rcNotification);

        // use a linear layout manager
//        _rcNotification = (RecyclerView) mActivity.findViewById(R.id._rcNotification);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager();


        notificationAdapter.addData(listNotificationBean);


        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(false);
    }

    public NotificationFragment newInstance(Bundle bundles) {
        NotificationFragment notificationFragment = new NotificationFragment();
        bundle = bundles;
        return notificationFragment;
    }


//    public void callNotification() {
//        callRestAPI(PathUrl.MAIN_URL + PathUrl.PARENT_NOTIFICATION + "/",
//                new HashMap<String, Object>(),
//                EnumMethodApi.GET,
//                NotificationFragment.this,
//                EnumNameApi.GET_PARENT_NOTIFICATION,
//                UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, false)
//                ,
//                EnumTypeHeader.JSON
//        );
//    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onRestCallBack(String response, EnumNameApi nameApiEnum) {
        if (nameApiEnum == EnumNameApi.PARENTS_KIDS_HISTORY) {
            // getNotifications(response);
            if (swipeRefreshLayout != null)
                swipeRefreshLayout.setRefreshing(false);
            notificationAdapter.clearData();
            labNoNotification.setText(mActivity.getString(R.string.no_notification));

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onRestCallBack(JSONObject response, EnumNameApi nameApiEnum) {
        if (nameApiEnum == EnumNameApi.PARENTS_KIDS_HISTORY) {

            try {
                JSONArray ja = response.getJSONArray("notifications");
                if (ja.length() == 0) {
                    if (swipeRefreshLayout != null)
                        swipeRefreshLayout.setRefreshing(false);
                    notificationAdapter.clearData();
                    /*  UtilityParent.showMessageDialog(mActivity,"","no notifcatios");*/
                    //  noNotification="No notifications";
                    /*returnNotification("No notifications");*/
                    labNoNotification.setText(mActivity.getString(R.string.no_notification));
                } else {
                    getNotifications(response);
                }
            } catch (JSONException e) {
                Application.logEvents(nameApiEnum.name(), "NotificationFragment - onRestCallBack  ", e);

            }
            // getNotifications(response);
        }
    }

    @Override
    public void onRestCallBack(VolleyError volleyError, EnumNameApi nameApiEnum) {
        if (nameApiEnum == EnumNameApi.PARENTS_KIDS_HISTORY) {
            notificationAdapter.clearData();
            if (swipeRefreshLayout != null)
                swipeRefreshLayout.setRefreshing(false);
            labNoNotification.setText(mActivity.getString(R.string.no_notification));
//            UtilityParent.showMessageDialog(mActivity, mActivity.getString(R.string.error_api), volleyError.getMessage());
//            UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeMessage(R.string.api_send_error);
            Application.logEvents(nameApiEnum.name(), "NotificationFragment - onRestCallBack  ", volleyError);

        }
    }

    @Override
    public void onRestCallBack(NetworkResponse networkResponse, EnumNameApi nameApiEnum) {

    }


    public void callNotification(String startDate, String endDate) {
        try {
            if ((startDate == null && endDate == null) || (startDate.equals("") || endDate.equals(""))) {
                startDate = DateTools.Formats.DATE_FORMAT_GMT.format(UtilityParent.getDateSevenDayesBefore());
                endDate = DateTools.Formats.DATE_FORMAT_GMT.format(UtilityParent.getDateNow());
            } else {
                startDate = DateTools.Formats.DATE_FORMAT_GMT.format(DateTools.Formats.DATEONLY_FORMAT_LOCAL.parse(startDate));
                endDate = DateTools.Formats.DATE_FORMAT_GMT.format(DateTools.Formats.DATEONLY_FORMAT_LOCAL.parse(endDate));
            }

            Log.v("kids-history:start_date",startDate);
            Log.v("kids-history:end_date",endDate);
        } catch (Exception e) {

        }


        String[] authTokens= new String[UtilityParent.getSavedlDataHolders().size()];
        for (int i = 0; i < UtilityParent.getSavedlDataHolders().size(); i++) {
            authTokens[i]= UtilityParent.getSavedlDataHolders().get(i).getAuthorization();

        }





        String finalStartDate = startDate;
        String finalEndDate = endDate;
        callRestAPI(Constants.Urls.PARENTS_KIDS_HISTORY,
                new HashMap() {{
                    put("start_date", finalStartDate/*"2017-04-27 09:54:44"*/);
                    put("end_date", finalEndDate/*"2017-04-27 09:54:44"*/);
                    put("auth_tokens", authTokens);
//                    put("school_id",schoolId);

                }},
                EnumMethodApi.POST,
                NotificationFragment.this,
                EnumNameApi.PARENTS_KIDS_HISTORY,
                UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true)
                ,
                EnumTypeHeader.JSON

        );

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void getNotifications(JSONObject response) {
        try {


            ArrayList<NotificationBean> listNotificationBean = MyGson.getGson().fromJson(response.getJSONArray("notifications").toString(), new TypeToken<ArrayList<NotificationBean>>() {
            }.getType());


            setListAdapter(listNotificationBean);

//            for (int i = 0; i < jaNotifications.length(); i++) {
//                NotificationBean notificationBean = new NotificationBean();
//                JSONObject joNotification = jaNotifications.getJSONObject(i);
//
//                notificationBean.setAvatar(joNotification.optString("student_avatar"));
//                String notificationDate = joNotification.optString("date_time","");
//
////                if(notificationDate.equals("")){
////                    notificationBean.setTime("");
////                    notificationBean.setFromDate("");
////                }else {
////                    String[] dateTime = notificationDate.split(" ");
////                    String date = dateTime[0];
////                    String time = dateTime[1];
////                    notificationBean.setTime(time);
////                    notificationBean.setFromDate(date);
////
////
////                }
//
//
//                notificationBean.setNotifications_title(joNotification.optString("notifications_title"));
//                notificationBean.setDetails(joNotification.optString("student_name"));
//                notificationBean.setRoundType(joNotification.optString("type_of_round"));
//
//                notificationBean.setActivityType(joNotification.optString("activity_type"));
//
//                roundType = joNotification.optString("type_of_round");
//
//                if (joNotification.getString("notifications_text") == null || joNotification.getString("notifications_text") == "null") {
//
//                    if (joNotification.optString("activity_type").trim().toLowerCase().equals("in")) {
//
//                        if (roundType.trim().equals("pick")) {
//                            String checkIn = actions_pickUp.getCheckIn().replaceAll("@student_name", joNotification.optString("student_name"));
//                            String notificationText = checkIn;
//                            String notificationDetails = notificationText.replaceAll("@bus_num", joNotification.optString("bus_number")).replace("@bus_number", joNotification.optString("bus_number"));
//                            notificationBean.setDetails(notificationDetails);
//                        } else if (roundType.trim().equals("drop")) {
//                            String checkIn = actions_dropOff.getCheckIn().replace("@student_name".trim().toLowerCase(), joNotification.optString("student_name"));
//                            String notificationText = checkIn;
//                            String notificationDetails = notificationText.replace("@bus_num", joNotification.optString("bus_number"));
//                            notificationBean.setDetails(notificationDetails);
//                        }
//
//                    } else if (joNotification.optString("activity_type").trim().toLowerCase().equals("out")) {
//                        if (roundType.trim().equals("pick")) {
//                            String checkOut = actions_pickUp.getCheckOut().replace("@student_name".trim().toLowerCase(), joNotification.optString("student_name"));
//                            String notificationText = checkOut;
//                            notificationBean.setDetails(notificationText);
//                        } else if (roundType.trim().equals("drop")) {
//                            String checkOut = actions_dropOff.getCheckOut().replace("@student_name".trim().toLowerCase(), joNotification.optString("student_name"));
//                            String notificationText = checkOut;
//                            notificationBean.setDetails(notificationText);
//                        }
//                    } else if (joNotification.optString("activity_type").trim().toLowerCase().equals("absent")) {
//                        if (roundType.trim().equals("pick")) {
//                            String absent = actions_pickUp.getAbsent().replace("@student_name".trim().toLowerCase(), joNotification.optString("student_name"));
//                            String notificationText = absent;
//                            notificationBean.setDetails(notificationText);
//                        }/*else if(roundType.trim().equals("drop")){
//                         String absent= actions_pickUp.getNoShow();
//                         absent.replace(" @student_name ",joNotification.optString("student_name"));
//                         notificationBean.setDetails(absent);
//                     }*/
//
//                    } else if (joNotification.optString("activity_type").trim().toLowerCase().equals("no-show")) {
//
//
//                        if (roundType.trim().equals("drop")) {
//
//                            if (!actions_dropOff.getNoShow().equals(null) || !actions_dropOff.getNoShow().equals("null")) {
//                                String noShow = actions_dropOff.getNoShow().replaceAll("@student_name".trim().toLowerCase(), joNotification.optString("student_name"));
//                                ;
//                                String notificationText = noShow;
//                                String notificationDetails = notificationDate.replaceAll(" @round_name", joNotification.optString("round_name"));
//                                notificationBean.setDetails(notificationDetails);
//                            }
//
//                        }
//                    } else if (joNotification.optString("activity_type").trim().toLowerCase().equals("absent-by-parent")) {
//                        if (roundType.trim().toLowerCase().equals("pick-up")) {
//                            String absent = actions_pickUp.getAbsent();
//                        /* absent.replace("@student_name".trim().toLowerCase(), joNotification.optString("student_name"));*/
//                            String notificationText = absent.replace("@student_name".trim().toLowerCase(), joNotification.optString("student_name"));
//                            notificationBean.setDetails(notificationText);
//                        }
//                    }
//                } else {
//                    notificationBean.setDetails(joNotification.optString("notifications_text"));
//
//                }
//
//
//                // notificationBean.setAvatar(joNotification.optString("student_name"));
//
//                listNotificationBean.add(notificationBean);
//            }


        } catch (JSONException e) {
            e.printStackTrace();
            Application.logEvents("getNotifications", "NotificationFragment - onRestCallBack  ", e);

        }

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

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
        return true;
    }



    @Override
    protected String initToolBarTitle() {
        return getString(R.string.notification);
    }

}
