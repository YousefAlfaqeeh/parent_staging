package trackwareschoolbus.parentschool.screens_v2.home_kids_list_v2.request_and_response;


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
