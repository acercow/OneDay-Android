package com.acercow.oneday.net;

import android.content.Context;

import com.acercow.androidlib.net.RetrofitClient;
import com.acercow.oneday.mockdata.MockService;

/**
 * Created by zhaosen on 2018/1/21.
 */

public class ApiUtils {
//    public static final String BASE_URL = "https://api.stackexchange.com/2.2/";

    public static <T> T getService(Context context, String urlKey, Class<T> clz) {
        UrlData urlData = UrlConfigManager.findUrlData(context, urlKey);
        if (urlData != null) {
            if (urlData.getExpires() > 0) {
                // TODO Cache

            } else {
                if (urlData.getNetType().equals("GET")) {
                    return RetrofitClient.getClient(urlData.getUrl()).create(clz);
                } else if (urlData.getNetType().equals("POST")) {

                }
            }
        }
        return null;
    }

    public static String getMockJsonString(Context context, String urlKey) {
        UrlData urlData = UrlConfigManager.findUrlData(context, urlKey);
        if (urlData != null) {
            try {
                return ((MockService) Class.forName(urlData.getMockClass()).newInstance()).getJsonData();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
