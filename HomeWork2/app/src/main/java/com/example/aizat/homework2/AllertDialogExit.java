package com.example.aizat.homework2;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Aizat on 19.09.2017.
 */

public class AllertDialogExit extends DialogFragment implements DialogInterface.OnClickListener {

    private Uri uri = Uri.parse("http://dropmefiles.com/8tZ7f");
    private int a;
    private int b;
    public AllertDialogExit(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_next_step, null,false);
        return new AlertDialog
                .Builder(getActivity())
                .setView(v)
                .setPositiveButton(R.string.dialog_positive,this)
                .setNegativeButton(R.string.dialog_negative,this)
                .create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case DialogInterface.BUTTON_POSITIVE:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Я ответил на "+ a+
                        " вопросов из "+ b + ". Попробуй повтори."
                        + "\n"+ uri);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
        }
    }
    public void onBackPressed(){
    }
}
