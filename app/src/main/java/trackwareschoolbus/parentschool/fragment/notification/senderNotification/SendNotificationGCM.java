package trackwareschoolbus.parentschool.fragment.notification.senderNotification;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import trackwareschoolbus.parentschool.API.ApiFacade;
import trackwareschoolbus.parentschool.API.ApiRequest;
import trackwareschoolbus.parentschool.API.enumApi.EnumMethodApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumTypeHeader;
import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallBack;
import trackwareschoolbus.parentschool.API_new.APIs.Constants;
import trackwareschoolbus.parentschool.utilityParent.UtilityParent;

/**
 * Created by  on 3/26/17.
 */

public class SendNotificationGCM implements IRestCallBack {


    protected void callRestAPI(
            String PATH_URL,
            HashMap params,
            EnumMethodApi verb,
            IRestCallBack restCallBack,
            EnumNameApi enumNameApi,
            Map<String, String> mapHeader,
            EnumTypeHeader enumTypeHeader
    ) {
        ApiFacade callApi = new ApiFacade();
        callApi.onStartVolley(new ApiRequest(PATH_URL,
                        params,
                        verb,
                        restCallBack,
                        enumNameApi,
                        mapHeader
                )
                ,
                enumTypeHeader
        );
    }

    public void sendNotification(final String token, final String msg) {

        if (UtilityParent.isEmptyString(token)) {
            return;
        }
        System.err.println(token + " @@@ " + msg);
        callRestAPI(Constants.Urls.NOTIFY_URL + Constants.Urls.PUSH_NOTIFICATION
                ,
                new HashMap() {{
                    put("endpoint_arn", token);
                    put("message", msg);
                }}
                ,
                EnumMethodApi.POST
                ,
                SendNotificationGCM.this
                ,
                EnumNameApi.PUSH_NOTIFICATION
                ,
                UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true)
                ,
                EnumTypeHeader.JSON
        );
    }

    @Override
    public void onRestCallBack(String response, EnumNameApi nameApiEnum) {

    }

    @Override
    public void onRestCallBack(JSONObject response, EnumNameApi nameApiEnum) {
        if (nameApiEnum == EnumNameApi.PUSH_NOTIFICATION) {

        }
    }

    @Override
    public void onRestCallBack(VolleyError volleyError, EnumNameApi nameApiEnum) {

    }

    @Override
    public void onRestCallBack(NetworkResponse networkResponse, EnumNameApi nameApiEnum) {

    }
//        Thread thread = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                try  {
//                    //Your code goes here
//
//
//
//
//            try {
//                // Prepare JSON containing the GCM message content. What to send and where to send.
//                JSONObject jGcmData = new JSONObject();
//                JSONObject jData = new JSONObject();
//                jData.put("message", msg);
//                // Where to send GCM message.
////            if (args.length > 1 && args[1] != null) {
//                jGcmData.put("to", token);
////            } else {
////                jGcmData.put("to", "/topics/global");
////            }
//                // What to send in GCM message.
//                jGcmData.put("data", jData);
//
//                // Create connection to send GCM Message request.
//                URL url = new URL("https://fcm.googleapis.com/fcm/send");
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestProperty("Authorization", "key=" + UtilityParent.WEB_API_KEY_GCM);
//                conn.setRequestProperty("Content-Type", "application/json");
//                conn.setRequestMethod("POST");
//                conn.setDoOutput(true);
//
//                // Send GCM message content.
//                OutputStream outputStream = conn.getOutputStream();
//                outputStream.write(jGcmData.toString().getBytes());
//
//                // Read GCM response.
//                InputStream inputStream = conn.getInputStream();
////                String resp = IOUtils.toString(inputStream);
//                System.out.println(inputStream.toString());
//                System.out.println("Check your device/emulator for notification or logcat for " +
//                        "confirmation of the receipt of the GCM message.");
//            } catch (Exception e) {
//                System.out.println("Unable to send GCM message.");
//                System.out.println("Please ensure that API_KEY has been replaced by the server " +
//                        "API key, and that the device's registration token is correct (if specified).");
//                e.printStackTrace();
//            }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        thread.start();
//                }


}


