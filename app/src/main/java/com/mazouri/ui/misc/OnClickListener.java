package com.mazouri.ui.misc;

import android.view.View;

/**
 * Created by anlijiu on 16-8-4.
 */
public interface OnClickListener<T> {
    void onClick(View v, T item);
}
