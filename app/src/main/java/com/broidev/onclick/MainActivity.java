package com.broidev.onclick;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int targetNumber; // stan gry: wylosowana liczba
    private EditText etGuess;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etGuess = findViewById(R.id.editTextNumberSigned);
        tvResult = findViewById(R.id.result);

        // Losowanie pierwszej liczby przy starcie aplikacji
        startNewGame();

        // 1. Sposób podpięcia zdarzenia: LAMBDA dla przycisku "Zgadnij"
        findViewById(R.id.buttonGuess).setOnClickListener(v -> checkGuess());

        // 2. Sposób podpięcia zdarzenia: WSPÓLNY LISTENER z getId()
        View.OnClickListener actionListener = v -> {
            int id = v.getId();
            if (id == R.id.buttonRestart) {
                startNewGame();
                Toast.makeText(this, R.string.toast_new_game, Toast.LENGTH_SHORT).show();
            } else if (id == R.id.buttonGiveUp) {
                tvResult.setText(getString(R.string.result_give_up, targetNumber));
            }
        };

        findViewById(R.id.buttonRestart).setOnClickListener(actionListener);
        findViewById(R.id.buttonGiveUp).setOnClickListener(actionListener);
    }

    private void startNewGame() {
        // Losuje liczbę od 1 do 100
        targetNumber = new Random().nextInt(100) + 1;
        tvResult.setText("");
        etGuess.setText("");
    }

    private void checkGuess() {
        String input = etGuess.getText().toString();
        
        // Zabezpieczenie przed pustym polem
        if (input.isEmpty()) {
            Toast.makeText(this, R.string.toast_empty_input, Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int guess = Integer.parseInt(input);
            
            // Logika gry
            if (guess > targetNumber) {
                tvResult.setText(R.string.result_too_high);
            } else if (guess < targetNumber) {
                tvResult.setText(R.string.result_too_low);
            } else {
                tvResult.setText(getString(R.string.result_win, targetNumber));
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.toast_invalid_input, Toast.LENGTH_SHORT).show();
        }
    }
}