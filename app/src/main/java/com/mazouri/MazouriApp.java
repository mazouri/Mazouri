package com.mazouri;

import android.app.Application;
import android.content.Context;

import com.mazouri.base.injector.component.ApplicationComponent;
import com.mazouri.tools.Tools;
import com.mazouri.ui.ActivityHierarchyServer;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

/**
 * Created by wangdongdong on 17-3-2.
 */

public class MazouriApp extends Application {
    public static final String TAG = MazouriApp.class.getSimpleName();

    private volatile static MazouriApp app;
    private ApplicationComponent component;

    @Inject
    ActivityHierarchyServer activityHierarchyServer;

    @Override
    public void onCreate() {
        super.onCreate();
        Tools.init(this);

        Tools.log().d(TAG, "onCreate");
        app = this;
        Tools.log().d(TAG, "onCreate app : " + app);
        LeakCanary.install(this);

        RxJavaPlugins.getInstance().registerErrorHandler(new RxJavaErrorHandler() {
            @Override
            public void handleError(Throwable e) {
                super.handleError(e);
                Tools.log().d(TAG, "RxJavaPlugins.getInstance() handleError -> %s", e);
            }
        });

        buildComponentAndInject();

        registerActivityLifecycleCallbacks(activityHierarchyServer);
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    public static MazouriApp getApplication() {
        Tools.log().d(TAG, "getApplication app : " + app);
        return app;
    }

    public void buildComponentAndInject() {
        if(component == null)
            component = ApplicationComponent.Initializer.init(this);
        component.inject(this);
    }

    public ApplicationComponent component() {
        return component;
    }

    public static MazouriApp get(Context context) {
        return (MazouriApp) context.getApplicationContext();
    }
}
