package trackwareschoolbus.parentschool.toolsV2;

import trackwareschoolbus.parentschool.API_new.APIs.Constants;

/**
 * Created by  on 2/18/2018.
 */


public interface ConstantsV2 {
    boolean Testing = true;

    interface URLS {
        String HTTP = "https://";
        String DEV_OR_PROD  = Constants.DEV_PROD;
        String BASE_URL = HTTP + "boknyyx648.execute-api.eu-central-1.amazonaws.com/" + DEV_OR_PROD + "/";
        /**/
        String LOG_IN = "api/login";
        String TIME_ZONE = "api/time_zone";
        String AUTHORIZATION = "api/authorization";
        String LOCALE = "api/locale";
        String GET_AND_SEND_SETTING = "api/parents/settings";
        String GTE_KIDS_LIST = "api/parents/kids_list";
        String PARENT_NOTIFICATION = "api/notify";
        String GET_STUDENT_LIST = "api/parents/student_list";
        String FORGET_PASSWORD = "api/forgot-pin";
        String REGISTER = "api/parents/register";
        String PARENTS_KIDS_HISTORY = "api/parents/kids-history";
        String PARENT_IS_REGISTER = "api/parents/parent-registered";
        String GET_KIDS_TEACHER = "api/parents/get-kids-teachers";
        String PRE_ARRIVE = "api/parents/pre-arrive";
        String IS_STUDENT_SERVED = "api/parents/is-student-served";
        String ACTIVATE_KID = "api/parents/activate-student";
        String SEND_STUDENT_BEACON = "api/parents/set-student-beacon";
        /**/
//        String ROUND_STATUS = "rt/api/schools/round_status";
//        String PUSH_NOTIFICATION = "rt/api/schools/push-notification";
//        String BUS_LOCATION = "rt/api/schools/bus_location";
        /**/
//
        /**/
        /**/

    }

    interface HEADERS_KEYS {
        String AUTHORIZATION = "Authorization";
        String LOCALE = "locale";
    }

    interface BODY_KEYS {
        String TOKEN = "TOKEN";
        String SECREET_TOKEN = "SECREET_TOKEN";
    }

    interface APP_KEYS {

    }

    interface SHARED_PREFERENCES_KEYS {
        String AUTHORIZATION = "AUTH";
        String NOTIFICATION_CHECK_ALERTED = "NOTIFICATION_CHECK_ALERTED";
//        String USER_SELECTED_LANGUAGE = "USER_SELECTED_LANGUAGE";
        String USER_SAVED_MOBILE_NUMBER = "USER_SAVED_MOBILE_NUMBER";
        String USER_SAVED_PIN = "USER_SAVED_PIN";
        String USER_SAVED_NAME = "USER_SAVED_NAME";
        String USER_SAVED_EMAIL = "USER_SAVED_EMAIL";
        String USER_LOG_IN_DATA = "USER_LOG_IN_DATA";
        /**/
        interface LAST_USER_STATE {
            String LAST_USER_STATE = "LAST_USER_STATE";
            String SELECTING_LANGUAGE = "SELECTING_LANGUAGE";
            String CONFIRMING_PHONE_NUMBER = "CONFIRMING_PHONE_NUMBER";
            String LOGING_IN = "LOGING_IN";
            String REGESTERING = "REGESTERING";
            String SHOWING_TUTORIAL = "SHOWING_TUTORIAL";
            String AT_HOME = "AT_HOME";
        }
    }


}

