package com.mazouri.ui.menu;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.mazouri.R;

/**
 * Created by wangdongdong on 17-3-3.
 */

public class NormalActionProvider extends ActionProvider {

    private ImageButton imageButton;

    private OnClickMenuListener onClickListener;

    /**
     * Creates a new instance.
     *
     * @param context Context for accessing resources.
     */
    public NormalActionProvider(Context context) {
        super(context);
    }

    @Override
    public View onCreateActionView() {
        int size = getContext().getResources().getDimensionPixelSize(
                android.support.design.R.dimen.abc_action_bar_default_height_material);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(size, size);
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.menu_normal_provider, null, false);

        view.setLayoutParams(layoutParams);
        imageButton = (ImageButton) view.findViewById(R.id.menu_item);
        view.setOnClickListener(onViewClickListener);
        return view;
    }

    // 点击处理。
    private View.OnClickListener onViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onClickListener != null)
                onClickListener.onClick();
        }
    };

    public void setOnClickListener(OnClickMenuListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickMenuListener {
        void onClick();
    }

    // 设置图标。
    public void setIcon(@DrawableRes int icon) {
        imageButton.setImageResource(icon);
    }
}
