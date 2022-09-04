package trackwareschoolbus.parentschool.API;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import trackwareschoolbus.parentschool.API.enumApi.EnumMethodApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi;
import trackwareschoolbus.parentschool.API.enumApi.EnumTypeHeader;
import trackwareschoolbus.parentschool.API.interfaceApi.IVolleySendApi;
import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.app.Application;
import trackwareschoolbus.parentschool.utilityParent.StaticValue;
import trackwareschoolbus.parentschool.utilityParent.UtilDialogs;
import trackwareschoolbus.parentschool.utilityParent.UtilityParent;


/**
 * Created by  on 1/7/17.
 */

public class ApiClient implements IVolleySendApi {

    //    public static final String ERROR_VOLLEY = "ERROR_VOLLEY";
    ApiRequest volleyBeans;
    Button view;
   // ProgressDialog progressDialog;
    Activity activity;

    public ApiClient(ApiRequest volleyBean) {
        this.volleyBeans = volleyBean;
        activity = volleyBean.getActivity();
       // progressDialog = new ProgressDialog(activity);
        view = (Button) volleyBean.getView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVolley(volleyBeans);
            }
        });
        sendVolley(volleyBeans);
    }

    public ApiClient(ApiRequest volleyBeans, EnumTypeHeader enumTypeHeader) {
        if (StaticValue.mActivity != null){
            if (!UtilityParent.isNetworkAvailable(StaticValue.mActivity)){
                UtilDialogs.showGeneralErrorDialog(StaticValue.mActivity).setDialogeMessage(R.string.internet_connection);
//                UtilityParent.showMessageDialog(StaticValue.mActivity,"",StaticValue.mActivity.getString(R.string.internet_connection));
                if (StaticValue.progressDialog!=null){
                    if (StaticValue.progressDialog.isShowing())
                        StaticValue.progressDialog.dismiss();
                }
                return;
            }
        }
        this.volleyBeans = volleyBeans;
        if (enumTypeHeader == EnumTypeHeader.JSON) {
            try {
                sendVolleyJson(volleyBeans);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            sendVolley(volleyBeans, true);
        }
    }

    @Override
    public void sendVolley(final ApiRequest volleyBean) {
       // showProgressDialog();
        int typeMethod = Request.Method.POST;
        if (volleyBean.getMethod().equals(EnumMethodApi.GET)) {
            typeMethod = Request.Method.GET;
        }
        StringRequest jsonObjReq = new StringRequest(typeMethod,
                volleyBean.getUrl_path(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //StaticValue.progressDialog.dismiss();
                        view.setVisibility(View.INVISIBLE);
                        volleyBeans.restCallBack.onRestCallBack(response, volleyBean.getEnumNameApi());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //StaticValue.progressDialog.dismiss();
                view.setVisibility(View.VISIBLE);
                view.setText(activity.getString(R.string.retry));
                volleyBeans.restCallBack.onRestCallBack(error, volleyBean.getEnumNameApi());

            }
        }) {
            @Override
            protected Map getParams() {
                return volleyBean.getMap();
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                volleyBeans.restCallBack.onRestCallBack(response, volleyBean.getEnumNameApi());
                return super.parseNetworkResponse(response);
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return volleyBean.getMapHeader();
            }
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Application.getInstanceVolly().addToRequestQueue(jsonObjReq, volleyBean.getUrl_path());
    }


    @Override
    public void sendVolley(final ApiRequest volleyBean, boolean value) {
        //mashowProgressDialog();
        int typeMethod = Request.Method.POST;
        if (volleyBean.getMethod().equals(EnumMethodApi.GET)) {
            typeMethod = Request.Method.GET;
        }
        final String requestBody = new JSONObject(volleyBean.getMap()).toString();
        StringRequest jsonObjReq = new StringRequest(typeMethod,
                volleyBean.getUrl_path(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // StaticValue.progressDialog.dismiss();
                        volleyBeans.restCallBack.onRestCallBack(response, volleyBean.getEnumNameApi());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyBeans.restCallBack.onRestCallBack(error, volleyBean.getEnumNameApi());
            }
        }) {
//            @Override
//            protected Map<String,Object> getParams() {
//                return volleyBean.getMap();
//            }

           /* added mohammad bader*/
            /*@Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                volleyBeans.restCallBack.onRestCallBack(response, volleyBean.getEnumNameApi());
                return super.parseNetworkResponse(response);
            }*/

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return volleyBean.getMapHeader();
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Application.getInstanceVolly().addToRequestQueue(jsonObjReq, volleyBean.getUrl_path());
    }

    @Override
    public void sendVolleyJson(final ApiRequest volleyBean) throws Exception{
        int typeMethod = Request.Method.POST;
        if (volleyBean.getMethod().equals(EnumMethodApi.GET)) {
            typeMethod = Request.Method.GET;
        }
        JSONObject jsonObject = new JSONObject(volleyBean.getMap());
        if (volleyBean.getEnumNameApi() == EnumNameApi.PARENTS_KIDS_HISTORY){
            System.err.println("");
        }
        JsonObjectRequest myRequest = new JsonObjectRequest(
                typeMethod,
                volleyBean.getUrl_path(),
                jsonObject,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (StaticValue.progressDialog!=null){
                            if (StaticValue.progressDialog.isShowing())
                                StaticValue.progressDialog.dismiss();
                        }
                        volleyBeans.restCallBack.onRestCallBack(response, volleyBean.getEnumNameApi());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (StaticValue.progressDialog!=null){
                            if (StaticValue.progressDialog.isShowing())
                                StaticValue.progressDialog.dismiss();
                        }
                        volleyBeans.restCallBack.onRestCallBack(error, volleyBean.getEnumNameApi());
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return UtilityParent.typeHeaderMap(EnumTypeHeader.JSON, true);
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                volleyBeans.restCallBack.onRestCallBack(response, volleyBean.getEnumNameApi());
                return super.parseNetworkResponse(response);
            }
        };
        Application.getInstance().addToRequestQueue(myRequest, "tag");
    }

    /*public void showProgressDialog() {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.setMessage(activity.getString(R.string.waiting));
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, activity.getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                view.setVisibility(View.INVISIBLE);
                                progressDialog.dismiss();
                            }
                        });

                progressDialog.show();

            }
        });


    }*/


}
