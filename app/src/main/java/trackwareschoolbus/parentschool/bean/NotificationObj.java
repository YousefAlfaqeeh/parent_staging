package trackwareschoolbus.parentschool.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class NotificationObj implements Parcelable {

    private final String actionTypeSchool = "school";
    private final String actionTypeUpdate = "update";
    private final String actionTypeDriver = "driver";
    private final String actionTypeNear = "near";
    private final String actionTypeArriveAlarm = "arrive_alarm";
    private final String actionTypeOther = "other";
    private final String actionTypeConfirmStudentPickUp = "confirm_student_pick_up";


    //
    public enum NotificationType {
        SCHOOL,
        UPDATE,
        DRIVER,
        NEAR,
        ARRIVE_ALARM,
        CONFIRM_PICKUP,
        OTHER
    }


    @SerializedName("action")
    private String action;

    @SerializedName("title")
    private String title;

    @SerializedName("message")
    private String message;

//    @SerializedName("data")
//    private NotificationObj data;


    public NotificationType getAction() {
        if (action == null)
            return NotificationType.OTHER;
        /**/
        switch (action) {
            case actionTypeSchool:
                return NotificationType.SCHOOL;
            case actionTypeUpdate:
                return NotificationType.UPDATE;
            case actionTypeDriver:
                return NotificationType.DRIVER;
            case actionTypeNear:
                return NotificationType.NEAR;
            case actionTypeArriveAlarm:
                return NotificationType.ARRIVE_ALARM;
            case actionTypeConfirmStudentPickUp:
                return NotificationType.CONFIRM_PICKUP;
            case actionTypeOther:
                return NotificationType.OTHER;
            default:
                return NotificationType.OTHER;
        }
        /**/
    }


//    {"title":null,"message":null,"avatar":null,"action":"update"}
//    {"title":"this is a title","message":"body","avatar":"","action":"school"}
//    {"title":"this is a title","message":"body","avatar":"","action":"driver"}
//    {"title":"this is a title","message":"body","avatar":"","action":"near"}
//    {"title":"this is a title","message":"body","avatar":"","action":"other"}


//    public String getAction() {
//        return action;
//    }

    public NotificationObj setAction(String action) {
        this.action = action;
        return this;
    }



    public String getTitle() {
        if (title==null || title.isEmpty())
            title= "Trackware - School Transport Parents";
        return title;
    }

    public NotificationObj setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public NotificationObj setMessage(String message) {
        this.message = message;
        return this;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.action);
        dest.writeString(this.title);
        dest.writeString(this.message);
    }

    public NotificationObj() {
    }

    protected NotificationObj(Parcel in) {
        this.action = in.readString();
        this.title = in.readString();
        this.message = in.readString();
    }

    public static final Parcelable.Creator<NotificationObj> CREATOR = new Parcelable.Creator<NotificationObj>() {
        @Override
        public NotificationObj createFromParcel(Parcel source) {
            return new NotificationObj(source);
        }

        @Override
        public NotificationObj[] newArray(int size) {
            return new NotificationObj[size];
        }
    };
}