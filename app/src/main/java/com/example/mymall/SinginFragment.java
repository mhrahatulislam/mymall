package com.example.mymall;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SinginFragment extends Fragment {
    public SinginFragment() {
        // Required empty public constructor
    }
    private TextView dontHaveAnAccount;
    private FrameLayout parentFramelayout;

    private EditText editText_email;
    private EditText editText_password;

    private TextView textViewforget_password;

    private Button button_singin;

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
        button_singin= view.findViewById(R.id.btn_singin);
        button_close = view.findViewById(R.id.btn_image);

        firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dontHaveAnAccount.setOnClickListener(v -> SetFragment(new SingupFragment()));
        textViewforget_password.setOnClickListener(v -> {
            SetFragment (new ForgetPassword());
        });

        editText_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkinputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkinputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button_singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkEmailAndPassword();

            }
        });


    }
    private void SetFragment(Fragment fragment){
        FragmentTransaction  fragmentTransaction= requireActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
        fragmentTransaction.replace(parentFramelayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    private void checkinputs(){
        if(!TextUtils.isEmpty(editText_email.getText())){
            if(!TextUtils.isEmpty(editText_password.getText())){
                button_singin.setEnabled(true);
                button_singin.setTextColor(Color.rgb(255, 255, 255));
            }else {
                button_singin.setEnabled(false);
                button_singin.setTextColor(Color.argb(50,255,255,255));
            }

        }else{
            button_singin.setEnabled(false);
            button_singin.setTextColor(Color.argb(50,255,255,255));
        }

    }

    private void checkEmailAndPassword() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"; // Make sure to replace with your actual email pattern

        if (editText_email.getText().toString().matches(emailPattern)) {
            String password = editText_password.getText().toString();

            if (password.length() >= 8) {
                button_singin.setEnabled(false);
                button_singin.setTextColor(Color.rgb(255, 255, 255));
                firebaseAuth.signInWithEmailAndPassword(editText_email.getText().toString(), password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent mainIntent = new Intent(getActivity(),MainActivity.class);
                                    startActivity(mainIntent);
                                    requireActivity().finish();
                                } else {
                                    String error = Objects.requireNonNull(task.getException()).getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                Toast.makeText(getActivity(), "Password should be at least 8 characters", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Invalid email format", Toast.LENGTH_SHORT).show();
        }
    }


}