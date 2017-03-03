package com.mazouri.ui;

import android.app.Activity;

import com.mazouri.base.injector.scope.PerApplication;
import com.mazouri.base.mvpvm.Registry;
import com.mazouri.base.navigation.activity.ActivityScreenSwitcher;
import com.mazouri.ui.annotation.ActivityScreenSwitcherServer;
import com.mazouri.ui.misc.SocketActivityHierarchyServer;

import dagger.Module;
import dagger.Provides;

import static com.mazouri.base.mvpvm.Registry.SERVER;

@Module
public class UiModule {

    @Provides
    @PerApplication
    ActivityScreenSwitcher provideActivityScreenSwitcher() {
        return new ActivityScreenSwitcher();
    }

    @Provides
    @PerApplication
    @ActivityScreenSwitcherServer
    ActivityHierarchyServer provideActivityScreenSwitcherServer(final ActivityScreenSwitcher screenSwitcher) {
        return new ActivityHierarchyServer.Empty() {
            @Override
            public void onActivityStarted(Activity activity) {
                screenSwitcher.attach(activity);
            }

            @Override
            public void onActivityStopped(Activity activity) {
                screenSwitcher.detach();
            }
        };
    }

    @Provides
    @PerApplication
    ViewContainer provideAppContainer() {
        return ViewContainer.DEFAULT;
    }

    @Provides
    @PerApplication
    ActivityHierarchyServer provideActivityHierarchyServer(@ActivityScreenSwitcherServer ActivityHierarchyServer server) {
        final ActivityHierarchyServer.Proxy proxy = new ActivityHierarchyServer.Proxy();
        proxy.addServer(server);
        proxy.addServer(Registry.SERVER);
        proxy.addServer(SERVER);
        proxy.addServer(new SocketActivityHierarchyServer());
        return proxy;
    }

}
