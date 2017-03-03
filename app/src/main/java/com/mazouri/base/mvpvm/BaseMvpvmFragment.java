package com.mazouri.base.mvpvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mazouri.BR;
import com.mazouri.base.IView;
import com.mazouri.base.injector.AbstractInjectableFragment;

import butterknife.ButterKnife;


public abstract class BaseMvpvmFragment<C> extends AbstractInjectableFragment<C> {
    public static final int VIEW_ID_SELF = 0;
    IViewModel viewModel;
    IView baseView;
    protected ViewDataBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Registry.add(this, viewId(), presenter());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if(viewId() != VIEW_ID_SELF) {
            baseView = ButterKnife.findById(view, viewId());
        } else {
            baseView = (IView) this;
        }

        viewModel = viewModel();
        binding.setVariable(BR.viewModel, viewModel);
        BasePresenter presenter = presenter();
        presenter.attach(baseView, viewModel);
        return view;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
        binding =  DataBindingUtil.inflate(inflater, layoutId(), container, false);
        return binding.getRoot();
        //return inflater.inflate(layoutId(), container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BasePresenter presenter = presenter();
        presenter.detach(baseView, viewModel);
    }

    @Override
    public void onComponentCreated(@NonNull final C component) {
    }

    @Override
    public void onPostComponentCreated() {
    }

    protected abstract BasePresenter<? extends IView, ? extends IViewModel> presenter();

    protected abstract <VM extends IViewModel> VM viewModel();

    protected abstract @IdRes int viewId();

}
