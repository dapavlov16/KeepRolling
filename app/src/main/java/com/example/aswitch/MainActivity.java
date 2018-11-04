package com.example.aswitch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;
//import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    TextView binary1, binary2, binary3, decimalTotal, decimalTarget;
    Switch switchOne, switchTwo, switchThree;
    Button prizeButton, roll;
    int target = 0, total;

    static int rand(){
        Random r = new Random();
        return r.nextInt(8 - 1) + 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binary1 = findViewById(R.id.textView4);
        binary2 = findViewById(R.id.textView5);
        binary3 = findViewById(R.id.textView6);
        decimalTotal = findViewById(R.id.textView3);
        decimalTarget = findViewById(R.id.textView2);
        switchOne = findViewById(R.id.switch2);
        switchTwo = findViewById(R.id.switch3);
        switchThree = findViewById(R.id.switch4);
        prizeButton = findViewById(R.id.button1);
        roll = findViewById(R.id.button3);
        //target = ThreadLocalRandom.current().nextInt(10, 80) / 10;
        //target = r.nextInt(8 - 1) + 1;
        //target = rand();
        //decimalTarget.setText(Integer.toString(target));
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                prizeButton.setVisibility(View.GONE);
                decimalTarget.setTextColor(getColor(R.color.orange));
                switch(buttonView.getId()){
                    case R.id.switch2:
                        binary1.setText(switchOne.isChecked()?R.string.one : R.string.zero);
                        break;
                    case R.id.switch3:
                        binary2.setText(switchTwo.isChecked()?R.string.one : R.string.zero);
                        break;
                    case R.id.switch4:
                        binary3.setText(switchThree.isChecked()?R.string.one : R.string.zero);
                        break;
                }
                total = Integer.valueOf(binary1.getText().toString()) * 4 + Integer.valueOf(binary2.getText().toString()) * 2 + Integer.valueOf(binary3.getText().toString());
                decimalTotal.setText(Integer.toString(total));
                if(total == target){
                    prizeButton.setVisibility(View.VISIBLE);
                    decimalTarget.setTextColor(getColor(R.color.green));
                }
            }
        };
        prizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityPrize.class);
                startActivity(i);
            }
        });
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                target = rand();
                decimalTarget.setText(Integer.toString(target));
                prizeButton.setVisibility(View.GONE);
                switchOne.setChecked(false);
                switchTwo.setChecked(false);
                switchThree.setChecked(false);
                decimalTarget.setTextColor(getColor(R.color.orange));
            }
        });
        switchOne.setOnCheckedChangeListener(listener);
        switchTwo.setOnCheckedChangeListener(listener);
        switchThree.setOnCheckedChangeListener(listener);
    }

    @Override
    protected void onResume(){
        super.onResume();
        roll.performClick();
    }
}
