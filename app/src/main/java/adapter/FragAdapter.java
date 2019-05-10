package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentLists=new ArrayList<>();

    public FragAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        fragmentLists=fragmentList;
    }


    @Override
    public Fragment getItem(int i) {
        return fragmentLists.get(i);
    }

    @Override
    public int getCount() {
        return fragmentLists.size();
    }
}
