
package trackwareschoolbus.parentschool.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeacherBean implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("is_assistants")
    @Expose
    private Boolean isAssistants;
    @SerializedName("school_id")
    @Expose
    private Integer schoolId;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("secret_token")
    @Expose
    private String secretToken;
    @SerializedName("mobile_token")
    @Expose
    private String mobileToken;
    @SerializedName("photo")
    @Expose
    private Object photo;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("pin")
    @Expose
    private String pin;
    @SerializedName("platform")
    @Expose
    private String platform;
    @SerializedName("staff_id")
    @Expose
    private Integer staffId;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("settings")
    @Expose
    private String settings;
    @SerializedName("class_room_id")
    @Expose
    private Integer classRoomId;
    @SerializedName("deleted_at")
    @Expose
    private String deletedAt;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("chatting_end_time")
    @Expose
    private String chattingEndTime;
    @SerializedName("chatting_start_time")
    @Expose
    private String chattingStartTime;
    public final static Parcelable.Creator<TeacherBean> CREATOR = new Creator<TeacherBean>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TeacherBean createFromParcel(Parcel in) {
            return new TeacherBean(in);
        }

        public TeacherBean[] newArray(int size) {
            return (new TeacherBean[size]);
        }

    }
            ;

    protected TeacherBean(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.isAssistants = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.schoolId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.subject = ((String) in.readValue((String.class.getClassLoader())));
        this.secretToken = ((String) in.readValue((String.class.getClassLoader())));
        this.mobileToken = ((String) in.readValue((String.class.getClassLoader())));
        this.photo = ((Object) in.readValue((Object.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.pin = ((String) in.readValue((String.class.getClassLoader())));
        this.platform = ((String) in.readValue((String.class.getClassLoader())));
        this.staffId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createdAt = ((Object) in.readValue((Object.class.getClassLoader())));
        this.updatedAt = ((Object) in.readValue((Object.class.getClassLoader())));
        this.settings = ((String) in.readValue((String.class.getClassLoader())));
        this.classRoomId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.deletedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.deleted = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.chattingEndTime = ((String) in.readValue((String.class.getClassLoader())));
        this.chattingStartTime = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public TeacherBean() {
    }

    /**
     *
     * @param classRoomId
     * @param chattingStartTime
     * @param platform
     * @param staffId
     * @param isAssistants
     * @param chattingEndTime
     * @param subject
     * @param settings
     * @param mobileToken
     * @param deleted
     * @param photo
     * @param updatedAt
     * @param id
     * @param secretToken
     * @param email
     * @param pin
     * @param createdAt
     * @param name
     * @param deletedAt
     * @param schoolId
     * @param mobile
     */
    public TeacherBean(Integer id, String name, String mobile, Boolean isAssistants, Integer schoolId, String subject, String secretToken, String mobileToken, Object photo, String email, String pin, String platform, Integer staffId, Object createdAt, Object updatedAt, String settings, Integer classRoomId, String deletedAt, Boolean deleted, String chattingEndTime, String chattingStartTime) {
        super();
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.isAssistants = isAssistants;
        this.schoolId = schoolId;
        this.subject = subject;
        this.secretToken = secretToken;
        this.mobileToken = mobileToken;
        this.photo = photo;
        this.email = email;
        this.pin = pin;
        this.platform = platform;
        this.staffId = staffId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.settings = settings;
        this.classRoomId = classRoomId;
        this.deletedAt = deletedAt;
        this.deleted = deleted;
        this.chattingEndTime = chattingEndTime;
        this.chattingStartTime = chattingStartTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getIsAssistants() {
        return isAssistants;
    }

    public void setIsAssistants(Boolean isAssistants) {
        this.isAssistants = isAssistants;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSecretToken() {
        return secretToken;
    }

    public void setSecretToken(String secretToken) {
        this.secretToken = secretToken;
    }

    public String getMobileToken() {
        return mobileToken;
    }

    public void setMobileToken(String mobileToken) {
        this.mobileToken = mobileToken;
    }

    public Object getPhoto() {
        return photo;
    }

    public void setPhoto(Object photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    public Integer getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(Integer classRoomId) {
        this.classRoomId = classRoomId;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getChattingEndTime() {
        return chattingEndTime;
    }

    public void setChattingEndTime(String chattingEndTime) {
        this.chattingEndTime = chattingEndTime;
    }

    public String getChattingStartTime() {
        return chattingStartTime;
    }

    public void setChattingStartTime(String chattingStartTime) {
        this.chattingStartTime = chattingStartTime;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(mobile);
        dest.writeValue(isAssistants);
        dest.writeValue(schoolId);
        dest.writeValue(subject);
        dest.writeValue(secretToken);
        dest.writeValue(mobileToken);
        dest.writeValue(photo);
        dest.writeValue(email);
        dest.writeValue(pin);
        dest.writeValue(platform);
        dest.writeValue(staffId);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(settings);
        dest.writeValue(classRoomId);
        dest.writeValue(deletedAt);
        dest.writeValue(deleted);
        dest.writeValue(chattingEndTime);
        dest.writeValue(chattingStartTime);
    }

    public int describeContents() {
        return 0;
    }

}