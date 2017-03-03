package com.mazouri.base.injector;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mazouri.MazouriApp;
import com.mazouri.base.injector.component.ApplicationComponent;


public final class InjectHelper {

    private InjectHelper() {
    }

    /**
     * Returns component provided by given Activity.
     *
     * @param componentType Class of component
     * @param context      context providing the component
     * @param <C>           Type of component
     * @return Component instance
     */
    @SuppressWarnings("unchecked")
    public static <C> C getComponent(@NonNull final Class<C> componentType,
                                     @NonNull final Context context) {

        return componentType.cast(((HasComponent<C>) context).getComponent());
    }

//    @NonNull
//    public static ApplicationComponent getApplicationComponent(@NonNull final Context context) {
//
//        return ((MazouriApp) context.getApplicationContext()).component();
//    }

    @NonNull
    public static ApplicationComponent getApplicationComponent() {

        return MazouriApp.getApplication().component();
    }

    @SuppressWarnings("unchecked")
    public static <C> C getComponent(Context context) {
        return ((HasComponent<C>) context).getComponent();
    }

}
