package trackwareschoolbus.parentschool;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import trackwareschoolbus.parentschool.utilityParent.LocalUtil;
import trackwareschoolbus.parentschool.utilityParent.UtilityParent;
//import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class LocalizationAndFontHandlerActivity extends PermissionsHandlerActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getResources().updateConfiguration(LocalUtil.getLocal(this,getSavedLanguage()), getApplicationContext().getResources().getDisplayMetrics());
        super.onCreate(savedInstanceState);

    }

//    @Override
//    protected void attachBaseContext(Context newBase) {
////        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }

    public void toggleLanguage() {
        String convertLanguageTo = getSavedLanguage().equals("ar") ? "en" : "ar";
        setAppLanguage(convertLanguageTo);
    }


    public void setAppLanguage(String convertLanguageTo) {
        UtilityParent.setStringShared(UtilityParent.LANGUAGE_SELECTED, convertLanguageTo);
        UtilityParent.setStringShared(UtilityParent.LANGUAGE, convertLanguageTo);
//        LocalUtil.setLocal(this,convertLanguageTo);
//        setLanguage(convertLanguageTo);
//        setDefaultLanguage(convertLanguageTo);
        restartActivity();
    }

    public String getSavedLanguage() {
        return UtilityParent.getStringShared(UtilityParent.LANGUAGE).equals("ar") ? "ar" : "en";
    }




    protected void restartActivity() {
        Intent intent = new Intent(this, MainFragmentActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(intent);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        String lang = UtilityParent.getStringShared(UtilityParent.LANGUAGE).equals("ar") ? "ar" : "en";
        setLocale(new Locale(lang));
    }

    private void setLocale(Locale locale) {
        Configuration conf = getBaseContext().getResources().getConfiguration();
        conf.locale = locale;
        getBaseContext().getResources().updateConfiguration(conf, getResources().getDisplayMetrics());
        Configuration systemConf = Resources.getSystem().getConfiguration();
        systemConf.locale = locale;
        Resources.getSystem().updateConfiguration(systemConf, Resources.getSystem().getDisplayMetrics());
        Locale.setDefault(locale);
    }

}
