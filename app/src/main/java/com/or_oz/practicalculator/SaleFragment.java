package com.or_oz.practicalculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class SaleFragment extends Fragment {

    View v;
    TextView amountSavedTV;
    EditText originalPriceEdit,salePercentEdit,finalPriceEdit;
    Double originalPrice,salePercent,finalPrice,amountSaved;


    public SaleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_sale, container, false);
        instantiateButtons();

        NavActivity main = (NavActivity)getActivity();
        main.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSale();
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    private void calculateSale() {
        originalPrice=salePercent=finalPrice=amountSaved=0.0;

        if(originalPriceEdit.getText().toString().equals(""))
            originalPriceEdit.setText("50");
        if(salePercentEdit.getText().toString().equals(""))
            salePercentEdit.setText("20");
        originalPrice = Double.parseDouble(originalPriceEdit.getText().toString());
        salePercent = Double.parseDouble(salePercentEdit.getText().toString())*.01;

        finalPrice = originalPrice*(1-salePercent);
        amountSaved = originalPrice - finalPrice;

        finalPriceEdit.setText(String.format("%.2f", finalPrice));
        amountSavedTV.setText(String.format("%.2f", amountSaved));
    }

    private void instantiateButtons() {
        originalPriceEdit = (EditText)v.findViewById(R.id.originalPriceEdit);
        salePercentEdit = (EditText)v.findViewById(R.id.salePercentEdit);
        finalPriceEdit = (EditText)v.findViewById(R.id.finalPriceEdit);
        amountSavedTV = (TextView)v.findViewById(R.id.amountSavedTV);
    }

}
