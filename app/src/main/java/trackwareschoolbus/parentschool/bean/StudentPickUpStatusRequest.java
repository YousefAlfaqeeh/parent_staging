
package trackwareschoolbus.parentschool.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentPickUpStatusRequest implements Parcelable {

    @SerializedName("student_id")
    @Expose
    public Integer studentId;
    @SerializedName("status")
    @Expose
    public String status;

    public Integer getStudentId() {
        return studentId;
    }

    public StudentPickUpStatusRequest setStudentId(Integer studentId) {
        this.studentId = studentId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public StudentPickUpStatusRequest setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.studentId);
        dest.writeString(this.status);
    }

    public StudentPickUpStatusRequest() {
    }

    protected StudentPickUpStatusRequest(Parcel in) {
        this.studentId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.status = in.readString();
    }

    public static final Parcelable.Creator<StudentPickUpStatusRequest> CREATOR = new Parcelable.Creator<StudentPickUpStatusRequest>() {
        @Override
        public StudentPickUpStatusRequest createFromParcel(Parcel source) {
            return new StudentPickUpStatusRequest(source);
        }

        @Override
        public StudentPickUpStatusRequest[] newArray(int size) {
            return new StudentPickUpStatusRequest[size];
        }
    };
}
