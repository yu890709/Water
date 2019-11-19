package com.fju.water;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText next;
    private EditText month;
    private Button calButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        month = findViewById(R.id.month);
        next = findViewById(R.id.next);
        calButton = findViewById(R.id.calButton);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        calButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cul();
            }
        });
    }

    public void cul(){
        String title="每月抄表費用";
        String message="費用:";
        int doo=0;
        float level=0;
        float k=0;
        float money=0;
        DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                month.setText("");
                next.setText("");
            }
        };
        if(TextUtils.isEmpty(month.getText())&&TextUtils.isEmpty(next.getText())){
            title="錯誤";
            message="沒有輸入度數";
        }else if(!TextUtils.isEmpty(month.getText())){
            doo= Integer.parseInt(month.getText().toString());
            if(doo<=10){
                level=7.35f;
                k=0;
            }else if(doo<=30){
                level=9.45f;
                k=21;
            }else if(doo<=50){
                level=11.55f;
                k=84;
            }else{
                level=12.075f;
                k=110.25f;
            }
            money=doo*level-k;
            message="費用:"+money;
        }else{
            title="隔月抄表費用";
            doo= Integer.parseInt(next.getText().toString());
            if(doo<=20){
                level=7.35f;
                k=0;
            }else if(doo<=60){
                level=9.45f;
                k=42;
            }else if(doo<=100){
                level=11.55f;
                k=168;
            }else{
                level=12.075f;
                k=220.5f;
            }
            money=doo*level-k;
            message="費用:"+money;
        }

        Intent intent=new Intent(MainActivity.this,ResultActivity.class);
        intent.putExtra("MONEY", money);
        startActivity(intent);


//        new AlertDialog.Builder(MainActivity.this)
//                .setTitle(title)
//                .setMessage(message)
//                .setPositiveButton("OK",listener)
//                .show();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
