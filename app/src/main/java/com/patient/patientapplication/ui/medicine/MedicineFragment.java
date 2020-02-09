package com.patient.patientapplication.ui.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.patient.patientapplication.R;
import com.patient.patientapplication.ui.logs.AddLog;
import com.patient.patientapplication.ui.logs.LogsFragment;

public class MedicineFragment extends Fragment {

    private MedicineViewModel medicineViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        medicineViewModel =
                ViewModelProviders.of(this).get(MedicineViewModel.class);
        View root = inflater.inflate(R.layout.fragment_medicine, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        medicineViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        FloatingActionButton addMedicine = root.findViewById(R.id.addMedicine);
        addMedicine.bringToFront();
        addMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MedicineFragment.this.getActivity(), AddMedicine.class));
            }
        });
        return root;
    }
}