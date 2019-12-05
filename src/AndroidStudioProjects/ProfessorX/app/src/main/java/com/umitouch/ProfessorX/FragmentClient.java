package com.umitouch.ProfessorX;

import android.os.Handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class FragmentClient  extends Thread
{
    qrcode_camera QC;
    Socket socket = null;
    BufferedWriter bw = null;
    BufferedReader br = null;

    String tmp;
    public String SocketInstruct = "";

    boolean isTerminated = false;

    int che6 = 6,che7 = 7,che8 = 8,che9 = 9;

    public static Handler mHandler = new Handler();

    public void setSource(qrcode_camera QC)
    {
        this.QC = QC;
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
        String[] SData = str.split(String.valueOf((char)(che6)))[1].split(String.valueOf((char)(che9)));

        if (SData[0].equals("Message"))
        {
            QC.textView2.setText(SData[1]);

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
            socket = new Socket("192.168.1.45",5251);

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
