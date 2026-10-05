package com.broidev.onclick;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;

    private double storedValue = 0;          // pierwsza liczba działania
    private String operation = "";           // "+", "-", "*", "/" albo pusty napis
    private boolean startNewNumber = true;   // czy kolejna cyfra zaczyna nową liczbę

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

        tvDisplay = findViewById(R.id.resultText);

        View.OnClickListener symbolListener =
                v -> appendSymbol(((Button) v).getText().toString());

        int[] symbolIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
                R.id.buttonComma
        };

        for (int id : symbolIds) {
            findViewById(id).setOnClickListener(symbolListener);
        }

        // operacje - jeden listener, rozpoznanie po identyfikatorze
        View.OnClickListener operationListener = v -> {
            int id = v.getId();
            if (id == R.id.buttonPlus) {
                setOperation("+");
            } else if (id == R.id.buttonMinus) {
                setOperation("-");
            } else if (id == R.id.buttonMultiply) {
                setOperation("*");
            } else {
                setOperation("/");
            }
        };
        findViewById(R.id.buttonPlus).setOnClickListener(operationListener);
        findViewById(R.id.buttonMinus).setOnClickListener(operationListener);
        findViewById(R.id.buttonMultiply).setOnClickListener(operationListener);
        findViewById(R.id.buttonDivide).setOnClickListener(operationListener);

        // pojedyncze przyciski - własne lambdy
        findViewById(R.id.buttonEquals).setOnClickListener(v -> calculate());
        findViewById(R.id.buttonClear).setOnClickListener(v -> clearAll());
    }

    private void appendSymbol(String symbol) {
        String current = tvDisplay.getText().toString();

        if (startNewNumber) {
            tvDisplay.setText(symbol.equals(",") ? "0," : symbol);
            startNewNumber = false;
            return;
        }
        if (symbol.equals(",") && current.contains(",")) {
            return;                        // drugi przecinek w tej samej liczbie
        }
        tvDisplay.setText(current + symbol);
    }

    private void setOperation(String newOperation) {
        storedValue = readDisplay();
        operation = newOperation;
        startNewNumber = true;
    }

    private void calculate() {
        if (operation.isEmpty()) {
            return;                        // nie wybrano jeszcze działania
        }

        double second = readDisplay();

        if (operation.equals("/") && second == 0) {
            Toast.makeText(this, R.string.error_divide_zero, Toast.LENGTH_SHORT).show();
            clearAll();
            return;
        }

        double result;
        if (operation.equals("+")) {
            result = storedValue + second;
        } else if (operation.equals("-")) {
            result = storedValue - second;
        } else if (operation.equals("*")) {
            result = storedValue * second;
        } else {
            result = storedValue / second;
        }

        showResult(result);
        operation = "";
        startNewNumber = true;
    }

    private double readDisplay() {
        String text = tvDisplay.getText().toString().replace(',', '.');
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void showResult(double result) {
        if (result == Math.rint(result) && Math.abs(result) < 1e15) {
            tvDisplay.setText(String.valueOf((long) result));
        } else {
            String text = String.format(Locale.US, "%.2f", result);
            tvDisplay.setText(text.replace('.', ','));   // zawsze przecinek
        }
    }

    private void clearAll() {
        tvDisplay.setText(R.string.display_start);
        storedValue = 0;
        operation = "";
        startNewNumber = true;
    }
}