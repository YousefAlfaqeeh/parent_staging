package trackwareschoolbus.parentschool.basePage;

import static trackwareschoolbus.parentschool.utilityParent.StaticValue.mActivity;

import android.app.ProgressDialog;
import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

import trackwareschoolbus.parentschool.API.ApiFacade;
import trackwareschoolbus.parentschool.API.ApiRequest;
import trackwareschoolbus.parentschool.API.enumApi.EnumMethodApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumTypeHeader;
import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallBack;
import trackwareschoolbus.parentschool.AppUpdateCheckerActivity;
import trackwareschoolbus.parentschool.R;


public class BaseActivity extends AppUpdateCheckerActivity {

    protected void callRestAPI(
            String PATH_URL,
            HashMap params,
            EnumMethodApi verb,
            IRestCallBack restCallBack,
            EnumNameApi enumNameApi,
            Map<String, String> mapHeader,
            EnumTypeHeader enumTypeHeader
    ) {

        ApiFacade callApi = new ApiFacade();
        callApi.onStartVolley(new ApiRequest(PATH_URL,
                        params,
                        verb,
                        restCallBack,
                        enumNameApi,
                        mapHeader
                )
                ,
                enumTypeHeader
        );
    }

    private ProgressDialog mprogressDialog;

    public ProgressDialog showProgressDialoge() {
        try {
            if (mprogressDialog == null) {
                mprogressDialog = ProgressDialog.show(mActivity, "",
                        getString(R.string.waiting), true);
            } else if (!mprogressDialog.isShowing()) {
                mprogressDialog.show();
            }
        } catch (Exception e) {

        }

        return mprogressDialog;

    }

    public void hideProgressDialoge() {
        try {
            if (mprogressDialog != null) {
                mprogressDialog.dismiss();
            }
        } catch (Exception e) {

        }

    }


    public void closeApplication() {

        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }


//    public void gainAllPermissions(PermissionsHandlerActivity.PermissionsLisiner lisiner) {
//        super.gainPermission(lisiner, PermissionsList.all);
//    }
//
//    public void gainPermission(PermissionsHandlerActivity.PermissionsLisiner permissionsLisiner, String... permissions) {
//        super.gainPermission(permissionsLisiner, permissions);
//    }


}
