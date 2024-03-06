package com.example.mymall;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SingupFragment extends Fragment {

    public SingupFragment() {
        // Required empty public constructor
    }

    private TextView alreadyHaveAnAccount;
    private FrameLayout parentFramelayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.singup_fragment, container, false);
        alreadyHaveAnAccount = view.findViewById(R.id.btn_singup_alradyhaveanaccount);
        parentFramelayout = requireActivity().findViewById(R.id.registration_framelayout);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        alreadyHaveAnAccount.setOnClickListener(v -> SetFragment(new SinginFragment()));

    }

    private void SetFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction= requireActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slidefrom_left,R.anim.slideout_from_right);
        fragmentTransaction.replace(parentFramelayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}