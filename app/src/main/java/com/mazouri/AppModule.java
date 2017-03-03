package com.mazouri;

import android.app.Application;
import android.content.Context;

import com.mazouri.base.injector.ContextLife;
import com.mazouri.base.injector.scope.PerApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wangdongdong on 17-3-2.
 */

@Module
public class AppModule {
    private final MazouriApp app;

    public AppModule(MazouriApp app) {
        this.app = app;
    }

    @Provides
    @PerApplication
    Application provideApplication() {
        return app;
    }

    @Provides
    @PerApplication
    @ContextLife("Application")
    Context provideApplicationContext() {
        return app.getApplicationContext();
    }
}
