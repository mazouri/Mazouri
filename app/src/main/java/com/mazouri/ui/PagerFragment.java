/*
 * Copyright (c) 2015 PocketHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mazouri.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mazouri.base.injector.AbstractInjectableFragment;

public abstract class PagerFragment<C> extends AbstractInjectableFragment<C> implements
    ViewPager.OnPageChangeListener {

    /**
     * Get provider of the currently selected fragment
     *
     * @return fragment provider
     */
    protected abstract FragmentProvider getProvider();

    /**
     * Get selected fragment
     *
     * @return fragment
     */
    protected Fragment getFragment() {
        FragmentProvider provider = getProvider();
        if (provider != null)
            return provider.getSelected();
        else
            return null;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        Fragment fragment = getFragment();
        if (fragment != null)
            return fragment.onOptionsItemSelected(item);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Fragment fragment = getFragment();
        if (fragment != null)
            fragment.onCreateOptionsMenu(menu, getActivity().getMenuInflater());
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
        int positionOffsetPixels) {
        // Intentionally left blank
    }

    @Override
    public void onPageSelected(int position) {
        // Intentionally left blank
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // Intentionally left blank
    }
}
