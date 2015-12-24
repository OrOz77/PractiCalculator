package com.or_oz.practicalculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GPAFragment extends Fragment {

    View v;
    TextView gpaTV;
    Spinner spinner1,spinner2,spinner3,spinner4,spinner5,spinner6,spinner7;
    EditText credits1,credits2,credits3,credits4,credits5,credits6,credits7;
    Spinner[] spinnersArray;
    EditText[] creditsArray;
    final String[] grades = new String[]{"","A","A-","B+","B","B-","C+","C","C-","D+","D","F"};
    double totalCredits = 0;
    double totalPoints = 0;

    public GPAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_gpa,container, false);
        instatiateButtons();
        spinnersArray = new Spinner[]{spinner1, spinner2, spinner3, spinner4, spinner5, spinner6, spinner7};
        creditsArray = new EditText[]{credits1,credits2,credits3,credits4,credits5,credits6,credits7};

        NavActivity main = (NavActivity)getActivity();
        main.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateGPA();
            }
        });



        return v;
    }



    private void instatiateButtons() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,grades);

        spinner1 = (Spinner)v.findViewById(R.id.spinner1);
        spinner2 = (Spinner)v.findViewById(R.id.spinner2);
        spinner3 = (Spinner)v.findViewById(R.id.spinner3);
        spinner4 = (Spinner)v.findViewById(R.id.spinner4);
        spinner5 = (Spinner)v.findViewById(R.id.spinner5);
        spinner6 = (Spinner)v.findViewById(R.id.spinner6);
        spinner7 = (Spinner)v.findViewById(R.id.spinner7);


        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);
        spinner5.setAdapter(adapter);
        spinner6.setAdapter(adapter);
        spinner7.setAdapter(adapter);

        credits1 = (EditText)v.findViewById(R.id.credits1);
        credits2 = (EditText)v.findViewById(R.id.credits2);
        credits3 = (EditText)v.findViewById(R.id.credits3);
        credits4 = (EditText)v.findViewById(R.id.credits4);
        credits5 = (EditText)v.findViewById(R.id.credits5);
        credits6 = (EditText)v.findViewById(R.id.credits6);
        credits7 = (EditText)v.findViewById(R.id.credits7);

        gpaTV = (TextView)v.findViewById(R.id.gpaTV);
    }

    private void calculateGPA() {
        Double credit,grade;
        String gradeLetter;
        totalCredits = 0;
        totalPoints = 0;
        for(int i = 0; i<spinnersArray.length;i++){
            if(spinnersArray[i].getSelectedItem().toString().equals("") || creditsArray[i].getText().toString().equals(""))
                break;
            gradeLetter = spinnersArray[i].getSelectedItem().toString();
            credit = Double.parseDouble(creditsArray[i].getText().toString());

            switch (gradeLetter){
                case "":
                    grade = 0.0;
                    break;
                case "A":
                    grade = 4.00;
                    break;
                case "A-":
                    grade = 3.67;
                    break;
                case "B+":
                    grade = 3.33;
                    break;
                case "B":
                    grade = 3.00;
                    break;
                case "B-":
                    grade = 2.67;
                    break;
                case "C+":
                    grade = 2.33;
                    break;
                case "C":
                    grade = 2.00;
                    break;
                case "C-":
                    grade = 1.67;
                    break;
                case "D+":
                    grade = 1.33;
                    break;
                case "D":
                    grade = 1.00;
                    break;
                case "F":
                    grade = 0.00;
                    break;
                default:
                    grade = 0.00;
                    break;
            }
            totalPoints += grade*credit;
            totalCredits += credit;
        }

        Double gpa = totalPoints/totalCredits;
        if(gpa == Double.NaN){
            gpa = 0.00;
        }


        gpaTV.setText(String.format("%.2f", gpa));

    }

}

//TODO fix NaN error when gpa is 0/0