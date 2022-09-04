package trackwareschoolbus.parentschool.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedbackRequest {

    @SerializedName("school_id")
    @Expose
    public Integer schoolId;
    @SerializedName("student_id")
    @Expose
    public Integer studentId;
    @SerializedName("feed_back")
    @Expose
    public String feedBack;
    @SerializedName("impression")
    @Expose
    public String impression;

    public Integer getSchoolId() {
        return schoolId;
    }

    public FeedbackRequest setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
        return this;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public FeedbackRequest setStudentId(Integer studentId) {
        this.studentId = studentId;
        return this;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public FeedbackRequest setFeedBack(String feedBack) {
        this.feedBack = feedBack;
        return this;
    }

    public String getImpression() {
        return impression;
    }

    public FeedbackRequest setImpression(String impression) {
        this.impression = impression;
        return this;
    }
}
