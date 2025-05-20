package com.example.assignment1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignInActivity extends AppCompatActivity {

    private TextInputLayout tilEmail, tilPassword;
    private TextInputEditText etEmail, etPassword;
    private Button btnSignIn;
    private TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        
        // Initialize views
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvSignUp = findViewById(R.id.tvSignUp);
        
        // Set click listeners
        btnSignIn.setOnClickListener(v -> attemptLogin());
        tvSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }
    
    private void attemptLogin() {
        // Reset errors
        tilEmail.setError(null);
        tilPassword.setError(null);
        
        String email = etEmail.getText() != null ? etEmail.getText().toString() : "";
        String password = etPassword.getText() != null ? etPassword.getText().toString() : "";
        
        boolean cancel = false;
        View focusView = null;
        
        // Check for a valid password
        if (password.isEmpty()) {
            tilPassword.setError("Password is required");
            focusView = etPassword;
            cancel = true;
        }
        
        // Check for a valid email address
        if (email.isEmpty()) {
            tilEmail.setError("Email is required");
            focusView = etEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            tilEmail.setError("Please enter a valid email");
            focusView = etEmail;
            cancel = true;
        }
        
        if (cancel) {
            // There was an error; focus the first form field with an error
            if (focusView != null) {
                focusView.requestFocus();
            }
        } else {
            // Check credentials against stored values or fallback to demo credentials
            boolean isAuthenticated = false;
            
            // Try to get stored credentials
            SharedPreferences prefs = getSharedPreferences("user_credentials", 0);
            String storedEmail = prefs.getString("user_email", "");
            String storedPassword = prefs.getString("user_password", "");
            
            // Check if credentials match the stored ones
            if (!storedEmail.isEmpty() && email.equals(storedEmail) && password.equals(storedPassword)) {
                isAuthenticated = true;
            }
            
            // Fallback to demo credentials if no registered users or for testing
            if (!isAuthenticated && email.equals("user@example.com") && password.equals("password")) {
                isAuthenticated = true;
            }
            
            if (isAuthenticated) {
                navigateToHome(email);
            } else {
                Toast.makeText(this, "Invalid credentials. Check your email and password.", Toast.LENGTH_LONG).show();
            }
        }
    }
    
    private boolean isEmailValid(String email) {
        return email.contains("@") && email.contains(".");
    }
    
    private void navigateToHome(String email) {
        // Save user email to preferences (for user display in home)
        getSharedPreferences("user_prefs", 0)
                .edit()
                .putString("user_email", email)
                .apply();
        
        // Navigate to Home Activity
        Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
        startActivity(intent);
        finish(); // Close login activity so user can't go back using back button
    }
} 