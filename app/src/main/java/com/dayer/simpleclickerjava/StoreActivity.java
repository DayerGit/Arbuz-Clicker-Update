package com.dayer.simpleclickerjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StoreActivity extends AppCompatActivity {

    private TextView balanceText, CPSText;
    long count, add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        EventBus.getDefault().register(this);

        balanceText = findViewById(R.id.balanceView);
        CPSText = findViewById(R.id.CPSView);
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

        balanceText.setText(String.format("%s %d %s", getString(R.string.balance), + count, getBalanceText(count)));
        CPSText.setText(String.format("%s %d", getString(R.string.clickPerSecounds), add));
    }

    @SuppressLint("DefaultLocale")
    public void onBuyClick(View view) {
        if(count >= add*2) {
            count -= add*2;
            add++;

            balanceText.setText(String.format("%s %d %s", getString(R.string.balance), count, getBalanceText(count)));
            CPSText.setText(String.format("%s %d", getString(R.string.clickPerSecounds), add));

            EventBus.getDefault().post(new CountMessage(count, add));
        }

        else {
            Toast.makeText(this, "Недостаточно кликов!", Toast.LENGTH_SHORT).show();
        }
    }

    private String getBalanceText(long clicks) {
        if (clicks % 10 == 1 && clicks % 100 != 11) {
            return "клик";
        } else if (clicks % 10 >= 2 && clicks % 10 <= 4 && !(clicks % 100 >= 12 && clicks % 100 <= 14)) {
            return "клика";
        } else {
            return "кликов";
        }
    }
}