package trackwareschoolbus.parentschool.API.enumApi;

/**
 * Created by  on 12/5/2016.
 */

public enum EnumMethodApi {
    GET(1),POST(2);
    private final int value;

    private EnumMethodApi(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
