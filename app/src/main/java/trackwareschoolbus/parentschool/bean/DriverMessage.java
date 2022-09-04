//package trackwareschoolbus.parentschool.bean;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//public class DriverMessage implements Parcelable {
//
//    @SerializedName("student_name")
//    @Expose
//    private String studentName;
//    @SerializedName("round_type")
//    @Expose
//    private String roundType;
//    @SerializedName("title")
//    @Expose
//    private String title;
//    @SerializedName("message")
//    @Expose
//    private String message;
//    @SerializedName("avatar")
//    @Expose
//    private String avatar;
//
//    /**
//     * No args constructor for use in serialization
//     */
//    public DriverMessage() {
//    }
//
//    /**
//     * @param message
//     * @param roundType
//     * @param title
//     * @param studentName
//     * @param avatar
//     */
//    public DriverMessage(String studentName, String roundType, String title, String message, String avatar) {
//        super();
//        this.studentName = studentName;
//        this.roundType = roundType;
//        this.title = title;
//        this.message = message;
//        this.avatar = avatar;
//    }
//
//    public String getStudentName() {
//        return studentName;
//    }
//
//    public void setStudentName(String studentName) {
//        this.studentName = studentName;
//    }
//
//    public String getRoundType() {
//        return roundType;
//    }
//
//    public void setRoundType(String roundType) {
//        this.roundType = roundType;
//    }
//
//    public String getTitle() {
//        try {
//            return title.trim().replaceAll("driver_", "").trim();
//        } catch (Exception e) {
//            return "";
//        }
//
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public String getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(String avatar) {
//        this.avatar = avatar;
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
//        dest.writeString(this.studentName);
//        dest.writeString(this.roundType);
//        dest.writeString(this.title);
//        dest.writeString(this.message);
//        dest.writeString(this.avatar);
//    }
//
//    protected DriverMessage(Parcel in) {
//        this.studentName = in.readString();
//        this.roundType = in.readString();
//        this.title = in.readString();
//        this.message = in.readString();
//        this.avatar = in.readString();
//    }
//
//    public static final Creator<DriverMessage> CREATOR = new Creator<DriverMessage>() {
//        @Override
//        public DriverMessage createFromParcel(Parcel source) {
//            return new DriverMessage(source);
//        }
//
//        @Override
//        public DriverMessage[] newArray(int size) {
//            return new DriverMessage[size];
//        }
//    };
//
////    public String getFullTitle(Context c) {
////        String round_type = getRoundType().equalsIgnoreCase("DROP_ROUND") ? c.getString(R.string.notification_text_drop) : c.getString(R.string.notification_text_pick);
////        return round_type + ", " + getFirstName();
////    }
//
//    public String getFirstName() {
//        try {
//            return getStudentName().trim().split(" ")[0];
//        } catch (Exception e) {
//            return "";
//        }
//    }
//}