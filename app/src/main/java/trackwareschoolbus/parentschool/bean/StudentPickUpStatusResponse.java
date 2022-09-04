
package trackwareschoolbus.parentschool.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentPickUpStatusResponse implements Parcelable {

    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("student_id")
    @Expose
    public Integer studentId;

    public String getMessage() {
        return message;
    }

    public StudentPickUpStatusResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public StudentPickUpStatusResponse setStudentId(Integer studentId) {
        this.studentId = studentId;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.message);
        dest.writeValue(this.studentId);
    }

    public StudentPickUpStatusResponse() {
    }

    protected StudentPickUpStatusResponse(Parcel in) {
        this.message = in.readString();
        this.studentId = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<StudentPickUpStatusResponse> CREATOR = new Parcelable.Creator<StudentPickUpStatusResponse>() {
        @Override
        public StudentPickUpStatusResponse createFromParcel(Parcel source) {
            return new StudentPickUpStatusResponse(source);
        }

        @Override
        public StudentPickUpStatusResponse[] newArray(int size) {
            return new StudentPickUpStatusResponse[size];
        }
    };
}
