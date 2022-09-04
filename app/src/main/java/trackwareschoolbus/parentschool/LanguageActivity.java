package trackwareschoolbus.parentschool;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import trackwareschoolbus.parentschool.basePage.BaseActivity;
import trackwareschoolbus.parentschool.utilityParent.UtilityParent;


public class LanguageActivity extends BaseActivity {
    private View button_english, button_arabic;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**/
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        /**/
        setContentView(R.layout.language_main);
        /**/
        button_english = findViewById(R.id.button_english);
        button_arabic = findViewById(R.id.button_arabic);
        button_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilityParent.setStringShared(UtilityParent.LANGUAGE,"en");
                UtilityParent.setStringShared(UtilityParent.LANGUAGE_SELECTED,"en");
                UtilityParent.setLocale("en",LanguageActivity.this.getApplication());
                Intent intent = new Intent(LanguageActivity.this, MainFragmentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        button_arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilityParent.setStringShared(UtilityParent.LANGUAGE,"ar");
                UtilityParent.setStringShared(UtilityParent.LANGUAGE_SELECTED,"ar");
                UtilityParent.setLocale("ar",LanguageActivity.this.getApplication());
                Intent intent = new Intent(LanguageActivity.this, MainFragmentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


//        locationPermission(this,null);
//        gainPermission(new PermissionsLisiner() {
//            @Override
//            public void afterDialogDone(boolean permissionsAccepted) {
//            }
//        }, PermissionsList.location);
    }

    @Override
    public void onBackPressed() {
        FinishAppActivity.finishAppActivity(this);
    }


    //    public void onBackPressed(){
//        Intent a = new Intent(Intent.ACTION_MAIN);
//        a.addCategory(Intent.CATEGORY_HOME);
//        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(a);
//    }

//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }
}
