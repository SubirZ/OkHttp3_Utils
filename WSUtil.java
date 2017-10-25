package com.example.webservice;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.example.R;
import com.example.utils.WsConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;


/**
 * Web Service utility class to call web urls. And returns response.
 */

class WSUtil {
    private final int TIMEOUT_SECONDS = 30;
    private final int TIMEOUT_SECONDS_LONG = 30 * 2 * 2;
    private Context mContext;
    private OkHttpClient client;

    WSUtil(Context context) {

        mContext = context;
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build();

    }

    public String callServiceHttpPost(final Context context, final String url, final RequestBody requestBody) {
        Log.e(WSUtil.class.getSimpleName(), String.format("Request String : %s", requestBody.toString()));
        String responseString = "";
        try {
            final OkHttpClient okHttpClient = new OkHttpClient();
            final OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient().newBuilder();
            okHttpClientBuilder.connectTimeout(WsConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS);
            okHttpClientBuilder.readTimeout(WsConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS);

            final Request.Builder requestBuilder = new Request.Builder();
            requestBuilder.url(url);
            requestBuilder.post(requestBody);
            final Request request = requestBuilder.build();
            final Response response = okHttpClient.newCall(request).execute();
            responseString = response.body().string();
            if (TextUtils.isEmpty(responseString) || !isJSONValid(responseString)) {
                responseString = getNetWorkError();
            }
            Log.e(WSUtil.class.getSimpleName(), String.format("Response String : %s", responseString));
        } catch (IOException e) {
            e.printStackTrace();
            responseString = getNetWorkError();
        }
        return responseString;
    }

    /**
     * Builds url with parameters
     *
     * @param url    url
     * @param bundle parameters
     * @return
     */
    String buildURL(String url, Bundle bundle) {

        HttpUrl.Builder builder = getBaseUrl().addPathSegment(url);
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                if (bundle.get(key) != null && !TextUtils.isEmpty(bundle.get(key).toString())) {
                    builder.addQueryParameter(key, bundle.get(key).toString());
                }
            }
        }

        final String fullUrl = Uri.decode(builder.build().toString());
        Log.d("builder", "builder==" + fullUrl);

        return fullUrl;
    }

    /**
     * GET network request
     *
     * @param url url
     * @return
     */
    protected String GET(String url) {
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            String res = response.body().string();

            if (TextUtils.isEmpty(res) || !isJSONValid(res)) {
                res = getNetWorkError();
            }

            return res;
        } catch (Exception e) {
            try {
            } catch (Exception ignored) {
            }
        }
        return "";
    }

    /**
     * Builds request body
     *
     * @param bundle parameters
     * @return
     */
    protected RequestBody generateRequest(Bundle bundle) {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                if (bundle.get(key) != null && !TextUtils.isEmpty(bundle.get(key).toString())) {
                    formBodyBuilder.add(key, bundle.get(key).toString());
                }
            }
        }
        return formBodyBuilder.build();
    }

    public String callServiceHttpPost(final Context mContext, final String url, final RequestBody requestBody, String token) {
        this.mContext = mContext;
        String responseString;

        try {
            final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .connectTimeout(WsConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(WsConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .build();
            Request request = new Request.Builder()
                    .addHeader("authorization", token)
                    .addHeader("Content-Type", "image/jpeg")
                    .addHeader("Content-Length", String.valueOf(requestBody.contentLength()))
                    .url(url)
                    .post(requestBody)
                    .build();
            final Response response = okHttpClient.newCall(request).execute();
            responseString = response.body().string();

            if (TextUtils.isEmpty(responseString) || !isJSONValid(responseString)) {
                responseString = getNetWorkError();
            }
        } catch (IOException e) {
            e.printStackTrace();
            responseString = getNetWorkError();
        }
        return responseString;

    }
    
    public String postBinaryData(final Context mContext, final String url,final InputStream inputStream, final String token) {
        this.mContext = mContext;
        String responseString;

        try {
            final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            final OkHttpClient okHttpClient = new OkHttpClient();
            final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("image/*");

            RequestBody requestBody = create(MEDIA_TYPE_MARKDOWN, inputStream);
            Request request = new Request.Builder()
                    .addHeader("authorization", token)
                    .addHeader("Content-Type", "image/jpeg")
                    .addHeader("Content-Length", String.valueOf(requestBody.contentLength()))
                    .url(url)
                    .post(requestBody)
                    .build();

            final Response response = okHttpClient.newCall(request).execute();
            responseString = response.body().string();

            if (TextUtils.isEmpty(responseString) || !isJSONValid(responseString)) {
                responseString = getNetWorkError();
            }
        } catch (IOException e) {
            e.printStackTrace();
            responseString = getNetWorkError();
        }
        return responseString;
    }

    public RequestBody create(final MediaType mediaType, final InputStream inputStream) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return mediaType;
            }

            @Override
            public long contentLength() {
                try {
                    return inputStream.available();
                } catch (IOException e) {
                    return 0;
                }
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                Source source = null;
                try {
                    source = Okio.source(inputStream);
                    sink.writeAll(source);
                } finally {
                    Util.closeQuietly(source);
                }
            }
        };
    }

    public String callServiceHttpGet(final Context mContext, final String url, final String token) {
        this.mContext = mContext;
        String responseString;
        try {
            final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .connectTimeout(WsConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(WsConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            final Response response = okHttpClient.newCall(request).execute();
            responseString = response.body().string();
            //Utils.writeLog(mContext, "Response String : " + responseString);
            if (TextUtils.isEmpty(responseString) || !isJSONValid(responseString)) {
                responseString = getNetWorkError();
            }
        } catch (IOException e) {
            e.printStackTrace();
            responseString = getNetWorkError();
        }
        return responseString;
    }

    public String callServiceHttpGet(final Context mContext, final String url) {
        this.mContext = mContext;
        String responseString;
        try {
            final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .connectTimeout(WsConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(WsConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .build();
            WsConstants wsConstants = new WsConstants();
            Request request = new Request.Builder()
//                    .header("access_token", token)
                    .url(url)
                    .get()
                    .build();

            final Response response = okHttpClient.newCall(request).execute();
            responseString = response.body().string();
            //Utils.writeLog(mContext, "Response String : " + responseString);
            if (TextUtils.isEmpty(responseString) || !isJSONValid(responseString)) {
                responseString = getNetWorkError();
            }
        } catch (IOException e) {
            e.printStackTrace();
            responseString = getNetWorkError();
        }
        return responseString;
    }

    public String getAuthentication(final String url, final String encodedString) {
        final OkHttpClient client = new OkHttpClient();
        String responseString;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("authorization", encodedString)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            responseString = response.body().string();
            if (TextUtils.isEmpty(responseString) || !isJSONValid(responseString)) {
                responseString = getNetWorkError();
            }
        } catch (IOException e) {
            e.printStackTrace();
            responseString = getNetWorkError();
        }
        return responseString;
    }
    
    // checks if there is any network error and returs check network message in case of network error.
    private String getNetWorkError() {
        final JSONObject jsonObject = new JSONObject();
        try {
            final WsConstants wsConstants = new WsConstants();


            final JSONObject jsonObjectSetting = new JSONObject();
            jsonObjectSetting.put(wsConstants.PARAMS_SUCCESS, "0");
            jsonObjectSetting.put(wsConstants.PARAMS_MESSAGE, mContext.getString(R.string.check_connection));

            jsonObject.put(wsConstants.PARAMS_SETTING, jsonObjectSetting);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
    
    
    // checks weather the json is valid.
    private boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
