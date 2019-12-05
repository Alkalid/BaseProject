package com.umitouch.ProfessorX;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BlankFragment_2 extends Fragment
{
    View view;
    private MainActivity MA;
    private Button UMi_Button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_blank_2, container, false);
        UMi_Button = (Button)view.findViewById(R.id.button);
        UMi_Button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        MA.setData("123");
        }
    });
        // Inflate the layout for this fragment
        return view;
    }

    public void setSource(MainActivity MA)
    {
        this.MA = MA;
    }
}