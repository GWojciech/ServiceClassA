package com.golabek.wkck.serviceclassa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        Button button = (Button) findViewById(R.id.searchButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText =(EditText) findViewById(R.id.searchText);
                Editable text = editText.getText();
                Toast.makeText(getApplicationContext(), text.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }


}
