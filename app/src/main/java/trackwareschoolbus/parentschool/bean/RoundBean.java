package trackwareschoolbus.parentschool.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by  on 3/8/2017.
 */

public class RoundBean  extends  BaseBean implements Serializable {
    private String nameRound;
    private String time;
    private String mobile;
    private List<StudentBean> listStudentBean = new ArrayList<>();

    public RoundBean() {


    }

    public RoundBean(int id, String nameRound, String time,List<StudentBean> listStudentBean ) {
        super(id);
        this.nameRound = nameRound;
        this.time = time;
        this.listStudentBean = listStudentBean;
        this.mobile=mobile;
    }

    public String getNameRound() {
        return nameRound;
    }

    public void setNameRound(String nameRound) {
        this.nameRound = nameRound;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<StudentBean> getListStudentBean() {
        return listStudentBean;
    }

    public void setListStudentBean(List<StudentBean> listStudentBean) {
        this.listStudentBean = listStudentBean;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}