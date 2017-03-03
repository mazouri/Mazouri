package com.mazouri.base.injector.component;

import com.mazouri.AppModule;
import com.mazouri.MazouriApp;
import com.mazouri.base.injector.AppGraph;
import com.mazouri.base.injector.scope.PerApplication;
import com.mazouri.data.DataModule;
import com.mazouri.ui.UiModule;

import dagger.Component;

/**
 * Created by wangdongdong on 17-3-2.
 */

@PerApplication
@Component(modules = { AppModule.class, UiModule.class, DataModule.class })
public interface ApplicationComponent extends AppGraph {

    void inject(MazouriApp app);

    /**
     * An initializer that creates the graph from an application.
     */
    final class Initializer {
        public static ApplicationComponent init(MazouriApp app) {
            return DaggerApplicationComponent.builder()
                    .appModule(new AppModule(app))
                    .build();
        }
        private Initializer() {} // No instances.
    }
}
