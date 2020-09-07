package com.codexo.gads2020leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    }
                }, 2000);
    }

    @Override
    public void onBackPressed() { // Disable back navigation
    }
}