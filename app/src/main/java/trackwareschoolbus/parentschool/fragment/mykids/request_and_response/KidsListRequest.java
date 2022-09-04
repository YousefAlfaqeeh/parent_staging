package trackwareschoolbus.parentschool.fragment.mykids.request_and_response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KidsListRequest {

    @SerializedName("kid_id")
    @Expose
    public Integer kidId;

    public KidsListRequest withKidId(Integer kidId) {
        this.kidId = kidId;
        return this;
    }

}
