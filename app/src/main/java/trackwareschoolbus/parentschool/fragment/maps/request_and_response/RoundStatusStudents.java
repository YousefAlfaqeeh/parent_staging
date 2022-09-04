package trackwareschoolbus.parentschool.fragment.maps.request_and_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoundStatusStudents {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("id")
    @Expose
    public Integer id;
}