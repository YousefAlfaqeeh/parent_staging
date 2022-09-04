package trackwareschoolbus.parentschool.utilityParent;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;


public abstract class LocalUtil {



//	private static String LANG = "lang";

//	public static void setLocal(Context context, String local) {
//		SharedPreferences prefs = context.getSharedPreferences(LANG, context.MODE_PRIVATE);
//		SharedPreferences.Editor editor = prefs.edit();
//		editor.putString(LANG, local);
//		editor.commit();
//	}

//	public static void setLanguageToUS(Context context) {
//		setLocal(context,"en");
//	}
//	public static void setLanguageToAR(Context context) {
//		setLocal(context,"ar");
//	}

//	public static String getLocalLangCode(Context context) {
//		SharedPreferences prefs = context.getSharedPreferences(LANG, context.MODE_PRIVATE);
//		String lang = prefs.getString(LANG, "en");
//		return lang;
//	}
//
//
//
//
	public static Configuration getLocal(Context context,String savedLang) {
		Configuration config = new Configuration();
		Locale locale = new Locale(savedLang);
		Locale.setDefault(locale);
		config.locale = locale;
		return config;
	}

}
