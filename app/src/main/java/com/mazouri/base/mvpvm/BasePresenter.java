package com.mazouri.base.mvpvm;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.mazouri.base.IView;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IView, VM extends IViewModel> implements IPresenter<V, VM> {
    private WeakReference<V> view = null;
    private WeakReference<VM> viewModel = null;
    /**
     * Load has been called for the current {@link #view}.
     */
    private boolean loaded;

    public void attach(@NonNull V view, VM viewModel) {
        if (view == null) throw new NullPointerException("new view must not be null");

        if (this.view != null) detach(this.view.get(), this.viewModel.get());

        this.view = new WeakReference<>(view);
        this.viewModel = new WeakReference<VM>(viewModel);
        if (!loaded) {
            loaded = true;
            onLoad();
        }
    }

    /**
     * 释放资源
     */
    public void detach(V view, VM viewModel) {
        if (view == null) throw new NullPointerException("dropped view must not be null");
        loaded = false;
        this.view = null;
        this.viewModel = null;
        onDestroy();
    }

    protected final V getView() {
        if (view == null) throw new NullPointerException("getView called when view is null. Ensure takeView(View view) is called first.");
        return view.get();
    }

    protected final VM getViewModel() {
        if (viewModel == null) throw new NullPointerException("getViewModel called when viewModel is null. Ensure takeView(View view) is called first.");
        return viewModel.get();
    }


    protected void onLoad() {
    }

    protected void onDestroy() {
    }

    protected void onRestore(@NonNull Bundle savedInstanceState) {
    }

    protected void onSave(@NonNull Bundle outState) {
    }
}
