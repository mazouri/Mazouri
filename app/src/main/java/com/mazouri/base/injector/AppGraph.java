package com.mazouri.base.injector;


import android.content.Context;

import com.f2prateek.rx.preferences.Preference;
//import com.jakewharton.disklrucache.DiskLruCache;
import com.mazouri.MainActivity;
import com.mazouri.MazouriApp;
import com.mazouri.base.navigation.activity.ActivityScreenSwitcher;
import com.mazouri.data.ApiEndpoint;
import com.mazouri.modules.control.ControlComponent;
import com.mazouri.modules.control.ControlModule;
import com.mazouri.modules.control.ControlPagerFragment;
import com.mazouri.modules.home.HomeComponent;
import com.mazouri.modules.home.HomeModule;
import com.mazouri.modules.me.MeComponent;
import com.mazouri.modules.me.MeModule;
import com.mazouri.ui.ViewContainer;

import javax.inject.Named;

/**
 * A common interface implemented by both the internal and production flavored components.
 * only provide should be here, no inject, inject should be in component.
 */
public interface AppGraph {

    HomeComponent homeComponent(HomeModule module);
    ControlComponent controlComponent(ControlModule module);
    MeComponent meComponent(MeModule module);

    void inject(MainActivity activity);
//    void inject(LoginActivity activity);
//    void inject(RegisterActivity activity);
//    void inject(WelcomeActivity activity);
//
    void inject(ControlPagerFragment fragment);

    void inject(MazouriApp app);



    ViewContainer viewContainer();
//    DiskLruCache diskLruCache();
    ActivityScreenSwitcher activityScreenSwitcher();


//    ActivityHierarchyServer activityHierarchyServer();


//    @ApiEndpoint
//    Preference<String> apiEndPoint();
//    @Named("base_endpoint")
//    Preference<String> apiBase();

    @ContextLife("Application")
    Context context();
}
