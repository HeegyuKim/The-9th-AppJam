package kr.applepi.drinkingcare.Activitys;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import kr.applepi.drinkingcare.Fragments.AddFragment1;
import kr.applepi.drinkingcare.Fragments.AddFragment2;
import kr.applepi.drinkingcare.R;
import kr.applepi.drinkingcare.TestViewPager;

public class AddActivity extends AppCompatActivity {
    static public TestViewPager MainActivitypager;

    static public int drinkType = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("추가");

        MainActivitypager = (TestViewPager) findViewById(R.id.pager_add);
        MainActivitypager.setAdapter(new AddAdapter(getSupportFragmentManager()));
        MainActivitypager.setOffscreenPageLimit(2);
        MainActivitypager.setCurrentItem(0);
        MainActivitypager.enabled=false;
    }

    public class AddAdapter extends FragmentPagerAdapter{

        public AddAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = null;
            switch (position) {
                case 0:
                    f = new AddFragment1();
                    break;
                case 1:
                    f = new AddFragment2();
                    break;
            }
            return f;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            default:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
