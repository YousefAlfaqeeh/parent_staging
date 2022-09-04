
package trackwareschoolbus.parentschool.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageHistoryRequest implements Parcelable
{

    @SerializedName("receiver_id")
    @Expose
    private Integer receiverId;
    @SerializedName("receiver")
    @Expose
    private String receiver;
    @SerializedName("classroom_id")
    @Expose
    private Integer classroomId;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    public final static Parcelable.Creator<MessageHistoryRequest> CREATOR = new Creator<MessageHistoryRequest>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MessageHistoryRequest createFromParcel(Parcel in) {
            return new MessageHistoryRequest(in);
        }

        public MessageHistoryRequest[] newArray(int size) {
            return (new MessageHistoryRequest[size]);
        }

    }
            ;

    protected MessageHistoryRequest(Parcel in) {
        this.receiverId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.receiver = ((String) in.readValue((String.class.getClassLoader())));
        this.classroomId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.startDate = ((String) in.readValue((String.class.getClassLoader())));
        this.endDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public MessageHistoryRequest() {
    }

    /**
     *
     * @param startDate
     * @param receiver
     * @param receiverId
     * @param endDate
     * @param classroomId
     */
    public MessageHistoryRequest(Integer receiverId, String receiver, Integer classroomId, String startDate, String endDate) {
        super();
        this.receiverId = receiverId;
        this.receiver = receiver;
        this.classroomId = classroomId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(receiverId);
        dest.writeValue(receiver);
        dest.writeValue(classroomId);
        dest.writeValue(startDate);
        dest.writeValue(endDate);
    }

    public int describeContents() {
        return 0;
    }

}