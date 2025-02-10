package com.dayer.simpleclickerjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.Subscribe;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private long count = 0, add = 1;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.textView);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @SuppressLint("DefaultLocale")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(CountMessage event) {
        count = event.getCount();
        add = event.getAdd();

        String countText = getCountText(count);
        text.setText(String.format("Ты кликнул %d %s", count, countText));
    }

    @SuppressLint("DefaultLocale")
    public void onClickArbuz(View view) {
        count+=add;
        String countText = getCountText(count);
        text.setText(String.format("Ты кликнул %d %s", count, countText));

        if (count % 5 == 0) {
            Toast.makeText(this, "Юбилейное нажатие!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickStore(View view){
        Intent intent = new Intent(this, StoreActivity.class);
        startActivity(intent);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            EventBus.getDefault().post(new CountMessage(count, add));
        }, 200);
    }

    private String getCountText(long count) {
        if (count % 10 >= 2 && count % 10 <= 4 && !(count % 100 >= 12 && count % 100 <= 14)) {
            return "раза";
        } else {
            return "раз";
        }
    }
}