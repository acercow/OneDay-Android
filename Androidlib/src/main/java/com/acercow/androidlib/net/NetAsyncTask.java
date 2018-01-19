package com.acercow.androidlib.net;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Just for practising
 * Created by jansen on 2018/1/19.
 */

@Deprecated
public abstract class NetAsyncTask<T> extends AsyncTask<String, Float, Response<T>> {

    public abstract void onSuccess(Response<T> response);
    public abstract void onFail(String strError);

    public NetAsyncTask() {
        super();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Response<T> doInBackground(String... strings) {
        return getResponseURL(strings[0]);
    }

    @Override
    protected void onPostExecute(Response<T> response) {
        if (response.getIsError()) {
            onFail(response.getErrorMessage());
        } else {
            onSuccess(response);
        }

    }

    @Override
    protected void onProgressUpdate(Float... values) {
        super.onProgressUpdate(values);
    }

    private Response<T> getResponseURL(String urlStr) {
        Response<T> response = new Response<>();
        String strResponse = null;
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == 200) {
                in = urlConnection.getInputStream();
                out = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                    out.flush();
                }
                strResponse = out.toString("utf-8");
            }
            if (strResponse == null) {
                response.setIsError(true);
                response.setErrorType(-1);
                response.setErrorMessage("网络异常，返回为空");
            } else {
                Gson gson = new Gson();
                Type responseType = new TypeToken<Response<T>>(){}.getType();
                response = gson.fromJson(strResponse, responseType);
            }

        } catch (Exception e) {
           response.setIsError(true);
           response.setErrorType(-1);
           response.setErrorMessage(e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return response;
    }
}
