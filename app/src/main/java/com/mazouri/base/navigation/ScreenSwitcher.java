package com.mazouri.base.navigation;

public interface ScreenSwitcher {
    void open(Screen screen);
    void open(Screen screen, int requestCode);
    void goBack();
}
