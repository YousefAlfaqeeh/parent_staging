package trackwareschoolbus.parentschool.fragment.maps.request_and_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IsStudentSrvedResponse {

    @SerializedName("result")
    @Expose
    public Boolean result;
}
