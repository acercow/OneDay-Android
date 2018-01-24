package com.acercow.oneday.mockdata;

import com.acercow.androidlib.net.Response;
import com.acercow.oneday.bean.DiaryBean;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2018/1/24.
 */

public class MockBaseResponse extends MockService {
    @Override
    public String getJsonData() {
        Response<DiaryBean> response = new Response<>();
        response.setIsError(false);
        DiaryBean diaryBean = new DiaryBean();
        diaryBean.setUserName("zhaosen");
        response.setResult(diaryBean);
        return new Gson().toJson(response);
    }
}
