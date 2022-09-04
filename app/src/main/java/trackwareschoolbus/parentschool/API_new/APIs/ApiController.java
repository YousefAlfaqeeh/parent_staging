package trackwareschoolbus.parentschool.API_new.APIs;


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import trackwareschoolbus.parentschool.API_V2.GsonUtils;
import trackwareschoolbus.parentschool.API_new.APIs.ListenersAndInterFaces.OnApiComplete;
import trackwareschoolbus.parentschool.API_new.APIs.ListenersAndInterFaces.RequestHeader;
import trackwareschoolbus.parentschool.app.Application;
import trackwareschoolbus.parentschool.bean.ApplicationVersionJson;
import trackwareschoolbus.parentschool.bean.FeedbackRequest;
import trackwareschoolbus.parentschool.bean.MessageHistoryObject;
import trackwareschoolbus.parentschool.bean.MessageHistoryRequest;
import trackwareschoolbus.parentschool.bean.SendMessageRequest;
import trackwareschoolbus.parentschool.bean.SendMessageResponse;
import trackwareschoolbus.parentschool.bean.StudentPickUpStatusRequest;
import trackwareschoolbus.parentschool.bean.StudentPickUpStatusResponse;
import trackwareschoolbus.parentschool.screens_v2.home_kids_list_v2.request_and_response.KidsListRequest;
import trackwareschoolbus.parentschool.toolsV2.ConstantsV2;
import trackwareschoolbus.parentschool.utilityParent.DateTools;
import trackwareschoolbus.parentschool.utilityParent.UtilityParent;


public class ApiController implements Constants {

    public static final String TAG = ApiController.class.getSimpleName();
//    private static final int TIME = 1000 * 60 * 30;
//    private final static String PLATFORM = "android";
//    private final static String USER_TYPE = "teacher";


    //////////////
    private static void sendRequest(Context context, Request request) {
        Log.e(TAG, "url: " + request.getUrl());
//        ((RequestHeader) request).addHeader("locale", "en");
        if (UtilityParent.getStringShared(UtilityParent.AUTH) == null || UtilityParent.getStringShared(UtilityParent.AUTH).equals("")) {
            Log.v("Authorization", " Authorization is null or empty");
        } else {
            if (!request.getUrl().equals(SCHOOLS_LIST))
            ((RequestHeader) request).addHeader("Authorization", UtilityParent.getStringShared(UtilityParent.AUTH));
        }
        if (!request.getUrl().equals(SCHOOLS_LIST))
        ((RequestHeader) request).addHeader("locale", UtilityParent.getStringShared(UtilityParent.LANGUAGE));
        ServiceRequestQueue.getInstance(context).add(request);
    }
    //////////////


