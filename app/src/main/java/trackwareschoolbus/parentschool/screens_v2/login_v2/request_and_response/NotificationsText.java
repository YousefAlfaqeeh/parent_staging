package trackwareschoolbus.parentschool.screens_v2.login_v2.request_and_response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationsText implements Parcelable {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("actions")
    @Expose
    public List<Action> actions = null;

    public String getType() {
        return type;
    }

    public NotificationsText setType(String type) {
        this.type = type;
        return this;
    }

    public List<Action> getActions() {
        return actions;
    }

    public NotificationsText setActions(List<Action> actions) {
        this.actions = actions;
        return this;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeTypedList(this.actions);
    }

    public NotificationsText() {
    }

    protected NotificationsText(Parcel in) {
        this.type = in.readString();
        this.actions = in.createTypedArrayList(Action.CREATOR);
    }

    public static final Creator<NotificationsText> CREATOR = new Creator<NotificationsText>() {
        @Override
        public NotificationsText createFromParcel(Parcel source) {
            return new NotificationsText(source);
        }

        @Override
        public NotificationsText[] newArray(int size) {
            return new NotificationsText[size];
        }
    };
}
