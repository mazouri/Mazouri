package com.mazouri.modules.home;


import android.databinding.ObservableField;

import com.mazouri.base.mvpvm.IViewModel;

public class HomeViewModel implements IViewModel {

    public ObservableField<String> txt = new ObservableField<>();

    public HomeViewModel() {
        txt.set("test string from viewmodel");
    }
}
