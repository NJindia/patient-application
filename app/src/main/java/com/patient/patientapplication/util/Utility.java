package com.patient.patientapplication.util;

import android.content.SharedPreferences;

public class Utility {
    public static int findLastLogIndex(SharedPreferences preferences) {
        int i = 0;
        while(preferences.contains("log" + i)) i++;
        return i;
    }
    public static int findLastCategoryIndex(SharedPreferences preferences) {
        int i = 0;
        while(preferences.contains("category" + i)) i++;
        return i;
    }
}
