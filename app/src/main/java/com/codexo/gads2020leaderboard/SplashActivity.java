package com.codexo.gads2020leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                }, 2000);
    }

    @Override
    public void onBackPressed() { // Disable back navigation
    }
}