package trackwareschoolbus.parentschool.bean;

public class ImportantNotification<Message> {

    public static String DRIVER = "driver";
    public static String SCHOOL = "school";


    private Message message;
    private String id;
    private String type;


    public Message getMessage() {
        return message;
    }



    public ImportantNotification setMessage(Message message) {
        this.message = message;
        return this;
    }

    public String getId() {
        return id;
    }

    public ImportantNotification setId(String id) {
        this.id = id;
        return this;
    }

    public ImportantNotification setId(int id) {
        this.id = id+"";
        return this;
    }

    public String getType() {
        return type;
    }

    public ImportantNotification setType(String type) {
        this.type = type;
        return this;
    }
}






