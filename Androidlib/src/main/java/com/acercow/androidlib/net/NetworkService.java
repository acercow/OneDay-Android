package com.acercow.androidlib.net;

/**
 * Created by jansen on 2018/1/19.
 */

public class NetworkService<T> {
    public static NetworkService instance;


    private NetworkService() {

    }

    public static NetworkService getInstance() {
        if (instance == null) {
            synchronized (NetworkService.class) {
                if (instance == null) {
                    instance = new NetworkService();
                }
            }
        }
        return instance;
    }

    public interface RequestCallback<T> {
        void onSuccess(T response);
        void onFail(String strError);
    }

}
