package com.mazouri.modules.me;

import android.databinding.ObservableField;

import com.mazouri.base.mvpvm.IViewModel;

public class MeViewModel implements IViewModel {

    public ObservableField<String> txt = new ObservableField<>();

    public MeViewModel() {
        txt.set("test string from viewmodel");
    }
}
