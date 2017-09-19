package com.example.aizat.homework2;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Aizat on 19.09.2017.
 */

public class QuestionFragment extends Fragment implements View.OnClickListener{

    private SharedPreferences sharedPreferences;

    private final String KEY = "key";

    private RadioGroup radioGroup;
    private TextView textView;
    private Button button;

    private String[] getAnswer;

    final String [] quenstions = getResources().getStringArray(R.array.questions);
    final int [] rightAnswers = getResources().getIntArray(R.array.right_answers);
    final int [] selectedAnswers =  new int [rightAnswers.length];

    private int mCountSave;

    public static QuestionFragment newInstance() {
        Bundle args = new Bundle();

        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question,container, false);

        textView = view.findViewById(R.id.question);
        textView.setText(quenstions[mCountSave]); // выводит первый текст
        radioGroup = view.findViewById(R.id.radioGroup);
        for (int i = 0; i < radioGroup.getChildCount();i++){
            getAnswers(i);
            ((RadioButton) radioGroup.getChildAt(i)).setText(getAnswer[mCountSave]);
        }
        mCountSave++;
        selectedAnswers[mCountSave] = rbClick();
        button = view.findViewById(R.id.go_to_result);
        button.setOnClickListener(this);
        return view;
    }

    private void getAnswers(int i ){
        switch (i){
            case 0: getAnswer = getResources().getStringArray(R.array.answerFirst); break;
            case 1: getAnswer = getResources().getStringArray(R.array.answerSecond); break;
            case 2: getAnswer = getResources().getStringArray(R.array.answerThird); break;
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

    public void changeQuestion (){
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container,QuestionFragment.newInstance()) // заменяем текущий объект на новый
                .commit();
    }

    public void goToFinish(int a){
        Intent intent = new Intent(getActivity(),FinishActivity.class);
        intent.putExtra(KEY,a);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (mCountSave-1 < quenstions.length) {
            radioGroup.clearCheck();
            changeQuestion();
        } else {
            int correct = 0;
            for (int z = 0; z < selectedAnswers.length;z++){
                if(rightAnswers[z] == (selectedAnswers[z])){
                    correct++;
                }
            }
            goToFinish(correct);
        }
    }
}
