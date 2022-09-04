package trackwareschoolbus.parentschool.fragment.maps;

/**
 * Created by  on 3/8/2017.
 */

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import trackwareschoolbus.parentschool.API.enumApi.EnumMethodApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumTypeHeader;
import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallBack;
import trackwareschoolbus.parentschool.API_new.APIs.Constants;
import trackwareschoolbus.parentschool.MainFragmentActivity;
import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.app.Application;
import trackwareschoolbus.parentschool.basePage.BasePresenter;
import trackwareschoolbus.parentschool.bean.StudentBean;
import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener;
import trackwareschoolbus.parentschool.utilityParent.UtilDialogs;
import trackwareschoolbus.parentschool.utilityParent.UtilityParent;

public class MapParentPresenter extends BasePresenter implements IRestCallBack {
    public MapParentFragment mapsFragment;
    Activity mActivity;
    ArrayList<StudentBean> listStudentBean;
    String roundType;
    // ChangeLocationDialog changeLocationDialog;


    public MapParentPresenter(Activity mActivity, MapParentFragment mapsFragment) {
        this.mActivity = mActivity;
        this.mapsFragment = mapsFragment;

    }

    public MapParentPresenter(Activity mActivity, String roundType) {
        this.mActivity = mActivity;
        this.roundType = roundType;
    }


