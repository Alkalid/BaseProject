package com.umitouch.ProfessorX;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Fragment_Calender extends Fragment
{
    View view;
    private MainActivity MA;
    private LinearLayout Main_LinearLayout;



    private Button set_Button;
    private Button view_Button;
    private Button close_button;
    private Button openDate_button;
    private Button openTime_button;
    private EditText title_EditText;
    private TimePicker timePicker;
    private DatePicker datePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) //認親//
    {
        Log.d("TestMain:" , "Calender   01  ");
        view = inflater.inflate(R.layout.fragment_calender, container, false);
        Main_LinearLayout = (LinearLayout)view.findViewById(R.id.Main);
        title_EditText = (EditText) view.findViewById(R.id.title);
        timePicker = (TimePicker) view.findViewById(R.id.timePicker);
        datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        close_button = (Button)view.findViewById(R.id.close_button);
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.setVisibility(View.GONE);
                datePicker.setVisibility(View.GONE);
                openDate_button.setText("日期: "+datePicker.getYear()+"/"+(datePicker.getMonth()+1) +"/"+datePicker.getDayOfMonth());
                openTime_button.setText("時間: "+timePicker.getCurrentHour()+":"+(timePicker.getCurrentMinute()) );
                //show();
            }
        });//
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        openDate_button = (Button)view.findViewById(R.id.openDate_button);
        openDate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePicker.setVisibility(View.VISIBLE);

            }
        });//
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        openTime_button = (Button)view.findViewById(R.id.openTime_button);
        openTime_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timePicker.setVisibility(View.VISIBLE);

            }
        });//
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        view_Button  = (Button)view.findViewById(R.id.CalendarView_button);
        view_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarView( view);
                //show();
            }
        });//
        set_Button = (Button)view.findViewById(R.id.aaa);
        set_Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AddCalendarEvent( view);
            }
        });//

        Log.d("TestMain:" , "Calender   03  ");
        init();

        return view;
    }

    private void init()
    {
        Log.d("TestMain:" , "notify   init  ");
    }




    public void AddCalendarEvent(View view)
    {

        Calendar beginTime = Calendar.getInstance();
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();

        int hour = timePicker.getCurrentHour();
        int min = timePicker.getCurrentMinute();


        beginTime.set(year, month, day, hour, min);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2020, 0, 19, 8, 30);



        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, title_EditText.getText().toString() )
                //.putExtra(CalendarContract.Events.DESCRIPTION, "Group class")
                //.putExtra(CalendarContract.Events.EVENT_LOCATION, "The gym")
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
                //.putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com");
        startActivity(intent);
    }

    public void CalendarView(View view)
    {
        Calendar calendarEvent = Calendar.getInstance();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setType("vnd.android.cursor.item/event");
        startActivity(i);
    }

    public void setSource(MainActivity MA)
    {
        this.MA = MA;
    }
}

