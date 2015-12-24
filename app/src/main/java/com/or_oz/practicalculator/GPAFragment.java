package com.or_oz.practicalculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class GPAFragment extends Fragment {

    View v;
    Spinner spinner1,spinner2;
    final String[] grades = new String[]{"","A","A-","B+","B","B-","C+","C","C-","D+","D","D-","F"};

    public GPAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_gpa,container, false);
        spinner1 = (Spinner)v.findViewById(R.id.spinner1);
        spinner2 = (Spinner)v.findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,grades);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);


        return v;
    }

}
