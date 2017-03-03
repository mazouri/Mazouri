package com.mazouri.base.mvpvm;

import android.support.annotation.NonNull;

import com.mazouri.base.IView;


public interface IPresenter<V extends IView, VM extends IViewModel> {

    /**
     * 注入View，使之能够与View相互响应
     *
     * @param iView
     */
    void attach(@NonNull V iView, VM viewModel);

    /**
     * 释放资源
     */
    void detach(V view, VM viewModel);
}
