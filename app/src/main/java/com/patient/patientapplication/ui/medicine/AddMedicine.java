package com.patient.patientapplication.ui.medicine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.patient.patientapplication.MainActivity;
import com.patient.patientapplication.R;
import com.patient.patientapplication.ui.logs.AddLog;
import com.patient.patientapplication.util.Utility;

import java.util.ArrayList;
import java.util.Arrays;

public class AddMedicine extends AppCompatActivity {
    private String timeNum;

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


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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

                timeNum = (String) parent.getItemAtPosition(position);
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

    public void onAddMedicineButtonClick(View v) {
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
        EditText medicationName = findViewById(R.id.medication_text);
        String name = medicationName.getText().toString();
        EditText medicationDosage = findViewById(R.id.dosage_text);
        String dosage = medicationDosage.getText().toString();
        String daysToTake = "";
        CheckBox monday = findViewById(R.id.monday);
        CheckBox tuesday = findViewById(R.id.tuesday);
        CheckBox wednesday = findViewById(R.id.wednesday);
        CheckBox thursday = findViewById(R.id.thursday);
        CheckBox friday = findViewById(R.id.friday);
        CheckBox saturday = findViewById(R.id.saturday);
        CheckBox sunday = findViewById(R.id.sunday);
        if(monday.isChecked()) {
            daysToTake = daysToTake + "Monday, ";
        }
        if(tuesday.isChecked()) {
            daysToTake = daysToTake + "Tuesday, ";
        }
        if(wednesday.isChecked()) {
            daysToTake = daysToTake + "Wednesday, ";
        }
        if(thursday.isChecked()) {
            daysToTake = daysToTake + "Thursday, ";
        }
        if(friday.isChecked()) {
            daysToTake = daysToTake + "Friday, ";
        }
        if(saturday.isChecked()) {
            daysToTake = daysToTake + "Saturday, ";
        }
        if(sunday.isChecked()) {
            daysToTake = daysToTake + "Sunday, ";
        }
        if(daysToTake.contains(",")) {
            daysToTake = daysToTake.substring(0, daysToTake.lastIndexOf(","));
        }
        String times = "";
        String time;
        switch (timeNum) {
            case "12":
                time = time12.getText().toString();
                times = ", " + time + times;
            case "11":
                time = time11.getText().toString();
                times = ", " + time + times;
            case "10":
                time = time10.getText().toString();
                times = ", " + time + times;
            case "9":
                time = time9.getText().toString();
                times = ", " + time + times;
            case "8":
                time = time8.getText().toString();
                times = ", " + time + times;
            case "7":
                time = time7.getText().toString();
                times = ", " + time + times;
            case "6":
                time = time6.getText().toString();
                times = ", " + time + times;
            case "5":
                time = time5.getText().toString();
                times = ", " + time + times;
            case "4":
                time = time4.getText().toString();
                times = ", " + time + times;
            case "3":
                time = time3.getText().toString();
                times = ", " + time + times;
            case "2":
                time = time2.getText().toString();
                times = ", " + time + times;
            case "1":
                time = time1.getText().toString();
                times = ", " + time + times;
                break;
            default:
                break;
        }
        times = times.substring(2).trim();
        String value = name + "$dose$" + dosage + "$days$" + daysToTake + "$times$" + times + "$end$";
        SharedPreferences medicines =
                getApplicationContext().getSharedPreferences("Medicines", 0);
        SharedPreferences.Editor editor = medicines.edit();
        int index = Utility.findLastMedicineIndex(medicines);
        editor.putString("medicine"+index, value);
        editor.apply();
        startActivity(new Intent(AddMedicine.this, MainActivity.class));
    }
    public void onBackButtonClick (View v) {
        startActivity(new Intent(AddMedicine.this, MainActivity.class));
    }
}
