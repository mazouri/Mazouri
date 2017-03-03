package com.mazouri.modules.control;

import android.support.annotation.NonNull;

import com.mazouri.R;
import com.mazouri.base.IView;
import com.mazouri.base.injector.InjectHelper;
import com.mazouri.base.mvpvm.BaseMvpvmFragment;

import javax.inject.Inject;

public class ClimateFragment extends BaseMvpvmFragment<ControlComponent> implements IView {

    @Inject ClimatePresenter presenter;
    @Inject ClimateViewModel viewModel;


    @Override
    protected int layoutId() {
        return R.layout.fragment_climate;
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
    protected  ClimatePresenter presenter() {
        return presenter;
    }

    @Override
    protected ClimateViewModel viewModel() {
        return viewModel;
    }

    @Override
    protected int viewId() {
        return VIEW_ID_SELF;
    }
}