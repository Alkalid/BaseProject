package com.umitouch.ProfessorX;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Barcode_camera extends Fragment
{
    View view;
    private MainActivity MA;
    private ImageView scan_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_barcode_camera, container, false);
        return view;
    }

    public void setSource(MainActivity MA)
    {
        this.MA = MA;
    }
}