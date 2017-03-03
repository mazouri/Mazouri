package com.mazouri.modules.me;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
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

public class MeFragment extends BaseMvpvmFragment<MeComponent> implements IView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @Inject MePresenter presenter;
    @Inject MeViewModel viewModel;


    @Override
    protected int layoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected MeComponent createComponent() {
         return InjectHelper.getApplicationComponent().meComponent(new MeModule());

    }

    @Override
    public void onComponentCreated(@NonNull MeComponent component) {
        component.inject(this);
    }

    @Override
    protected void configToolBar() {
        toolbarTitle.setText("我");
        toolbar.setTitle("");
//        setHasOptionsMenu(true);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_settings_black_24dp);
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        menu.clear();
//        inflater.inflate(R.menu.menu_fragment_me, menu);
//        MenuItem item = menu.findItem(R.id.action_me_right);
//        NormalActionProvider mActionProvider = (NormalActionProvider) MenuItemCompat.getActionProvider(item);
//        mActionProvider.onCreateActionView();
//        mActionProvider.setIcon(R.drawable.ic_msg_black_24dp);
//        mActionProvider.setOnClickListener(() -> Tools.toast().showToast(getActivity(), "You click action item"));// 设置点击监听。
//    }

    @Override
    protected void onViewDestroy() {

    }

    @Override
    protected  MePresenter presenter() {
        return presenter;
    }

    @Override
    protected MeViewModel viewModel() {
        return viewModel;
    }

    @Override
    protected int viewId() {
        return VIEW_ID_SELF;
    }

}