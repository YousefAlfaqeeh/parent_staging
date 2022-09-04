//package trackwareschoolbus.parentschool.fragment.dialog;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Build;
//import android.support.annotation.NonNull;
//import android.view.View;
//import android.view.Window;
//import android.widget.Button;
//
//import trackwareschoolbus.parentschool.R;
//import trackwareschoolbus.parentschool.basePage.BaseDialog;
//
///**
// * Created by  on 3/9/2017.
// */
//
//
//
//public class CallDialog extends BaseDialog {
//
//    Button btnCallDriver;
//    Button btnCallSchool;
//    Activity mActivity;
//    View btnCancel;
//    public static String roundType;
//    Context mContext;
//
//    public CallDialog(@NonNull Context context) {
//        super(context);
//
//             declaerView();
//
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
//            btnCallDriver.setText(context.getResources().getString(R.string.pic_up));
//            btnCallSchool.setText(context.getResources().getString(R.string.drop_off));
//        btnCallDriver.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                roundType="drop-off";
////               MainActivity.showChangeLocationFragment(studentID);
//                dismiss();
//            }
//        });
//
//
//        btnCallSchool.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                roundType="pick-up";
////                MainActivity.showChangeLocationFragment(studentID);
//                dismiss();
//            }
//        });
//        }
//
//
//
//    public CallDialog(@NonNull Context context, final String driverPhoneNumber, final String schoolPhoneNumber ) {
//
//        super(context);
//
//
//          declaerView();
//
//
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
//
//        btnCallDriver.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//               /* MainActivity.makeCall(driverPhoneNumber);*/
//                dismiss();
//            }
//        });
//
//
//        btnCallSchool.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//              /*  MainActivity.makeCall(schoolPhoneNumber);*/
//                dismiss();
//            }
//        });
//
//    }
//    public void declaerView(){
//
//        mContext=getContext();
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setBackgroundDrawableResource(R.drawable.dialog_regtangel_round_shape);
//        setCancelable(false);
//        setContentView(R.layout.dialog_call_new_);
//        btnCancel= findViewById(R.id.btnCancel);
//        btnCallDriver=findViewById(R.id.btnCallDriver);
//        btnCallSchool= findViewById(R.id.btnCallSchool);
//
//
//    }
//}
