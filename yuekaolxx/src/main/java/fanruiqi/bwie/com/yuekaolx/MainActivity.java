package fanruiqi.bwie.com.yuekaolx;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import fanruiqi.bwie.com.yuekaolx.fragment.Afrag;
import fanruiqi.bwie.com.yuekaolx.fragment.Bfrag;
import fanruiqi.bwie.com.yuekaolx.fragment.Cfrag;
import fanruiqi.bwie.com.yuekaolx.fragment.Dfrag;
import fanruiqi.bwie.com.yuekaolx.fragment.Efrag;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager viewPager = findViewById(R.id.main_vp);
        RadioGroup radioGroup = findViewById(R.id.main_group);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment=null;

                switch (position){
                    case 0:
                        fragment = new Afrag();
                        break;
                    case 1:
                        fragment = new Bfrag();
                        break;
                    case 2:
                        fragment = new Cfrag();
                        break;
                    case 3:
                        fragment = new Dfrag();
                        break;
                    case 4:
                        fragment = new Efrag();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.r1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.r2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.r3:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.r4:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.r5:
                        viewPager.setCurrentItem(4);
                        break;
                }
            }
        });
    }
}
