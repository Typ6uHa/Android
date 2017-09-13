package com.example.aizat.home_work;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Aizat on 09.09.2017.
 */

public class ThirdActivity extends AppCompatActivity{

    // TODO Модификаторы доступа
    final String KEY = "key";

    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        button = (Button) findViewById(R.id.repeat);
        textView = (TextView) findViewById(R.id.result);
        int count = getIntent().getIntExtra(KEY,0);
        textView.setText(String.valueOf(count)+" / "+getResources().getIntArray(R.array.right_answers).length);
        button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // TODO makeIntent
               Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
               startActivity(intent);
           }
       });
    }
}
