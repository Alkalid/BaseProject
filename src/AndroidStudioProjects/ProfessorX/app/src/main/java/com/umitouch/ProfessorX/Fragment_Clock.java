package com.umitouch.ProfessorX;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment_Clock extends Fragment
{
    View view;
    private MainActivity MA;

    private Button UMi_Button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) //認親
    {
        Log.d("TestMain:" , "notify   01  ");
        view = inflater.inflate(R.layout.fragment_clock, container, false);

        Log.d("TestMain:" , "notify   02  ");

        UMi_Button = (Button)view.findViewById(R.id.button);    // 認親
        Log.d("TestMain:" , "notify   03  ");
        UMi_Button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        MA.setData("123");
        }
    });
        // Inflate the layout for this fragment


        init();

        return view;
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
