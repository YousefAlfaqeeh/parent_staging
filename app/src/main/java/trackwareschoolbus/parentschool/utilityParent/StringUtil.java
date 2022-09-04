package trackwareschoolbus.parentschool.utilityParent;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import trackwareschoolbus.parentschool.R;


public class StringUtil {


//    public static String convertTimeToRangeString(long currentTimeMilliseconds) {
//        String today = "Today";
//        String at = "at";
//        String dateSpaces = "/";
//        String timeSpaces = ":";
//        StringBuilder finalFormatString = new StringBuilder();
//        if(DateUtils.isToday(currentTimeMilliseconds)) {
//            finalFormatString.append(today);
//            finalFormatString.append(" ");
//        }
//
//        return finalFormatString.toString();
//    }

    public static boolean isEmptyString(String text) {
        if (text == null) {
            return true;
        } else if (text.toString().trim().equals("")) {
            return true;
        }
        return false;
    }


    public static Calendar getPlusOneYearCalendar() {
        Calendar cal = Calendar.getInstance();
//		Date today = cal.getTime();
        cal.add(Calendar.YEAR, 1); // to get previous year add -1
//		Date nextYear = cal.getTime();
        return cal;
    }


//    public static String getNowDate() {
//        Calendar date = Calendar.getInstance();
//        date.set(Calendar.YEAR, 2017);
//        date.set(Calendar.MONTH, 29);
//        date.set(Calendar.MONTH, 12);
//
//        return dateFormat_general.format(date.getTime());
//
//    }


