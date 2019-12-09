package com.umitouch.ProfessorX;

import android.os.Bundle;
import android.support.v4.app.Fragment;


import android.accounts.Account;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class Fragment_Login extends Fragment
{
    View view;
    private Client_Login DataSocket ;

    private ProgressBar progressBar;

    private EditText Account;
    private EditText PW;
    private MainActivity main;


    private MainActivity MA;


    private Boolean CheckSuccess = false;
    private String ID;
    private String UID;
    private String Name;
    private String UserData = "";
    private int che9=9;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        Account = (EditText)view.findViewById(R.id.id);
        PW = (EditText)view.findViewById(R.id.password);
        Button btn1 =  (Button)view.findViewById(R.id.Login_Button);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //progressBar.setVisibility(View.VISIBLE);//中間旋轉的

                String str_id = Account.getText().toString();
                String str_pw = PW.getText().toString();

                if ( str_id==null || str_id.equals("") || str_pw==null || str_pw.equals(""))
                {
                    Toast.makeText(getActivity().getBaseContext(),"帳號或密碼錯誤",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    CheckIn(Account.getText().toString() , PW.getText().toString() );
                }


            }
        });

        return view;
    }

    public void setSource(MainActivity MA)
    {
        this.MA = MA;
    }

    public void Pass(String UID,String[] UserData)
    {

        Toast.makeText(  getActivity(), "登入成功", Toast.LENGTH_SHORT).show();
        MA.UID = UID;
        MA.UserName = UserData[0];
        MA.Account = Account.getText().toString();
        MA.SetUserData();






    }
    public void Fail()
    {
        Toast.makeText(  getActivity(), "登入失敗 帳號或密碼錯誤", Toast.LENGTH_SHORT).show();

    }
    public void CheckIn(String Account, String Password)
    {
        DataSocket = new Client_Login();
        DataSocket.setSource(this);
        DataSocket.SocketInstruct = "CheckIn "+Account+String.valueOf((char)(che9))+Password+String.valueOf((char)(che9)) ;
        DataSocket.start();
    }
}
