package trackwareschoolbus.parentschool.screens_v2.settings_v2.request_and_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationsSettings {

    @SerializedName("locale")
    @Expose
    public String locale;
    @SerializedName("nearby")
    @Expose
    public Boolean nearby;
    @SerializedName("check_in")
    @Expose
    public Boolean checkIn;
    @SerializedName("check_out")
    @Expose
    public Boolean checkOut;
}
