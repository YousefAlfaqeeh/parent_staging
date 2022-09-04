package trackwareschoolbus.parentschool.screens_v2.settings_v2.request_and_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSettingsResponse {

    @SerializedName("notifications")
    @Expose
    public NotificationsSettings notificationsSettings;
}
