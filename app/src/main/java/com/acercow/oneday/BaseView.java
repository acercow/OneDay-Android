package com.acercow.oneday;

/**
 * Created by JansenZhao on 2017/11/15.
 */

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
