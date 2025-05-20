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

public class SignUpActivity extends AppCompatActivity {

    private TextInputLayout tilName, tilEmail, tilPassword, tilConfirmPassword;
    private TextInputEditText etName, etEmail, etPassword, etConfirmPassword;
    private Button btnSignUp;
    private TextView tvSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        
        // Initialize views
        tilName = findViewById(R.id.tilName);
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        tilConfirmPassword = findViewById(R.id.tilConfirmPassword);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvSignIn = findViewById(R.id.tvSignIn);
        
        // Set click listeners
        btnSignUp.setOnClickListener(v -> attemptRegistration());
        tvSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
        });
    }
    
    private void attemptRegistration() {
        // Reset errors
        tilName.setError(null);
        tilEmail.setError(null);
        tilPassword.setError(null);
        tilConfirmPassword.setError(null);
        
        String name = etName.getText() != null ? etName.getText().toString() : "";
        String email = etEmail.getText() != null ? etEmail.getText().toString() : "";
        String password = etPassword.getText() != null ? etPassword.getText().toString() : "";
        String confirmPassword = etConfirmPassword.getText() != null ? etConfirmPassword.getText().toString() : "";
        
        boolean cancel = false;
        View focusView = null;

        // Check for a valid name
        if (name.isEmpty()) {
            tilName.setError("Name is required");
            focusView = etName;
            cancel = true;
        }
        
        // Check for a valid confirm password
        if (confirmPassword.isEmpty()) {
            tilConfirmPassword.setError("Please confirm your password");
            focusView = etConfirmPassword;
            cancel = true;
        } else if (!password.equals(confirmPassword)) {
            tilConfirmPassword.setError("Passwords don't match");
            focusView = etConfirmPassword;
            cancel = true;
        }
        
        // Check for a valid password
        if (password.isEmpty()) {
            tilPassword.setError("Password is required");
            focusView = etPassword;
            cancel = true;
        } else if (password.length() < 6) {
            tilPassword.setError("Password must be at least 6 characters");
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
            // Save user credentials to SharedPreferences
            SharedPreferences prefs = getSharedPreferences("user_credentials", 0);
            prefs.edit()
                    .putString("user_name", name)
                    .putString("user_email", email)
                    .putString("user_password", password)
                    .apply();
            
            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
            
            // Navigate to Home
            navigateToHome(name, email);
        }
    }
    
    private boolean isEmailValid(String email) {
        return email.contains("@") && email.contains(".");
    }
    
    private void navigateToHome(String name, String email) {
        // Save user info to preferences (for user display in home)
        getSharedPreferences("user_prefs", 0)
                .edit()
                .putString("user_name", name)
                .putString("user_email", email)
                .apply();
        
        // Navigate to Home Activity
        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
} 