package com.acercow.oneday.net;

import com.acercow.androidlib.net.RetrofitClient;

/**
 * Created by zhaosen on 2018/1/21.
 */

public class ApiUtils {
    public static final String BASE_URL = "https://api.stackexchange.com/2.2/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
