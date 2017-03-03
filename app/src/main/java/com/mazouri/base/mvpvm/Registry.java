package com.mazouri.base.mvpvm;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;

import com.mazouri.Constants;
import com.mazouri.base.IView;
import com.mazouri.tools.Tools;
import com.mazouri.ui.ActivityHierarchyServer;

import java.util.Arrays;
import java.util.HashMap;

import butterknife.ButterKnife;

public class Registry {
    private static final HashMap<String, Entry> registers = new HashMap<>();
    public static final ActivityHierarchyServer.Empty SERVER = new ActivityHierarchyServer.Empty() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            final String key = getKey(activity);
            final Entry entry = registers.get(key);
            Tools.log().e("%s onActivityCreated", key);
            if (entry != null && savedInstanceState != null) {
                entry.presenter.onRestore(savedInstanceState);
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public void onActivityStarted(Activity activity) {
            final String key = getKey(activity);
            final Entry entry = registers.get(key);
            Tools.log().e("%s onActivityStarted", key);
            if (entry != null) {
                final IBaseView view = getRealView(entry.viewId, activity) ;
                entry.presenter.attach(view, entry.viewModel);
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public void onActivityStopped(Activity activity) {
            final String key = getKey(activity);
            final Entry entry = registers.get(key);
            Tools.log().e("%s onActivityStopped", key);
            if (entry != null) {
                final IBaseView view = getRealView(entry.viewId, activity) ;
                entry.presenter.detach(view, entry.viewModel);
            }
        }

        private IBaseView getRealView(int viewId, Activity activity) {
            final IBaseView view;
            if (viewId == BaseMvpvmActivity.VIEW_ID_SELF) {
                view = (IBaseView) activity;
            } else {
                view = ButterKnife.findById(activity, viewId);
            }
            return view;
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            final String key = getKey(activity);
            final Entry entry = registers.get(key);
            Tools.log().e("%s onActivitySaveInstanceState", key);
            if (entry != null) {
                entry.presenter.onSave(outState);
            }
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            final String key = getKey(activity);
            Tools.log().e("%s onActivityDestroyed", key);
            registers.remove(key);
        }
    };

    public static <V extends IView, VM extends IViewModel> void add(Activity activity, @IdRes int viewId, BasePresenter<V, VM> presenter, VM viewModel) {
        registers.put(getKey(activity), new Entry<>(viewId, presenter, viewModel));
    }

    private static String getKey(Activity activity) {
        StringBuilder builder = new StringBuilder();
        builder.append(activity.getClass().getName());
        if (activity instanceof BaseMvpvmActivity) {
            final String uniqueKey = ((BaseMvpvmActivity) activity).uniqueKey();
            if (!uniqueKey.isEmpty()) {
                builder.append(Constants.DOT).append(uniqueKey);
            }
        } else {
            final String action = activity.getIntent().getAction();
            if (action != null) {
                builder.append(Constants.DOT).append(action);
            }
            final Uri data = activity.getIntent().getData();
            if (data != null) {
                builder.append(Constants.DOT).append(data.toString());
            }
            final Bundle extras = activity.getIntent().getExtras();
            if (extras != null) {
                for (String key : extras.keySet()) {
                    Object value = extras.get(key);
                    if (value == null) continue;
                    String valueString;
                    if (value.getClass().isArray()) {
                        valueString = Arrays.toString((Object[]) value);
                    } else {
                        valueString = value.toString();
                    }

                    builder.append(Constants.DOT);
                    builder.append(key).append(Constants.COLON);
                    builder.append(valueString);
                }
            }
        }
        return builder.toString();
    }

    private static class Entry<V extends IView, VM extends IViewModel> {
        @IdRes
        public final int viewId;
        public final VM viewModel;
        public final BasePresenter<V, VM> presenter;

        public Entry(int viewId, BasePresenter<V, VM> presenter, VM viewModel) {
            this.viewId = viewId;
            this.presenter = presenter;
            this.viewModel = viewModel;
        }
    }
}
