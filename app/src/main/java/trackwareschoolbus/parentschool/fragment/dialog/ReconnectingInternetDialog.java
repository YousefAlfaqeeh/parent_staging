package trackwareschoolbus.parentschool.fragment.dialog;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import android.view.Window;

import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.basePage.BaseDialog;

/**
 * Created by  on 7/10/17.
 */

public class ReconnectingInternetDialog extends BaseDialog {
    public ReconnectingInternetDialog(@NonNull Context context) {
        super(context);
        mActivity = (Activity) context;
        startDialog();
    }


    public void startDialog() {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(R.drawable.dialog_regtangel_round_shape);
        setCancelable(false);
        setContentView(R.layout.dialog_reconnecting_internet);


    }
}
