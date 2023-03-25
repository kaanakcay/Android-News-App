package edu.sabanciuniv.operatingsystemsexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    MyViewPagerAdapter myViewPagerAdapter;

    String name1 = "";
    String name2 = "";
    String name3 = "";


    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List<Catagories> data = (List<Catagories>)msg.obj;

            for (int i=0; i<data.size(); i++){
                if(data.get(i).getId() == 1){
                    name1 = data.get(i).getName();
                }
                if(data.get(i).getId() == 2){
                    name2 = data.get(i).getName();
                }
                if(data.get(i).getId() == 3){
                    name3 = data.get(i).getName();
                }
            }
            return true;
        }
    });




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.home_fill0_wght400_grad0_opsz48);
        }


        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.view_pager);
        myViewPagerAdapter = new MyViewPagerAdapter(this);
        viewPager2.setAdapter(myViewPagerAdapter);


        NewsRepo repo = new NewsRepo();
        repo.getAllCatagory(((NewsApp) getApplication()).srv, dataHandler);


        TabLayout.Tab tab1 = tabLayout.getTabAt(0);
        tab1.setText("ECONOMICS");
        TabLayout.Tab tab2 = tabLayout.getTabAt(1);
        tab2.setText("SPORTS");
        TabLayout.Tab tab3 = tabLayout.getTabAt(2);
        tab3.setText("POLITICS");


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });



    }


}