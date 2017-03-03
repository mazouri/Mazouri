package com.mazouri.base.injector;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;


import com.mazouri.R;
import com.mazouri.base.BaseFragment;
import com.mazouri.tools.Tools;

import butterknife.ButterKnife;

/**
 * Generic base class for injectable fragments.
 * <p/>
 * {@link #onComponentCreated(Object)} will be called in {@link #onAttach(Context)}.
 *
 * @see ComponentLifecycle
 */
public abstract class AbstractInjectableFragment<C> extends BaseFragment
        implements HasComponent<C>, ComponentLifecycle<C> {

    private static final String TAG = AbstractInjectableFragment.class.getName();
    private C mComponent;

    @NonNull
    @Override
    public C getComponent() {
        return mComponent;
    }

    protected abstract @LayoutRes int layoutId();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView in");
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        View view = createView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        onViewCreate();
        return view;
    }

    public View createView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
        return inflater.inflate(layoutId(), container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onViewDestroy();
    }

    /*
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(TAG, "onAttach  activity");
        mComponent = createComponent();

        if (mComponent == null) {
            throw new NullPointerException("Component must not be null");
        }

        onComponentCreated(mComponent);
        onPostComponentCreated();
    }
    */

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach");
        mComponent = createComponent();

        if (mComponent == null) {
            throw new NullPointerException("Component must not be null");
        }

        onComponentCreated(mComponent);
        onPostComponentCreated();
    }

    protected void onViewCreate() {
        configToolBar();
    }

    protected void configToolBar() {

    }

    @Override
    public void onComponentCreated(@NonNull final C component) {
    }

    @Override
    public void onPostComponentCreated() {
    }

    /**
     * Creates component instance.
     */
    protected abstract C createComponent();



    protected abstract void onViewDestroy();
}
