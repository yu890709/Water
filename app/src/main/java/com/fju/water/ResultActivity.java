package com.fju.water;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private static final String TAG = ResultActivity.class.getSimpleName();
    private static final float DEFAULT_FEE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent=getIntent();
        float money=intent.getFloatExtra(getString(R.string.extra_money),DEFAULT_FEE);
        Log.d(TAG, money+"");
        TextView moneyText=findViewById(R.id.money);
        int result=(int)(money+0.5);
        moneyText.setText(result+"");
    }
}