    //    public static void register(Context context,
//                                String secret_token,
//                                String pin,
//                                String email,
//                                final OnApiComplete<String> onComplete) {
//
//        VolleyRequest<String> request = new VolleyRequest<String>(
//                context,
//                HttpMethod.POST,
//                REGISTER,
//                new TypeToken<String>() {
//                }.getType(),
//                onComplete
//        );
//        RegisterRequest reqObject = new RegisterRequest();
//        reqObject.setSecretToken(secret_token);
//        reqObject.setPin(pin);
//        reqObject.setEmail(email);
//        request.setData(reqObject);
//        /**/
//        UtilDialogs.ProcessingDialog.show(context, null);
//        sendRequest(context, request);
//    }
//
//
//    public static void logIn(Context context,
//                             String secret_token,
//                             String pin,
//                             String mobile_token,
//                             final OnApiComplete<String> onComplete) {
//
//        VolleyRequest<String> request = new VolleyRequest<String>(
//                context,
//                HttpMethod.POST,
//                LOGIN,
//                new TypeToken<String>() {
//                }.getType(),
//                onComplete
//        );
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setPlatform(PLATFORM);
//        loginRequest.setUserType(USER_TYPE);
//        loginRequest.setSecretToken(secret_token);
//        loginRequest.setPin(pin);
//        loginRequest.setMobileToken(mobile_token);
//        request.setData(loginRequest);
//        /**/
//        UtilDialogs.ProcessingDialog.show(context, null);
//        sendRequest(context, request);
//    }
//
//
//    public static void getClassRoomsWithSections(Context context, final OnApiComplete<List<ClassRoomsWithSection>> onComplete) {
//        VolleyRequest<List<ClassRoomsWithSection>> request = new VolleyRequest<>(
//                context,
//                HttpMethod.GET,
//                Urls.GET_CLASS_ROOMS_WITH_SECTIONS,
//                new TypeToken<List<ClassRoomsWithSection>>() {
//                }.getType(),
//                onComplete
//        );
////        UtilDialogs.ProcessingDialog.show(context, null);
//        sendRequest(context, request);
//    }
//
//
//    public static void getStudentsForGrade(Context context,
//                                           ArrayList<Section_Grade> sectionGradeList,
//                                           final OnApiComplete<List<Student>> onComplete) {
//
//        VolleyRequest<List<Student>> request = new VolleyRequest<List<Student>>(
//                context,
//                HttpMethod.POST,
//                GET_STUDENTS_FOR_GRADE,
//                new TypeToken<List<Student>>() {
//                }.getType(),
//                onComplete
//        );
//        request.setData(sectionGradeList);
////        UtilDialogs.ProcessingDialog.show(context, null);
//        sendRequest(context, request);
//    }
//
//
//    public static void createClassRoom(Context context,
//                                       ArrayList<Section_Grade> sectionGradeList,
//                                       final OnApiComplete<List<TeacherClass>> onComplete) {
//
//        VolleyRequest<List<TeacherClass>> request = new VolleyRequest<>(
//                context,
//                HttpMethod.POST,
//                CREATE_CLASS_ROOM,
//                new TypeToken<List<TeacherClass>>() {
//                }.getType(),
//                onComplete
//        );
//        request.setData(sectionGradeList);
////        UtilDialogs.ProcessingDialog.show(context, null);
//        sendRequest(context, request);
//    }
//
//
//    public static void getTeacherClasses(Context context, Integer iD, final OnApiComplete<List<TeacherClass>> onComplete) {
//        String url = Urls.GET_TEACHER_CLASSES;
//        if (iD != null)
//            url = Urls.GET_TEACHER_CLASSES + "?cid=" + iD;
//
//        VolleyRequest<List<TeacherClass>> request = new VolleyRequest<>(
//                context,
//                HttpMethod.GET,
//                url,
//                new TypeToken<List<TeacherClass>>() {
//                }.getType(),
//                onComplete
//        );
////        UtilDialogs.ProcessingDialog.show(context, null);
//        sendRequest(context, request);
//    }
//
//
//    public static void deleteClass(Context context,
//                                   DeleteClassRequest deleteClassRequest,
//                                   final OnApiComplete<Object> onComplete) {
//
//        VolleyRequest<Object> request = new VolleyRequest<>(
//                context,
//                HttpMethod.POST,
//                DELETE_CLASSES,
//                new TypeToken<Object>() {
//                }.getType(),
//                onComplete
//        );
//        request.setData(deleteClassRequest);
////        UtilDialogs.ProcessingDialog.show(context, null);
//        sendRequest(context, request);
//    }
//
//
//    public static void sendClassroomMessage(Context context,
//                                            SendClassroomMessage sendClassroomMessage,
//                                            final OnApiComplete<Object> onComplete) {
//
//        VolleyRequest<Object> request = new VolleyRequest<Object>(
//                context,
//                HttpMethod.POST,
//                SEND_CLASSROOM_MESSAGE,
//                new TypeToken<Object>() {
//                }.getType(),
//                onComplete
//        );
//        request.setData(sendClassroomMessage);
////        UtilDialogs.ProcessingDialog.show(context, null);
//        sendRequest(context, request);
//    }
//
//
    public static void getMessageHistory(Context context,
                                         MessageHistoryRequest messageHistoryRequest,
                                         final OnApiComplete<ArrayList<MessageHistoryObject>> onComplete) {

        VolleyRequest<ArrayList<MessageHistoryObject>> request = new VolleyRequest<ArrayList<MessageHistoryObject>>(
                context,
                HttpMethod.POST,
                Constants.Urls.MESSAGE_HISTORY,
                new TypeToken<ArrayList<MessageHistoryObject>>() {
                }.getType(),
                onComplete
        );
        request.setData(messageHistoryRequest);
        sendRequest(context, request);
    }

    //
//


    public static void sendLogout(Context context, final OnApiComplete<Object> onComplete) {
        VolleyRequest<Object> request = new VolleyRequest<>(
                context,
                HttpMethod.POST,
                Constants.Urls.LOGOUT,
                new TypeToken<ArrayList<SendMessageResponse>>() {
                }.getType(),
                onComplete
        );
        sendRequest(context, request);
    }


