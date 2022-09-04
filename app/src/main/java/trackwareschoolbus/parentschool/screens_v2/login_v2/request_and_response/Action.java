package trackwareschoolbus.parentschool.screens_v2.login_v2.request_and_response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Action implements Parcelable {

    @SerializedName("no-show")
    @Expose
    public String noShow;
    @SerializedName("check_in")
    @Expose
    public String checkIn;
    @SerializedName("check_out")
    @Expose
    public String checkOut;
    @SerializedName("absent")
    @Expose
    public String absent;
    @SerializedName("near_by")
    @Expose
    public String nearBy;

    public String getNoShow() {
        return noShow;
    }

    public Action setNoShow(String noShow) {
        this.noShow = noShow;
        return this;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public Action setCheckIn(String checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public Action setCheckOut(String checkOut) {
        this.checkOut = checkOut;
        return this;
    }

    public String getAbsent() {
        return absent;
    }

    public Action setAbsent(String absent) {
        this.absent = absent;
        return this;
    }

    public String getNearBy() {
        return nearBy;
    }

    public Action setNearBy(String nearBy) {
        this.nearBy = nearBy;
        return this;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.noShow);
        dest.writeString(this.checkIn);
        dest.writeString(this.checkOut);
        dest.writeString(this.absent);
        dest.writeString(this.nearBy);
    }

    public Action() {
    }

    protected Action(Parcel in) {
        this.noShow = in.readString();
        this.checkIn = in.readString();
        this.checkOut = in.readString();
        this.absent = in.readString();
        this.nearBy = in.readString();
    }

    public static final Creator<Action> CREATOR = new Creator<Action>() {
        @Override
        public Action createFromParcel(Parcel source) {
            return new Action(source);
        }

        @Override
        public Action[] newArray(int size) {
            return new Action[size];
        }
    };
}
