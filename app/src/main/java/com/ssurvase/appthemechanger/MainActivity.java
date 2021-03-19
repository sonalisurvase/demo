package com.ssurvase.appthemechanger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView pattern = findViewById(R.id.pattern);
        String temp = "";
        for (int i =1; i<=5; i++){
//            for (int m=4; m>0; m--) {
                for (int k = 4; k > 0; k--) {
                    temp = temp + " ";

                }
                for (int j = 0; j <= i; j++) {
                    temp = temp+" 1" + (i)+"\n";
                }

//            }
        }

        pattern.setText(temp);

    }
}