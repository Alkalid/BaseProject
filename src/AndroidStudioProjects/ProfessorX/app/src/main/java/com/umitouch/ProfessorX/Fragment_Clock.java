package com.umitouch.ProfessorX;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;

public class Fragment_Clock extends Fragment
{
    View view;
    private MainActivity MA;

    private Button comfirm_button;
    private Button TimeSetting_button;
    private TimePicker timePicker;
    private DatePicker datePicker;

    private int Year;
    private int Month;
    private int Day;

    private int Hour;
    private int Minute;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) //認親//
    {
        Log.d("TestMain:" , "notify   01  ");
        view = inflater.inflate(R.layout.fragment_clock, container, false);

        timePicker = (TimePicker) view.findViewById(R.id.timePicker);
        datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        ////
        TimeSetting_button = (Button)view.findViewById(R.id.comfirm_button);
        TimeSetting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeVarify();
            }
        });//
        Log.d("TestMain:" , "notify   02  ");



        clock_open();

        init();

        return view;
    }

    private void init()
    {
        Log.d("TestMain:" , "notify   init  ");
        Year = datePicker.getYear();
        Month = datePicker.getMonth();
        Day = datePicker.getDayOfMonth();

        Hour = timePicker.getCurrentHour();
        Minute = timePicker.getCurrentMinute();
    }

    private void clock_open() {
        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        i.putExtra(AlarmClock.EXTRA_MESSAGE, "New Alarm");
        i.putExtra(AlarmClock.EXTRA_HOUR, 13);
        i.putExtra(AlarmClock.EXTRA_MINUTES, 30);
        startActivity(i);
    }

    private void TimeVarify(){

    }

    private void store_ClockData(String Data)
    {
        MA.StoreClockData(Data);
    }
    private String[] get_ClockData()
    {
        String[] ClockData =  MA.getClockData().split(";");

        return ClockData ;
    }

    public void setSource(MainActivity MA)
    {
        this.MA = MA;
    }
}
