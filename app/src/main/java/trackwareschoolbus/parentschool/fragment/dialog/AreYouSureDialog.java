package trackwareschoolbus.parentschool.fragment.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import trackwareschoolbus.parentschool.API.enumApi.EnumMethodApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumTypeHeader;
import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallBack;
import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallPhoenix;
import trackwareschoolbus.parentschool.API_new.APIs.Constants;
import trackwareschoolbus.parentschool.MainFragmentActivity;
import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.api_v3.GetKidsResp;
import trackwareschoolbus.parentschool.basePage.BaseActivity;
import trackwareschoolbus.parentschool.basePage.BaseDialog;
import trackwareschoolbus.parentschool.enums.EnumFragment;
import trackwareschoolbus.parentschool.fragment.notification.senderNotification.SendNotificationGCM;
import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener;
import trackwareschoolbus.parentschool.utilityParent.DateTools;
import trackwareschoolbus.parentschool.utilityParent.StaticValue;
import trackwareschoolbus.parentschool.utilityParent.UtilDialogs;
import trackwareschoolbus.parentschool.utilityParent.UtilityParent;

//import trackwareschoolbus.parentschool.PhoenixPlug;

/**
 * Created by  on 3/8/2017.
 */

public class AreYouSureDialog extends BaseDialog implements IRestCallBack, IRestCallPhoenix {


    Button btnSubmit;
    Button btnCancel;
    TextView labTitle;
    Activity mActivity;
    String studentName;


    public static ArrayList<Integer> kidsApsent = new ArrayList<Integer>();
    int[] kidasId = new int[1];
    static int numberKIdsApsent = 0;
    GetKidsResp.Student kidBean = new GetKidsResp.Student();

    public AreYouSureDialog(@NonNull final Activity mActivity, GetKidsResp.Student kidBean) { //CHANGE_LOCATION
        super(mActivity);
        this.mActivity = mActivity;
        declaerView();
        labTitle.setText(mActivity.getResources().getString(R.string.change_Location));
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragmentActivity.showChangeLocationFragment(kidBean.getId(), !kidBean.getPickupByParent(), !kidBean.getDropOffByParent());

                dismiss();
            }
        });


    }


    public AreYouSureDialog(@NonNull final Activity mActivity, EnumFragment enumFragment, final int studentID) {
        super(mActivity);
        this.mActivity = mActivity;
        declaerView();


        if (enumFragment == EnumFragment.FORGET_PASSWORD) {

            labTitle.setText(mActivity.getResources().getString(R.string.forget_password));
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    dismiss();
                    StaticValue.progressDialog.show();
                    callForgetPassword();
                }
            });
        }
