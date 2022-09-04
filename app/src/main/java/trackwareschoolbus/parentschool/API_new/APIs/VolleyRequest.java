package trackwareschoolbus.parentschool.API_new.APIs;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import trackwareschoolbus.parentschool.API_new.APIs.ListenersAndInterFaces.DefaultOnComplete;
import trackwareschoolbus.parentschool.API_new.APIs.ListenersAndInterFaces.OnApiComplete;
import trackwareschoolbus.parentschool.API_new.APIs.ListenersAndInterFaces.RequestHeader;
import trackwareschoolbus.parentschool.API_new.APIs.ListenersAndInterFaces.ServiceResponseErrorListener;
import trackwareschoolbus.parentschool.R;


public class VolleyRequest<RespType> extends Request<RespType> implements RequestHeader {

    public static final String TAG = VolleyRequest.class.getSimpleName();

    private Map<String, String> mapParams = new HashMap<String, String>();
    private Map<String, String> mapHeaders = new HashMap<String, String>();
    private byte[] data;
    private Gson gson = GsonInstance.getGson();
    private OnApiComplete<RespType> onComplete;
    private Type respType;
    private Context context;
    private String authorization;
    private boolean saveAuthorization = false;

    public VolleyRequest(Context context,
                         HttpMethod method,
                         String url,
                         Type respType,
                         OnApiComplete<RespType> onComplete,
                         int timeOutInMillis) {
        super(method.getValue(), url, new ServiceResponseErrorListener(context, onComplete));
        this.onComplete = onComplete;
        this.respType = respType;
        this.context = context;
        setRetryPolicy(new DefaultRetryPolicy(timeOutInMillis, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        this.saveAuthorization = false;
    }


    public VolleyRequest(Context context,
                         HttpMethod method,
                         String url,
                         Type respType,
                         OnApiComplete<RespType> onComplete) {
        this(context, method, url, respType, onComplete, 10000);
        this.saveAuthorization = false;
    }

    public VolleyRequest(Context context,
                         HttpMethod method,
                         String url,
                         Type respType,
                         OnApiComplete<RespType> onComplete,
                         boolean saveAuthorization) {
        this(context, method, url, respType, onComplete, 10000);
        this.saveAuthorization = saveAuthorization;
    }

    @Override
    public void addHeader(String key, Object o) {

        if (o == null || key == null) {
            return;
        }
        mapHeaders.put(key, o.toString());
    }

    @Override
    public void addParam(String key, Object o) {
        if (o == null || key == null) {
            return;
        }
        mapParams.put(key, o.toString());
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mapHeaders;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mapParams;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Response<RespType> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, "UTF-8");
            Log.v("response", "" + response.toString());
            RespType resp = gson.fromJson(json, this.respType);
            authorization = response.headers.get("Authorization");
            if (saveAuthorization) {
//                SavedDataUtil.setShared(SavedDataUtil.AUTHORIZATION, authorization, context);


            }
            return Response.success(resp, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(RespType response) {
        if (response != null) {
            try {
                onComplete.onSuccess(response);
            } catch (Exception e) {
                onComplete.onError(DefaultOnComplete.NULL_RESPONSE, context.getString(R.string.message_error_in_server));
            }
        } else {
            onComplete.onError(DefaultOnComplete.NULL_RESPONSE, context.getString(R.string.message_no_data));
        }
    }

    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }

    @Override
    protected String getParamsEncoding() {
        return "UTF-8";
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return data;
    }

    public void setData(Object data) {
        if (data instanceof byte[]) {
            this.data = (byte[]) data;
        } else if (data instanceof String) {
            this.data = ((String) data).getBytes();
        }else {
            String str = gson.toJson(data);
            Log.e(TAG, "data: " + str);
            this.data = str.getBytes();
        }
    }


}