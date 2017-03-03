package com.mazouri.base;

import android.accounts.Account;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.mazouri.R;
import com.mazouri.accounts.AccountsManager;
import com.mazouri.ui.misc.SystemBarTintManager;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

/**
 * Created by wangdongdong on 17-3-2.
 */

public class BaseActivity extends RxAppCompatActivity {

    private boolean toolbarEnabled = true;
    private Toolbar toolbar;
    private AccountsManager accountsManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        configureTheme();
//        setTranslucentStatus();
        super.onCreate(savedInstanceState);
        /*
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明底部导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        */
        accountsManager = new AccountsManager();
    }

    private void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(0);

//        if (this.getClass().getSimpleName().equals(StartActivity.class.getSimpleName())) {
//            tintManager.setTintColor(android.R.color.transparent);    //StartActivity 专用颜色
//        } else if (this.getClass().getSimpleName().equals(IDAuthPhotoActivity.class.getSimpleName())) {
//            //nothing
//        } else {
//            tintManager.setStatusBarTintResource(R.color.control_title_bg_black);
//        }
    }

    private void configureTheme() {
        SharedPreferences defaultSharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String pref_theme =
                defaultSharedPreferences.getString("pref_theme", getString(R.string.theme_light));
        configureTheme("theme_dark".equalsIgnoreCase(pref_theme));
    }

    protected void configureTheme(boolean dark) {

        if (dark) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            //setTheme(R.style.AppTheme_Dark);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            //setTheme(R.style.AppTheme);
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        if (isToolbarEnabled()) {
            toolbar = (Toolbar) findViewById(getToolbarId());

            if (toolbar != null) {
                toolbar.setTitle(R.string.app_name);
                setSupportActionBar(toolbar);

                // On Lollipop, the action bar shadow is provided by default, so have to remove it explicitly
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setElevation(0);
                }
            }
        }
    }

    public void setToolbarEnabled(boolean enabled) {
        this.toolbarEnabled = enabled;
    }

    public boolean isToolbarEnabled() {
        return toolbarEnabled;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    public void setTitle(CharSequence title) {
        if (toolbar != null) {
            toolbar.setTitle(title);
        } else {
            super.setTitle(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        if (toolbar != null) {
            toolbar.setTitle(titleId);
        }
        super.setTitle(titleId);
    }

    @NonNull
    protected List<Account> getAccounts() {
        return accountsManager.getAccounts(this.getApplicationContext());
    }

    protected void removeAccount(Account selectedAccount,
                                 final AccountsManager.RemoveAccountCallback removeAccountCallback) {
        accountsManager.removeAccount(this, selectedAccount, removeAccountCallback);
    }

    protected void changeNotificationState(Account account, boolean enabled) {
        accountsManager.changeNotificationState(getApplicationContext(), account, enabled);
    }

    public void reload() {
        getContent();
    }

    protected void getContent() {

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
