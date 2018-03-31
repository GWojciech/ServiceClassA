package com.golabek.wkck.serviceclassa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListeners();
    }

    private void initListeners(){
        Button button = (Button) findViewById(R.id.group1Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Group1Activity.class));
            }
        });
        Button button2 = (Button) findViewById(R.id.group2Button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Group2Activity.class));
            }
        });

        Button button3 = (Button)findViewById(R.id.group3Button);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Group3Activity.class));
            }
        });

        Button otherButton = (Button) findViewById(R.id.otherButton);
        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,OtherActivity.class));
            }
        });
    }
}