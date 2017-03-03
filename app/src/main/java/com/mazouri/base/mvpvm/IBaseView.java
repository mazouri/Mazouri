package com.mazouri.base.mvpvm;

import com.mazouri.base.IView;

public interface IBaseView extends IView {
    void showLoading();
    void showContent();
    void showEmpty();
    void showError(Throwable throwable);
}
