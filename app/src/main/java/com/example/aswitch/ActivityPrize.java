package com.example.aswitch;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityPrize extends AppCompatActivity {

    Button upgradeButton, back;
    ConstraintLayout l;
    boolean anime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prize);
        l = findViewById(R.id.constraintLayout);
        upgradeButton = findViewById(R.id.buttonU);
        back = findViewById(R.id.button2);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.buttonU:
                        if(anime) {
                            l.setBackgroundResource(R.drawable.prize);
                            upgradeButton.setText(R.string.go_back);
                            back.setVisibility(View.VISIBLE);
                            anime = false;
                        }
                        else {
                            l.setBackgroundResource(R.drawable.prize2);
                            upgradeButton.setText(R.string.upgrade);
                            anime = true;
                        }
                        break;
                    case R.id.button2:
                        finish();
                        break;
                }
            }
        };
        upgradeButton.setOnClickListener(onClickListener);
        back.setOnClickListener(onClickListener);
    }
}
