package com.patient.patientapplication.ui.medicine;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.patient.patientapplication.R;
import com.patient.patientapplication.util.Utility;

import java.util.ArrayList;
import java.util.Arrays;

public class AddMedicine extends AppCompatActivity {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);
        Spinner spinner = findViewById(R.id.timeSpinner);
        ArrayList<String> list = new ArrayList<>();
        for(int i = 1; i <= 12; i++) {
            list.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        final EditText time12 = findViewById(R.id.time12);
        final EditText time11 = findViewById(R.id.time11);
        final EditText time10 = findViewById(R.id.time10);
        final EditText time9 = findViewById(R.id.time9);
        final EditText time8 = findViewById(R.id.time8);
        final EditText time7 = findViewById(R.id.time7);
        final EditText time6 = findViewById(R.id.time6);
        final EditText time5 = findViewById(R.id.time5);
        final EditText time4 = findViewById(R.id.time4);
        final EditText time3 = findViewById(R.id.time3);
        final EditText time2 = findViewById(R.id.time2);
        final EditText time1 = findViewById(R.id.time1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                time12.setVisibility(View.INVISIBLE);
                time11.setVisibility(View.INVISIBLE);
                time10.setVisibility(View.INVISIBLE);
                time9.setVisibility(View.INVISIBLE);
                time8.setVisibility(View.INVISIBLE);
                time7.setVisibility(View.INVISIBLE);
                time6.setVisibility(View.INVISIBLE);
                time5.setVisibility(View.INVISIBLE);
                time4.setVisibility(View.INVISIBLE);
                time3.setVisibility(View.INVISIBLE);
                time2.setVisibility(View.INVISIBLE);
                time1.setVisibility(View.INVISIBLE);

                String timeNum = (String) parent.getItemAtPosition(position);
                switch (timeNum) {
                    case "12":
                        time12.setVisibility(View.VISIBLE);
                    case "11":
                        time11.setVisibility(View.VISIBLE);
                    case "10":
                        time10.setVisibility(View.VISIBLE);
                    case "9":
                        time9.setVisibility(View.VISIBLE);
                    case "8":
                        time8.setVisibility(View.VISIBLE);
                    case "7":
                        time7.setVisibility(View.VISIBLE);
                    case "6":
                        time6.setVisibility(View.VISIBLE);
                    case "5":
                        time5.setVisibility(View.VISIBLE);
                    case "4":
                        time4.setVisibility(View.VISIBLE);
                    case "3":
                        time3.setVisibility(View.VISIBLE);
                    case "2":
                        time2.setVisibility(View.VISIBLE);
                    case "1":
                        time1.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
