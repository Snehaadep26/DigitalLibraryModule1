package com.example.digitallibrarymodule.AdminAdapter;





import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdminViewPagerAdapter extends FragmentPagerAdapter {


    private final List<Fragment> isFragment=new ArrayList<>();
    private final List<String> isTitles=new ArrayList<>();


    public AdminViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return isFragment.get(position);
    }


    @Override
    public int getCount() {
        return isTitles.size();
    }


    @Override
    public CharSequence getPageTitle(int position)
    {
        return isTitles.get(position);
    }


    public void AddFragment(Fragment fragment,String title)
    {
        isFragment.add(fragment);
        isTitles.add(title);
    }
}
