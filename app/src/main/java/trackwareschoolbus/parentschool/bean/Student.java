//
//package trackwareschoolbus.parentschool.bean;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//public class Student implements Parcelable
//{
//
//    @SerializedName("id")
//    @Expose
//    private Integer id;
//    @SerializedName("name")
//    @Expose
//    private String name;
//    @SerializedName("photo_name")
//    @Expose
//    private String photoName;
//    @SerializedName("father_id")
//    @Expose
//    private Integer fatherId;
//    @SerializedName("mother_id")
//    @Expose
//    private Integer motherId;
//    @SerializedName("section")
//    @Expose
//    private String section;
//    @SerializedName("grade")
//    @Expose
//    private String grade;
//    @SerializedName("city")
//    @Expose
//    private String city;
//    @SerializedName("gender")
//    @Expose
//    private String gender;
//    public final static Parcelable.Creator<Student> CREATOR = new Creator<Student>() {
//
//
//        @SuppressWarnings({
//                "unchecked"
//        })
//        public Student createFromParcel(Parcel in) {
//            Student instance = new Student();
//            instance.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
//            instance.name = ((String) in.readValue((String.class.getClassLoader())));
//            instance.photoName = ((String) in.readValue((String.class.getClassLoader())));
//            instance.fatherId = ((Integer) in.readValue((Integer.class.getClassLoader())));
//            instance.motherId = ((Integer) in.readValue((Integer.class.getClassLoader())));
//            instance.section = ((String) in.readValue((String.class.getClassLoader())));
//            instance.grade = ((String) in.readValue((String.class.getClassLoader())));
//            instance.city = ((String) in.readValue((String.class.getClassLoader())));
//            instance.gender = ((String) in.readValue((String.class.getClassLoader())));
//            return instance;
//        }
//
//        public Student[] newArray(int size) {
//            return (new Student[size]);
//        }
//
//    }
//            ;
//
//    /**
//     * No args constructor for use in serialization
//     *
//     */
//    public Student() {
//    }
//
//    /**
//     *
//     * @param id
//     * @param photoName
//     * @param name
//     * @param gender
//     * @param grade
//     * @param motherId
//     * @param fatherId
//     * @param section
//     * @param city
//     */
//    public Student(Integer id, String name, String photoName, Integer fatherId, Integer motherId, String section, String grade, String city, String gender) {
//        super();
//        this.id = id;
//        this.name = name;
//        this.photoName = photoName;
//        this.fatherId = fatherId;
//        this.motherId = motherId;
//        this.section = section;
//        this.grade = grade;
//        this.city = city;
//        this.gender = gender;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPhotoName() {
//        return photoName;
//    }
//
//    public void setPhotoName(String photoName) {
//        this.photoName = photoName;
//    }
//
//    public Integer getFatherId() {
//        if (fatherId==null)
//            return -1;
//        return fatherId;
//    }
//
//    public void setFatherId(Integer fatherId) {
//        this.fatherId = fatherId;
//    }
//
//    public Integer getMotherId() {
//        if (motherId==null)
//            return -1;
//        return motherId;
//    }
//
//    public void setMotherId(Integer motherId) {
//        this.motherId = motherId;
//    }
//
//    public String getSection() {
//        return section;
//    }
//
//    public void setSection(String section) {
//        this.section = section;
//    }
//
//    public String getGrade() {
//        return grade;
//    }
//
//    public void setGrade(String grade) {
//        this.grade = grade;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeValue(id);
//        dest.writeValue(name);
//        dest.writeValue(photoName);
//        dest.writeValue(fatherId);
//        dest.writeValue(motherId);
//        dest.writeValue(section);
//        dest.writeValue(grade);
//        dest.writeValue(city);
//        dest.writeValue(gender);
//    }
//
//    public int describeContents() {
//        return 0;
//    }
//
//}