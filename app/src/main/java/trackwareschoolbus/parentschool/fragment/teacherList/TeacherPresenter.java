//package trackwareschoolbus.parentschool.fragment.teacherList;
//
//import android.app.Activity;
//import android.os.Build;
//import androidx.annotation.RequiresApi;
//
//import com.android.volley.NetworkResponse;
//import com.android.volley.VolleyError;
//import com.google.gson.reflect.TypeToken;
//
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import trackwareschoolbus.parentschool.API.enumApi.EnumMethodApi;
//import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
//import trackwareschoolbus.parentschool.API.enumApi.EnumTypeHeader;
//import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallBack;
//import trackwareschoolbus.parentschool.API_new.APIs.Constants;
//import trackwareschoolbus.parentschool.R;
//import trackwareschoolbus.parentschool.basePage.BasePresenter;
//import trackwareschoolbus.parentschool.bean.TeacherBean;
//import trackwareschoolbus.parentschool.utilityParent.MyGson;
//import trackwareschoolbus.parentschool.utilityParent.UtilDialogs;
//import trackwareschoolbus.parentschool.utilityParent.UtilityParent;
//
//
///**
// * Created by  on 3/8/17.
// */
//
//public class TeacherPresenter extends BasePresenter implements IRestCallBack {
//
//    private TeacherFragment teacherFragment;
//    Activity mActivity;
//
//    public TeacherPresenter(Activity mActivity, TeacherFragment teacherFragment) {
//        this.mActivity = mActivity;
//        this.teacherFragment = teacherFragment;
//
//    }
//
//    public void getListTeacher(final int kid_id) {
//        callRestAPI(Constants.Urls.GET_KIDS_TEACHER,
//                new HashMap<String, Object>() {{
//                    // "68692a58e98f6edb4c7e228025e1d3ad"
//                    put("kid_id", kid_id);
//                }},
//                EnumMethodApi.POST,
//                TeacherPresenter.this,
//                EnumNameApi.GET_KIDS_TEACHER,
//                UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true),
//                EnumTypeHeader.JSON
//        );
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
//    public void listTeachers(JSONObject response) {
//        List<TeacherBean> listTeacherBean = new ArrayList<>();
////        TeacherBean teacherBean = null;
//
//
//        try {
//
//            listTeacherBean = MyGson.getGson().fromJson(response.getJSONArray("teachers").toString(), new TypeToken<ArrayList<TeacherBean>>() {
//            }.getType());
//
//
//           /*JSONObject response1=new JSONObject(testJson);*/
////            JSONArray jaTeachers = response.getJSONArray("teachers");
////            for (int i = 0; i < jaTeachers.length(); i++) {
////                teacherBean = new TeacherBean();
////                JSONObject joTeacher = jaTeachers.getJSONObject(i);
////                teacherBean.setId(joTeacher.getInt("id"));
////                teacherBean.setName(joTeacher.getString("driver_name"));
////                teacherBean.setAvatar(joTeacher.getString("photo"));
////                listTeacherBean.add(teacherBean);
////            }
//            teacherFragment.setListAdapter(listTeacherBean);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Override
//    public void onRestCallBack(String response, EnumNameApi nameApiEnum) {
//
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
//    @Override
//    public void onRestCallBack(JSONObject response, EnumNameApi nameApiEnum) {
//        if (nameApiEnum == EnumNameApi.GET_KIDS_TEACHER) {
//            listTeachers(response);
//        }
//    }
//
//    @Override
//    public void onRestCallBack(VolleyError volleyError, EnumNameApi nameApiEnum) {
////        UtilityParent.showMessageDialog(mActivity, mActivity.getString(R.string.error_api), volleyError.getMessage());
//        UtilDialogs.showGeneralErrorDialog(mActivity).setDialogeMessage(R.string.api_send_error);
//    }
//
//    @Override
//    public void onRestCallBack(NetworkResponse networkResponse, EnumNameApi nameApiEnum) {
//
//    }
//}
