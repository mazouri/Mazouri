package com.mazouri.modules.control;

import dagger.Subcomponent;

@ControlScope
@Subcomponent(
    modules = ControlModule.class
)
public interface ControlComponent {
    void inject(ClimateFragment fragment);
    void inject(FindCarFragment fragment);
    void inject(DoorLockFragment fragment);
}
