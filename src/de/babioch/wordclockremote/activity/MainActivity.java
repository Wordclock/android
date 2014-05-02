package de.babioch.wordclockremote.activity;

import java.util.Locale;

import de.babioch.wordclockremote.R;
import de.babioch.wordclockremote.fragment.AdvancedFragment;
import de.babioch.wordclockremote.fragment.MainFragment;
import de.babioch.wordclockremote.fragment.TerminalFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                actionBar.setSelectedNavigationItem(position);

            }

        });

        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {

            Tab tab = actionBar.newTab();
            tab.setText(mSectionsPagerAdapter.getPageTitle(i));
            tab.setTabListener(this);

            actionBar.addTab(tab);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();

        if (id == R.id.action_about) {

            startActivity(new Intent(this, AboutActivity.class));

        } else if (id == R.id.action_settings) {

            startActivity(new Intent(this, SettingsActivity.class));

        } else {

            return super.onOptionsItemSelected(item);

        }

        return true;

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction transaction) {

        mViewPager.setCurrentItem(tab.getPosition());

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager manager) {

            super(manager);

        }

        @Override
        public Fragment getItem(int position) {

            switch(position) {

                case 0: return new MainFragment();
                case 1: return new AdvancedFragment();
                case 2: return new TerminalFragment();

            }

            return null;

        }

        @Override
        public int getCount() {

            return 3;

        }

        @Override
        public CharSequence getPageTitle(int position) {

            Locale l = Locale.getDefault();

            switch (position) {

                case 0: return getString(R.string.title_section_main).toUpperCase(l);
                case 1: return getString(R.string.title_section_advanced).toUpperCase(l);
                case 2: return getString(R.string.title_section_terminal).toUpperCase(l);

            }

            return null;

        }

    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction transaction)
    {

    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction transaction)
    {

    }

}
