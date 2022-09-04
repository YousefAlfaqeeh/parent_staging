package trackwareschoolbus.parentschool.fragment.maps.request_and_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IsStudentSrvedRequest {

    @SerializedName("round_id")
    @Expose
    public Integer roundId;
    @SerializedName("student_id")
    @Expose
    public Integer studentId;



}
