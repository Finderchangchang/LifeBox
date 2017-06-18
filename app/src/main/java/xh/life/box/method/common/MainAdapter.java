package xh.life.box.method.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import xh.life.box.frag.MainFragment;

/**
 * Created by Finder丶畅畅 on 2017/1/17 21:15
 * QQ群481606175
 */

public class MainAdapter extends FragmentPagerAdapter {
    Fragment fragment[] = {new MainFragment(), new MainFragment(), new MainFragment(), new MainFragment()};

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragment[position];
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }
}
