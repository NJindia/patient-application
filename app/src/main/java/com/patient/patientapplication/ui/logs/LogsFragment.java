package com.patient.patientapplication.ui.logs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.patient.patientapplication.R;
import com.patient.patientapplication.util.Utility;

import java.util.ArrayList;
import java.util.Arrays;

public class LogsFragment extends Fragment {

    private LogsViewModel logsViewModel;
    private String currentCategory = "";

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        logsViewModel =
                ViewModelProviders.of(this).get(LogsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_logs, container, false);
        final TextView logsTextView = root.findViewById(R.id.logs);
        logsTextView.setMovementMethod(new ScrollingMovementMethod());

        FloatingActionButton addLog = root.findViewById(R.id.addLog);
        addLog.bringToFront();
        addLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogsFragment.this.getActivity(), AddLog.class));
            }
        });

        //Create Spinner
        Spinner spinner = root.findViewById(R.id.categories);
        final SharedPreferences catPrefs =
                this.getActivity().getSharedPreferences("Categories", 0);
        int index = Utility.findLastCategoryIndex(catPrefs);
        String[] array = {};
        ArrayList<String> list = new ArrayList<>(Arrays.asList(array));
        for(int i = 0; i < index; i++) {
            String cat = catPrefs.getString("category" + i, "NULL");
            if(i == 0) currentCategory = cat;
            list.add(cat);
        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(root.getContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                logsTextView.setText("");
                currentCategory = parent.getItemAtPosition(position).toString();
                setLogsTextView(root);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        setLogsTextView(root);
        return root;

    }

    private void setLogsTextView(View v) {
        final TextView logsTextView = v.findViewById(R.id.logs);
        SharedPreferences logs = this.getActivity().getSharedPreferences("Logs", 0);
        int index = Utility.findLastLogIndex(logs);
        String logString = "";
        for(int i = 0; i < index; i++) {
            String value = logs.getString("log" + i, "NO LOG");
            int catIndex = value.indexOf("$cat$");
            String category = value.substring(catIndex + 5).trim();
            if(currentCategory.equals(category)) {
                int index2 = value.indexOf("$");
                int index1 = value.lastIndexOf(":", index2);
                value = value.substring(0, index1 + 1) + value.substring(index2, catIndex);
                value = value.replace("$log$", "\t").trim();
                logString = logString + value + "\n";
            }
        }
        logsTextView.setText(logString);
    }


}