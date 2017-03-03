package com.mazouri.modules.me;

import dagger.Subcomponent;

@MeScope
@Subcomponent(
    modules = MeModule.class
)
public interface MeComponent {
    void inject(MeFragment fragment);
}
