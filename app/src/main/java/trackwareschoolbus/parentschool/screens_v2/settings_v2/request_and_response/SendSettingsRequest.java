package trackwareschoolbus.parentschool.screens_v2.settings_v2.request_and_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendSettingsRequest   {

    @SerializedName("settings")
    @Expose
    public GetSettingsResponse settings;

    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("secret_token")
    @Expose
    public String secret_token;


}
