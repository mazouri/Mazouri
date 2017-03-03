package com.mazouri.modules.control;

import android.databinding.ObservableField;

import com.mazouri.base.mvpvm.IViewModel;

public class ClimateViewModel implements IViewModel {


    public ObservableField<String> txt = new ObservableField<>();

    public ClimateViewModel() {
        txt.set("test string from viewmodel");
    }

}
