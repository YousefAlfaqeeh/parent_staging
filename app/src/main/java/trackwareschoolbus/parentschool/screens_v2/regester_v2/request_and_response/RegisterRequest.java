package trackwareschoolbus.parentschool.screens_v2.regester_v2.request_and_response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRequest implements Parcelable {

    @SerializedName("mobile_number")
    @Expose
    private String mobile_number;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("pin")
    @Expose
    private String pin;


    public String getMobile_number() {
        return mobile_number;
    }

    public RegisterRequest setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
        return this;
    }

    public String getName() {
        return name;
    }

    public RegisterRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPin() {
        return pin;
    }

    public RegisterRequest setPin(String pin) {
        this.pin = pin;
        return this;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mobile_number);
        dest.writeValue(this.name);
        dest.writeString(this.email);
        dest.writeString(this.pin);
    }

    public RegisterRequest() {
    }

    protected RegisterRequest(Parcel in) {
        this.mobile_number = in.readString();
        this.name =  in.readString();
        this.email = in.readString();
        this.pin = in.readString();
    }

    public static final Creator<RegisterRequest> CREATOR = new Creator<RegisterRequest>() {
        @Override
        public RegisterRequest createFromParcel(Parcel source) {
            return new RegisterRequest(source);
        }

        @Override
        public RegisterRequest[] newArray(int size) {
            return new RegisterRequest[size];
        }
    };
}
