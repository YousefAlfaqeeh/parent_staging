
package trackwareschoolbus.parentschool.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendMessageResponse implements Parcelable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("receiver_id")
    @Expose
    private Integer receiverId;
    @SerializedName("details")
    @Expose
    private String details;
    public final static Parcelable.Creator<SendMessageResponse> CREATOR = new Creator<SendMessageResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SendMessageResponse createFromParcel(Parcel in) {
            return new SendMessageResponse(in);
        }

        public SendMessageResponse[] newArray(int size) {
            return (new SendMessageResponse[size]);
        }

    };

    protected SendMessageResponse(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.receiverId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.details = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public SendMessageResponse() {
    }

    /**
     * @param details
     * @param receiverId
     * @param status
     */
    public SendMessageResponse(String status, Integer receiverId, String details) {
        super();
        this.status = status;
        this.receiverId = receiverId;
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(receiverId);
        dest.writeValue(details);
    }

    public int describeContents() {
        return 0;
    }

}