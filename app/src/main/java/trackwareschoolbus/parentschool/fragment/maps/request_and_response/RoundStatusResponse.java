package trackwareschoolbus.parentschool.fragment.maps.request_and_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoundStatusResponse {
    @SerializedName("students")
    @Expose
    public List<RoundStatusStudents> students = null;

}
