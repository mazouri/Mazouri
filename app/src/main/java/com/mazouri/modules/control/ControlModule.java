package com.mazouri.modules.control;


import dagger.Module;
import dagger.Provides;

@Module
public class ControlModule {

    @Provides
    @ControlScope
    ClimateViewModel providesClimateViewModel() {
        return new ClimateViewModel();
    }

    @Provides
    @ControlScope
    ClimatePresenter providesClimatePresenter() {
        return new ClimatePresenter();
    }

    @Provides
    @ControlScope
    DoorLockViewModel providesDoorLockViewModel() {
        return new DoorLockViewModel();
    }

    @Provides
    @ControlScope
    DoorLockPresenter providesDoorLockPresenter() {
        return new DoorLockPresenter();
    }

    @Provides
    @ControlScope
    FindCarViewModel providesFindCarViewModel() {
        return new FindCarViewModel();
    }

    @Provides
    @ControlScope
    FindCarPresenter providesFindCarPresenter() {
        return new FindCarPresenter();
    }

}
