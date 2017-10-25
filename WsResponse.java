package com.example.webservice;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.example.model.ResponseModel;
import com.example.utils.WsConstants;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class WsResponse extends WSUtil {

    private Context context;
    private String message;
    private boolean success;

    public WsResponce(Context context) {
        super(context);
    }

    public ResponseModel executeService(Context context,final String baseUrl, final String appKey,
                                        final String pairKey) {
        this.context = context;
        return parseResponse(callServiceHttpPost(context, baseUrl, generateRequest(appKey, pairKey)));
    }

    private RequestBody generateRequest(final String appKey, final String pairKey) {
        final WsConstants wsConstants = new WsConstants();
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        formBodyBuilder.add(wsConstants.PARAMS_APP_KEY, appKey);
        formBodyBuilder.add(wsConstants.PARAMS_PAIR_KEY, pairKey);
        return formBodyBuilder.build();
    }

    private ResponseModel parseResponse(final String response) {
        Log.e("respponse", response);

        final WsConstants wsConstants = new WsConstants();
        if (response != null && response.trim().length() > 0) {
            try {
                final JSONObject jsonObject = new JSONObject(response);
                final String app = jsonObject.optString(wsConstants.PARAMS_APP);
                final JSONObject jsonObjectapp = new JSONObject(app);
                final String responseString = jsonObjectapp.optString(wsConstants.PARAMS_RESPONSE);
                final JSONObject jsonObjectResponse = new JSONObject(responseString);

                success = !jsonObjectResponse.optBoolean(wsConstants.PARAMS_ERROR);
                message = jsonObjectResponse.optString(wsConstants.PARAMS_MESSAGE);
                setSuccess(isSuccess());
                setMessage(message);

                if (success) {
                    final Gson gson = new Gson();
                    return gson.fromJson(jsonObjectResponse.toString(), ResponseModel.class);
                }

            } catch (Exception e) {
                e.printStackTrace();
                try {
                    final JSONObject jsonObject = new JSONObject(response);
                    final String settings = jsonObject.optString(wsConstants.PARAMS_SETTING);
                    final JSONObject jsonObjectSettings = new JSONObject(settings);

                    success = jsonObjectSettings.optString(wsConstants.PARAMS_SUCCESS).equals("1");
                    message = jsonObjectSettings.optString(wsConstants.PARAMS_MESSAGE);

                    setSuccess(isSuccess());
                    setMessage(message);

                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
