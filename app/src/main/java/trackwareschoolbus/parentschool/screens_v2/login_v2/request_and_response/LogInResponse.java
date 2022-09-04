//package trackwareschoolbus.parentschool.screens_v2.login_v2.request_and_response;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//import java.util.List;
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//import com.google.gson.reflect.TypeToken;
//
//
//public class LogInResponse implements Parcelable {
//
//    @SerializedName("status")
//    @Expose
//    public String status;
////    @SerializedName("kids")
////    @Expose
////    public List<Kid> kids = null;
//    @SerializedName("notifications_text")
//    @Expose
//    public List<NotificationsText> notificationsText = null;
//
//    public String getStatus() {
//        return status;
//    }
//
//    public LogInResponse setStatus(String status) {
//        this.status = status;
//        return this;
//    }
//
////    public List<Kid> getKids() {
////        return kids;
////    }
//
////    public LogInResponse setKids(List<Kid> kids) {
////        this.kids = kids;
////        return this;
////    }
//
//    public List<NotificationsText> getNotificationsText() {
//        return notificationsText;
//    }
//
//    public LogInResponse setNotificationsText(List<NotificationsText> notificationsText) {
//        this.notificationsText = notificationsText;
//        return this;
//    }
//
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(this.status);
////        dest.writeTypedList(this.kids);
//        dest.writeTypedList(this.notificationsText);
//    }
//
//    public LogInResponse() {
//    }
//
//    protected LogInResponse(Parcel in) {
//        this.status = in.readString();
////        this.kids = in.createTypedArrayList(Kid.CREATOR);
//        this.notificationsText = in.createTypedArrayList(NotificationsText.CREATOR);
//    }
//
//    public static final Creator<LogInResponse> CREATOR = new Creator<LogInResponse>() {
//        @Override
//        public LogInResponse createFromParcel(Parcel source) {
//            return new LogInResponse(source);
//        }
//
//        @Override
//        public LogInResponse[] newArray(int size) {
//            return new LogInResponse[size];
//        }
//    };
//
//
//
//
//}
