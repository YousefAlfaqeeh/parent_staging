package trackwareschoolbus.parentschool.utilityParent;

/**
 * Created by  on 2/4/2018.
 */

public interface PermissionsList {
    String[] all = new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.CAMERA};
    String[] location = new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION};
    String[] camera = new String[]{android.Manifest.permission.CAMERA};
//    String[] call = new String[]{android.Manifest.permission.CALL_PHONE};
//    String[] storage = new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
}
