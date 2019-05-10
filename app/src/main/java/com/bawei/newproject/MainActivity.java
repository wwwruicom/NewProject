package com.bawei.newproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import adapter.FragAdapter;

public class MainActivity extends AppCompatActivity {

    List<Fragment> fragmentList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager viewpager = findViewById(R.id.viewpager);
        final RadioGroup group = findViewById(R.id.group);

        FragmentOne fragmentOne=new FragmentOne();
        final FragmentTwo fragmentTwo=new FragmentTwo();
        final FragmentTwo fragmentThree=new FragmentTwo();
        fragmentList.add(fragmentOne);
        fragmentList.add(fragmentTwo);
        fragmentList.add(fragmentThree);

        fragmentTwo.setValues("第二");
        fragmentThree.setValues("第三");

        FragAdapter fragAdapter=new FragAdapter(getSupportFragmentManager(),fragmentList);
        viewpager.setAdapter(fragAdapter);
        /**
         * 点击监听
         */
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn1:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.btn2:
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.btn3:
                        viewpager.setCurrentItem(2);
                        break;
                }
            }
        });
        /**
         * 滑动监听
         */
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        group.check(R.id.btn1);
                        break;
                    case 1:
                        group.check(R.id.btn2);
                        break;
                    case 2:
                        group.check(R.id.btn3);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }
}
