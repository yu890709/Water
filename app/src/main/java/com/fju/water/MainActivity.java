package com.fju.water;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText input;
    private Button calButton;
    boolean isNext=false;
    private Switch sw;
    private Spinner cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        input = findViewById(R.id.inputText);
        calButton = findViewById(R.id.calButton);
        sw = findViewById(R.id.sw);

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isNext=isChecked;
                TextView type=findViewById(R.id.type);
                sw.setText(isNext?getString(R.string.every_other_month):getString(R.string.monthly));
                type.setText(isNext?getString(R.string.every_other_month):getString(R.string.monthly));

            }
        });
        calButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cul();
            }
        });
        cities = findViewById(R.id.spinner);
        cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, getResources().getStringArray(R.array.cities)[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    public void cul(){
        String title=getString(R.string.monthly);
        String message=getString(R.string.fee);
        int doo=0;
        float level=0;
        float k=0;
        float money=0;
        DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                input.setText("");
            }
        };
        if(TextUtils.isEmpty(input.getText())){
            title=getString(R.string.error);
            message=getString(R.string.no_input);
        }else if(!isNext){
            doo= Integer.parseInt(input.getText().toString());
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
            message=getString(R.string.fee)+money;
        }else{
            title=getString(R.string.every_other_month);
            doo= Integer.parseInt(input.getText().toString());
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
            message=getString(R.string.fee)+money;
        }

        Intent intent=new Intent(MainActivity.this,ResultActivity.class);
        intent.putExtra(getString(R.string.extra_money), money);
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
