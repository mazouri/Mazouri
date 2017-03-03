package com.mazouri.base.injector.module;

import android.content.Context;

import com.mazouri.MazouriApp;
import com.mazouri.base.injector.ContextLife;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {
    private MazouriApp mApplication;


    public ApplicationModule(MazouriApp application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    @ContextLife("Application")
    public Context provideContext() {
        return mApplication.getApplicationContext();
    }

}
