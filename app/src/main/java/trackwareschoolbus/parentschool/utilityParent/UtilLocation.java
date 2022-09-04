package trackwareschoolbus.parentschool.utilityParent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;

/**
 * Created by  on 1/21/2018.
 */

public class UtilLocation {

    public static boolean islocationAvailable(Activity activity) {
        LocationManager lm = (LocationManager) activity.getSystemService(activity.LOCATION_SERVICE);
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER) || !lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            return false;

        }
        return true;
    }


}
