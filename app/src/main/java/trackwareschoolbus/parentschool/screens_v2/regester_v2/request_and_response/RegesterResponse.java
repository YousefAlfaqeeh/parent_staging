package trackwareschoolbus.parentschool.screens_v2.regester_v2.request_and_response;

public class RegesterResponse {


    public enum RegisterStatus {
        Registered, NotRegistered, NotExist
    }

   public RegisterStatus status = RegisterStatus.NotExist;
    public String pin = null;
    public String name = null;
    public String email = null;
    public String message_en = null;
    public String message_ar = null;


}
