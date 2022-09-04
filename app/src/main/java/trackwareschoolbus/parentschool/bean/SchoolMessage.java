//
//package trackwareschoolbus.parentschool.bean;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//import android.os.Parcelable.Creator;
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//public class SchoolMessage implements Parcelable
//{
//
//    @SerializedName("message")
//    @Expose
//    private String message;
//    @SerializedName("title")
//    @Expose
//    private String title;
//    @SerializedName("avatar")
//    @Expose
//    private String avatar;
//    public final static Creator<SchoolMessage> CREATOR = new Creator<SchoolMessage>() {
//
//
//        @SuppressWarnings({
//            "unchecked"
//        })
//        public SchoolMessage createFromParcel(Parcel in) {
//            return new SchoolMessage(in);
//        }
//
//        public SchoolMessage[] newArray(int size) {
//            return (new SchoolMessage[size]);
//        }
//
//    }
//    ;
//
//    protected SchoolMessage(Parcel in) {
//        this.message = ((String) in.readValue((String.class.getClassLoader())));
//        this.title = ((String) in.readValue((String.class.getClassLoader())));
//        this.avatar = ((String) in.readValue((String.class.getClassLoader())));
//    }
//
//    /**
//     * No args constructor for use in serialization
//     *
//     */
//    public SchoolMessage() {
//    }
//
//    /**
//     *
//     * @param message
//     * @param title
//     * @param avatar
//     */
//    public SchoolMessage(String message, String title, String avatar) {
//        super();
//        this.message = message;
//        this.title = title;
//        this.avatar = avatar;
//    }
//
//    public String getMessage() {
//        return message.trim();
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public String getTitle() {
//        return title.replaceAll("school_","").trim();
//    }
//    public void setTitle(String title) {
//        this.title = title;
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
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeValue(message);
//        dest.writeValue(title);
//        dest.writeValue(avatar);
//    }
//
//    public int describeContents() {
//        return  0;
//    }
//
//}
