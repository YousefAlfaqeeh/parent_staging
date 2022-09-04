package trackwareschoolbus.parentschool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

import trackwareschoolbus.parentschool.basePage.BaseActivity;


public class FinishAppActivity extends BaseActivity {


    public static void finishAppActivity(Context c) {
//        Intent intent = new Intent(c, FinishAppActivity.class);
////        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//        c.startActivity(intent);

        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        c.startActivity(startMain);

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finish();
    }
}
