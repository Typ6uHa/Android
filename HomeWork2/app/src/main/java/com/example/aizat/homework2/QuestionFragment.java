package com.example.aizat.homework2;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Aizat on 19.09.2017.
 */

public class QuestionFragment extends Fragment {

    private final String KEY = "key";

    private RadioGroup radioGroup;
    private TextView textView;
    private Button button;

    private String[] getAnswer;

    // TODO статические переменные плохо
    // TODO нужно передавать это в следующий фрагмент через агрументы
    private static int mCountSave = 0;
    private static int result = 0;

    public static QuestionFragment newInstance() {

        Bundle args = new Bundle();

        // TODO ключи в константы
        args.putInt("Count",mCountSave);
        args.putInt("Result",result);

        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question,container, false);

        final String [] questions = getResources().getStringArray(R.array.questions);
        final int [] rightAnswers = getResources().getIntArray(R.array.right_answers);

        // TODO ключи в константы
        mCountSave = getArguments().getInt("Count");
        result = getArguments().getInt("Result");

        textView = view.findViewById(R.id.question);
        textView.setText(questions[mCountSave]);
        radioGroup = view.findViewById(R.id.radioGroup);

        for (int i = 0; i < radioGroup.getChildCount();i++){
            getAnswers(i);
            ((RadioButton) radioGroup.getChildAt(i)).setText(getAnswer[mCountSave]);
        }

        button = view.findViewById(R.id.go_to_result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCountSave < questions.length-1) {

                    if (rightAnswers [mCountSave] == rbClick()){
                        result++;
                    }

                    radioGroup.clearCheck();

                    mCountSave++;

                    changeQuestion();
                } else {
                    if (rightAnswers [mCountSave] == rbClick()){
                        result++;
                    }
                    goToFinish(result);
                }
            }
        });
        return view;
    }

    private void getAnswers(int i ){
        // TODO это нужно сделать через getIdentifier
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
                .replace(R.id.fragment_container, QuestionFragment.newInstance()) // заменяем текущий объект на новый
                .commit();
    }

    public void goToFinish(int a){
        // TODO makeIntent
        Intent intent = new Intent(getActivity(),FinishActivity.class);
        intent.putExtra(KEY,a);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
