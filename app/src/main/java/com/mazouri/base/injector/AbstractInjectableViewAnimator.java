package com.mazouri.base.injector;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.mazouri.ui.misc.BetterViewAnimator;

/**
 * Created by anlijiu on 16-11-17.
 */

public abstract class AbstractInjectableViewAnimator<C> extends BetterViewAnimator
        implements HasComponent<C>, ComponentLifecycle<C> {

    private static final String TAG = AbstractInjectableFragment.class.getName();
    private C mComponent;


    public AbstractInjectableViewAnimator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @NonNull
    @Override
    public C getComponent() {
        return mComponent;
    }

    protected abstract @LayoutRes
    int layoutId();

    /**
     * Creates component instance.
     */
    protected abstract C createComponent();

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //ButterKnife.bind(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mComponent = createComponent();

        if (mComponent == null) {
            throw new NullPointerException("Component must not be null");
        }

        onComponentCreated(mComponent);
        onPostComponentCreated();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
