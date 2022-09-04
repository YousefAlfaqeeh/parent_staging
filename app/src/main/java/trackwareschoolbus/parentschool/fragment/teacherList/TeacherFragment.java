//package trackwareschoolbus.parentschool.fragment.teacherList;
//
//import android.app.Activity;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Build;
//import android.os.Bundle;
//
//import androidx.annotation.RequiresApi;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.android.volley.NetworkResponse;
//import com.android.volley.VolleyError;
//
//import org.json.JSONObject;
//
//import java.util.List;
//
//import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
//import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallBack;
//import trackwareschoolbus.parentschool.R;
//import trackwareschoolbus.parentschool.api_v3.GetKidsResp;
//import trackwareschoolbus.parentschool.basePage.BaseFragment;
//import trackwareschoolbus.parentschool.bean.TeacherBean;
//
///**
// * Created by  on 2/28/2017.
// */
//@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
//public class TeacherFragment extends BaseFragment implements IRestCallBack {
//
//
//    Activity mActivity;
//    TeacherAdapter teacherAdapter;
//    TeacherPresenter mPresenter;
//    private TeacherBean teacherBean;
//    private GetKidsResp.Student kidBean;
//    RecyclerView rcTeacher;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_teacher, container, false);
//
//
//        mActivity = getActivity();
//        rcTeacher = (RecyclerView) view.findViewById(R.id.rcTeacher);
//        mPresenter = new TeacherPresenter(mActivity, TeacherFragment.this);
//        kidBean = getArguments().getParcelable("kidBean");
//        teacherBean = getArguments().getParcelable("teacherBean");
//        mPresenter.getListTeacher(kidBean.getId());
//
//        /**/
//
//        view.findViewById(R.id.barImage).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().onBackPressed();
//            }
//        });
//
//        /**/
//        return view;
//    }
//
//
//    public void setListAdapter(List list) {
//        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
//        rcTeacher.setLayoutManager(mLayoutManager);
//        teacherAdapter = new TeacherAdapter(list, kidBean, mActivity, mPresenter, getDatabase());
//        rcTeacher.setAdapter(teacherAdapter);
//    }
//
//
//    @Override
//    public void onRestCallBack(String response, EnumNameApi nameApiEnum) {
//
//    }
//
//    @Override
//    public void onRestCallBack(JSONObject response, EnumNameApi nameApiEnum) {
//
//    }
//
//    @Override
//    public void onRestCallBack(VolleyError volleyError, EnumNameApi nameApiEnum) {
//
//    }
//
//    @Override
//    public void onRestCallBack(NetworkResponse networkResponse, EnumNameApi nameApiEnum) {
//
//    }
//
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        registerMessageReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                teacherAdapter.notifyDataSetChanged();
//            }
//        });
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if (teacherAdapter != null)
//            teacherAdapter.notifyDataSetChanged();
//    }
//}