    public static void sendMessage(Context context,
                                   SendMessageRequest sendMessageRequest,
                                   final OnApiComplete<ArrayList<SendMessageResponse>> onComplete) {

        VolleyRequest<ArrayList<SendMessageResponse>> request = new VolleyRequest<ArrayList<SendMessageResponse>>(
                context,
                HttpMethod.POST,
                Constants.Urls.SEND_MESSAGE,
                new TypeToken<ArrayList<SendMessageResponse>>() {
                }.getType(),
                onComplete
        );
        request.setData(sendMessageRequest);
        sendRequest(context, request);
    }


//    public static void geSingleKidList(Context context, KidsListRequest kidsListRequest, final OnApiComplete<KidsListResponse> onComplete) {
//        VolleyRequest<KidsListResponse> request = new VolleyRequest<>(
//                context,
//                HttpMethod.POST,
//                Urls.GTE_KIDS_LIST,
//                new TypeToken<KidsListResponse>() {
//                }.getType(),
//                onComplete
//        );
//        if (kidsListRequest != null)
//            request.setData(kidsListRequest);
//
//        sendRequest(context, request);
//    }

//    public static void getAllKidsList(Context context, final OnApiComplete<KidsListResponse> onComplete) {
//        geSingleKidList(context, null, onComplete);
//    }


