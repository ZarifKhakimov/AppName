package com.example.appname;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Listener extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initViews();
        initViews2();

        TextInputEditText textInputLayout;
        textInputLayout = (TextInputEditText) findViewById(R.id.textInputLayout);

        textInputLayout.setOnKeyListener(new TextInputLayout.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                return false;
            }
        });

    }

    void initViews() {
        TextInputEditText input1 = (TextInputEditText) findViewById(R.id.input1);
        TextInputEditText input2 = (TextInputEditText) findViewById(R.id.input2);
        Button go1 = (Button) findViewById(R.id.Go1);

        go1.setEnabled(false); //Default turning off my button


        TextWatcher watcher = new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                go1.setEnabled(!TextUtils.isEmpty(input1.getHint())
                        && !TextUtils.isEmpty(input2.getHint()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }

     void initViews2(){
         Spannable string = null;
         int start = -1;
        String pTagString = "I would #like to do #something similar to the #Twitter app";

        for (int i = 0; i < pTagString.length(); i++) {
            if (pTagString.charAt(i) == '#') {
                start = i;
            } else if (pTagString.charAt(i) == ' ' || pTagString.charAt(i) == '\n' || (i == pTagString.length() - 1 && start != -1)) {
                if (start != -1) {
                    if (i == pTagString.length() - 1) {
                        i++; // case for if hash is last word and there is no
                        // space after word
                    }

                    final String tag = pTagString.substring(start, i);
                    string.setSpan(new ClickableSpan() {

                        @Override
                        public void onClick(View widget) {
                            Log.d("Hash", String.format("Clicked %s!", tag));
                        }

                        @Override
                        public void updateDrawState(TextPaint ds) {
                            // link color
                            ds.setColor(Color.parseColor("#0AE81E"));
                            ds.setUnderlineText(false);
                        }
                    }, start, i, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    start = -1;
                }
            }
        }

        TextView pTextView = findViewById(R.id.pTextView);
        pTextView.setMovementMethod(LinkMovementMethod.getInstance());
        pTextView.setText(string);
    }
}