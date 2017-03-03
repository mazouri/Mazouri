package com.mazouri.modules.home;


import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    @Provides
    @HomeScope
    HomeViewModel providesHomeViewModel() {
        return new HomeViewModel();
    }

    @Provides
    @HomeScope
    HomePresenter providesHomePresenter() {
        return new HomePresenter();
    }

}
