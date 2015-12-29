package kr.applepi.drinkingcare.Activitys;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kr.applepi.drinkingcare.Fragments.*;
import kr.applepi.drinkingcare.R;
import kr.applepi.drinkingcare.TestViewPager;

import me.relex.circleindicator.CircleIndicator;

public class TutorialActivity extends AppCompatActivity {
    CircleIndicator indicator;
    TestViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        indicator = (CircleIndicator)findViewById(R.id.indicator);
        viewPager = (TestViewPager)findViewById(R.id.pager_add);
        viewPager.setAdapter(new TutorialAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(6);
        viewPager.setCurrentItem(0);
        indicator.setViewPager(viewPager);
    }
    public class TutorialAdapter extends FragmentPagerAdapter {

        public TutorialAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = null;
            switch (position) {
                case 0:
                    f = new TutorialFragment1();
                    break;
                case 1:
                    f = new TutorialFragment2();
                    break;
                case 2:
                    f = new TutorialFragment3();
                    break;
                case 3:
                    f = new TutorialFragment4();
                    break;
                case 4:
                    f = new TutorialFragment5();
                    break;
                case 5:
                    f = new TutorialFragment6();
                    break;
            }
            return f;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 6;
        }

    }
}
