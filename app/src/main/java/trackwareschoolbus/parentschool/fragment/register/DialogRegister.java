//package trackwareschoolbus.parentschool.fragment.register;
//
//import android.app.Activity;
//import androidx.annotation.NonNull;
//import android.view.View;
//import android.view.Window;
//import android.widget.Button;
//import android.widget.EditText;
//
//import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
//import trackwareschoolbus.parentschool.R;
//import trackwareschoolbus.parentschool.basePage.BaseDialog;
//import trackwareschoolbus.parentschool.utilityParent.UtilityParent;
//
///**
// * Created by  on 8/6/17.
// */
//
//public class DialogRegister extends BaseDialog {
////    RegisterPresenter mPresenter;
//    EditText txtUrl;
//    Button btnSubmit, btnCancel;
//
//    public DialogRegister(@NonNull Activity context) {
//        super(context);
//        mActivity = context;
//        mPresenter=new RegisterPresenter(mActivity);
//        declaerView();
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String urlPath = txtUrl.getText().toString();
//              if (!UtilityParent.isEmptyString(urlPath)){
//                  String secretToken[] = urlPath.split("/");
//                  RegisterFragment.registerBean.setSecretToken(secretToken[secretToken.length-1]);
//                  UtilityParent.setStringShared(UtilityParent.SECREET_TOKEN,  RegisterFragment.registerBean.getSecretToken());
//                  mPresenter.callRegister(RegisterFragment.registerBean, EnumNameApi.REGISTER_DIALOG);
//                  dismiss();
//
//              }else{
//                  UtilityParent.showMessage(mActivity,mActivity.getString(R.string.error),mActivity.getString(R.string.please_enter_url));
//              }
//            }
//        });
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
//
//
//    }
//
//
//    public void declaerView() {
//
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setBackgroundDrawableResource(R.drawable.dialog_regtangel_round_shape);
//        setCancelable(false);
//        setContentView(R.layout.dialog_again_register);
//
//        txtUrl = (EditText) findViewById(R.id.txtUrl);
//        btnSubmit = (Button) findViewById(R.id.btnSubmit);
//        btnCancel = (Button) findViewById(R.id.btnCancel);
//        show();
//    }
//}
