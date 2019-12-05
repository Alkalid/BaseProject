package com.umitouch.ProfessorX;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;

public class qrcode_camera extends Fragment
{
    View view;

    private ImageView scan_btn;

    private MainActivity MA;

    private FragmentClient FC;
    private String SocketInstruct = "";

    public TextView textView2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.qrcode_camera_fragment, container, false);

        textView2 = (TextView)view.findViewById(R.id.textView2);


        scan_btn = (ImageView)view.findViewById(R.id.scan_btn);
        scan_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
        return view;
    }

    public void setSource(MainActivity MA)
    {
        this.MA = MA;
    }

    public void connect()
    {
        FC = new FragmentClient();
        FC.setSource(this);
        FC.SocketInstruct = SocketInstruct;
        FC.start();
    }
}