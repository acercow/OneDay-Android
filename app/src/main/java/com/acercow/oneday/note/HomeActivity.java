package com.acercow.oneday.note;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.acercow.androidlib.activity.BaseActivity;
import com.acercow.oneday.R;
import com.acercow.oneday.note.feed.FeedFragment;
import com.acercow.oneday.note.media.MediaFlowFragment;
import com.acercow.oneday.note.timeline.TimelineFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements HomeContract.View {
    private HomeContract.Presenter mHomePresenter;
    private FloatingActionButton mFab;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private int[] tabIcons = {
            R.drawable.ic_book_white_48dp,
            R.drawable.ic_insert_invitation_white_48dp,
            R.drawable.ic_monochrome_photos_white_48dp
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void initView(View view) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFab = findViewById(R.id.fab);
        mTabLayout = findViewById(R.id.tab_layout_main);
        mFab.setOnClickListener(this);
        mViewPager = findViewById(R.id.home_view_pager);
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        adapter.addPage(TimelineFragment.newInstance("", ""), "");
        adapter.addPage(FeedFragment.newInstance(1), "");
        adapter.addPage(MediaFlowFragment.newInstance("", ""), "");
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        setTabIcon();
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }

    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.mHomePresenter  = presenter;
    }

    @Override
    public void showSearch() {

    }

    @Override
    public void showProfile() {

    }

    @Override
    public void showCalendar() {

    }

    @Override
    public void inputTextMode() {

    }

    @Override
    public void choosePhotoMode() {

    }

    @Override
    public void recorderMode() {

    }

    @Override
    public void scheduleMode() {

    }

    public class SectionPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragments = new ArrayList<>();
        private final List<String> titles = new ArrayList<>();

        SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        void addPage(Fragment fragment, String title) {
            fragments.add(fragment);
            titles.add(title);
        }
    }

    private void setTabIcon() {
        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
        mTabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }
}
