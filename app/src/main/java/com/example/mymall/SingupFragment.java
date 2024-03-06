package com.example.mymall;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;

public class SingupFragment extends Fragment {

    public SingupFragment() {
        // Required empty public constructor
    }

    private TextView alreadyHaveAnAccount;
    private FrameLayout parentFramelayout;


    private EditText email;
    private EditText fullName;
    private EditText password;
    private EditText confirmPassword;
    private ImageView crossBtn;
    private Button singUpBtn;
    private ProgressBar progressBtn;

    private FirebaseAuth firebaseAuth;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.singup_fragment, container, false);


        parentFramelayout = requireActivity().findViewById(R.id.registration_framelayout);

        alreadyHaveAnAccount = view.findViewById(R.id.btn_singup_alradyhaveanaccount);

        email = view.findViewById(R.id.email_filed_siup);
        fullName = view.findViewById(R.id.namefiled_siup);
        password = view.findViewById(R.id.editTextTextPassword);
        confirmPassword = view.findViewById(R.id.editTextTextPassword2);
        crossBtn = view.findViewById(R.id.btn_cls_sinup);
        singUpBtn = view.findViewById(R.id.btn_singup_siup);
        progressBtn = view.findViewById(R.id.progressBar);
        firebaseAuth = FirebaseAuth.getInstance();
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        alreadyHaveAnAccount.setOnClickListener(v -> SetFragment(new SinginFragment()));


        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        singUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo send data to firebase
                checkEmailandPassword();
            }
        });

    }
    private void SetFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slidefrom_left, R.anim.slideout_from_right);
        fragmentTransaction.replace(parentFramelayout.getId(), fragment);
        fragmentTransaction.commit();
    }
    private void checkInputs() {
        if (!TextUtils.isEmpty(email.getText())) {
            if (!TextUtils.isEmpty(fullName.getText())) {
                if (!TextUtils.isEmpty(password.getText())) {
                    if (!TextUtils.isEmpty(confirmPassword.getText())) {
                        singUpBtn.setEnabled(true);
                        singUpBtn.setTextColor(Color.rgb(255,255,255));
                    } else {
                        // Handle case when confirmPassword is empty
                        singUpBtn.setEnabled(false);
                        singUpBtn.setTextColor(Color.rgb(255,255,255));
                    }
                } else {
                    // Handle case when password is empty
                    singUpBtn.setEnabled(false);
                    singUpBtn.setTextColor(Color.rgb(255,205,155));
                }
            } else {
                // Handle case when fullName is empty
                singUpBtn.setEnabled(false);
                singUpBtn.setTextColor(Color.rgb(255,255,255));
            }
        } else {
            // Handle case when email is empty
            singUpBtn.setEnabled(false);
            singUpBtn.setTextColor(Color.rgb(255,255,255));
        }
    }
    private void checkEmailandPassword(){


    }

}