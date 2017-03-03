package com.mazouri.modules.me;


import dagger.Module;
import dagger.Provides;

@Module
public class MeModule {

    @Provides
    @MeScope
    MeViewModel providesMeViewModel() {
        return new MeViewModel();
    }

    @Provides
    @MeScope
    MePresenter providesMePresenter() {
        return new MePresenter();
    }

}
