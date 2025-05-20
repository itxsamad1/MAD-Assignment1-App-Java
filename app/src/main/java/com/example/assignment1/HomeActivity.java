package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private CardView cardGame, cardAdmission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        // Initialize views
        tvWelcome = findViewById(R.id.tvWelcome);
        cardGame = findViewById(R.id.cardGame);
        cardAdmission = findViewById(R.id.cardAdmission);
        
        // Get user's name from preferences
        String userName = getSharedPreferences("user_prefs", 0)
                .getString("user_name", "");
        
        // If no name is saved, use email as fallback
        if (userName.isEmpty()) {
            String userEmail = getSharedPreferences("user_prefs", 0)
                    .getString("user_email", "User");
            
            // Extract the username from the email (everything before @)
            userName = userEmail.contains("@") 
                    ? userEmail.substring(0, userEmail.indexOf('@')) 
                    : userEmail;
        }
        
        // Capitalize the first letter of the username
        if (!userName.isEmpty()) {
            userName = userName.substring(0, 1).toUpperCase() + userName.substring(1);
        }
        
        tvWelcome.setText(String.format("Welcome back, %s!", userName));
        
        // Set click listeners
        cardGame.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, GameActivity.class);
            startActivity(intent);
        });
        
        cardAdmission.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AdmissionActivity.class);
            startActivity(intent);
        });
    }
} 