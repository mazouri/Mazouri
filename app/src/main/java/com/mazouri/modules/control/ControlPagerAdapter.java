package com.mazouri.modules.control;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import com.mazouri.R;
import com.mazouri.ui.FragmentPagerAdapter;

import java.util.HashSet;
import java.util.Set;

/**
 * Pager adapter for a user's different views
 */
public class ControlPagerAdapter extends FragmentPagerAdapter {

    private final FragmentManager fragmentManager;

    private final Resources resources;

    private final Set<String> tags = new HashSet<>();

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //empty this function to avoid recycler fragment in this adapter
        super.destroyItem(container, position, object);
    }

    public ControlPagerAdapter(final Fragment fragment) {
        super(fragment);

        fragmentManager = fragment.getChildFragmentManager();
        resources = fragment.getResources();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new ClimateFragment();
                break;
            case 1:
                fragment = new DoorLockFragment();
                break;
            case 2:
                fragment = new FindCarFragment();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        if (fragment instanceof Fragment)
            tags.add(((Fragment) fragment).getTag());
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return resources.getString(R.string.title_door_lock);
            case 1:
                return resources.getString(R.string.title_climate);
            case 2:
                return resources.getString(R.string.title_find_car);
            default:
                return null;
        }
    }
}
