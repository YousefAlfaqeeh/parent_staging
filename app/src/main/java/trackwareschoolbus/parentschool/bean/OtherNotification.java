//package trackwareschoolbus.parentschool.bean;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//import android.os.Parcelable.Creator;
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//public class OtherNotification implements Parcelable
//{
//
//    @SerializedName("message")
//    @Expose
//    private String message;
//    @SerializedName("type")
//    @Expose
//    private String type;
//    @SerializedName("avatar")
//    @Expose
//    private String avatar;
//    @SerializedName("title")
//    @Expose
//    private String title;
//    public final static Parcelable.Creator<OtherNotification> CREATOR = new Creator<OtherNotification>() {
//
//
//        @SuppressWarnings({
//                "unchecked"
//        })
//        public OtherNotification createFromParcel(Parcel in) {
//            return new OtherNotification(in);
//        }
//
//        public OtherNotification[] newArray(int size) {
//            return (new OtherNotification[size]);
//        }
//
//    }
//            ;
//
//    protected OtherNotification(Parcel in) {
//        this.message = ((String) in.readValue((String.class.getClassLoader())));
//        this.type = ((String) in.readValue((String.class.getClassLoader())));
//        this.avatar = ((String) in.readValue((String.class.getClassLoader())));
//        this.title = ((String) in.readValue((String.class.getClassLoader())));
//    }
//
//    /**
//     * No args constructor for use in serialization
//     *
//     */
//    public OtherNotification() {
//    }
//
//    /**
//     *
//     * @param message
//     * @param title
//     * @param avatar
//     * @param type
//     */
//    public OtherNotification(String message, String type, String avatar, String title) {
//        super();
//        this.message = message;
//        this.type = type;
//        this.avatar = avatar;
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
//    public OtherNotification withMessage(String message) {
//        this.message = message;
//        return this;
//    }
//
//    public String getType() {
//        return type==null?"":type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public OtherNotification withType(String type) {
//        this.type = type;
//        return this;
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
//    public OtherNotification withAvatar(String avatar) {
//        this.avatar = avatar;
//        return this;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public OtherNotification withTitle(String title) {
//        this.title = title;
//        return this;
//    }
//
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeValue(message);
//        dest.writeValue(type);
//        dest.writeValue(avatar);
//        dest.writeValue(title);
//    }
//
//    public int describeContents() {
//        return 0;
//    }
//
//}