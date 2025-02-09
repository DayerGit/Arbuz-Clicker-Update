package com.dayer.simpleclickerjava;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private long count = 0;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.textView);
    }

    public void onClick(View view) {
        count++;
        String timesText = getTimesText(count); 
        text.setText(String.format("Ты кликнул %d %s", count, timesText));

        if (count % 5 == 0) {
            Toast.makeText(this, "Юбилейное нажатие!", Toast.LENGTH_SHORT).show();
        }
    }

    private String getTimesText(long count) {
        if (count % 10 == 1 && count % 100 != 11) {
            return "раз";
        } else if (count % 10 >= 2 && count % 10 <= 4 && !(count % 100 >= 12 && count % 100 <= 14)) {
            return "раза";
        } else {
            return "раз";
        }
    }
}