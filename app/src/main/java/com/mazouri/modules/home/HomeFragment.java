package com.mazouri.modules.home;

import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.mazouri.R;
import com.mazouri.base.IView;
import com.mazouri.base.injector.InjectHelper;
import com.mazouri.base.mvpvm.BaseMvpvmFragment;
import com.mazouri.tools.Tools;
import com.mazouri.ui.menu.NormalActionProvider;

import javax.inject.Inject;

import butterknife.BindView;

public class HomeFragment extends BaseMvpvmFragment<HomeComponent> implements IView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @Inject HomePresenter presenter;
    @Inject HomeViewModel viewModel;


    @Override
    protected int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeComponent createComponent() {
        return InjectHelper.getApplicationComponent().homeComponent(new HomeModule());

    }

    @Override
    public void onComponentCreated(@NonNull final HomeComponent component) {
        component.inject(this);
    }

    @Override
    protected void configToolBar() {
        toolbarTitle.setText("首页");
        toolbar.setTitle("");
//        setHasOptionsMenu(true);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_weather_black_24dp);
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        menu.clear();
//        inflater.inflate(R.menu.menu_fragment_home, menu);
//        MenuItem item = menu.findItem(R.id.action_home_right);
//        NormalActionProvider mActionProvider = (NormalActionProvider) MenuItemCompat.getActionProvider(item);
//        mActionProvider.onCreateActionView();
//        mActionProvider.setIcon(R.drawable.ic_flash_black_24dp);
//        mActionProvider.setOnClickListener(() -> Tools.toast().showToast(getActivity(), "You click action item"));// 设置点击监听。
//    }

    @Override
    protected void onViewDestroy() {

    }

    @Override
    protected HomePresenter presenter() {
        return presenter;
    }

    @Override
    protected HomeViewModel viewModel() {
        return viewModel;
    }

    @Override
    protected int viewId() {
        return VIEW_ID_SELF;
    }

}