package com.acercow.oneday.permissiongrant;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.acercow.androidlib.activity.BaseActivity;
import com.acercow.oneday.R;
import com.acercow.oneday.bean.Item;
import com.acercow.oneday.bean.SOAnswersResponse;
import com.acercow.oneday.net.ApiUtils;
import com.acercow.oneday.net.SOService;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PermissionGrantActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private AnswersAdapter mAdapter;
    private SOService mService;

    private TextView mTvTest;
    private CompositeDisposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_permission_grant;
    }

    @Override
    public void initView(View view) {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_answers);
        mAdapter = new AnswersAdapter(this, new ArrayList<Item>(0), new AnswersAdapter.PostItemListener() {

            @Override
            public void onPostClick(long id) {
                Toast.makeText(PermissionGrantActivity.this, "Post id is" + id, Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        mTvTest = findViewById(R.id.tv_test);
        mDisposable = new CompositeDisposable();

    }

    @Override
    public void doBusiness(Context mContext) {
        mService = ApiUtils.getSOService();
        mService.getAnswers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SOAnswersResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.v(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(SOAnswersResponse soAnswersResponse) {
                        Log.v(TAG, "onNext " + soAnswersResponse);
                        mAdapter.updateAnswers(soAnswersResponse.getItems());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v(TAG, "onError");

                    }

                    @Override
                    public void onComplete() {
                        Log.v(TAG, "onComplete");

                    }
                });

//        mService.getAnswers().enqueue(new Callback<SOAnswersResponse>() {
//            @Override
//            public void onResponse(Call<SOAnswersResponse> call, Response<SOAnswersResponse> response) {
//                if(response.isSuccessful()) {
//                    mAdapter.updateAnswers(response.body().getItems());
//                    Log.d("MainActivity", "posts loaded from API");
//                }else {
//                    int statusCode  = response.code();
//                    // handle request errors depending on status code
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
//                Log.d("MainActivity", "error loading from API");
//            }
//        });

//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onNext(3);
//            }
//        }).map(new Function<Integer, String>() {
//            @Override
//            public String apply(Integer integer) throws Exception {
//                return "This is result " + integer;
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.d(TAG, s);
//            }
//        });
    }

    @Override
    public void onSingleClick(View v) {

    }
}
