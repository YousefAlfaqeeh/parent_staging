package trackwareschoolbus.parentschool.screens_v2.home_kids_list_v2.request_and_response;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentStatus_v2 implements Parcelable {

    @SerializedName("activity_type")
    @Expose
    public String activityType;
    @SerializedName("round_id")
    @Expose
    public Integer roundId;
    @SerializedName("datetime")
    @Expose
    public String datetime;

    public String getActivityType() {
        return activityType;
    }

    public StudentStatus_v2 setActivityType(String activityType) {
        this.activityType = activityType;
        return this;
    }

    public Integer getRoundId() {
        return roundId;
    }

    public StudentStatus_v2 setRoundId(Integer roundId) {
        this.roundId = roundId;
        return this;
    }

    public String getDatetime() {
        return datetime;
    }

    public StudentStatus_v2 setDatetime(String datetime) {
        this.datetime = datetime;
        return this;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.activityType);
        dest.writeValue(this.roundId);
        dest.writeString(this.datetime);
    }

    public StudentStatus_v2() {
    }

    protected StudentStatus_v2(Parcel in) {
        this.activityType = in.readString();
        this.roundId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.datetime = in.readString();
    }

    public static final Creator<StudentStatus_v2> CREATOR = new Creator<StudentStatus_v2>() {
        @Override
        public StudentStatus_v2 createFromParcel(Parcel source) {
            return new StudentStatus_v2(source);
        }

        @Override
        public StudentStatus_v2[] newArray(int size) {
            return new StudentStatus_v2[size];
        }
    };
}
