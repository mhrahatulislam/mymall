package com.example.mymall;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


public class SinginFragment extends Fragment {

    public SinginFragment() {
        // Required empty public constructor
    }

    private TextView dontHaveAnAccount;
    private FrameLayout parentFramelayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.singin_fragment, container, false);
        dontHaveAnAccount=view.findViewById(R.id.btn_singup_donthaveAnAccount);
        parentFramelayout = getActivity().findViewById(R.id.registration_framelayout);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetFragment(new SingupFragment());
            }
        });
    }

    private void SetFragment(Fragment fragment){

        FragmentTransaction  fragmentTransaction= getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
        fragmentTransaction.replace(parentFramelayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}