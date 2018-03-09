package com.acercow.oneday.net;

import com.acercow.oneday.dialog.bean.SOAnswersResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zhaosen on 2018/1/21.
 */

public interface SOService {
    @GET("?order=desc&sort=activity&site=stackoverflow")
    Observable<SOAnswersResponse> getAnswers();

    @GET("?order=desc&sort=activity&site=stackoverflow")
    Observable<SOAnswersResponse> getAnswers(@Query("tagged") String tags);

}
