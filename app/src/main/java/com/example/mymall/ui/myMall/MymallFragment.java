package com.example.mymall.ui.myMall;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymall.R;
import com.example.mymall.databinding.FragmentHomeBinding;
import com.example.mymall.ui.home.HomeViewModel;


public class MymallFragment extends Fragment {

    public MymallFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MymallViewModel mymallViewModel =
                new ViewModelProvider(this).get(MymallViewModel.class);

        com.example.mymall.databinding.FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        mymallViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;

        // Inflate the layout for this fragment
    }
}