    public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate)) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }


    public static void callThisNumber(Context c, String number) {
        try {
            String uri = "tel:" + number;
            Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(uri));
//            if (ActivityCompat.checkSelfPermission(c, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                c.gainPermission(null, PermissionsList.call);
//                return;
//            }
            c.startActivity(callIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

//    public static final SimpleDateFormat server_date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
//
//    static {
//        server_date_format.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
//    }
//
//    /**/
//    /**/
//    public static final SimpleDateFormat dateFormat_date_only_localTimeZone = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//    public static final SimpleDateFormat dateFormat_time_only_12h_localTime = new SimpleDateFormat("hh:mm a", Locale.getDefault());
//
//    static {
//        dateFormat_date_only_localTimeZone.setTimeZone(TimeZone.getDefault());
//        dateFormat_time_only_12h_localTime.setTimeZone(TimeZone.getDefault());
//
//    }
//    public static final SimpleDateFormat dateFormat_date_only = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    /**/
//    public static final SimpleDateFormat dateFormat_from_notifications_api= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
//    public static final SimpleDateFormat dateFormat_from_notifications_api_localTime= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    /**/
//    public static final SimpleDateFormat dateFormat_time_only_12h = new SimpleDateFormat("hh:mm a", Locale.US);

    /**/
//    static {
//        TimeZone localTimeZone = TimeZone.getDefault();
//        /**/
//        dateFormat_from_notifications_api.setTimeZone(serverTimeZone);
//        dateFormat_from_notifications_api_localTime.setTimeZone(localTimeZone);
//        dateFormat_date_only.setTimeZone(serverTimeZone);
//        dateFormat_date_only_localTime.setTimeZone(localTimeZone);
//        dateFormat_time_only_12h.setTimeZone(serverTimeZone);
//        dateFormat_time_only_12h_localTime.setTimeZone(localTimeZone);
//
//    }


    //    public static final ArrayList<String> monthsDays = new ArrayList<String>(Arrays.asList(new String[]{"jan", " feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"}));
//    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
//	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
//    public static final SimpleDateFormat dateFormat_general = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
//    public static final SimpleDateFormat dateFormat_diagram = new SimpleDateFormat("MM/dd", Locale.US);
//    public static final SimpleDateFormat dateFormat_time = new SimpleDateFormat("HH:mm", Locale.US);
////    public static final SimpleDateFormat dateFormat_full = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
//    public static final SimpleDateFormat dateFormat_day = new SimpleDateFormat("dd", Locale.US);
//    public static final SimpleDateFormat dateFormat_month = new SimpleDateFormat("MMM", Locale.US);
//    public static final SimpleDateFormat dateFormat_dayOfmonth = new SimpleDateFormat("d", Locale.US);
//    public static final SimpleDateFormat dateFormat_dayOfWeek = new SimpleDateFormat("EEEE", Locale.US);
//    //	public static final SimpleDateFormat dateFormat_seconds = new SimpleDateFormat("ss", Locale.US);
//    public static final SimpleDateFormat dateFormat_topbar_date_en = new SimpleDateFormat("MMMM d,yyyy", Locale.US);
//    public static final SimpleDateFormat dateFormat_topbar_date_ar = new SimpleDateFormat("MMMM yyyy,d", Locale.US);//yyyy,dd MMMM
//    public static final SimpleDateFormat dateFormat_topbar_day = new SimpleDateFormat("EEEE", Locale.US);
//
//    public static final SimpleDateFormat dateFormat_yesr_month_for_compare = new SimpleDateFormat("MMMM,yyyy", Locale.US);


    public static ArrayList<String> updateWIDGET(ArrayList<String> duplicate_strings) {
//		List<String> al = new ArrayList<>();
        Set<String> hs = new HashSet<>();
        hs.addAll(duplicate_strings);
        duplicate_strings.clear();
        duplicate_strings.addAll(hs);
        return duplicate_strings;

    }


    public static int getRandomInteger() {
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

//    public static boolean isValidEmail(String email) {
//        String regExpn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
//                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
//                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
//                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
//                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
//                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
//
//        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(email);
//
//        return matcher.matches();
//    }

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

//    public static String getCurrentTime_GMT() {
//
//        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss");
//        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
//
//        return dateFormatGmt.format(new Date()) + "";
//    }

//    public static String parseDateToddMMyyyy(String time) {
//        if (time == null) {
//            return "";
//        }
//        if (time.isEmpty()) {
//            return "";
//        }
//
//        SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a", Locale.US);
//        SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
//
//        Date date = null;
//        String str = null;
//
//        try {
//            date = inputFormat.parse(time);
//            str = outputFormat.format(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return str;
//    }


    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] textBytes = text.getBytes("iso-8859-1");
        md.update(textBytes, 0, textBytes.length);
        byte[] sha1hash = md.digest();
        return convertToHex(sha1hash);
    }


    public static void hightLightText(TextView textView, String item, String mSearchText, Context c) {
        textView.setText(item);

        String fullText = item;
        // highlight search textView
        if (mSearchText != null && !mSearchText.isEmpty()) {
            int startPos = fullText.toLowerCase(Locale.US).indexOf(mSearchText.toLowerCase(Locale.US));
            int endPos = startPos + mSearchText.length();

            if (startPos != -1) {
                Spannable spannable = new SpannableString(fullText);
                ColorStateList blueColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{c.getResources().getColor(R.color.notification_hightlight_text)});
                TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);
                spannable.setSpan(highlightSpan, startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.setText(spannable);
            } else {
                textView.setText(fullText);
            }
        } else {
            textView.setText(fullText);
        }
    }


//    public static StringUtil  getTimeSince_Ago(String notificationDateOnly){
//        try {
//            StringBuilder stringBuilder = new StringBuilder();
//            long now = System.currentTimeMillis();
//            Date convertedDate = dateFormat_date_only.parse(notificationDateOnly);
//            CharSequence relavetime1 = DateUtils.getRelativeTimeSpanString(
//                    convertedDate.getTime(),
//                    now,
//                    DateUtils.DAY_IN_MILLIS);
//
//
//
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//    }
//    public static Spanned setStringColorHTML(String orgText , String coloredText , String color) {
//
//        return  Html.fromHtml(" <font color='"+color+"'>*</font>");
//
//
//
//    }

}


