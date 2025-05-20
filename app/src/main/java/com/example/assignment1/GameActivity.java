package com.example.assignment1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private Button btnRestart;
    private TextView tvStatus;
    
    private boolean playerXTurn = true;
    private int roundCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        
        // Initialize TextView
        tvStatus = findViewById(R.id.tvStatus);
        
        // Initialize Game Board Buttons
        buttons[0][0] = findViewById(R.id.btn00);
        buttons[0][1] = findViewById(R.id.btn01);
        buttons[0][2] = findViewById(R.id.btn02);
        buttons[1][0] = findViewById(R.id.btn10);
        buttons[1][1] = findViewById(R.id.btn11);
        buttons[1][2] = findViewById(R.id.btn12);
        buttons[2][0] = findViewById(R.id.btn20);
        buttons[2][1] = findViewById(R.id.btn21);
        buttons[2][2] = findViewById(R.id.btn22);
        
        // Set click listeners for all game buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setOnClickListener(this);
            }
        }
        
        // Initialize Restart Button
        btnRestart = findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(v -> resetGame());
    }

    @Override
    public void onClick(View v) {
        // Ignore click if button is not empty or game is over
        if (!((Button) v).getText().toString().isEmpty()) {
            return;
        }
        
        // Set X or O based on player turn
        if (playerXTurn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }
        
        roundCount++;
        
        // Check if someone won
        if (checkForWin()) {
            if (playerXTurn) {
                playerXWins();
            } else {
                playerOWins();
            }
        } else if (roundCount == 9) {
            // Draw
            draw();
        } else {
            // Switch turns
            playerXTurn = !playerXTurn;
            updateStatusText();
        }
    }
    
    private boolean checkForWin() {
        String[][] field = new String[3][3];
        
        // Get current button texts
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].isEmpty()) {
                return true;
            }
        }
        
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].isEmpty()) {
                return true;
            }
        }
        
        // Check diagonal (top-left to bottom-right)
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].isEmpty()) {
            return true;
        }
        
        // Check diagonal (top-right to bottom-left)
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].isEmpty()) {
            return true;
        }
        
        return false;
    }
    
    private void playerXWins() {
        tvStatus.setText("Player X wins!");
        disableButtons();
    }
    
    private void playerOWins() {
        tvStatus.setText("Player O wins!");
        disableButtons();
    }
    
    private void draw() {
        tvStatus.setText("It's a draw!");
    }
    
    private void updateStatusText() {
        tvStatus.setText(playerXTurn ? "Player X's turn" : "Player O's turn");
    }
    
    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
    
    private void resetGame() {
        // Clear all buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        
        roundCount = 0;
        playerXTurn = true;
        updateStatusText();
    }
} 