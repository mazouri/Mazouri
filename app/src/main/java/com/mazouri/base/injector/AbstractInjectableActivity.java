package com.mazouri.base.injector;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mazouri.base.BaseActivity;
import com.mazouri.ui.ViewContainer;

import java.util.UUID;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Generic base class for injectable activities.
 * <p/>
 * {@link #onComponentCreated(Object)} will be called in {@link #onCreate(Bundle)}.
 *
 * @see ComponentLifecycle
 */
public abstract class AbstractInjectableActivity<C> extends BaseActivity
        implements HasComponent<C>, ComponentLifecycle<C> {
    private static final String BF_UNIQUE_KEY = AbstractInjectableActivity.class.getName() + ".unique.key";
    @Inject
    ViewContainer viewContainer;

    private C mComponent;

    private String uniqueKey;

    protected abstract @LayoutRes int layoutId();

    @NonNull
    @Override
    public C getComponent() {
        return mComponent;
    }

    @Override
    public abstract void onComponentCreated(@NonNull final C component);

    @Override
    public void onPostComponentCreated() {
    }

    /**
     * Creates component instance.
     */
    protected abstract C createComponent();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Bundle params = getIntent().getExtras();
        if (params != null) {
            onExtractParams(params);
        }

        if (savedInstanceState != null && savedInstanceState.containsKey(BF_UNIQUE_KEY)) {
            uniqueKey = savedInstanceState.getString(BF_UNIQUE_KEY);
        } else {
            uniqueKey = UUID.randomUUID().toString();
        }

        // Deliberately create the component before the super() call so that it's initialized in
        // Fragment's onAttach()!
        mComponent = createComponent();

        if (mComponent == null) {
            throw new NullPointerException("Component must not be null");
        }

        super.onCreate(savedInstanceState);

        onComponentCreated(mComponent);


        if (viewContainer == null) {
            throw new IllegalStateException("No injection happened. Add component.inject(this) in onCreateComponent() implementation.");
        }

        //Registry.add(this, viewId(), presenter());
        final LayoutInflater layoutInflater = getLayoutInflater();
        ViewGroup container = viewContainer.forActivity(this);
        View view = createView(layoutInflater, layoutId(), container);
        ButterKnife.bind(this, view);
        ButterKnife.bind(this);
        onPostComponentCreated();
    }

    public View createView(LayoutInflater inflater, @LayoutRes int layoutId, ViewGroup parent) {
        return inflater.inflate(layoutId, parent);
    }

    public String uniqueKey() {
        return uniqueKey;
    }

    protected void onExtractParams(@NonNull Bundle params) {
        // default no implementation
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BF_UNIQUE_KEY, uniqueKey);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        uniqueKey = savedInstanceState.getString(BF_UNIQUE_KEY);
    }



}
