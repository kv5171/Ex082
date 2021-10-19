package com.example.ex082;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Intent gi;
    TextView firstNum, seqD, itemPlace, sumN;
    ListView seqList;
    float firstNumber, numD, seqSum;
    boolean seqType;
    String[] seqArr;
    float[] sumValuesArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        firstNum = (TextView)findViewById(R.id.firstNum);
        seqD = (TextView)findViewById(R.id.seqD);
        itemPlace = (TextView)findViewById(R.id.itemPlace);
        sumN = (TextView)findViewById(R.id.sumN);

        seqList = (ListView)findViewById(R.id.seqList);

        seqSum = 0;
        seqArr = new String[20];
        sumValuesArr = new float[20];

        gi = getIntent();
        seqType = gi.getBooleanExtra("seqType", false);
        firstNumber = gi.getFloatExtra("firstNum", 0);
        numD = gi.getFloatExtra("seqD", 0);

        firstNum.setText("X1 = " + fixValue(firstNumber));
        seqD.setText("D = " + fixValue(numD));

        calcArrValues();

        seqList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        seqList.setOnItemClickListener(this);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, seqArr);
        seqList.setAdapter(adp);
    }

    public void returnMain(View view) {
        finish();
    }

    public void calcArrValues(){
        for (int i = 0; i < 20; i++){
            if (seqType){
                seqArr[i] = fixValue(firstNumber + i * numD);
            }
            else {
                seqArr[i] = fixValue((float) (firstNumber * Math.pow(numD, i)));
            }
            seqSum += Float.parseFloat(seqArr[i]);
            sumValuesArr[i] = seqSum;
        }
    }

    public String fixValue(float value){
        if ((float)((int)value) == value) {
            return String.valueOf((int)value);
        }
        return String.valueOf(value);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        itemPlace.setText("n = " + (position + 1));
        sumN.setText("Sn = " + fixValue(sumValuesArr[position]));
    }
}