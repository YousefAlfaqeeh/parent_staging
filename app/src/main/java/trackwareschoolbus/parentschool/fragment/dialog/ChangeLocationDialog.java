//package trackwareschoolbus.parentschool.fragment.dialog;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.view.Window;
//import android.widget.Button;
//
//import com.android.volley.NetworkResponse;
//import com.android.volley.VolleyError;
//
//import org.json.JSONObject;
//
//import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
//import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallBack;
//import trackwareschoolbus.parentschool.R;
//import trackwareschoolbus.parentschool.basePage.BaseDialog;
//
///**
// * Created by  on 4/25/2017.
// */
//
//public class ChangeLocationDialog extends BaseDialog implements IRestCallBack{
//
//
//    Button btnSaveLocation;
//
//    public ChangeLocationDialog(@NonNull Context context ) {
//        super(context);
//
//
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setBackgroundDrawableResource(R.drawable.dialog_regtangel_round_shape);
//        setCancelable(false);
//        setContentView(R.layout.dialog_change_location);
//
//        /*btnSaveLocation=(Button) findViewById(R.id.btnSaveLocattion);
//
//        btnSaveLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//              //  callChangeLocation(lat,lng);
//                dismiss();
//            }
//        });*/
//    }
//
////
////    public void callChangeLocation(final double lat, final double log) {
////
////
////        callRestAPI(PathUrl.MAIN_URL + PathUrl.PARENT_NOTIFICATION,
////                new HashMap<String, Object>() {{
////                    put("name", "changed_location");
////                    put("location_type", CallDialog.roundType);
////                    put("long", log );
////                    put("lat", lat );
////
////                }},
////                EnumMethodApi.POST,
////                ChangeLocationDialog.this,
////                EnumNameApi.CHANGE_LOCATION,
////                UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true),
////                EnumTypeHeader.JSON
////        );
////
////    }
//
//      public void disimissDialog(){
//          dismiss();
//      }
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
