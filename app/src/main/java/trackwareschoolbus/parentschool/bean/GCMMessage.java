package trackwareschoolbus.parentschool.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GCMMessage implements Parcelable
{

    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("classroom_id")
    @Expose
    private Integer classroomId;
    @SerializedName("student_id")
    @Expose
    private Integer studentId;
    @SerializedName("student_name")
    @Expose
    private String studentName;
    @SerializedName("teacher_name")
    @Expose
    private String teacherName;
    @SerializedName("school_id")
    @Expose
    private Integer schoolId;
    @SerializedName("teacher_id")
    @Expose
    private Integer teacherId;
    @SerializedName("father_id")
    @Expose
    private Integer fatherId;
    @SerializedName("mother_id")
    @Expose
    private Integer motherId;
    public final static Parcelable.Creator<GCMMessage> CREATOR = new Creator<GCMMessage>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GCMMessage createFromParcel(Parcel in) {
            return new GCMMessage(in);
        }

        public GCMMessage[] newArray(int size) {
            return (new GCMMessage[size]);
        }

    }
            ;

    protected GCMMessage(Parcel in) {
        this.avatar = ((String) in.readValue((String.class.getClassLoader())));
        this.body = ((String) in.readValue((String.class.getClassLoader())));
        this.classroomId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.studentId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.studentName = ((String) in.readValue((String.class.getClassLoader())));
        this.teacherName = ((String) in.readValue((String.class.getClassLoader())));
        this.schoolId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.teacherId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.fatherId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.motherId = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public GCMMessage() {
    }

    /**
     *
     * @param body
     * @param studentId
     * @param studentName
     * @param schoolId
     * @param motherId
     * @param fatherId
     * @param avatar
     * @param teacherName
     * @param classroomId
     * @param teacherId
     */
    public GCMMessage(String avatar, String body, Integer classroomId, Integer studentId, String studentName, String teacherName, Integer schoolId, Integer teacherId, Integer fatherId, Integer motherId) {
        super();
        this.avatar = avatar;
        this.body = body;
        this.classroomId = classroomId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.teacherName = teacherName;
        this.schoolId = schoolId;
        this.teacherId = teacherId;
        this.fatherId = fatherId;
        this.motherId = motherId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public Integer getMotherId() {
        return motherId;
    }

    public void setMotherId(Integer motherId) {
        this.motherId = motherId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(avatar);
        dest.writeValue(body);
        dest.writeValue(classroomId);
        dest.writeValue(studentId);
        dest.writeValue(studentName);
        dest.writeValue(teacherName);
        dest.writeValue(schoolId);
        dest.writeValue(teacherId);
        dest.writeValue(fatherId);
        dest.writeValue(motherId);
    }

    public int describeContents() {
        return 0;
    }

}