
package trackwareschoolbus.parentschool.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageHistoryObject implements Parcelable {

    @SerializedName("school_id")
    @Expose
    private Integer schoolId;
    @SerializedName("sender")
    @Expose
    private String sender;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("sender_id")
    @Expose
    private Integer senderId;
    public final static Parcelable.Creator<MessageHistoryObject> CREATOR = new Creator<MessageHistoryObject>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MessageHistoryObject createFromParcel(Parcel in) {
            return new MessageHistoryObject(in);
        }

        public MessageHistoryObject[] newArray(int size) {
            return (new MessageHistoryObject[size]);
        }

    };

    protected MessageHistoryObject(Parcel in) {
        this.schoolId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.sender = ((String) in.readValue((String.class.getClassLoader())));
        this.dateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.senderId = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public MessageHistoryObject() {
    }

    /**
     * @param message
     * @param sender
     * @param dateTime
     * @param senderId
     * @param schoolId
     * @param type
     */
    public MessageHistoryObject(Integer schoolId, String sender, String dateTime, String type, String message, Integer senderId) {
        super();
        this.schoolId = schoolId;
        this.sender = sender;
        this.dateTime = dateTime;
        this.type = type;
        this.message = message;
        this.senderId = senderId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(schoolId);
        dest.writeValue(sender);
        dest.writeValue(dateTime);
        dest.writeValue(type);
        dest.writeValue(message);
        dest.writeValue(senderId);
    }

    public int describeContents() {
        return 0;
    }

}