package com.mazouri.base.mvpvm;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mazouri.BR;
import com.mazouri.base.IView;
import com.mazouri.base.injector.AbstractInjectableActivity;


public abstract class BaseMvpvmActivity<C, V extends IView, VM extends IViewModel> extends AbstractInjectableActivity<C> {
    public static final int VIEW_ID_SELF = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Registry.add(this, viewId(), presenter(), viewModel());
    }

    public View createView(LayoutInflater inflater, @LayoutRes int layoutId, ViewGroup parent) {
        ViewDataBinding binding = binding();
        binding.setVariable(BR.viewModel, viewModel());
        return binding.getRoot();
        //return inflater.inflate(layoutId, parent);
    }


    @Override
    public void onPostComponentCreated() {
    }

    protected abstract BasePresenter<V, VM> presenter();

    protected abstract ViewDataBinding binding();

    protected abstract @IdRes int viewId();

    protected abstract VM viewModel();
}
