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

    // Fragment를 생성하는 정적 메서드
    // Step_page클래스로부터 문자열을 받는다. 받은 문자열을 화면에 띄울것이다.
    public static StepFragment newInstance(String step) {
        // StepFragment 객체 생성.
        StepFragment fragment = new StepFragment();
        // 전달받은 매개변수 값을 저장할 번들 객체 생성
        Bundle bundle = new Bundle();
        // 생성된 번들에 전달받은 문자열 값 전달.
        bundle.putString(ARG_STEP, step);
        // 전달받은 값을 프레그먼트의 인자로 설정(set).
        fragment.setArguments(bundle);
        return fragment;
    }

    // 프레그먼트가 UI를 생성해서 화면에 뿌릴때 호출되는 메서드
    @Nullable
    @Override
    // infalter : XML레이아웃을 View객체로 바꿔줌 / container : 뷰 그룹 / savedInstanecState : 이전에 저장된 상태정보를 저장.
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // fragment_step.xml을 view객체로 변환할 객체 생성.(inflater사용)
        View view = inflater.inflate(R.layout.fragment_step, container, false);
        // fragment_step.xml 레이아웃에 있는 TextView객체 찾아오기.
        TextView stepTextView = view.findViewById(R.id.stepTextView);

        // get메서드로 전달받은 데이터를  TextView의 Text값으로 설정,
        if (getArguments() != null) {
            String step = getArguments().getString(ARG_STEP);
            stepTextView.setText(step);
        }

        // UI에 뿌리기.
        return view;
    }
}