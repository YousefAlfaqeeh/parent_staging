//package trackwareschoolbus.parentschool.screens_v2.login_v2.request_and_response;
//
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//public class Kid implements Parcelable {
//
//    @SerializedName("name")
//    @Expose
//    public String name;
//    @SerializedName("id")
//    @Expose
//    public Integer id;
//    @SerializedName("avatar")
//    @Expose
//    public String avatar;
//    @SerializedName("school_id")
//    @Expose
//    public Integer schoolId;
//    @SerializedName("school_model")
//    @Expose
//    public String schoolModel;
//    @SerializedName("school_name")
//    @Expose
//    public String schoolName;
//    @SerializedName("school_mobile_number")
//    @Expose
//    public String schoolMobileNumber;
//    @SerializedName("student_grade")
//    @Expose
//    public String studentGrade;
//    @SerializedName("pick_up_lat")
//    @Expose
//    public String pickUpLat;
//    @SerializedName("pick_up_lng")
//    @Expose
//    public String pickUpLng;
//    @SerializedName("drop_off_lat")
//    @Expose
//    public String dropOffLat;
//    @SerializedName("drop_off_lng")
//    @Expose
//    public String dropOffLng;
//
//    public String getName() {
//        return name;
//    }
//
//    public Kid setName(String name) {
//        this.name = name;
//        return this;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public Kid setId(Integer id) {
//        this.id = id;
//        return this;
//    }
//
//    public String getAvatar() {
//        return avatar;
//    }
//
//    public Kid setAvatar(String avatar) {
//        this.avatar = avatar;
//        return this;
//    }
//
//    public Integer getSchoolId() {
//        return schoolId;
//    }
//
//    public Kid setSchoolId(Integer schoolId) {
//        this.schoolId = schoolId;
//        return this;
//    }
//
//    public String getSchoolModel() {
//        return schoolModel;
//    }
//
//    public Kid setSchoolModel(String schoolModel) {
//        this.schoolModel = schoolModel;
//        return this;
//    }
//
//    public String getSchoolName() {
//        return schoolName;
//    }
//
//    public Kid setSchoolName(String schoolName) {
//        this.schoolName = schoolName;
//        return this;
//    }
//
//    public String getSchoolMobileNumber() {
//        return schoolMobileNumber;
//    }
//
//    public Kid setSchoolMobileNumber(String schoolMobileNumber) {
//        this.schoolMobileNumber = schoolMobileNumber;
//        return this;
//    }
//
//    public String getStudentGrade() {
//        return studentGrade;
//    }
//
//    public Kid setStudentGrade(String studentGrade) {
//        this.studentGrade = studentGrade;
//        return this;
//    }
//
//    public String getPickUpLat() {
//        return pickUpLat;
//    }
//
//    public Kid setPickUpLat(String pickUpLat) {
//        this.pickUpLat = pickUpLat;
//        return this;
//    }
//
//    public String getPickUpLng() {
//        return pickUpLng;
//    }
//
//    public Kid setPickUpLng(String pickUpLng) {
//        this.pickUpLng = pickUpLng;
//        return this;
//    }
//
//    public String getDropOffLat() {
//        return dropOffLat;
//    }
//
//    public Kid setDropOffLat(String dropOffLat) {
//        this.dropOffLat = dropOffLat;
//        return this;
//    }
//
//    public String getDropOffLng() {
//        return dropOffLng;
//    }
//
//    public Kid setDropOffLng(String dropOffLng) {
//        this.dropOffLng = dropOffLng;
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
//        dest.writeString(this.name);
//        dest.writeValue(this.id);
//        dest.writeString(this.avatar);
//        dest.writeValue(this.schoolId);
//        dest.writeString(this.schoolModel);
//        dest.writeString(this.schoolName);
//        dest.writeString(this.schoolMobileNumber);
//        dest.writeString(this.studentGrade);
//        dest.writeString(this.pickUpLat);
//        dest.writeString(this.pickUpLng);
//        dest.writeString(this.dropOffLat);
//        dest.writeString(this.dropOffLng);
//    }
//
//    public Kid() {
//    }
//
//    protected Kid(Parcel in) {
//        this.name = in.readString();
//        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
//        this.avatar = in.readString();
//        this.schoolId = (Integer) in.readValue(Integer.class.getClassLoader());
//        this.schoolModel = in.readString();
//        this.schoolName = in.readString();
//        this.schoolMobileNumber = in.readString();
//        this.studentGrade = in.readString();
//        this.pickUpLat = in.readString();
//        this.pickUpLng = in.readString();
//        this.dropOffLat = in.readString();
//        this.dropOffLng = in.readString();
//    }
//
//    public static final Creator<Kid> CREATOR = new Creator<Kid>() {
//        @Override
//        public Kid createFromParcel(Parcel source) {
//            return new Kid(source);
//        }
//
//        @Override
//        public Kid[] newArray(int size) {
//            return new Kid[size];
//        }
//    };
//}