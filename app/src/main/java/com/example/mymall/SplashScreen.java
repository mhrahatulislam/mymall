package com.example.mymall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);

        // Sleep for 3 seconds (3000 milliseconds)
        SystemClock.sleep(50);

        // Create an intent to navigate to the LoginActivity
        Intent Register = new Intent(SplashScreen.this, RegisterActivity.class);

        // Start the LoginActivity
        startActivity(Register);

        // Close the SplashScreen to prevent it from going back when the LoginActivity is shown
        finish();

    }
}