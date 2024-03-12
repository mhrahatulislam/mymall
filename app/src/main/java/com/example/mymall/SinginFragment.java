package com.example.mymall;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;

public class SinginFragment extends Fragment {
    public SinginFragment() {
        // Required empty public constructor
    }
    private TextView dontHaveAnAccount;
    private FrameLayout parentFramelayout;

    private EditText editText_email;
    private EditText editText_password;

    private TextView textViewforget_password;

    private Button button_singup;

    private ImageView button_close;

    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.singin_fragment, container, false);
        dontHaveAnAccount=view.findViewById(R.id.btn_singup_donthaveAnAccount);
        parentFramelayout = requireActivity().findViewById(R.id.registration_framelayout);

        editText_email= view.findViewById(R.id.email_filed);
        editText_password=view.findViewById(R.id.password_filed);
        textViewforget_password=view.findViewById(R.id.forgat_password);
        button_singup= view.findViewById(R.id.btn_singin);
        button_close = view.findViewById(R.id.btn_image);

        firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dontHaveAnAccount.setOnClickListener(v -> SetFragment(new SingupFragment()));
    }
    private void SetFragment(Fragment fragment){
        FragmentTransaction  fragmentTransaction= requireActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
        fragmentTransaction.replace(parentFramelayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}