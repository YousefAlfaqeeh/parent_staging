package trackwareschoolbus.parentschool.fragment.maps.request_and_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusLocationResponse {

    @SerializedName("long")
    @Expose
    public Integer _long;
    @SerializedName("lat")
    @Expose
    public Integer lat;
    @SerializedName("date_time")
    @Expose
    public String dateTime;
}