package com.example.ex082;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent si;
    Switch seqType;
    EditText firstNum, sequenceD;
    String firstValue, stringD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNum = (EditText)findViewById(R.id.firstNum);
        sequenceD = (EditText)findViewById(R.id.seqD);
        seqType = (Switch)findViewById(R.id.seqType);

        si = new Intent(this, ResultActivity.class);
    }

    public void goSolver(View view) {
        firstValue = firstNum.getText().toString();
        stringD = sequenceD.getText().toString();

        if (!firstValue.equals("") && !stringD.equals("")
                && !firstValue.equals(".") && !stringD.equals(".")
                && !firstValue.equals("-") && !stringD.equals("-")
                && !firstValue.equals("-.") && !stringD.equals("-."))
        {

            si.putExtra("seqType", seqType.isChecked());
            si.putExtra("firstNum", Float.parseFloat(firstValue));
            si.putExtra("seqD", Float.parseFloat(stringD));

            startActivity(si);
        }
        else {
            Toast.makeText(this, "Error! Fill all thes values", Toast.LENGTH_SHORT).show();
        }
    }
}