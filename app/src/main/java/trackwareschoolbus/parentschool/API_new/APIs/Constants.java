package trackwareschoolbus.parentschool.API_new.APIs;

/**
 * Created by  on 11/27/2017.
 */

public interface Constants {

    String HTTPS = "https://";
//    String DEV_PROD = "prod";
    String DEV_PROD = "dev";
    String  SCHOOLS_LIST = "https://trackware-schools.s3.eu-central-1.amazonaws.com/qc1.json"; // DEV
//    String  SCHOOLS_LIST = "https://trackware-schools.s3.eu-central-1.amazonaws.com/infos.json"; // PROD

//    String  SCHOOLS_LIST = SCHOOLS_LIST_DEV;

    interface Keys {


    }

    interface Values {
        int VERSION_NUMBER = 1;
        String PLATFORM = "android";
    }

    interface Urls {
        // chatting URLs

        String HOST = HTTPS + "boknyyx648.execute-api.eu-central-1.amazonaws.com/" + DEV_PROD;
        //        String HOST = HTTPS + "boknyyx648.execute-api.eu-central-1.amazonaws.com/prod";
        String LOCAL = "http://54.93.253.221:4011";
        //    String LOCAL =  "http://192.168.1.17:4011/";
        String MESSAGE_HISTORY = HOST + "/api/schools/chat/message-history";
        String SEND_MESSAGE = LOCAL + "/api/schools/chat/send-message";
        // -----------
//    URLs
        String MAIN_URL = HTTPS + "boknyyx648.execute-api.eu-central-1.amazonaws.com/" + DEV_PROD + "/api/";
        String NOTIFY_URL = HTTPS + "boknyyx648.execute-api.eu-central-1.amazonaws.com/" + DEV_PROD + "/";
        /**/
//        String MAIN_URL = HTTPS + "boknyyx648.execute-api.eu-central-1.amazonaws.com/prod/api/";
//        String NOTIFY_URL = HTTPS + "boknyyx648.execute-api.eu-central-1.amazonaws.com/prod/";
        /**/

        String LOG_IN = MAIN_URL + "login";
        String GET_SETTING = MAIN_URL + "parents/settings";
        String SET_SETTING = MAIN_URL + "parents/settings";
        String GTE_KIDS_LIST = MAIN_URL + "parents/kids_list";
        String STUDENT_PICK_UP_STATUS = MAIN_URL + "parents/student-pick-up-status";
        String UPLOAD_STUDENT_IMAGE = MAIN_URL + "parents/upload-student-image";

        String FEED_BACK = MAIN_URL + "parents/feed-back";

        //    String SET_ABSENCE=MAIN_URL+"parents/set_absence";
        String PARENT_NOTIFICATION = MAIN_URL + "notify";
        String GET_STUDENT_LIST = MAIN_URL + "parents/student_list";
        String FORGET_PASSWORD = MAIN_URL + "forgot-pin";
        String REGISTER = MAIN_URL + "parents/register";
        String PARENTS_KIDS_HISTORY = MAIN_URL + "parents/kids-history";
        String PARENT_IS_REGISTER = MAIN_URL + "parents/parent-registered";
        String GET_KIDS_TEACHER = MAIN_URL + "parents/get-kids-teachers";
        String PRE_ARRIVE = MAIN_URL + "parents/pre-arrive";
        String ACTIVATE_KID = MAIN_URL + "parents/activate-student";
        String SEND_STUDENT_BEACON = MAIN_URL + "parents/set-student-beacon";
        String LOGOUT = MAIN_URL + "parents/logout";

        /**/
//        String LOG_URL = "https://92254f12dc3343b5ae953f658f6bbe57:915e5a7232f141a587cb449dda03e7ac@sentry.io/178693".trim();
        public static String LATEST_APP_VERSION_URL = "https://raw.githubusercontent.com/Trackware/versions/master/schools_parents.json".trim();

        /**/
        String ROUND_STATUS = "rt/api/schools/round_status";
        String PUSH_NOTIFICATION = "rt/api/schools/push-notification";
        String BUS_LOCATION = "rt/api/schools/bus_location";

        String PHOENIX_SOCKET = "wss://rt.trackware.com:81/socket/websocket".trim();

        // maps apis
        String IS_STUDENT_SERVED = MAIN_URL + "parents/is-student-served";


    }





}
