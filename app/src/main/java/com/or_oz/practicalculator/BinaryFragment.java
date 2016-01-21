package com.or_oz.practicalculator;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class BinaryFragment extends Fragment {
    View v;
    Spinner fromSpinner, toSpinner;
    ImageButton reverseButton;
    EditText inputEditText;
    TextView resultTV;
    final String[] conversionTypes = new String[]{"Binary", "Decimal"};
    int id,input,output;
    String outputString;

    public BinaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_binary,container,false);
        instantiateButtons();

        NavActivity main = (NavActivity)getActivity();
        main.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });

        reverseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = fromSpinner.getSelectedItemPosition();
                fromSpinner.setSelection(toSpinner.getSelectedItemPosition());
                toSpinner.setSelection(id);
                calculate();
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    private void calculate() {
        if(!inputEditText.getText().toString().equalsIgnoreCase("") && inputEditText.getText().length() <=10){
            input = Integer.parseInt(inputEditText.getText().toString());
        }else{
            Snackbar.make(v, "Input must be less than 11 digits", Snackbar.LENGTH_LONG).show();
            input=0;
        }
        switch(fromSpinner.getSelectedItemPosition()){
            case 0:
                //fromSpinner is Binary
                fromBinary();
                break;
            case 1:
                //fromSpinner is Decimal
                fromDecimal();
                break;
        }
        resultTV.setText(String.format("%d",output));
    }


    private void fromBinary() {
        switch (toSpinner.getSelectedItemPosition()){
            case 0:
                //Binary -> Binary
                //toSpinner is Binary
                output = input;
                break;
            case 1:
                //Binary -> Decimal
                //toSpinner is Decimal
                outputString = inputEditText.getText().toString();
                if(outputString.matches("[01]+")){
                    if(outputString.length()<=10){
                        output = Integer.parseInt(outputString,2);
                    } else {
                        output = 0;
                        Snackbar.make(v, "Binary input must be less than 11 bits", Snackbar.LENGTH_LONG).show();
                    }

                }else{
                    output = 0;
                    Snackbar.make(v, "Input must be binary", Snackbar.LENGTH_LONG).show();
                }

                break;
        }
    }

    private void fromDecimal() {
        switch (toSpinner.getSelectedItemPosition()){
            case 0:
                //Decimal -> Binary
                //toSpinner is Binary
                if(input<1024){
                    outputString = Integer.toBinaryString(input);
                    output = Integer.parseInt(outputString);
                } else {
                    output = 0;
                    Snackbar.make(v, "Decimal input must be less than 1024", Snackbar.LENGTH_LONG).show();
                }

                break;
            case 1:
                //Decimal -> Decimal
                //toSpinner is Decimal
                output = input;
                break;
        }
    }

    private void instantiateButtons() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,conversionTypes);
        fromSpinner = (Spinner)v.findViewById(R.id.fromSpinner);
        toSpinner = (Spinner)v.findViewById(R.id.toSpinner);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);
        toSpinner.setSelection(1);

        reverseButton = (ImageButton)v.findViewById(R.id.reverseButton);

        inputEditText = (EditText)v.findViewById(R.id.inputEditText);
        if(!inputEditText.getText().toString().equalsIgnoreCase("")){
            input = Integer.parseInt(inputEditText.getText().toString());
        }else input=0;

        resultTV = (TextView)v.findViewById(R.id.outputTV);



    }

}
