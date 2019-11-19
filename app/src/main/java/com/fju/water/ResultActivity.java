package com.fju.water;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent=getIntent();
        float money=intent.getFloatExtra("MONEY",0);
        Log.d("ResultActivity", money+"");
        TextView moneyText=findViewById(R.id.money);
        int result=(int)(money+0.5);
        moneyText.setText(result+"");
    }
}
