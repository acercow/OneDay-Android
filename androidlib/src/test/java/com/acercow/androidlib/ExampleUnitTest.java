package com.acercow.androidlib;

import com.acercow.androidlib.net.Response;
import com.google.gson.Gson;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        Gson gson = new Gson();
        Response res = gson.fromJson("{\n" +
                "  \"isError\" : true,\n" +
                "  \"errorType\" : 1,\n" +
                "  \"errorMessage\" : \"Network Error\",\n" +
                "  \"result\" : {\n" +
                "    \"text\" : \"niubi\"\n" +
                "  }\n" +
                "}", Response.class);
        System.out.println(res.getResult());
    }

    @Test
    public void rxTest() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                System.out.println("Observable Thread -> " + Thread.currentThread().getName());
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onComplete();
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            Disposable mDisposable;
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                if (integer == 2) {
                    mDisposable.dispose();
                }
                System.out.println("onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("Consumer Thread -> " + Thread.currentThread().getName());
                System.out.println("Consumer " + integer);
            }
        };

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(consumer);
    }
}