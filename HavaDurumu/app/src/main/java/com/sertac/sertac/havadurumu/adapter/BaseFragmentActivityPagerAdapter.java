package com.sertac.sertac.havadurumu.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;

public class BaseFragmentActivityPagerAdapter extends PagerAdapter {


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
