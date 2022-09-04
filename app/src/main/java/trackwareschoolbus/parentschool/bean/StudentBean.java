package trackwareschoolbus.parentschool.bean;

/**
 * Created by  on 3/8/2017.
 */


import trackwareschoolbus.parentschool.enums.TypeRoundEnum;

/**
 * Created by  on 2/28/2017.
 */

public class StudentBean extends BaseBean {


    private String nameStudent ;
    private String grade ;
    private String nameSchool ;
    private double latitude;
    private double longitude;
    private String avatar;
    private boolean absent;
    private TypeRoundEnum typeRoundEnum;
    private int routeOrder;
//    private boolean chat_teachers;

    public StudentBean(int id, String nameStudent, String grade, String nameSchool, double latitude,double longitude,TypeRoundEnum typeRoundEnum ,int routeOrder/*,boolean chat_teachers*/) {
        super(id);
        this.nameStudent = nameStudent;
        this.grade = grade;
        this.nameSchool = nameSchool;
        this.latitude = latitude;
        this.longitude = longitude;
        this.typeRoundEnum = typeRoundEnum;
        this.routeOrder=routeOrder;
//        this.chat_teachers=chat_teachers;


    }




    public StudentBean() {
    }

//    public boolean isChat_teachers() {
//        return chat_teachers;
//    }
//
//    public void setChat_teachers(boolean chat_teachers) {
//        this.chat_teachers = chat_teachers;
//    }

    public int getRouteOrder() {
        return routeOrder;
    }

    public void setRouteOrder(int routeOrder) {
        this.routeOrder = routeOrder;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getNameSchool() {
        return nameSchool;
    }

    public void setNameSchool(String nameSchool) {
        this.nameSchool = nameSchool;
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


    public TypeRoundEnum getTypeRoundEnum() {
        return typeRoundEnum;
    }

    public void setTypeRoundEnum(TypeRoundEnum typeRoundEnum) {
        this.typeRoundEnum = typeRoundEnum;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isAbsent() {
        return absent;
    }

    public void setAbsent(boolean absent) {
        this.absent = absent;
    }
}