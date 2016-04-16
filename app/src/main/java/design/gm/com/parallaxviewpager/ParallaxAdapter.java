package design.gm.com.parallaxviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

public class ParallaxAdapter extends FragmentStatePagerAdapter {

    private ArrayList<ParallaxFragment> mFragments;
    private ViewPager viewPager;

    public ParallaxAdapter(FragmentManager fm) {
        super(fm);

        mFragments = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void add(ParallaxFragment parallaxFragment) {
        mFragments.add(parallaxFragment);
        notifyDataSetChanged();
        viewPager.setCurrentItem(getCount() - 1, true);
    }
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }
}
