//package trackwareschoolbus.parentschool.screens_v2.login_v2.request_and_response;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//public class LogInRequest implements Parcelable {
//
//    @SerializedName("mobile_token")
//    @Expose
//    private String mobileToken;
//    @SerializedName("version_number")
//    @Expose
//    private Integer versionNumber;
//    @SerializedName("platform")
//    @Expose
//    private String platform;
//    @SerializedName("parent_pin")
//    @Expose
//    private String parentPin;
//    @SerializedName("mobile_number")
//    @Expose
//    private String mobileNumber;
//
//    public String getMobileToken() {
//        return mobileToken;
//    }
//
//    public LogInRequest setMobileToken(String mobileToken) {
//        this.mobileToken = mobileToken;
//        return this;
//    }
//
//    public Integer getVersionNumber() {
//        return versionNumber;
//    }
//
//    public LogInRequest setVersionNumber(Integer versionNumber) {
//        this.versionNumber = versionNumber;
//        return this;
//    }
//
//    public String getPlatform() {
//        return platform;
//    }
//
//    public LogInRequest setPlatform(String platform) {
//        this.platform = platform;
//        return this;
//    }
//
//    public String getParentPin() {
//        return parentPin;
//    }
//
//    public LogInRequest setParentPin(String parentPin) {
//        this.parentPin = parentPin;
//        return this;
//    }
//
//    public String getMobileNumber() {
//        return mobileNumber;
//    }
//
//    public LogInRequest setMobileNumber(String mobileNumber) {
//        this.mobileNumber = mobileNumber;
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
//        dest.writeString(this.mobileToken);
//        dest.writeValue(this.versionNumber);
//        dest.writeString(this.platform);
//        dest.writeString(this.parentPin);
//        dest.writeString(this.mobileNumber);
//    }
//
//    public LogInRequest() {
//    }
//
//    protected LogInRequest(Parcel in) {
//        this.mobileToken = in.readString();
//        this.versionNumber = (Integer) in.readValue(Integer.class.getClassLoader());
//        this.platform = in.readString();
//        this.parentPin = in.readString();
//        this.mobileNumber = in.readString();
//    }
//
//    public static final Creator<LogInRequest> CREATOR = new Creator<LogInRequest>() {
//        @Override
//        public LogInRequest createFromParcel(Parcel source) {
//            return new LogInRequest(source);
//        }
//
//        @Override
//        public LogInRequest[] newArray(int size) {
//            return new LogInRequest[size];
//        }
//    };
//}
