package com.codexo.gads2020leaderboard.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.codexo.gads2020leaderboard.fragment.LearningLeadersFragment;
import com.codexo.gads2020leaderboard.fragment.SkillLeadersFragment;

public class MainPagerAdapter extends FragmentStateAdapter {

    public MainPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = LearningLeadersFragment.newInstance();
                break;
            case 1:
                fragment = SkillLeadersFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
