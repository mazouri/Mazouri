
package com.mazouri.modules.control;

import android.support.design.widget.AppBarLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mazouri.R;
import com.mazouri.base.injector.InjectHelper;
import com.mazouri.base.injector.component.ApplicationComponent;
import com.mazouri.tools.Tools;
import com.mazouri.ui.TabPagerFragment;
import com.mazouri.ui.menu.NormalActionProvider;

import butterknife.BindView;

public class ControlPagerFragment extends TabPagerFragment<ControlPagerAdapter, ApplicationComponent> {

    private static final String TAG = "ControlPagerFragment";

    private static final String PREF_ORG_ID = "orgId";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @Override
    protected void onViewCreate() {
        super.onViewCreate();
        configureTabPager();
    }

    @Override
    protected ControlPagerAdapter createAdapter() {
        return new ControlPagerAdapter(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_control_pager;
    }

    @Override
    protected ApplicationComponent createComponent() {
        return InjectHelper.getApplicationComponent();
    }

    @Override
    protected void configToolBar() {
        Tools.log().d(TAG, "configToolBar 控制");
        toolbarTitle.setText("控制");
        toolbar.setTitle("");
//        setHasOptionsMenu(true);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_weather_black_24dp);
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        menu.clear();
//        inflater.inflate(R.menu.menu_fragment_control, menu);
//        MenuItem item = menu.findItem(R.id.action_control_right);
//        NormalActionProvider mActionProvider = (NormalActionProvider) MenuItemCompat.getActionProvider(item);
//        mActionProvider.onCreateActionView();
//        mActionProvider.setIcon(R.drawable.ic_flash_black_24dp);
//        mActionProvider.setOnClickListener(() -> Tools.toast().showToast(getActivity(), "You click action item"));// 设置点击监听。
//    }

    @Override
    protected void onViewDestroy() {

    }

    @Override
    public void onComponentCreated(ApplicationComponent component) {
        component.inject(this);
    }

}
