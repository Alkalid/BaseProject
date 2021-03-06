package com.umitouch.ProfessorX;

import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client_FaceIdentify extends Thread
{
    Fragment_FaceIdentify FL;
    Socket socket = null;
    BufferedWriter bw = null;
    BufferedReader br = null;

    String tmp;
    public String SocketInstruct = "";

    public String LobbyData_String = "";

    private String  RT_String;
    private Boolean Vision_lock = false;

    public String   L_RT_String   = "";
    private Boolean L_Vision_lock = false;



    boolean isTerminated = false;

    int che6 = 6,che7 = 7,che8 = 8,che9 = 9;

    public static Handler mHandler = new Handler();

    public void setSource(Fragment_FaceIdentify FL)
    {
        this.FL = FL;
    }

    public void sentMessage()
    {
        try
        {
            bw.write(this.SocketInstruct+"\n");
            bw.flush();
        }
        catch (IOException e)
        {

        }
    }
    public boolean isTerminated()
    {
        isTerminated = true;
        return isTerminated;
    }
    public void push(String str)
    {
        String[] SData = str.split(String.valueOf((char)(che9)))[1].split(String.valueOf((char)(che7)));
        Log.d("TestIdentify:" , str );
        if (SData[0].equals("Identify_start"))
        {
            RT_String = "";
            Log.d("TestIdentify:" , "Company_start");

        }
        if (SData[0].equals("Identify"))
        {
            RT_String += SData[1];
        }
        if (SData[0].equals("Identify_close"))
        {
            FL.IdentifySuccess(RT_String.split(String.valueOf((char)(che6))));
        }
        if (SData[0].equals("Identify_fail"))
        {
            FL.IdentifyFail(SData[1]);
        }

        if (SData[0].equals("Identify_resolution"))//解析度
        {
            FL.ShowResolution(SData[1]);
        }


    }

    public void setSocketInstruct(String SocketInstruct)
    {
        this.SocketInstruct = SocketInstruct;
    }

    public void connect()
    {
        try
        {
            socket = new Socket("114.35.11.36",5251);

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bw = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));
        }
        catch (IOException e)
        {

        }
    }

    @Override
    public void run()
    {
        String content = null;
        try
        {
            if (!isTerminated)
            {
                connect();
                sentMessage();
            }

            isTerminated = socket.isConnected();

            while (socket.isConnected())
            {
                while ((content = br.readLine()) != null)
                {
                    tmp = content;
                    mHandler.post(updateText);
                }
            }
        }
        catch (IOException e)
        {

        }
    }

    private Runnable updateText = new Runnable() {
        public void run() {
            // 加入新訊息並換行
            push(tmp);
        }
    };

}
