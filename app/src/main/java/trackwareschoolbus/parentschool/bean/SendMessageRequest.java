
package trackwareschoolbus.parentschool.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SendMessageRequest implements Parcelable {

    @SerializedName("message")
    @Expose
    private GCMMessage message;
    @SerializedName("school_id")
    @Expose
    private Integer schoolId;
    @SerializedName("classroom_id")
    @Expose
    private Integer classroomId;
    @SerializedName("sender_id")
    @Expose
    private Integer senderId;
    @SerializedName("receivers_ids")
    @Expose
    private List<Integer> receiversIds = new ArrayList<Integer>();
    @SerializedName("receiver")
    @Expose
    private String receiver;
    @SerializedName("sender")
    @Expose
    private String sender;
    @SerializedName("message_type")
    @Expose
    private String messageType;
    @SerializedName("app_env")
    @Expose
    private String appEnv;
    public final static Creator<SendMessageRequest> CREATOR = new Creator<SendMessageRequest>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SendMessageRequest createFromParcel(Parcel in) {
            return new SendMessageRequest(in);
        }

        public SendMessageRequest[] newArray(int size) {
            return (new SendMessageRequest[size]);
        }

    };

    protected SendMessageRequest(Parcel in) {
        this.message = ((GCMMessage) in.readValue((GCMMessage.class.getClassLoader())));
        this.schoolId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.classroomId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.senderId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.receiversIds, (Integer.class.getClassLoader()));
        this.receiver = ((String) in.readValue((String.class.getClassLoader())));
        this.sender = ((String) in.readValue((String.class.getClassLoader())));
        this.messageType = ((String) in.readValue((String.class.getClassLoader())));
        this.appEnv = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public SendMessageRequest() {
    }

    /**
     * @param sender
     * @param message
     * @param receiver
     * @param senderId
     * @param schoolId
     * @param messageType
     * @param classroomId
     * @param appEnv
     * @param receiversIds
     */
    public SendMessageRequest(GCMMessage message, Integer schoolId, Integer classroomId, Integer senderId, List<Integer> receiversIds, String receiver, String sender, String messageType, String appEnv) {
        super();
        this.message = message;
        this.schoolId = schoolId;
        this.classroomId = classroomId;
        this.senderId = senderId;
        this.receiversIds = receiversIds;
        this.receiver = receiver;
        this.sender = sender;
        this.messageType = messageType;
        this.appEnv = appEnv;
    }

    public GCMMessage getMessage() {
        return message;
    }

    public void setMessage(GCMMessage message) {
        this.message = message;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public List<Integer> getReceiversIds() {
        return receiversIds;
    }

    public void setReceiversIds(List<Integer> receiversIds) {
        this.receiversIds = receiversIds;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getAppEnv() {
        return appEnv;
    }

    public void setAppEnv(String appEnv) {
        this.appEnv = appEnv;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(schoolId);
        dest.writeValue(classroomId);
        dest.writeValue(senderId);
        dest.writeList(receiversIds);
        dest.writeValue(receiver);
        dest.writeValue(sender);
        dest.writeValue(messageType);
        dest.writeValue(appEnv);
    }

    public int describeContents() {
        return 0;
    }

}
