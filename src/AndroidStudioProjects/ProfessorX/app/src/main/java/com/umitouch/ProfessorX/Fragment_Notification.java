package com.umitouch.ProfessorX;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Calendar;

public class Fragment_Notification extends Fragment
{
    View view;
    private MainActivity MA;

    private Button toCalendar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) //
    {
        Log.d("TestMain:" , "notify   01  ");
        view = inflater.inflate(R.layout.fragment_notification, container, false);
        Log.d("TestMain:" , "notify   02  ");
        toCalendar = (Button)view.findViewById(R.id.toCalender);    // 認親
        Log.d("TestMain:" , "notify   03  ");

        toCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                CalendarView( view);
        }
    });
        // Inflate the layout for this fragment


        init();

        return view;
    }

    public void CalendarView(View view)
    {
        Calendar calendarEvent = Calendar.getInstance();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setType("vnd.android.cursor.item/event");
        startActivity(i);
    }

    private void init()
    {
        Log.d("TestMain:" , "notify   init  ");
    }

    public void setSource(MainActivity MA)
    {
        this.MA = MA;
    }
}
