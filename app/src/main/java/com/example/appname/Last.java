package com.example.appname;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Last extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        setTags(findViewById(R.id.et_simple), "I would #like to do #something similar to the #Twitter app");
    }

        private void setTags(TextView pTextView, String pTagString) {
            SpannableString string = new SpannableString(pTagString);

            int start = -1;
            for (int i = 0; i < pTagString.length(); i++) {
                if (pTagString.charAt(i) == '#') {
                    start = i;
                } else if (pTagString.charAt(i) == ' ' || pTagString.charAt(i) == '\n' || (i == pTagString.length() - 1 && start != -1)) {
                    if (start != -1) {
                        if (i == pTagString.length() - 1) {
                            i++;
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

            pTextView.setMovementMethod(LinkMovementMethod.getInstance());
            pTextView.setText(string);
        }
    }