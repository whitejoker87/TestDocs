package ru.orehovai.testdocs.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.orehovai.testdocs.view.AllDocsListFragment;
import ru.orehovai.testdocs.view.DummyFragment;

public class DocsFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] {"Все", "Разделы", "Избранное"};
    private Context context;
    public DocsFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return AllDocsListFragment.newInstance(position);
            case 1:
                return new DummyFragment();
            case 2:
                return AllDocsListFragment.newInstance(position);
            default:
                break;
        }
        return new DummyFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
