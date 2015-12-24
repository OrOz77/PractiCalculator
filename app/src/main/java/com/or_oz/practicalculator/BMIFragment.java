package com.or_oz.practicalculator;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;


public class BMIFragment extends Fragment {
    Boolean isImperial;
    View v;

    EditText weightUnitEdit, majorHeightEdit, minorHeightEdit;
    Switch switchUnitType;
    TextView weightUnit, majorHeight, minorHeight, bmi;

    Double majorH,minorH,weight, totalH, bmiDouble;

    public BMIFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_bmi, container, false);
        instantiateAll();

        isImperial = true;


        NavActivity main = (NavActivity)getActivity();
        switchUnitType.setChecked(true);
        switchUnitType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isImperial = !isImperial;
                updateText();
            }
        });
        main.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bmiDouble = calculateBMI();
                if(bmiDouble<=18.5)
                    bmi.setTextColor(Color.rgb(61,90,254));
                if(bmiDouble>18.5 && bmiDouble<25.0)
                    bmi.setTextColor(Color.rgb(0,200,83));
                if(bmiDouble>=25.0 && bmiDouble<30)
                    bmi.setTextColor(Color.rgb(245,127,23));
                if(bmiDouble>=30)
                    bmi.setTextColor(Color.RED);
                bmi.setText(String.format("%.1f", bmiDouble));
            }
        });


        return v;
    }

    private void updateText() {
        if(!isImperial){
            majorHeight.setText(getText(R.string.meters));
            minorHeight.setText(getText(R.string.centimeters));
            weightUnit.setText(getText(R.string.kilograms));
            switchUnitType.setText(getText(R.string.metric));

        }else{
            majorHeight.setText(getText(R.string.feet));
            minorHeight.setText(getText(R.string.inches));
            weightUnit.setText(getText(R.string.pounds));
            switchUnitType.setText(getText(R.string.imperial));
        }

    }

    private void instantiateAll() {

        weightUnitEdit = (EditText)v.findViewById(R.id.weightUnitEdit);
        majorHeightEdit = (EditText)v.findViewById(R.id.majorHeightUnitEdit);
        minorHeightEdit = (EditText)v.findViewById(R.id.minorHeightUnitEdit);

        majorHeight = (TextView)v.findViewById(R.id.majorHeightUnit);
        minorHeight = (TextView)v.findViewById(R.id.minorHeightUnit);
        weightUnit = (TextView)v.findViewById(R.id.weightUnit);
        bmi = (TextView)v.findViewById(R.id.bmi);

        switchUnitType = (Switch)v.findViewById(R.id.switchUnitType);


    }

    private double calculateBMI() {

        if(majorHeightEdit.getText().toString().equals(""))
            majorHeightEdit.setText("1");
        if(minorHeightEdit.getText().toString().equals(""))
            minorHeightEdit.setText("10");
        if(weightUnitEdit.getText().toString().equals(""))
            weightUnitEdit.setText("80");



        majorH = Double.parseDouble(majorHeightEdit.getText().toString());
        minorH = Double.parseDouble(minorHeightEdit.getText().toString());
        weight = Double.parseDouble(weightUnitEdit.getText().toString());

        if(isImperial){
            totalH = ((majorH*12)+(minorH))*.0254;
            weight *= .45;
        }else{
            totalH = majorH+(minorH*.01);
        }
        totalH *= totalH;
        return weight/totalH;


    }

}
