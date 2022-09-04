package trackwareschoolbus.parentschool.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import trackwareschoolbus.parentschool.utilityParent.StringUtil;

public class NotificationBean implements Parcelable {

    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("notifications_title")
    @Expose
    private String notificationsTitle;
    @SerializedName("notifications_text")
    @Expose
    private String notificationsText;
    public final static Parcelable.Creator<NotificationBean> CREATOR = new Creator<NotificationBean>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NotificationBean createFromParcel(Parcel in) {
            return new NotificationBean(in);
        }

        public NotificationBean[] newArray(int size) {
            return (new NotificationBean[size]);
        }

    };

    protected NotificationBean(Parcel in) {
        this.dateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.avatar = ((String) in.readValue((String.class.getClassLoader())));
        this.notificationsTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.notificationsText = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public NotificationBean() {
    }

    /**
     * @param dateTime
     * @param notificationsText
     * @param avatar
     * @param notificationsTitle
     */
    public NotificationBean(String dateTime, String avatar, String notificationsTitle, String notificationsText) {
        super();
        this.dateTime = dateTime;
        this.avatar = avatar;
        this.notificationsTitle = notificationsTitle;
        this.notificationsText = notificationsText;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public NotificationBean withDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public NotificationBean withAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getNotificationsTitle() {
        return notificationsTitle;
    }

    public void setNotificationsTitle(String notificationsTitle) {
        this.notificationsTitle = notificationsTitle;
    }

    public NotificationBean withNotificationsTitle(String notificationsTitle) {
        this.notificationsTitle = notificationsTitle;
        return this;
    }

    public String getNotificationsText() {
        return notificationsText;
    }

    public void setNotificationsText(String notificationsText) {
        this.notificationsText = notificationsText;
    }

    public NotificationBean withNotificationsText(String notificationsText) {
        this.notificationsText = notificationsText;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(dateTime);
        dest.writeValue(avatar);
        dest.writeValue(notificationsTitle);
        dest.writeValue(notificationsText);
    }

    public int describeContents() {
        return 0;
    }

    public static final String ACTIVITY_TYPE_ABSENT = "absent";
    public static final String ACTIVITY_TYPE_ABSENT_BY_PARENT = "absent-by-parent";
    public static final String ACTIVITY_TYPE_IN = "in";
    public static final String ACTIVITY_TYPE_OUT = "out";
    public static final String ACTIVITY_TYPE_ADMIN_MESSAGE = "admin_message";
    public static final String ACTIVITY_TYPE_EMERGENCY = "emergency";
    public static final String ACTIVITY_TYPE_NO_SHOW = "no-show";
    /**/
    public static String RoundType_pick = "pick";
    /**/

//    public String getDateOnly() {
//        try {
//            return StringUtil.dateFormat_date_only_localTimeZone.format(getDateTime());
//        } catch (Exception e) {
//        }
//        return "";
//    }


//    public String getTimeOnly() {
//        try {
//            return StringUtil.dateFormat_time_only_12h_localTime.format(getDateTime());
//        } catch (Exception e) {
//        }
//        return "";
//    }

    public String getTitleAndText() {
        String title = getNotificationsTitle().equals("") ? "" : getNotificationsTitle() ;
        String text = getNotificationsText();
        return title +"\n"+ text;

    }

//    public String getTitleAndTextForDialog() {
//        String title = getNotificationsTitle().equals("") ? "" : getNotificationsTitle() + " : ";
//        String text = getNotificationsText();
//        return title +"\n"+ text;
//
//    }


}






