package com.suitschool.screeningtest;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends Activity {
    EditText inputNama;
    String mInputNama;
    Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);

        final Intent intent = new Intent(this, MenuActivity.class);

        inputNama = (EditText) findViewById(R.id.home_nama_input);
        inputNama.requestFocus();
        nextButton = (Button) findViewById(R.id.home_next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mInputNama = inputNama.getText().toString();

                intent.putExtra("NAMA", mInputNama);

//                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("NAMA", mInputNama);
//                editor.commit();

                String message;
                if (isPalindrome(mInputNama))
                    message = "isPalindrome";
                else
                    message = "not palindrome";

                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setTitle("Message!")
                        .setMessage(message)
                        .setPositiveButton("oke", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(intent);
                            }
                        });


                AlertDialog dialog = builder.create();
                dialog.create();
                dialog.show();
            }
        });
    }


//    public void onClickNext(View view) {
//        final Intent intent = new Intent(this, MenuActivity.class);
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        mInputNama = inputNama.getText().toString();
//        editor.putString("NAMA", mInputNama);
//        editor.commit();
//
//        String message;
//        if (isPalindrome(mInputNama))
//            message = "isPalindrome";
//        else
//            message = "not palindrome";
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
//        builder.setTitle("Message!")
//                .setMessage(message)
//                .setPositiveButton("oke", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        startActivity(intent);
//                    }
//                });
//
//
//        AlertDialog dialog = builder.create();
//        dialog.create();
//        dialog.show();
//
//        //Toast.makeText(HomeActivity.this, "" + message, Toast.LENGTH_SHORT).show();
//    }

    boolean isPalindrome(String str) {
        boolean isPal = true;
        int left=0;
        int right = str.length()-1;

        if (right>0) {
            while(isPal && left<right) {
                if (str.charAt(left)==' ') left++;
                else if (str.charAt(right)==' ') right--;
                else if (str.charAt(left)!=str.charAt(right)) isPal=false;
                left++; right--;
            }
        }
        return isPal;
    }
}
