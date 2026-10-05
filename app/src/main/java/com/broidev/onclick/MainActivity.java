package com.broidev.onclick;

import android.os.Bundle;
import android.widget.Button;
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
    }

    private void startNewGame() {
        // Losuje liczbę od 1 do 100
        targetNumber = new Random().nextInt(100) + 1;
        tvResult.setText(R.string.empty_result);
        etGuess.setText("");
    }

    private void checkGuess() {
        String input = etGuess.getText().toString();
        
        // Zabezpieczenie przed pustym polem
        if (input.isEmpty()) {
            Toast.makeText(this, "Najpierw wpisz liczbę!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int guess = Integer.parseInt(input);
            
            // Logika gry
            if (guess > targetNumber) {
                tvResult.setText("Za dużo!");
            } else if (guess < targetNumber) {
                tvResult.setText("Za mało!");
            } else {
                tvResult.setText("Gratulacje! To jest " + targetNumber + "!");
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Niepoprawna liczba!", Toast.LENGTH_SHORT).show();
        }
    }
}