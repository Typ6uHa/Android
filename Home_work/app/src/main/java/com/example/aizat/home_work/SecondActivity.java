package com.example.aizat.home_work;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity{


    // TODO Модификаторы доступа
    RadioGroup radioGroup;
    TextView textView;
    Button button;

    final String KEY = "key";

    String[] getAnswer;
    
    int mCount = 1;
    int mCountSave = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final String [] quenstions = getResources().getStringArray(R.array.questions);
        final int [] rightAnswers = getResources().getIntArray(R.array.right_answers);
        final int [] selectedAnswers =  new int [rightAnswers.length];

        textView = (TextView) findViewById(R.id.question);
        textView.setText(quenstions[0]); // выводит первый текст
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        getAnswers(0); // выводит первые ответы

        for (int i = 0; i < radioGroup.getChildCount();i++){
            ((RadioButton) radioGroup.getChildAt(i)).setText(getAnswer[i]);
        }

        button = (Button) findViewById(R.id.go_to_result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCount < quenstions.length) {
                    textView.setText(quenstions[mCount]);
                    getAnswers(mCount);
                    for (int i = 0; i < radioGroup.getChildCount(); i++) {
                        ((RadioButton) radioGroup.getChildAt(i)).setText(getAnswer[i]);
                    }
                    selectedAnswers[mCountSave] = rbClick();
                    mCount++;
                    mCountSave++;
                    radioGroup.clearCheck();
                } else {
                    selectedAnswers[mCountSave] = rbClick();
                    int concurrences = 0;
                    for (int z = 0; z < selectedAnswers.length;z++){
                        if(rightAnswers[z] == (selectedAnswers[z])){
                            concurrences++;
                        }
                    }
                    // TODO makeIntent
                    Intent intent1 = new Intent (SecondActivity.this, ThirdActivity.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent1.putExtra(KEY,concurrences);
                    startActivity(intent1);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    private void getAnswers(int i){
        switch (i){
            // TODO сделать через getIdentifier
            case 0: getAnswer = getResources().getStringArray(R.array.answers1); break;
            case 1: getAnswer = getResources().getStringArray(R.array.answers2); break;
            case 2: getAnswer = getResources().getStringArray(R.array.answers3); break;
            case 3: getAnswer = getResources().getStringArray(R.array.answers4); break;
            case 4: getAnswer = getResources().getStringArray(R.array.answers5); break;
            case 5: getAnswer = getResources().getStringArray(R.array.answers6); break;
            case 6: getAnswer = getResources().getStringArray(R.array.answers7); break;
            case 7: getAnswer = getResources().getStringArray(R.array.answers8); break;
            case 8: getAnswer = getResources().getStringArray(R.array.answers9); break;
            case 9: getAnswer = getResources().getStringArray(R.array.answers10); break;
            case 10: getAnswer = getResources().getStringArray(R.array.answers11); break;
        }
    }

    private int rbClick(){
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        int a;
        switch (radioButtonId) {
            case R.id.first_radio_button : a = 1; break;
            case R.id.second_radio_button: a = 2; break;
            case R.id.third_radio_button : a = 3; break;
            default: a = 0;
        }
        return a;
    }
}
