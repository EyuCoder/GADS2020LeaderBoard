package com.codexo.gads2020leaderboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.codexo.gads2020leaderboard.adapter.MainPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new MainPagerAdapter(this));
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch (position) {
                            case 0:
                                tab.setText(R.string.learning_leaders);
                                break;
                            case 1:
                                tab.setText(R.string.skill_iq_leaders);
                        }
                    }
                }
        ).attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.menu_submit)
                .getActionView()
                .findViewById(R.id.btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, SubmitActivity.class));
                    }
                });

        return true;
    }
}