package com.patient.patientapplication.ui.medicine;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.patient.patientapplication.R;
import android.app.AlarmManager;
import com.patient.patientapplication.util.Utility;

import java.util.Calendar;

public class MedicineFragment extends Fragment {

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        MedicineViewModel medicineViewModel =
                ViewModelProviders.of(this).get(MedicineViewModel.class);
        View root = inflater.inflate(R.layout.fragment_medicine, container, false);
        FloatingActionButton addMedicine = root.findViewById(R.id.addMedicine);
        addMedicine.bringToFront();
        addMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MedicineFragment.this.getActivity(), AddMedicine.class));
            }
        });

/*
        TextView medicineText = root.findViewById(R.id.medicine);
        medicineText.setMovementMethod(new ScrollingMovementMethod());
    */
        SharedPreferences medicines =
                this.getActivity().getSharedPreferences("Medicines", 0);
        int index = Utility.findLastMedicineIndex(medicines);
        String sliderText, name;
        LinearLayout ll = root.findViewById(R.id.linLayoutMed);
        for(int i = 0; i < index; i++) {
            String value = medicines.getString("medicine"+i, "NULL");
            name = value.substring(0, value.indexOf("$dose$"));
            sliderText = "Dosage:\t" + value.substring(value.indexOf("$dose$")+6, value.indexOf("$days$"));
            sliderText +="\nDays To Take:\t" + value.substring(value.indexOf("$days$")+6, value.indexOf("$times$"));
            sliderText += "\nTime(s) To Take:\t" + value.substring(value.indexOf("$times$")+7, value.indexOf("$end$"));
            Switch toggle = new Switch(this.getContext());
            toggle.setText(sliderText);
            toggle.setChecked(true);
            TextView medicineName = new TextView(this.getContext());
            medicineName.setText("Name:\t" + name);
            ll.addView(medicineName);
            ll.addView(toggle);
            Log.d("HERE", value);
            //Push Notify
            String times = value.substring(value.indexOf("$times$")+7, value.indexOf("$end$"));
            while (!times.equals("")){
                String dates = value.substring(value.indexOf("$days$")+6, value.indexOf("$times$"));
                String time;
                if(times.contains(", ")) {
                    time = times.substring(0, times.indexOf(", "));
                    times = times.substring(times.indexOf(", ") + 2);
                } else {
                    time = times;
                    times = "";
                }

                while(!dates.equals("")) {
                    String date;
                    if(dates.contains(", ")) {
                        date = dates.substring(0, dates.indexOf(", ")).trim();
                        dates = dates.substring(dates.indexOf(", ") + 2);
                    } else {
                        date = dates;
                        dates = "";
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.substring(0, time.indexOf(":"))));
                    calendar.set(Calendar.MINUTE, Integer.parseInt(time.substring(time.indexOf(":") + 1)));
                    switch(date) {
                        case "Monday":
                            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                            break;
                        case "Tuesday":
                            calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                            break;
                        case "Wednesday":
                            calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                            break;
                        case "Thursday":
                            calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                            break;
                        case "Friday":
                            calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                            break;
                        case "Saturday":
                            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                            break;
                        case "Sunday":
                            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                            break;
                        default:
                            break;
                    }
                    //alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                    //Intent intent = new Intent(context, AlarmReceiver.class);
                    //alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

                    //alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    //        AlarmManager.INTERVAL_DAY, alarmIntent);
                }
            }
        }

        return root;
    }
}