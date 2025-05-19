package com.example.project_samsung;

import android.view.View;

public class Activity {
    public static android.app.Activity lastActivity;

    public static android.app.Activity getActivity() {
        return lastActivity;
    }

    public static void setActivity(View.OnClickListener activity) {
        lastActivity = (android.app.Activity) activity;
    }
}