package com.example.mobileproject.recommend;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobileproject.R;

public class StepFragment extends Fragment {
    private static final String ARG_STEP = "step";

    public static StepFragment newInstance(String step) {
        StepFragment fragment = new StepFragment();
        Bundle args = new Bundle();
        args.putString(ARG_STEP, step);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step, container, false);
        TextView stepTextView = view.findViewById(R.id.stepTextView);

        if (getArguments() != null) {
            String step = getArguments().getString(ARG_STEP);
            stepTextView.setText(step);
        }

        return view;
    }
}
