package com.mazouri.base.mvpvm;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.util.AttributeSet;

import com.mazouri.BR;
import com.mazouri.base.IView;
import com.mazouri.base.injector.AbstractInjectableViewAnimator;

/**
 * Created by anlijiu on 16-11-17.
 */

public abstract class BaseMvpvmViewAnimator<C> extends AbstractInjectableViewAnimator<C> implements IView {
    IViewModel viewModel;
    protected ViewDataBinding binding;

    public BaseMvpvmViewAnimator(Context context, AttributeSet attrs) {
        super(context, attrs);
        binding = binding();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        BasePresenter presenter = presenter();

        viewModel = viewModel();
        binding.setVariable(BR.viewModel, viewModel);
        presenter.attach(this, viewModel);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BasePresenter presenter = presenter();
        presenter.detach(this, viewModel);
        viewModel = null;
    }

    @Override
    public void onPostComponentCreated() {
    }

    protected abstract ViewDataBinding binding();

    protected abstract BasePresenter presenter();

    protected abstract <VM extends IViewModel> VM viewModel();
}
