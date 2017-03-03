package com.mazouri.modules.control;

import android.support.annotation.NonNull;

import com.mazouri.R;
import com.mazouri.base.IView;
import com.mazouri.base.injector.InjectHelper;
import com.mazouri.base.mvpvm.BaseMvpvmFragment;

import javax.inject.Inject;

public class FindCarFragment extends BaseMvpvmFragment<ControlComponent> implements IView {

    @Inject FindCarPresenter presenter;
    @Inject FindCarViewModel viewModel;


    @Override
    protected int layoutId() {
        return R.layout.fragment_find_car;
    }

    @Override
    protected ControlComponent createComponent() {
        return InjectHelper.getApplicationComponent().controlComponent(new ControlModule());

    }

    @Override
    public void onComponentCreated(@NonNull ControlComponent component) {
        component.inject(this);
    }

    @Override
    protected void onViewDestroy() {

    }

    @Override
    protected  FindCarPresenter presenter() {
        return presenter;
    }

    @Override
    protected FindCarViewModel viewModel() {
        return viewModel;
    }

    @Override
    protected int viewId() {
        return VIEW_ID_SELF;
    }

}