package com.or_oz.practicalculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;



public class TipFragment extends Fragment {
    View v;
    EditText billTotal, tipPercentage, partySize;
    Button ten,fifteen,twenty;
    TextView subtotal, subtotalPP, tipAmount;
    CheckBox round;
    Double billT,tipP,partyS,subT,subTPP,tipA;

    public TipFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_tip,container,false);
        instantiateButtons();
        NavActivity main = (NavActivity)getActivity();
        main.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
        return v;
    }

    private void calculate() {

        if(tipPercentage.getText().toString().equals("")){
            tipPercentage.setText("15");
        }
        if(partySize.getText().toString().equals("")){
            partySize.setText("1");
        }
        if(billTotal.getText().toString().equals("")){
            billTotal.setText("100");
        }

        billT = Double.parseDouble(billTotal.getText().toString());
        tipP = Double.parseDouble(tipPercentage.getText().toString()) * .01;
        partyS = Double.parseDouble(partySize.getText().toString());


        tipA = billT * tipP;
        subT = billT + tipA;



        if(round.isChecked()){
            subtotal.setText(String.format("%.0f", subT));
            tipA = Double.parseDouble(subtotal.getText().toString()) - billT;
            subTPP = Double.parseDouble(subtotal.getText().toString()) / partyS;
        }
        else{
            subTPP = subT / partyS;
            subtotal.setText(String.format("%.2f", subT));
        }

        tipAmount.setText(String.format("%.2f", tipA));

        subtotalPP.setText(String.format("%.2f", subTPP));
    }

    private void instantiateButtons() {

        billT = tipP = partyS = subT = subTPP = tipA = 0.0;
        subtotal = (TextView)v.findViewById(R.id.subtotal);
        subtotalPP = (TextView)v.findViewById(R.id.subtotalPP);
        tipAmount = (TextView)v.findViewById(R.id.tipAmount);
        billTotal = (EditText)v.findViewById(R.id.billTotal);
        tipPercentage = (EditText)v.findViewById(R.id.tipPercent);
        partySize = (EditText)v.findViewById(R.id.partySize);
        ten = (Button)v.findViewById(R.id.tenPercent);
        fifteen = (Button)v.findViewById(R.id.fifteenPercent);
        twenty = (Button)v.findViewById(R.id.twentyPercent);
        round = (CheckBox)v.findViewById(R.id.checkBox);

        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipPercentage.setText("10");
            }
        });
        fifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipPercentage.setText("15");
            }
        });
        twenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipPercentage.setText("20");
            }
        });
    }

}
