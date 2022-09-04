//package trackwareschoolbus.parentschool.screens_v2.home_kids_list_v2.request_and_response;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import trackwareschoolbus.parentschool.bean.KidBean;
//
//public class KidsListResponse implements Parcelable {
//
//    @SerializedName("students")
//    @Expose
//    public List<KidBean> students = null;
//    @SerializedName("parent_id")
//    @Expose
//    public Integer parentId;
//
//    public List<KidBean> getStudents() {
//        return students;
//    }
//
//    public KidsListResponse setStudents(List<KidBean> students) {
//        this.students = students;
//        return this;
//    }
//
//    public Integer getParentId() {
//        return parentId;
//    }
//
//    public KidsListResponse setParentId(Integer parentId) {
//        this.parentId = parentId;
//        return this;
//    }
//
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeList(this.students);
//        dest.writeValue(this.parentId);
//    }
//
//    public KidsListResponse() {
//    }
//
//    protected KidsListResponse(Parcel in) {
//        this.students = new ArrayList<KidBean>();
//        in.readList(this.students, KidBean.class.getClassLoader());
//        this.parentId = (Integer) in.readValue(Integer.class.getClassLoader());
//    }
//
//    public static final Creator<KidsListResponse> CREATOR = new Creator<KidsListResponse>() {
//        @Override
//        public KidsListResponse createFromParcel(Parcel source) {
//            return new KidsListResponse(source);
//        }
//
//        @Override
//        public KidsListResponse[] newArray(int size) {
//            return new KidsListResponse[size];
//        }
//    };
//}