    public static void isParentPhoneNumberRegistered(Context context,
                                                     String country_iso,
                                                     String mobile_number,
                                                     final OnApiComplete<JsonObject> onComplete) {

        VolleyRequest<JsonObject> request = new VolleyRequest<JsonObject>(
                context,
                HttpMethod.POST,
                Constants.Urls.PARENT_IS_REGISTER,
                new TypeToken<JsonObject>() {
                }.getType(),
                onComplete
        );

        try {
            JsonObject jsonObject = new JsonObject();
//            jsonObject.addProperty("secret_token", secret_token);
            jsonObject.addProperty("mobile_number", mobile_number);
            jsonObject.addProperty("country_iso", country_iso);
            request.setData(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            Application.logEvents(Constants.Urls.SEND_STUDENT_BEACON, "ApiController - isParentPhoneNumberRegistered  ", e);


        }
        /**/
        sendRequest(context, request);
    }

//    public static void getNumberFromFaceBook(Context context,
//                                             String access_token,
//                                             final OnApiComplete<JsonObject> onComplete) {
//
//        VolleyRequest<JsonObject> request = new VolleyRequest<JsonObject>(
//                context,
//                HttpMethod.GET,
//                "https://graph.accountkit.com/v1.2/me/?access_token=" + access_token,
//                new TypeToken<JsonObject>() {
//                }.getType(),
//                onComplete
//        );
//        /**/
//        sendRequest(context, request);
//    }


    public static void sendStudentBeacon(Context context,
                                         String mac_address,
                                         int student_id,
                                         final OnApiComplete<JsonObject> onComplete) {

        VolleyRequest<JsonObject> request = new VolleyRequest<>(
                context,
                HttpMethod.POST,
                Constants.Urls.SEND_STUDENT_BEACON,
                new TypeToken<JsonObject>() {
                }.getType(),
                onComplete
        );

        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("student_id", student_id);
            jsonObject.addProperty("mac_address", mac_address);
            request.setData(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            Application.logEvents(Constants.Urls.SEND_STUDENT_BEACON, "ApiController - sendStudentBeacon  ", e);


        }
        /**/
        sendRequest(context, request);
    }


    public static void checkForLatestAppVersion(Context context, final OnApiComplete<ApplicationVersionJson> onComplete) {
        VolleyRequest<ApplicationVersionJson> request = new VolleyRequest<ApplicationVersionJson>(
                context,
                HttpMethod.GET,
                Constants.Urls.LATEST_APP_VERSION_URL,
                new TypeToken<ApplicationVersionJson>() {
                }.getType(),
                onComplete
        );

        request.addHeader("Cache-Control", "no-cache");
        ServiceRequestQueue.getInstance(context).add(request);
    }


//    ///////////////////
//
//    public static void getRoundStatus(Context context, int round_id, final OnApiComplete<RoundStatusResponse> onComplete) {
//        VolleyRequest<RoundStatusResponse> request = new VolleyRequest<>(
//                context,
//                HttpMethod.GET,
//                Urls.GTE_KIDS_LIST + "?round_id=" + round_id,
//                new TypeToken<RoundStatusResponse>() {
//                }.getType(),
//                onComplete
//        );
//        sendRequest(context, request);
//    }
//
//
//    public static void getBusLocation(Context context, int bus_id, final OnApiComplete<BusLocationResponse> onComplete) {
//        VolleyRequest<BusLocationResponse> request = new VolleyRequest<>(
//                context,
//                HttpMethod.GET,
//                Urls.BUS_LOCATION + "?bus_id=" + bus_id,
//                new TypeToken<BusLocationResponse>() {
//                }.getType(),
//                onComplete
//        );
//        sendRequest(context, request);
//    }
//
//
//    public static void getBusLocation(Context context, IsStudentSrvedRequest isStudentSrvedRequest, final OnApiComplete<IsStudentSrvedResponse> onComplete) {
//        VolleyRequest<IsStudentSrvedResponse> request = new VolleyRequest<>(
//                context,
//                HttpMethod.POST,
//                Urls.IS_STUDENT_SERVED,
//                new TypeToken<IsStudentSrvedResponse>() {
//                }.getType(),
//                onComplete
//        );
//        request.setData(isStudentSrvedRequest);
//        sendRequest(context, request);
//    }
//    ///////////////////


    public static void getKidsHistory(Context context, String start_date, String end_date, int kid_id, final OnApiComplete<JsonObject> onComplete) {


        VolleyRequest<JsonObject> request = new VolleyRequest<>(
                context,
                HttpMethod.POST,
                Urls.PARENTS_KIDS_HISTORY,
                new TypeToken<JsonObject>() {
                }.getType(),
                onComplete
        );

        try {
            if ((start_date == null && end_date == null) || (start_date.equals("") || end_date.equals(""))) {
                start_date = DateTools.Formats.DATE_FORMAT_GMT.format(UtilityParent.getDateSevenDayesBefore());
                end_date = DateTools.Formats.DATE_FORMAT_GMT.format(UtilityParent.getDateNow());
            }

//            if (start_date != null && end_date != null && start_date.equals("") && end_date.equals("")) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("start_date", start_date);
            jsonObject.addProperty("end_date", end_date);
            jsonObject.addProperty("kid_id", kid_id);
            request.setData(jsonObject);
//            }

        } catch (Exception e) {
            e.printStackTrace();
            Application.logEvents(Constants.Urls.PARENTS_KIDS_HISTORY, "ApiController - getKidsHistory  ", e);

        }


        sendRequest(context, request);
    }


    public static void getStudentPickUpStatus(Context context, final OnApiComplete<ArrayList<StudentPickUpStatusResponse>> onComplete) {
//        VolleyRequest<ArrayList<StudentPickUpStatusResponse>> request = new VolleyRequest<>(
//                context,
//                HttpMethod.GET,
//                Urls.STUDENT_PICK_UP_STATUS,
//                new TypeToken<ArrayList<StudentPickUpStatusResponse>>() {
//                }.getType(),
//                onComplete
//        );
//        sendRequest(context, request);
    }

    public static void sendStudentPickUpStatus(Context context, StudentPickUpStatusRequest studentPickUpStatusRequest, final OnApiComplete<ArrayList<StudentPickUpStatusResponse>> onComplete) {
        VolleyRequest<ArrayList<StudentPickUpStatusResponse>> request = new VolleyRequest<>(
                context,
                HttpMethod.POST,
                Urls.STUDENT_PICK_UP_STATUS,
                new TypeToken<ArrayList<StudentPickUpStatusResponse>>() {
                }.getType(),
                onComplete
        );
        request.setData(studentPickUpStatusRequest);
        sendRequest(context, request);
    }


    public static void uploadStudentImage(Context context, int student_id, int school_id, String image_base64, final OnApiComplete<JsonObject> onComplete) {
        VolleyRequest<JsonObject> request = new VolleyRequest<>(
                context,
                HttpMethod.POST,
                Urls.UPLOAD_STUDENT_IMAGE,
                new TypeToken<JsonObject>() {
                }.getType(),
                onComplete
        );


        try{
            StringBuilder builder =new StringBuilder();
            builder.append("{");

            builder.append("\"");
            builder.append("student_id");
            builder.append("\"");
            builder.append(":");
            builder.append(student_id+"");

            builder.append("\"");
            builder.append("image_base64");
            builder.append("\"");
            builder.append(":");
            builder.append("\"");
            builder.append(image_base64+"");
            builder.append("\"");
            ;
            builder.append("}");


            request.setData(new GsonUtils.GsonBuilder().addParam("student_id",student_id).addParam("school_id",school_id).addParam("image_base64", image_base64).create().toString());

        }catch (Exception e){

        }


//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("student_id", student_id);
//        jsonObject.addProperty("school_id", school_id);
//        jsonObject.addProperty("", image_base64);
//        GsonUtils

        sendRequest(context, request);
    }


    public static void sendFeedBack(Context context, FeedbackRequest feedbackRequest, final OnApiComplete<Object> onComplete) {
        VolleyRequest<Object> request = new VolleyRequest<>(
                context,
                HttpMethod.POST,
                Urls.FEED_BACK,
                new TypeToken<Object>() {
                }.getType(),
                onComplete
        );
        request.setData(feedbackRequest);
        sendRequest(context, request);
    }


}
