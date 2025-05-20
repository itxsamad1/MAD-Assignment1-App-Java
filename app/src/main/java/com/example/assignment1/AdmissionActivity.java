package com.example.assignment1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AdmissionActivity extends AppCompatActivity {

    private Button btnApply;
    private TextView tvAdmissionInfo;
    private TextInputLayout tilFullName, tilEmail, tilPhone, tilHighSchool, tilGpa, tilPersonalStatement;
    private TextInputEditText etFullName, etEmail, etPhone, etHighSchool, etGpa, etPersonalStatement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission);
        
        // Initialize views
        btnApply = findViewById(R.id.btnApply);
        tvAdmissionInfo = findViewById(R.id.tvAdmissionInfo);
        
        // Initialize form fields
        tilFullName = findViewById(R.id.tilFullName);
        tilEmail = findViewById(R.id.tilEmail);
        tilPhone = findViewById(R.id.tilPhone);
        tilHighSchool = findViewById(R.id.tilHighSchool);
        tilGpa = findViewById(R.id.tilGpa);
        tilPersonalStatement = findViewById(R.id.tilPersonalStatement);
        
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etHighSchool = findViewById(R.id.etHighSchool);
        etGpa = findViewById(R.id.etGpa);
        etPersonalStatement = findViewById(R.id.etPersonalStatement);
        
        // Pre-fill with user's information if available
        String savedName = getSharedPreferences("user_prefs", 0).getString("user_name", "");
        String savedEmail = getSharedPreferences("user_prefs", 0).getString("user_email", "");
        
        if (!savedName.isEmpty()) {
            etFullName.setText(savedName);
        }
        
        if (!savedEmail.isEmpty()) {
            etEmail.setText(savedEmail);
        }
        
        // Set admission information text
        String admissionInfo = "Admission Requirements:\n\n" +
                "1. High School Diploma or equivalent\n" +
                "2. Minimum GPA of 3.0\n" +
                "3. SAT score of 1200+ or ACT score of 25+\n" +
                "4. Two letters of recommendation\n" +
                "5. Personal statement\n\n" +
                "Application Deadline: August 15, 2025\n\n" +
                "Tuition: $15,000 per year\n" +
                "Financial Aid: Available for qualifying students";
        
        tvAdmissionInfo.setText(admissionInfo);
        
        // Set click listener for apply button
        btnApply.setOnClickListener(v -> submitApplication());
    }
    
    private void submitApplication() {
        // Reset errors
        tilFullName.setError(null);
        tilEmail.setError(null);
        tilPhone.setError(null);
        tilHighSchool.setError(null);
        tilGpa.setError(null);
        tilPersonalStatement.setError(null);
        
        // Get values
        String fullName = etFullName.getText() != null ? etFullName.getText().toString() : "";
        String email = etEmail.getText() != null ? etEmail.getText().toString() : "";
        String phone = etPhone.getText() != null ? etPhone.getText().toString() : "";
        String highSchool = etHighSchool.getText() != null ? etHighSchool.getText().toString() : "";
        String gpa = etGpa.getText() != null ? etGpa.getText().toString() : "";
        String personalStatement = etPersonalStatement.getText() != null ? etPersonalStatement.getText().toString() : "";
        
        boolean cancel = false;
        View focusView = null;
        
        // Validate fields
        if (fullName.isEmpty()) {
            tilFullName.setError("Please enter your full name");
            focusView = etFullName;
            cancel = true;
        }
        
        if (email.isEmpty()) {
            tilEmail.setError("Please enter your email");
            focusView = etEmail;
            cancel = true;
        } else if (!isValidEmail(email)) {
            tilEmail.setError("Please enter a valid email");
            focusView = etEmail;
            cancel = true;
        }
        
        if (phone.isEmpty()) {
            tilPhone.setError("Please enter your phone number");
            focusView = etPhone;
            cancel = true;
        }
        
        if (highSchool.isEmpty()) {
            tilHighSchool.setError("Please enter your high school name");
            focusView = etHighSchool;
            cancel = true;
        }
        
        if (gpa.isEmpty()) {
            tilGpa.setError("Please enter your GPA");
            focusView = etGpa;
            cancel = true;
        } else {
            try {
                float gpaValue = Float.parseFloat(gpa);
                if (gpaValue < 0 || gpaValue > 4.0) {
                    tilGpa.setError("GPA must be between 0.0 and 4.0");
                    focusView = etGpa;
                    cancel = true;
                }
            } catch (NumberFormatException e) {
                tilGpa.setError("Please enter a valid GPA number");
                focusView = etGpa;
                cancel = true;
            }
        }
        
        if (personalStatement.isEmpty()) {
            tilPersonalStatement.setError("Please enter your personal statement");
            focusView = etPersonalStatement;
            cancel = true;
        }
        
        if (cancel) {
            // There was an error; focus the first form field with an error
            if (focusView != null) {
                focusView.requestFocus();
            }
        } else {
            // All fields are valid, submit application
            Toast.makeText(this, "Application submitted successfully!", Toast.LENGTH_SHORT).show();
            finish(); // Go back to the home screen
        }
    }
    
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
} 