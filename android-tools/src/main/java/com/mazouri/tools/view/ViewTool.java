package com.mazouri.tools.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.util.Property;
import android.view.View;

import com.mazouri.tools.anim.AnimTool;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by wangdongdong on 17-3-2.
 */

public class ViewTool {

    private static final Object lock = new Object();
    private static volatile ViewTool instance;

    private ViewTool() {
    }

    public static ViewTool instance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ViewTool();
                }
            }
        }
        return instance;
    }

    public final Property<View, Integer> BACKGROUND_COLOR
            = new AnimTool.IntProperty<View>("backgroundColor") {

        @Override
        public void setValue(View view, int value) {
            view.setBackgroundColor(value);
        }

        @Override
        public Integer get(View view) {
            Drawable d = view.getBackground();
            if (d instanceof ColorDrawable) {
                return ((ColorDrawable) d).getColor();
            }
            return Color.TRANSPARENT;
        }
    };

    public void showView(View view, boolean visibility) {
        if (view != null) {
            if (visibility) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }
    }


    public boolean hitTest(View v, int x, int y) {
        final int tx = (int) (ViewCompat.getTranslationX(v) + 0.5f);
        final int ty = (int) (ViewCompat.getTranslationY(v) + 0.5f);
        final int left = v.getLeft() + tx;
        final int right = v.getRight() + tx;
        final int top = v.getTop() + ty;
        final int bottom = v.getBottom() + ty;

        Log.e("hitTest", "x is " + x + " left is " + left + " right is " + right + "  y is " + y + "  top is " + top + " bottom is " + bottom);
        return (x >= left) && (x <= right) && (y >= top) && (y <= bottom);
    }

    public <V extends View> V setGone(final V view, final boolean gone) {
        if (view != null)
            if (gone) {
                if (GONE != view.getVisibility())
                    view.setVisibility(GONE);
            } else {
                if (VISIBLE != view.getVisibility())
                    view.setVisibility(VISIBLE);
            }
        return view;
    }
}
