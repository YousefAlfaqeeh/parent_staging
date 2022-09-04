package trackwareschoolbus.parentschool.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.core.content.ContextCompat;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class OpenHelper extends SQLiteAssetHelper {


    public static final String DATABASE_NAME = "LocationData3.sqlite";

//    public void getAvailable(Context context) {
//        String state = Environment.getExternalStorageState();
//        if (state.equals(Environment.MEDIA_MOUNTED) && (!state.equals(Environment.MEDIA_MOUNTED_READ_ONLY))) {
//            File removable = ContextCompat.getExternalFilesDirs(context, null)[1];
//            String THE_PATH = String.valueOf(removable);
//            if (THE_PATH.length() > 4) {
//                THE_PATH = THE_PATH + "/Documents/";
//            }
//
//        }
//    }

    private static final int DATABASE_VERSION = 1;

    //    Dirs[0] ==> Internal Sorage
//    Dirs[1] ==> External Storage
    private OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

//        super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);
//        super(context, DATABASE_NAME, ContextCompat.getExternalFilesDirs(context, null)[1].getAbsolutePath(), DATABASE_VERSION);
        Log.e("Database", "current version: " + getWritableDatabase().getVersion());
        setForcedUpgrade();

    }

    private OpenHelper(Context context, String absolute_path, String database_name) {
        super(context, database_name, absolute_path, null, DATABASE_VERSION);
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("Database", "current version: " + getWritableDatabase().getVersion());
        setForcedUpgrade();
    }

    public static SQLiteDatabase getDatabase(Context context, String absolute_path, String database_name) {
        return new OpenHelper(context, absolute_path, database_name).getWritableDatabase();
    }

    public static SQLiteDatabase getDatabase(Context context) {
        return new OpenHelper(context).getWritableDatabase();
    }


}
