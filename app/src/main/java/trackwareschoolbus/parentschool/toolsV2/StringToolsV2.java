package trackwareschoolbus.parentschool.toolsV2;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import androidx.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringToolsV2 {


    public static CharSequence highlightText(String search, String originalText) {
        if (search != null && !search.equalsIgnoreCase("")) {
            String normalizedText = Normalizer.normalize(originalText, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
            int start = normalizedText.indexOf(search);
            if (start < 0) {
                return originalText;
            } else {
                Spannable highlighted = new SpannableString(originalText);
                while (start >= 0) {
                    int spanStart = Math.min(start, originalText.length());
                    int spanEnd = Math.min(start + search.length(), originalText.length());
                    highlighted.setSpan(new ForegroundColorSpan(Color.BLUE), spanStart, spanEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    start = normalizedText.indexOf(search, spanEnd);
                }
                return highlighted;
            }
        }
        return originalText;
    }


    public static boolean isEmptyString(String text) {
        if (text == null) {
            return true;
        } else return text.trim().equals("");
    }


    @NonNull
    public static String getEnglishString(int stringId, Context c) {
        Configuration configuration = getLanguageConfiguration("en", c);
        return c.createConfigurationContext(configuration).getResources().getString(stringId);
    }

    @NonNull
    public static String[] getEnglishArray(int stringId, Context c) {
        Configuration configuration = getLanguageConfiguration("en", c);
        return c.createConfigurationContext(configuration).getResources().getStringArray(stringId);
    }

    @NonNull
    public static String[] getArabicArray(int stringId, Context c) {
        Configuration configuration = getLanguageConfiguration("ar", c);
        return c.createConfigurationContext(configuration).getResources().getStringArray(stringId);
    }

    @NonNull
    public static String getArabicString(int stringId, Context c) {
        Configuration configuration = getLanguageConfiguration("ar", c);
        return c.createConfigurationContext(configuration).getResources().getString(stringId);
    }


    @NonNull
    public static Configuration getLanguageConfiguration(String lang, Context c) {
        Configuration configuration = new Configuration(c.getResources().getConfiguration());
        configuration.setLocale(new Locale(lang));
        return configuration;
    }


    public static int getRandom() {
        try {

            SecureRandom generator = SecureRandom.getInstance("SHA1PRNG");
            int n = 10000;
            n = generator.nextInt(n);
            return n;

        } catch (Exception e) {
            return 10000;
        }

    }

    public static boolean isEmptyStrings(String... array) {
        for (String str : array) {
            if (str == null || str.length() == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidEmail(String email) {
        String regExpn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }


    public static String strBuilder(Object... objects) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Object object : objects) {
            try {
                stringBuilder.append(object.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

}
