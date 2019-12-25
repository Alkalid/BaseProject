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
import java.text.SimpleDateFormat;
import java.util.Date;

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

        //MA.StoreClockData("2020/1/1/5/5;");
    }

    private void clock_open() {
        Log.d("TestMain:" , " clock_open  00  ");
        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        i.putExtra(AlarmClock.EXTRA_MESSAGE, "New Alarm");
        i.putExtra(AlarmClock.EXTRA_HOUR, timePicker.getCurrentHour());
        i.putExtra(AlarmClock.EXTRA_MINUTES, timePicker.getCurrentMinute());
        startActivity(i);
        store_ClockData(get_ClockData() + datePicker.getYear()+"/"+ datePicker.getMonth()+"/"+ datePicker.getDayOfMonth()+"/"+ timePicker.getCurrentHour()+"/"+ timePicker.getCurrentMinute()+";"  );
    }



    private void TimeVarify(){


        try
        {
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
            Date d1 = sdformat.parse(Year+"-"+Month+"-"+Day+"-"+Hour+"-"+Minute);
            Date d2 = sdformat.parse(datePicker.getYear()+"-"+datePicker.getMonth()+"-"+datePicker.getDayOfMonth()+"-"+timePicker.getCurrentHour()+"-"+timePicker.getCurrentMinute());

            if(d1.compareTo(d2) > 0) {
                Log.d("TestMain:" , " TimeVarify  >0  ");
                Toast.makeText(getActivity(), "不能設定過去時間", Toast.LENGTH_SHORT).show();
            } else if(d1.compareTo(d2) < 0) {
                Log.d("TestMain:" , " TimeVarify  <0  ");
                TimeExist();
            } else if(d1.compareTo(d2) == 0) {
                Log.d("TestMain:" , " TimeVarify  =0  ");
                TimeExist();
            }
        }
        catch (Exception e){

        }



    }

    private void TimeExist(){
        Log.d("TestMain:" , " TimeExist  00  ");
        String[] data = null; //     year/month/date/hour/min
        boolean EXIST = false;
        Log.d("TestMain:" , " TimeExist  01  " + get_ClockData() );
        if(  !get_ClockData().equals(null) )
        {
            Log.d("TestMain:" , " TimeExist  02  ");
            data =  get_ClockData().split(";");
            Log.d("TestMain:" , " TimeExist  03  "+data.length + data[0]);
            for(int i = 0 ; i< data.length ; i++)
            {
                Log.d("TestMain:" , " TimeExist  3.5  ");
                String[] clockData = data[i].split("/");
                if( Integer.valueOf(clockData[0]) == datePicker.getYear() &&  Integer.valueOf(clockData[1]) == datePicker.getMonth() && Integer.valueOf(clockData[2]) == datePicker.getDayOfMonth() && Integer.valueOf(clockData[3]) == timePicker.getCurrentHour() && Integer.valueOf(clockData[4]) == timePicker.getCurrentMinute())
                {
                    EXIST = true;
                }
            }
            Log.d("TestMain:" , " TimeExist  04  ");
            if( EXIST ){//錯誤訊息
                Toast.makeText(getActivity(), "時間已存在", Toast.LENGTH_SHORT).show();
            }
            else{//call clock_open
                clock_open();
            }
        }
        else
        {
            Log.d("TestMain:" , " TimeExist  05  ");
            clock_open();
        }

    }

    private void store_ClockData(String Data)
    {
        MA.StoreClockData(Data);
    }
    private String get_ClockData()
    {
        String ClockData = null;
        if(  !MA.getClockData().equals(null) )
        {
            ClockData =  MA.getClockData();
        }


        return ClockData ;
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
