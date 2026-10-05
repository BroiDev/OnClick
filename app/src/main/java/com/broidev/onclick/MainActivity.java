package com.broidev.onclick;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;

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

        Button btn0 = findViewById(R.id.button0);
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        Button btn4 = findViewById(R.id.button4);
        Button btn5 = findViewById(R.id.button5);
        Button btn6 = findViewById(R.id.button6);
        Button btn7 = findViewById(R.id.button7);
        Button btn8 = findViewById(R.id.button8);
        Button btn9 = findViewById(R.id.button9);

        btn0.setOnClickListener(v -> append0());
        btn1.setOnClickListener(v -> append1());
        btn2.setOnClickListener(v -> append2());
        btn3.setOnClickListener(v -> append3());
        btn4.setOnClickListener(v -> append4());
        btn5.setOnClickListener(v -> append5());
        btn6.setOnClickListener(v -> append6());
        btn7.setOnClickListener(v -> append7());
        btn8.setOnClickListener(v -> append8());
        btn9.setOnClickListener(v -> append9());
    }

    private void append0() {
        tvDisplay.setText(tvDisplay.getText().toString() + "0");
    }

    private void append1() {
        tvDisplay.setText(tvDisplay.getText().toString() + "1");
    }

    private void append2() {
        tvDisplay.setText(tvDisplay.getText().toString() + "2");
    }

    private void append3() {
        tvDisplay.setText(tvDisplay.getText().toString() + "3");
    }

    private void append4() {
        tvDisplay.setText(tvDisplay.getText().toString() + "4");
    }

    private void append5() {
        tvDisplay.setText(tvDisplay.getText().toString() + "5");
    }

    private void append6() {
        tvDisplay.setText(tvDisplay.getText().toString() + "6");
    }

    private void append7() {
        tvDisplay.setText(tvDisplay.getText().toString() + "7");
    }

    private void append8() {
        tvDisplay.setText(tvDisplay.getText().toString() + "8");
    }

    private void append9() {
        tvDisplay.setText(tvDisplay.getText().toString() + "9");
    }
}