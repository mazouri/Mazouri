package com.mazouri.modules.home;

import dagger.Subcomponent;

@HomeScope
@Subcomponent(
    modules = HomeModule.class
)
public interface HomeComponent {
    void inject(HomeFragment fragment);
}
