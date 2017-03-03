package com.mazouri.base.mvpvm;

import android.support.annotation.LayoutRes;
import android.view.View;


public interface ItemViewModel extends IViewModel{

    @LayoutRes int layout();

    void onItemClick(View view);

}

