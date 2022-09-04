package trackwareschoolbus.parentschool.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class ApplicationVersionJson {


    @SerializedName("latest_version_code")
    private int latestVersionCode;
    @SerializedName("latest_version_name")
    private String latestVersionName;
    @SerializedName("google_play_link")
    private String googlePlayLink;
    @SerializedName("update_message_ar")
    private String updateMessageAr;
    @SerializedName("update_message_en")
    private String updateMessageEn;

    public static ApplicationVersionJson objectFromData(String str) {

        return new Gson().fromJson(str, ApplicationVersionJson.class);
    }

    public int getLatestVersionCode() {
        return latestVersionCode;
    }

    public void setLatestVersionCode(int latestVersionCode) {
        this.latestVersionCode = latestVersionCode;
    }

    public String getLatestVersionName() {
        return latestVersionName;
    }

    public void setLatestVersionName(String latestVersionName) {
        this.latestVersionName = latestVersionName;
    }

    public String getGooglePlayLink() {
        return googlePlayLink;
    }

    public void setGooglePlayLink(String googlePlayLink) {
        this.googlePlayLink = googlePlayLink;
    }

    public String getUpdateMessageAr() {
        return updateMessageAr;
    }

    public void setUpdateMessageAr(String updateMessageAr) {
        this.updateMessageAr = updateMessageAr;
    }

    public String getUpdateMessageEn() {
        return updateMessageEn;
    }

    public void setUpdateMessageEn(String updateMessageEn) {
        this.updateMessageEn = updateMessageEn;
    }
}
