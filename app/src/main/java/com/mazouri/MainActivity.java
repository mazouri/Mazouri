package com.mazouri;

import android.accounts.Account;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Window;
import android.widget.FrameLayout;

import com.mazouri.base.injector.AbstractInjectableActivity;
import com.mazouri.base.injector.InjectHelper;
import com.mazouri.base.injector.component.ApplicationComponent;
import com.mazouri.base.navigation.activity.ActivityScreen;
import com.mazouri.modules.control.ControlPagerFragment;
import com.mazouri.modules.home.HomeFragment;
import com.mazouri.modules.me.MeFragment;
import com.mazouri.tools.Tools;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends AbstractInjectableActivity<ApplicationComponent> {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;

    private List<Account> accountList;
    private Fragment currentFragment;
    private HomeFragment homeFragment;
    private ControlPagerFragment controlFragment;
    private MeFragment meFragment;

    private FragmentManager fragmentManager;
    private SparseArray fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tools.log().d("onCreate");
        accountList = getAccounts();
        Tools.log().d(TAG, "accountList.size %d", accountList.size());
//        if (accountList.isEmpty()) {
//            Intent intent = new Intent(this, WelcomeActivity.class);
//            startActivity(intent);
//            finish();
//        }
        setTitle("Ma");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Remove the status bar color. The DrawerLayout is responsible for drawing it from now on.
            setStatusBarColor(getWindow());
        }
        fragmentManager = getSupportFragmentManager();
        initToolbar();
        initPager(savedInstanceState);
        initBottomBar(savedInstanceState);

    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    private void initPager(Bundle savedInstanceState) {
        if (savedInstanceState != null) {  // “内存重启”时调用
            //Toast.makeText(this, " initPager, 内存重启 savedInstanceState is " + savedInstanceState, Toast.LENGTH_SHORT).show();
            homeFragment = (HomeFragment) fragmentManager.findFragmentByTag(HomeFragment.class.getName());
            meFragment = (MeFragment) fragmentManager.findFragmentByTag(MeFragment.class.getName());
            controlFragment = (ControlPagerFragment) fragmentManager.findFragmentByTag(ControlPagerFragment.class.getName());
            fragmentManager.beginTransaction()
                    .show(homeFragment)
                    .hide(controlFragment)
                    .hide(meFragment)
                    .commit();
        } else {  // 正常时
            //Toast.makeText(this, " initPager, 正常 ", Toast.LENGTH_SHORT).show();
            homeFragment = new HomeFragment();
            meFragment = new MeFragment();
            controlFragment = new ControlPagerFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.container, homeFragment, HomeFragment.class.getName())
                    .add(R.id.container, controlFragment, ControlPagerFragment.class.getName())
                    .add(R.id.container, meFragment, MeFragment.class.getName())
                    .hide(controlFragment)
                    .hide(meFragment)
                    .commit();
        }
        currentFragment = homeFragment;

        fragmentList = new SparseArray();
        fragmentList.append(R.id.tab_home, homeFragment);
        fragmentList.append(R.id.tab_control, controlFragment);
        fragmentList.append(R.id.tab_me, meFragment);
    }


    private void initBottomBar(Bundle savedInstanceState) {
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switchToFragment(tabId);
            }
        });
    }

    private void switchToFragment(int index) {
        Tools.log().d(TAG, "switchToFragment %d", index);
        Fragment to = (Fragment) fragmentList.get(index);
        if(to == null || to.getTag().equals(currentFragment.getTag())) return;
        Tools.log().d(TAG, "switchToFragment %s", to);
        fragmentManager.beginTransaction()
                .hide(currentFragment)
                .show(to)
                .commit();

        currentFragment = to;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onComponentCreated(@NonNull ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    protected ApplicationComponent createComponent() {
        return InjectHelper.getApplicationComponent();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static void setStatusBarColor(Window window) {
        window.setStatusBarColor(Color.TRANSPARENT);
    }


    public static class BrandNewScreen extends ActivityScreen {
        @Override
        protected Class<? extends Activity> activityClass() {
            return MainActivity.class;
        }

        @Override
        protected final void configureIntent(@NonNull Intent intent) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
    }
}
