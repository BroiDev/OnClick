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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int targetNumber; // stan gry: wylosowana liczba
    private EditText etGuess;
    private TextView result;

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
        result = findViewById(R.id.result);

        // Losowanie pierwszej liczby przy starcie aplikacji
        startNewGame();

        // 1. Sposób podpięcia zdarzenia: AKTYWNOŚĆ JAKO LISTENER dla przycisku "Zgadnij"
        findViewById(R.id.buttonGuess).setOnClickListener(this);

        // 3. Sposób podpięcia zdarzenia: LAMBDA dla przycisku "Poddaj się"
        findViewById(R.id.buttonGiveUp).setOnClickListener(v -> result.setText(getString(R.string.result_give_up, targetNumber)));
    }

    private void startNewGame() {
        // Losuje liczbę od 1 do 100
        targetNumber = new Random().nextInt(100) + 1;
        result.setText("");
        etGuess.setText("");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.buttonGuess) {
            checkGuess();
        }
    }

    // 2. Sposób podpięcia zdarzenia: android:onClick w pliku XML dla przycisku "Od nowa"
    public void onRestartClick(View view) {
        startNewGame();
        Toast.makeText(this, R.string.toast_new_game, Toast.LENGTH_SHORT).show();
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
                result.setText(R.string.result_too_high);
            } else if (guess < targetNumber) {
                result.setText(R.string.result_too_low);
            } else {
                result.setText(getString(R.string.result_win, targetNumber));
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.toast_invalid_input, Toast.LENGTH_SHORT).show();
        }
    }
}