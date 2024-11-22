package com.example.mobileproject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class RecipePagerAdapter extends FragmentStateAdapter {
    private final List<String> steps;

    public RecipePagerAdapter(@NonNull FragmentActivity fragmentActivity, List<String> steps) {
        super(fragmentActivity);
        this.steps = steps;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // 각 단계 내용을 전달하여 stepFragment 생성
        return StepFragment.newInstance(steps.get(position));
    }

    // 총 레시피 단계 수 반환
    @Override
    public int getItemCount() {
        return steps.size();
    }
}
