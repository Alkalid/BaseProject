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
        i.putExtra(AlarmClock.EXTRA_HOUR, timePicker.getCurrentHour());
        i.putExtra(AlarmClock.EXTRA_MINUTES, timePicker.getCurrentMinute());
        startActivity(i);
    }



    private void TimeVarify(){
        if( Year <= datePicker.getYear() ){
            if( Month <= datePicker.getMonth() ){
                if(  Day <= datePicker.getDayOfMonth() ){
                    if( Hour <= timePicker.getCurrentHour() ){
                        if( Minute <= timePicker.getCurrentMinute() ){
                            TimeExist();
                        }
                    }
                }
            }
        }
        else{//顯示錯誤
            Toast.makeText(getActivity(), "不能設定過去時間", Toast.LENGTH_SHORT).show();
        }

    }

    private void TimeExist(){
        String[] data = null; //     year/month/date/hour/min
        boolean EXIST = false;

        if(  get_ClockData().length != 0)
        {
            data =  get_ClockData();
            for(int i = 0 ; i< data.length ; i++)
            {
                String[] clockData = data[i].split("/");
                if( Integer.valueOf(clockData[0]) == datePicker.getYear() &&  Month == datePicker.getMonth() && Day == datePicker.getDayOfMonth() && Hour == timePicker.getCurrentHour() && Minute == timePicker.getCurrentMinute())
                {
                    EXIST = true;
                }
            }

            if( EXIST ){//錯誤訊息
                Toast.makeText(getActivity(), "時間已存在", Toast.LENGTH_SHORT).show();
            }
            else{//call clock_open
                clock_open();
            }
        }
        else
        {
            clock_open();
        }

    }

    private void store_ClockData(String Data)
    {
        MA.StoreClockData(Data);
    }
    private String[] get_ClockData()
    {
        String[] ClockData = null;
        if(  !MA.getClockData().equals(null) )
        {
            ClockData =  MA.getClockData().split(";");
        }


        return ClockData ;
    }

    public void setSource(MainActivity MA)
    {
        this.MA = MA;
    }
}
