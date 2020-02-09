package com.patient.patientapplication.ui.logs;

import com.patient.patientapplication.util.Utility;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.patient.patientapplication.MainActivity;
import com.patient.patientapplication.R;

public class AddLog extends AppCompatActivity {

    private boolean newCategory = true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_log);
        final EditText newCatText = findViewById(R.id.new_category_text);
        Spinner spinner = findViewById(R.id.add_category);
        SharedPreferences catPrefs =
                getApplicationContext().getSharedPreferences("Categories", 0);
        int index = Utility.findLastCategoryIndex(catPrefs);
        String[] array = {"New category..."};
        ArrayList<String> list = new ArrayList<>(Arrays.asList(array));
        for(int i = 0; i < index; i++) {
            String cat = catPrefs.getString("category" + i, "NULL");
            list.add(cat);
        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if((parent.getItemAtPosition(position)).equals("New category...")) {
                    newCatText.setVisibility(View.VISIBLE);
                    newCategory = true;
                } else {
                    newCatText.setVisibility(View.INVISIBLE);
                    newCategory = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }



    public void onAddButtonClick(View v) {
        String category;
        Spinner spinner = findViewById(R.id.add_category);
        EditText newCatText = findViewById(R.id.new_category_text);
        if(newCategory) {
            category = newCatText.getText().toString();
            SharedPreferences catPrefs =
                    getApplicationContext().getSharedPreferences("Categories", 0);
            SharedPreferences.Editor editor = catPrefs.edit();
            int index = Utility.findLastCategoryIndex(catPrefs);
            editor.putString("category" + index,category);
            editor.apply();
        } else {
            category = spinner.getSelectedItem().toString();
        }

        //get Time
        String currentTime = Calendar.getInstance().getTime().toString();

        //Get logs
        SharedPreferences logs =
                getApplicationContext().getSharedPreferences("Logs", 0);
        SharedPreferences.Editor editor = logs.edit();
        TextInputEditText logText = findViewById(R.id.log_text);
        int index = Utility.findLastLogIndex(logs);
        editor.putString("log" + index, currentTime + "$log$" + logText.getText().toString() +
                "$cat$" + category);
        editor.apply();
        startActivity(new Intent(AddLog.this, MainActivity.class));
    }

    public void onBackButtonClick (View v) {
        startActivity(new Intent(AddLog.this, MainActivity.class));
    }
}
