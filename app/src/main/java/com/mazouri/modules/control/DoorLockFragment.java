package com.mazouri.modules.control;

import android.support.annotation.NonNull;

import com.mazouri.R;
import com.mazouri.base.IView;
import com.mazouri.base.injector.InjectHelper;
import com.mazouri.base.mvpvm.BaseMvpvmFragment;

import javax.inject.Inject;

public class DoorLockFragment extends BaseMvpvmFragment<ControlComponent> implements IView {

    @Inject DoorLockPresenter presenter;
    @Inject DoorLockViewModel viewModel;


    @Override
    protected int layoutId() {
        return R.layout.fragment_door_lock;
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
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void onViewDestroy() {

    }

    @Override
    protected  DoorLockPresenter presenter() {
        return presenter;
    }

    @Override
    protected DoorLockViewModel viewModel() {
        return viewModel;
    }

    @Override
    protected int viewId() {
        return VIEW_ID_SELF;
    }

}