//        else if (enumFragment == EnumFragment.CHANGE_LOCATION) {
//
//            labTitle.setText(mActivity.getResources().getString(R.string.change_Location));
//            extPin.setHint(mActivity.getResources().getString(R.string.pin_code_requird));
//            labForgetPassword = (TextView) findViewById(R.id.labForgetPasswordDialog);
//            labForgetPassword.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (StaticValue.progressDialog != null) {
//                        StaticValue.progressDialog.show();
//                    }
//                    dismiss();
//                    callForgetPassword();
//                }
//            });
//            btnCancel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dismiss();
//                }
//            });
//            btnSubmit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    //checkPin();
//                    if (UtilityParent.getStringShared(UtilityParent.PIN_Code).equals(extPin.getText().toString())) {
//                        MainFragmentActivity.showChangeLocationFragment(studentID);
//                    } else {
//                        UtilityParent.shoeMessageToast(mActivity, mActivity.getString(R.string.pin_incorrect));
//                        return;
//                    }
//                    dismiss();
//                }
//            });
//
//        }

        else if (enumFragment == EnumFragment.QR_CODE) {
            labTitle.setText(mActivity.getResources().getString(R.string.qr_code_title));

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MainFragmentActivity.showQrCode(studentID);
                    dismiss();
                }
            });
        }
    }

    public AreYouSureDialog(@NonNull final BaseActivity mActivity, EnumFragment enumFragment, final GetKidsResp.Student kidBean, String studentName) {
        super(mActivity);
        this.mActivity = mActivity;

        callRestAPI(Constants.Urls.GTE_KIDS_LIST,
                new HashMap() {{
                    put("kid_id", kidBean.getId());
                }},
                EnumMethodApi.POST,
                AreYouSureDialog.this,
                EnumNameApi.GTE_KIDS_LIST_ABSENT,
                UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true),
                EnumTypeHeader.JSON
        );
        declaerView();

        this.kidBean = kidBean;
        this.studentName = studentName;


        if (enumFragment == EnumFragment.MYKIDS) {

            labTitle.setText(mActivity.getResources().getString(R.string.absent));
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UtilDialogs.showDatePickerDialog(mActivity, new OnActionDoneListener<Integer>() {
                        @Override
                        public void OnActionDone(Integer noDays) {
                            callAbsent(kidBean, noDays, 1);

                        }
                    });
                    dismiss();
                }
            });
        }
    }


    public AreYouSureDialog(final Activity mActivity, final OnActionDoneListener<AreYouSureDialog> onPinCorrect) {
        super(mActivity);
        this.mActivity = mActivity;

        declaerView();


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onPinCorrect.OnActionDone(AreYouSureDialog.this);

                dismiss();
            }
        });

    }

    @SuppressLint("ValidFragment")
    class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        Integer selectedChoice = -1;

        public DatePickerFragment(Integer sc) {
            selectedChoice = sc;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            int months = view.getMonth() + 1;
            if (months == 13) {
                months = 1;
            }
            int noDays = UtilityParent.getCountOfDays(UtilityParent.getDateFormat("dd/MM/yyyy"), (view.getDayOfMonth() + "/" + months + "/" + view.getYear()));
            if (noDays > -1) {
                callAbsent(kidBean, noDays, selectedChoice);
            } else {
                UtilityParent.showMessage(mActivity, mActivity.getString(R.string.error), mActivity.getString(R.string.please_chose_date));
            }
            // Do something with the date chosen by the user
//            TextView tv1= (TextView) getActivity().findViewById(R.id.textview1);
//            tv1.setText("Year: "+view.getYear()+" Month: "+view.getMonth()+" Day: "+view.getDayOfMonth());

        }
    }


    public void callForgetPassword() {
//        String value = ReferrerReceiver.getReferrerKey(mActivity, "secret_token");
//        if (UtilityParent.isEmptyString(value)) {
//            value = UtilityParent.getStringShared(UtilityParent.SECREET_TOKEN);
//        }
//        final String finalValue = value;
        callRestAPI(Constants.Urls.FORGET_PASSWORD,
                new HashMap<String, Object>() {{
//                    put("email",UtilityParent.getStringShared(UtilityParent.EMAIL));
//                    put("secret_token",ReferrerReceiver.getReferrerKey(mActivity,"secret_token"));
//                    put("secret_token", "");
                    put("mobile", UtilityParent.getStringShared(UtilityParent.MOBILE_NUMBER));
                    //put("secret_token","d46af7a0b0e01c7a8f988bf787e76c37");
//                    put("user_type","parent");
//                    put("pin",UtilityParent.getStringShared(UtilityParent.PIN_Code));
//                    put("mobile_number",ReferrerReceiver.getReferrerKey( mActivity,"mobile_number"));
                    // put("mobile_number","962795568031");
                }},
                EnumMethodApi.POST,
                AreYouSureDialog.this,
                EnumNameApi.FORGET_PASSWORD,
                UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true),
                EnumTypeHeader.JSON
        );
    }


    void callAbsent(final GetKidsResp.Student kidBean, final int when, Integer selectedChoice) { // notify

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
                    put("lat", kidBean.getTargetLat());
                    put("long", kidBean.getTargetLng());
//                    put("round_id", kidBean.getRoundId());
                    put("student_id", kidBean.getId());
                    put("when", when);
                    put("target_rounds", target_rounds);


                }},
                EnumMethodApi.POST,
                AreYouSureDialog.this,
                EnumNameApi.ABSENT,
                UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true),
                EnumTypeHeader.JSON
        );
    }

    @Override
    public void onRestCallBack(String response, EnumNameApi nameApiEnum) {
        if (StaticValue.progressDialog != null) {
            StaticValue.progressDialog.hide();
        }
        if (nameApiEnum == EnumNameApi.FORGET_PASSWORD) {
            UtilityParent.showMessage(mActivity, mActivity.getString(R.string.error_api), response.toString());
        } else if (nameApiEnum == EnumNameApi.ABSENT) {
            UtilityParent.showMessage(mActivity, mActivity.getString(R.string.error_api), response.toString());

        } else if (nameApiEnum == EnumNameApi.GTE_KIDS_LIST_ABSENT) {
            UtilityParent.showMessage(mActivity, mActivity.getString(R.string.error_api), response.toString());

        }

    }

    @Override
    public void onRestCallBack(JSONObject response, EnumNameApi nameApiEnum) {
        try {
            if (StaticValue.progressDialog != null) {
                StaticValue.progressDialog.hide();
            }

            if (nameApiEnum == EnumNameApi.ABSENT) {
                try {
                    String status = response.optString("status", "");
                    if (status.equalsIgnoreCase("ok")) {
                        /**/
                        UtilDialogs.showSuccessMessage(mActivity).setDialogeTitle(R.string.absent_send_succesfill);
                        JSONObject message = new JSONObject(

                                new HashMap() {{
                                    put("status", "absent");
                                    put("student_id", kidBean.getId());
                                    put("student_name", kidBean.getName_());
                                    put("round_id", kidBean.getRoundId());
                                    put("date_time", DateTools.Formats.DATEONLY_FORMAT_GMT.format(new Date()));

                                }}

                        );
                        new SendNotificationGCM().sendNotification(kidBean.getDriverMobileToken(), message.toString());
                        /**/
                    } else if (status.equals("")) {
                        UtilityParent.showMessage(mActivity, mActivity.getString(R.string.error_api), response.toString());

                    } else {
                        UtilDialogs.showSuccessMessage(mActivity).setDialogeTitle(status);
                    }
                } catch (Exception e) {
                    UtilityParent.showMessage(mActivity, mActivity.getString(R.string.error_api), response.toString());
                    e.printStackTrace();
                }


//                if (kidBean != null) {
//
//
//
//
//                }

            } else if (nameApiEnum == EnumNameApi.FORGET_PASSWORD) {
                if (response.getString("status").equalsIgnoreCase("a message was sent to your email")) {
                    UtilDialogs.showSuccessMessage(mActivity).setDialogeTitle(R.string.send_forget_password);
                } else {
                    UtilityParent.shoeMessageToast(mActivity, "Email incorrect");
                }
            } else if (nameApiEnum == EnumNameApi.GTE_KIDS_LIST_ABSENT) {
                this.kidBean = getKidsList(response, kidBean);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            UtilityParent.showMessage(mActivity, mActivity.getString(R.string.error_api), response.toString());
        }

    }

    // UtilityParent.showMessage(mmActivity,mmActivity.getString(R.string.error_api),volleyError.getMessage());
    public GetKidsResp.Student getKidsList(JSONObject response, GetKidsResp.Student kidBean) {
        try {
            JSONArray jaKids = response.getJSONArray("students");

            if (jaKids.length() != 0) {

                for (int i = 0; i < jaKids.length(); i++) {
                    JSONObject joKids = jaKids.getJSONObject(i);
                    if (response.has("round_type")) {
                        String value = joKids.getString("round_type");
                        if (value.contains("Pick-up")) {
                            kidBean.setId(joKids.getInt("id"));
                            Boolean current_active = joKids.getBoolean("is_active");
                            kidBean.setActive(current_active);
                            kidBean.setBusId(joKids.getInt("bus_id"));
                            kidBean = updateKidBean(kidBean, joKids, response);
                            return kidBean;
                        }
                    }
                }

            }
        } catch (JSONException e) {
            UtilDialogs.showGeneralErrorDialog(StaticValue.mActivity).setDialogeMessage(R.string.api_send_error);
//            UtilityParent.showMessageDialog(mActivity, "Fail", e.getMessage());
        }
        return kidBean;
    }

    private GetKidsResp.Student updateKidBean(GetKidsResp.Student kidBean, JSONObject joKids, JSONObject response) throws JSONException {
        if (response.has("target_lat"))
            kidBean.setTargetLat(joKids.optString("target_lat"));
        if (response.has("target_lng"))
            kidBean.setTargetLng(joKids.optString("target_lng"));
        if (response.has("name")) {
            String[] studentName = joKids.getString("name").trim().split(" ");
            kidBean.setName(studentName[0]);
            if (response.has("student_grade"))
                kidBean.setStudentGrade(joKids.getString("student_grade"));
            if (response.has("school_name"))
                kidBean.setSchoolName(joKids.getString("school_name"));
            if (response.has("avatar"))
                kidBean.setAvatar(joKids.getString("avatar"));
            if (response.has("bus_id"))
                kidBean.setBusId(joKids.getInt("bus_id"));
            if (response.has("driver_mobile_token"))
                kidBean.setDriverMobileToken(joKids.getString("driver_mobile_token"));
            if (response.has("school_mobile_number"))
                kidBean.setSchoolMobileNumber(joKids.getString("school_mobile_number"));
            if (response.has("driver_mobile_number"))
                kidBean.setSchoolMobileNumber(joKids.getString("driver_mobile_number"));
            if (response.has("round_type"))
                kidBean.setRoundType(joKids.getString("round_type"));
            if (response.has("round_id"))
                kidBean.setRoundId(joKids.getInt("round_id"));
            if (response.has("route_order"))
                kidBean.setRouteOrder(joKids.getInt("route_order"));
            if (response.has("school_lat"))
                kidBean.setSchoolLat(joKids.optString("school_lat"));
            if (response.has("school_lng"))
                kidBean.setSchoolLng(joKids.optString("school_lng"));


            if (response.has("change_location"))
                kidBean.setChangeLocation(joKids.optBoolean("change_location"));
            if (response.has("show_map"))
                kidBean.setShowMap(joKids.optBoolean("show_map"));

            kidBean.setShowAddBusCard(!joKids.has("show_add_bus_card") || joKids.optBoolean("show_add_bus_card", true));


        }
        return kidBean;
    }

    @Override
    public void onRestCallBack(VolleyError volleyError, EnumNameApi nameApiEnum) {
        if (nameApiEnum == EnumNameApi.FORGET_PASSWORD) {
            UtilityParent.showMessage(mActivity, mActivity.getString(R.string.error_api), volleyError.getMessage());
        } else if (nameApiEnum == EnumNameApi.ABSENT) {

        }


    }


    @Override
    public void onRestCallBack(NetworkResponse networkResponse, EnumNameApi nameApiEnum) {

    }

    public void declaerView() {

//        mContext =  mActivity;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(R.drawable.dialog_regtangel_round_shape);
        setCancelable(false);
        setContentView(R.layout.dialog_are_you_sure);
        btnSubmit = (Button) findViewById(R.id.btnSubmint);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        labTitle = (TextView) findViewById(R.id.labTitle);
    }


    @Override
    public void onReadyToPushMessage() {
//        onReadyToPushMessage("");
    }

//    @Override
//    public void onReadyToPushMessage(String response) {
//
//        Map<String, String> map = new HashMap<>();
//        map.put("student_id", kidBean.getId() + "");
//        map.put("student_name", kidBean.getStudentName());
//        map.put("status", "absent");
//        map.put("round_id", kidBean.getRoundId() + "");
//        map.put("date_time", UtilityParent.getDateFormat("dd/MM/yyyy hh:mm:ss"));
//
//        new SendNotificationGCM().sendNotification(kidBean.getDriverMobileToken(), new JSONObject(map).toString());
//
//        if (MainFragmentActivity.SOCKET_API) {
//            ObjectNode node = new ObjectNode(JsonNodeFactory.instance);
//            node.put("student_id", kidBean.getId() + "");
//            node.put("student_name", kidBean.getStudentName());
//            node.put("status", "absent");
//            node.put("round_id", kidBean.getRoundId() + "");
//            node.put("date_time", UtilityParent.getDateFormat("dd/MM/yyyy hh:mm:ss"));
//            PhoenixPlug.getInstance().pushPhoenix(node);
//        }
//
//        mActivity.runOnUiThread(new Runnable() {
//            public void run() {
//                if(UtilityParent.getStringShared(UtilityParent.LANGUAGE).equals("en")){
//                    new SendDialog(mContext,studentName+" "+"absent notification sent successfully"+" ",EnumFragment.MYKIDS).show();
//                }else if (UtilityParent.getStringShared(UtilityParent.LANGUAGE).equals("ar")){
//                    new SendDialog(mContext,studentName+" "+"تم ارسال إشعار الغياب بنجاح"+" ",EnumFragment.MYKIDS).show();
//                }
//                UtilityParent.setStringShared(UtilityParent.STUDENT_NAME,null);
//            }
//        });
//
//
//       // mContext,UtilityParent.getStringShared(UtilityParent.STUDENT_NAME)+
//
//    }

    @Override
    public void sendSocketMessage(String response, int usedId) {

    }

    @Override
    public void receivedSocketMessage(String response, int usedId) {

    }

    @Override
    public void newLocation(double lat, double lng, int usedId) {

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


}
