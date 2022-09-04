//package trackwareschoolbus.parentschool.fragment.dialog;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.view.View;
//import android.view.Window;
//import android.widget.Button;
//import android.widget.TextView;
//
//import trackwareschoolbus.parentschool.R;
//import trackwareschoolbus.parentschool.basePage.BaseDialog;
//import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener;
//
////import trackwareschoolbus.parentschool.PhoenixPlug;
//
///**
// * Created by  on 3/8/2017.
// */
//
//public class ConfermYesNoDialog extends BaseDialog {
//
//
//    Button btnSubmit;
//    Button btnCancel;
//    TextView labTitle;
//    Context mContext;
//    OnActionDoneListener<ConfermYesNoDialog> onCancelListener;
//
//    public ConfermYesNoDialog(@NonNull Context context, final String title, final OnActionDoneListener<ConfermYesNoDialog> onActionDoneListener) {
//        super(context);
//        declaerView();
//
//
//        labTitle.setText(title);
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//                if (onCancelListener != null)
//                    onCancelListener.OnActionDone(null);
//            }
//        });
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//                onActionDoneListener.OnActionDone(ConfermYesNoDialog.this);
//            }
//        });
//
//    }
//
//    public ConfermYesNoDialog onCancel(OnActionDoneListener<ConfermYesNoDialog> onCancelListener) {
//        this.onCancelListener = onCancelListener;
//        return this;
//
//    }
//
//    public void declaerView() {
//        mContext = getContext();
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setBackgroundDrawableResource(R.drawable.dialog_regtangel_round_shape);
//        setCancelable(false);
//        setContentView(R.layout.dialog_confirm_yes_no);
//        btnSubmit = (Button) findViewById(R.id.btnSubmint);
//        btnCancel = (Button) findViewById(R.id.btnCancel);
//        labTitle = (TextView) findViewById(R.id.labTitle);
//    }
//
//
//}
