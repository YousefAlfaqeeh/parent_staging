//package trackwareschoolbus.parentschool.fragment.dialog;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.view.View;
//import android.view.Window;
//import android.widget.Button;
//import android.widget.EditText;
//
//import com.android.volley.NetworkResponse;
//import com.android.volley.VolleyError;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//
//import trackwareschoolbus.parentschool.API.enumApi.EnumMethodApi;
//import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
//import trackwareschoolbus.parentschool.API.enumApi.EnumTypeHeader;
//import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallBack;
//import trackwareschoolbus.parentschool.API_new.APIs.Constants;
//import trackwareschoolbus.parentschool.R;
//import trackwareschoolbus.parentschool.basePage.BaseDialog;
//import trackwareschoolbus.parentschool.interfaceParent.IRestCallBackActivation;
//import trackwareschoolbus.parentschool.utilityParent.StaticValue;
//import trackwareschoolbus.parentschool.utilityParent.UtilityParent;
//
////import trackwareschoolbus.parentschool.PhoenixPlug;
//
///**
// * Created by  on 3/8/2017.
// */
//
//public class ActivationDialog extends BaseDialog implements IRestCallBack {
//
//    private int kidID;
//    private IRestCallBackActivation iRestCallBackActivation;
//    EditText txtActivate;
//    Button btnActivity;
//    Button btnCancel;
//
//    public ActivationDialog(@NonNull Context context, IRestCallBackActivation iRestCallBackActivation, final int kidID) {
//        super(context);
//        this.iRestCallBackActivation = iRestCallBackActivation;
//        this.kidID = kidID;
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setBackgroundDrawableResource(R.drawable.dialog_regtangel_round_shape);
//        setCancelable(false);
//        setContentView(R.layout.dialog_activation);
//        btnActivity = (Button) findViewById(R.id.btnActivity);
//        btnCancel = (Button) findViewById(R.id.btnCancel);
//        txtActivate = (EditText) findViewById(R.id.txtActivate);
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
//        btnActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String activationCode = txtActivate.getText().toString();
//                if (UtilityParent.isEmptyString(activationCode)) {
//                    UtilityParent.showMessage(mActivity, mActivity.getString(R.string.error), mActivity.getString(R.string.message_activate));
//                    return;
//                }
//                if (StaticValue.progressDialog != null) {
//                    StaticValue.progressDialog.show();
//                }
//                callRestAPI(Constants.Urls.ACTIVATE_KID,
//                        new HashMap() {{
//                            put("student_id", kidID);
//                            put("activation_code", activationCode);
//                        }},
//                        EnumMethodApi.POST,
//                        ActivationDialog.this,
//                        EnumNameApi.ACTIVATE_KID,
//                        UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true),
//                        EnumTypeHeader.JSON
//                );
//            }
//        });
//
//    }
//
//    @Override
//    public void onRestCallBack(String response, EnumNameApi nameApiEnum) {
//
//    }
//
//    @Override
//    public void onRestCallBack(JSONObject response, EnumNameApi nameApiEnum) {
//        if (StaticValue.progressDialog != null) {
//            StaticValue.progressDialog.dismiss();
//        }
//        String status = null;
//        try {
//            status = response.getString("status");
//            if (status.contains("Student activated successfully")) {
//                dismiss();
//                iRestCallBackActivation.done();
//            } else {
//                UtilityParent.showMessage(mActivity, mActivity.getString(R.string.error), status);
//            }
//        } catch (JSONException e) {
//            UtilityParent.showMessage(mActivity, mActivity.getString(R.string.error_api), mActivity.getString(R.string.error) + " " + mActivity.getString(R.string.message_activate));
//
//        }
//
//    }
//
//    @Override
//    public void onRestCallBack(VolleyError volleyError, EnumNameApi nameApiEnum) {
//        if (StaticValue.progressDialog != null) {
//            StaticValue.progressDialog.dismiss();
//        }
//        UtilityParent.showMessage(mActivity, mActivity.getString(R.string.error_api), mActivity.getString(R.string.error) + " " + mActivity.getString(R.string.message_activate));
//
//    }
//
//    @Override
//    public void onRestCallBack(NetworkResponse networkResponse, EnumNameApi nameApiEnum) {
//
//    }
//}
