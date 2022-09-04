package trackwareschoolbus.parentschool.bean;

import java.io.Serializable;

/**
 * Created by  on 2/23/2017.
 */


public class BaseBean implements Serializable {

    private int id;

    public BaseBean(int id) {
        this.id = id;
    }

    public BaseBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