    public void callGetStudentList(final int roundID) {
        try{
            callRestAPI(Constants.Urls.GET_STUDENT_LIST,
                    new HashMap<String, Object>() {{
                        put("round_id", roundID);
                    }},
                    EnumMethodApi.POST,
                    MapParentPresenter.this,
                    EnumNameApi.GET_STUDENT,
                    UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true),
                    EnumTypeHeader.JSON
            );
        }catch (Exception e){

        }



    }


    public void getStudentList(JSONObject response) {
        try{
            listStudentBean = new ArrayList<StudentBean>();
        }catch (Exception e){

        }



        try {
           /* JSONObject joStudentList=new JSONObject(response);*/
//            response =new JSONObject("{\n" +
//                    "  \"status\": \"ok\",\n" +
//                    "  \"students\": [\n" +
//                    "    {\n" +
//                    "      \"lat\": \"32.975257567361\",\n" +
//                    "      \"lng\": \"33.843879996618\",\n" +
//                    "      \"student_name\": \"Jamal Hajeer\",\n" +
//                    "      \"avatar\": null\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"lat\": \"34.975257567361\",\n" +
//                    "      \"lng\": \"35.843871496618\",\n" +
//                    "      \"student_name\": \"Mohammad Hajeer\",\n" +
//                    "      \"avatar\": null\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"lat\": \"36.975257567361\",\n" +
//                    "      \"lng\": \"37.843871496618\",\n" +
//                    "      \"student_name\": \"Sa3eed \",\n" +
//                    "      \"avatar\": null\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"lat\": \"38.975257567361\",\n" +
//                    "      \"lng\": \"39.843871496618\",\n" +
//                    "      \"student_name\": \"Tasneem\",\n" +
//                    "      \"avatar\": null\n" +
//                    "    }\n" +
//                    "  ]\n" +
//                    "}");

            JSONArray jaStudents = response.getJSONArray("students");
            for (int i = 0; i < jaStudents.length(); i++) {


                StudentBean studentBean = new StudentBean();
                JSONObject joStudent = jaStudents.getJSONObject(i);
                // JSONObject joStudentInfo=joStudent.getJSONObject("student");

                studentBean.setId(joStudent.getInt("student_id"));
                studentBean.setNameStudent(joStudent.getString("student_name"));


                studentBean.setLatitude(joStudent.getDouble("lat"));
                studentBean.setLongitude(joStudent.getDouble("lng"));
                studentBean.setAvatar(joStudent.getString("avatar"));
                studentBean.setRouteOrder(joStudent.getInt("route_order"));

                listStudentBean.add(studentBean);

            }

            mapsFragment.setPoint(listStudentBean, false);
            //  roundBean.setListStudentBean(listStudentBean);
        } catch (Exception e) {
            e.getMessage();
            Application.logEvents("getStudentList", "MapParentPresenter - getStudentList  ",e,response);

        }


    }

    public void callChangeLocation(final String roundType, final double lat, final double lng, final int studentId) {
        try{
            System.err.println(roundType + " :@W@W " + lat + " : " + lng + " : " + studentId);
            callRestAPI(Constants.Urls.PARENT_NOTIFICATION,
                    new HashMap() {{
                        put("name", "changed_location");
                        put("location_type", roundType);
                        put("long", lng);
                        put("lat", lat);
                        put("mobile", UtilityParent.getStringShared(UtilityParent.MOBILE_NUMBER));
                        put("student_id", studentId);
                    }},
                    EnumMethodApi.POST,
                    MapParentPresenter.this,
                    EnumNameApi.CHANGE_LOCATION,
                    UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true),
                    EnumTypeHeader.JSON

            );
        }catch (Exception e){

        }


    }


    @Override
    public void onRestCallBack(String response, EnumNameApi nameApiEnum) {
        try{
            if (nameApiEnum == EnumNameApi.GET_STUDENT) {
                // getStudentList(response);
            } else if (nameApiEnum == EnumNameApi.CHANGE_LOCATION) {
//            UtilityParent.showMessageDialog(mActivity, mActivity.getString(R.string.error_api), response);
                UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeMessage(R.string.error_api);
            }
        }catch (Exception e){

        }

    }

    @Override
    public void onRestCallBack(JSONObject response, EnumNameApi nameApiEnum) {
        try{
            if (nameApiEnum == EnumNameApi.GET_STUDENT) {
                getStudentList(response);
            } else if (nameApiEnum == EnumNameApi.CHANGE_LOCATION) {

                try {
                    if (response.getString("status").equals("ok")) {
                        OnActionDoneListener<UtilDialogs.MessageYesNoDialog> onActionDoneListener = new OnActionDoneListener<UtilDialogs.MessageYesNoDialog>() {
                            @Override
                            public void OnActionDone(UtilDialogs.MessageYesNoDialog Action) {
                                MainFragmentActivity.showMyKidsFragment();
                            }
                        };
                        if (roundType.equalsIgnoreCase("both")) {
                            UtilDialogs.showSuccessMessage(mActivity).setDialogeTitle(R.string.change_Location_both).setYesButtonClickListener(onActionDoneListener);
                        } else if (roundType.equalsIgnoreCase("pick-up")) {
                            UtilDialogs.showSuccessMessage(mActivity).setDialogeTitle(R.string.change_Location_pick).setYesButtonClickListener(onActionDoneListener);
                        } else if (roundType.equalsIgnoreCase("drop-off")) {
                            UtilDialogs.showSuccessMessage(mActivity).setDialogeTitle(R.string.change_Location_drop).setYesButtonClickListener(onActionDoneListener);
                        }
                    } else {
                        UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeTitle(R.string.error_api).setDialogeMessage(R.string.changed_location_failed);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Application.logEvents(nameApiEnum.name(), "MapParentPresenter - onRestCallBack  ",e);

                }

            }
        }catch (Exception e){

        }


    }

    @Override
    public void onRestCallBack(VolleyError volleyError, EnumNameApi nameApiEnum) {
        try{
            UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeTitle(R.string.error_api).setDialogeMessage(R.string.api_send_error);
            Application.logEvents(nameApiEnum.name(), "MapParentPresenter - onRestCallBack  ",volleyError);

        }catch (Exception e){

        }

//        if (nameApiEnum == EnumNameApi.GET_STUDENT) {
//            UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeTitle(R.string.error_api).setDialogeMessage(volleyError.getMessage());
//        } else if (nameApiEnum == EnumNameApi.CHANGE_LOCATION) {
//            UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeTitle(R.string.error_api).setDialogeMessage(volleyError.getMessage());
//
//        }
    }

    @Override
    public void onRestCallBack(NetworkResponse networkResponse, EnumNameApi nameApiEnum) {

    }

    public Bitmap MarkerWithOptionsBitmap() {
        try{
            View marker = LayoutInflater.from(mActivity).inflate(R.layout.marker_with_bus,
                    null);

            marker.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            marker.layout(0, 0, marker.getMeasuredWidth(), marker.getMeasuredHeight());

            final Bitmap clusterBitmap = Bitmap.createBitmap(marker.getMeasuredWidth(),
                    marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(clusterBitmap);
            marker.draw(canvas);

            return clusterBitmap;
        }catch (Exception e){
return null;
        }

    }

    public Bitmap MarkerSchoolWithOptionsBitmap() {
        try{
            View marker = LayoutInflater.from(mActivity).inflate(R.layout.marker_with_school,
                    null);

            marker.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            marker.layout(0, 0, marker.getMeasuredWidth(), marker.getMeasuredHeight());

            final Bitmap clusterBitmap = Bitmap.createBitmap(marker.getMeasuredWidth(),
                    marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(clusterBitmap);
            marker.draw(canvas);

            return clusterBitmap;
        }catch (Exception e){
            return null;
        }

    }
}