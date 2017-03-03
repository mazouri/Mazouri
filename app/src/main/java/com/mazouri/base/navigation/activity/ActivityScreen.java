package com.mazouri.base.navigation.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.view.View;

import com.mazouri.base.navigation.Screen;

import java.util.ArrayList;
import java.util.List;

public abstract class ActivityScreen implements Screen {

    private static final String BF_TRANSITION_VIEW = "ActivityScreen.transitionView";

    private List<Pair<View, String>> sharedElements = new ArrayList<>();
    public void attachTransitionView(@Nullable View view) {
        sharedElements.add(new Pair<>(view, view.getTransitionName()));
    }

    @Nullable
    protected Pair<View, String>[] detachTransitionView() {
        @SuppressWarnings("unchecked") Pair<View, String>[] arr = sharedElements.toArray(new Pair[sharedElements.size()]);
        sharedElements.clear();
        return arr;
    }

    @NonNull
    protected final Intent intent(Context context) {
        Intent intent = new Intent(context, activityClass());
        configureIntent(intent);
        return intent;
    }

    protected final Bundle activityOptions(Activity activity) {
        Pair<View, String>[] arr  = detachTransitionView();
        return ActivityOptionsCompat.makeSceneTransitionAnimation(activity, arr).toBundle();
    }

    protected abstract void configureIntent(@NonNull Intent intent);
    protected abstract Class<? extends Activity> activityClass();

    public static void setTransitionView(View view) {
        ViewCompat.setTransitionName(view, BF_TRANSITION_VIEW);
    }

}
