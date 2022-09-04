package trackwareschoolbus.parentschool.bean;

/**
 * Created by  on 2/23/2017.
 */

public class PicUpBean extends BaseBean {


    private String studentName;
    private String studentGrage;
    private String schoolName;
    private double latitude;
    private double longitude;
    private boolean chaeck;



    public PicUpBean(String studentName, String studentGrage, String schoolName) {
        this.studentName = studentName;
        this.studentGrage = studentGrage;
        this.schoolName = schoolName;


    }

    public boolean isChaeck() {
        return chaeck;
    }

    public void setChaeck(boolean chaeck) {
        this.chaeck = chaeck;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentGrage() {
        return studentGrage;
    }

    public void setStudentGrage(String studentGrage) {
        this.studentGrage = studentGrage;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

  /*  public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }*/
}
