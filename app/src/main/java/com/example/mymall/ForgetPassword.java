package com.example.mymall;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
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

import java.util.Objects;


public class ForgetPassword extends Fragment {
    private FrameLayout parentFramelayout;

    private FirebaseAuth firebaseAuth;

   private TextView textView_goback;
   private EditText editText_emailfildForget;
   private Button button_forget;


   private ViewGroup emailIconcontainer;

   private TextView textView_icon;
   private ImageView imageView_emailIcon;
   private ProgressBar progressBarforgetber;




    public ForgetPassword() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.forget_password, container, false);

        textView_goback =view.findViewById(R.id.textViewGoBackbtn);
        editText_emailfildForget= view.findViewById(R.id.editTextTextEmailAddress);
        button_forget= view.findViewById(R.id.btn_forgot);
        parentFramelayout= requireActivity().findViewById(R.id.registration_framelayout);

        emailIconcontainer=view.findViewById(R.id.linearLayout);
        textView_icon= view.findViewById(R.id.icontext);
        imageView_emailIcon=view.findViewById(R.id.emailIcon);
        progressBarforgetber=view.findViewById(R.id.progressBarforget);



        firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailIconcontainer.setVisibility(View.INVISIBLE);

        checkinupt();
        editText_emailfildForget.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkinupt();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button_forget.setOnClickListener(v -> {

            //use hide linear layout by the Transition Manager.
            TransitionManager.beginDelayedTransition(emailIconcontainer);
            imageView_emailIcon.setVisibility(View.VISIBLE);
            progressBarforgetber.setVisibility(View.VISIBLE);

            button_forget.setEnabled(false);
            button_forget.setTextColor(Color.argb(50,255,255,255));

            firebaseAuth.sendPasswordResetEmail(editText_emailfildForget.getText().toString())
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            //Toast.makeText(getActivity(), "Email Send Successfully", Toast.LENGTH_SHORT).show();
                            emailIconcontainer.setVisibility(View.VISIBLE);
                            editText_emailfildForget.setText("");

                        }else {
                            String error= Objects.requireNonNull(task.getException()).getMessage();
                            progressBarforgetber.setVisibility(View.GONE);

                            textView_icon.setText(error);
                            TransitionManager.beginDelayedTransition(emailIconcontainer);
                            emailIconcontainer.setVisibility(View.VISIBLE);
                            textView_icon.setVisibility(View.VISIBLE);
                            editText_emailfildForget.setText("");
                            //Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                        }
                        button_forget.setEnabled(true);
                        button_forget.setTextColor(Color.rgb(255,255,255));
                    });

        });

        textView_goback.setOnClickListener(v -> SetFragment(new SinginFragment()));

    }

    public void checkinupt(){
        if (TextUtils.isEmpty(editText_emailfildForget.getText())){
            button_forget.setEnabled(false);
            button_forget.setTextColor(Color.rgb(0,0,255));
        } else {
            button_forget.setEnabled(true);
            button_forget.setTextColor(Color.rgb(255,255,255));
        }
    }
    private void SetFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slidefrom_left, R.anim.slideout_from_right);
        fragmentTransaction.replace(parentFramelayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}