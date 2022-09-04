package trackwareschoolbus.parentschool.dataBase;


public class LonLat {

    private int LonLatId;

    private String longitude;

    private String latitude;

    private String date;

    public LonLat(String longitude, String latitude, String date) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
    }


    public int getLonLatId() {
        return LonLatId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}