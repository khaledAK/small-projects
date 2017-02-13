package com.example.exqueueme.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convert(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        Double amountOfDollars = Double.parseDouble(editText.getText().toString());

        Double poundAmount = amountOfDollars * 0.65;
        Toast.makeText(getApplicationContext() , "&" + poundAmount.toString() , Toast.LENGTH_LONG).show();
    }
}
