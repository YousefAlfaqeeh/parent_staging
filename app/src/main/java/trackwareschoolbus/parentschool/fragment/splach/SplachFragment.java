//package trackwareschoolbus.parentschool.fragment.splach;
//
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.android.volley.NetworkResponse;
//import com.android.volley.VolleyError;
//
//import org.json.JSONObject;
//
//import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
//import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallBack;
//import trackwareschoolbus.parentschool.R;
//import trackwareschoolbus.parentschool.basePage.BaseFragment;
//
///**
// * Created by  on 8/1/17.
// */
//
//public class SplachFragment extends BaseFragment implements IRestCallBack {
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        final View view = inflater.inflate(R.layout.fragment_splach, container, false);
////        boolean isFromnotification = false;
////        Bundle bundle = this.getArguments();
////        if (bundle != null) {
////             isFromnotification = bundle.getBoolean("isFromnotification");
////        }
//
//
////        if (!UtilityParent.getBooleanShared(UtilityParent.IS_REGISTER)){
////          /*  callIsRegister();*/
////            MainActivity.showRegisterFragment();
////
////        }else if(UtilityParent.getBooleanShared(UtilityParent.IS_LOgIn) && isFromnotification){
////            MainActivity.showNotificationFragment();
////        }else if(!UtilityParent.getBooleanShared(UtilityParent.IS_LOgIn)){
////            MainActivity.showLogInFragment();
////        }else {
////            MainActivity.showMyKidsFragment();
////        }
//        return view;
//    }
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
//}
