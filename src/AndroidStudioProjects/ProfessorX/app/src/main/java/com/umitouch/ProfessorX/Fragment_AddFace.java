package com.umitouch.ProfessorX;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Fragment_AddFace extends Fragment
{
    View view;
    private  Client_AddFace DataSocket;
    private MainActivity MA;
    private Button UMi_Button;
    private  int che9 = 9;

    private EditText id_EditText;
    private EditText name_EditText;
    private EditText fb_EditText;
    private EditText ig_EditText;
    private EditText identity_EditText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_addface, container, false);
        UMi_Button = (Button)view.findViewById(R.id.button);
        UMi_Button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        MA.setData("123");
        }
    });
<<<<<<< Updated upstream
        // Inflate the layout for this fragment
=======

>>>>>>> Stashed changes
        id_EditText = (EditText)view.findViewById(R.id.id);
        name_EditText=(EditText)view.findViewById(R.id.name);
        fb_EditText=(EditText)view.findViewById(R.id.fb);
        ig_EditText=(EditText)view.findViewById(R.id.ig);
        identity_EditText=(EditText)view.findViewById(R.id.identity);
<<<<<<< Updated upstream
       // CreatePerson("[img]https://upload.cc/i1/2019/12/18/naEAdh.jpg","王子嘉","https://www.facebook.com/profile.php?id=100003612286269","https://www.instagram.com/zi7909/?hl=zh-tw","學校:逢甲大學資工系","uml1uYPeVTUJU");
        CreatePerson("https://upload.cc/i1/2019/12/18/4b8V6z.jpg","林翊庭","https://www.facebook.com/lintippilin","https://www.instagram.com/tippi1105/?hl=zh-tw","學校:逢甲大學行銷系","uml1uYPeVTUJU");
=======
>>>>>>> Stashed changes

        return view;
    }

    public void setSource(MainActivity MA)
    {
        this.MA = MA;

    }

    public void AddSuccess()
    {
        CreatePerson(id_EditText.getText().toString(),name_EditText.getText().toString(),fb_EditText.getText().toString(),ig_EditText.getText().toString(),identity_EditText.getText().toString()  , MA.UID);
    }

    public void CreatePerson(String URL,String person_name,String person_fb,String person_ig ,String person_info ,String uid  )
    {
        DataSocket = new Client_AddFace();
        DataSocket.setSource(this);
        DataSocket.SocketInstruct = "CreatePerson "+URL+String.valueOf((char)(che9))+person_name+String.valueOf((char)(che9))+person_fb+String.valueOf((char)(che9))+person_ig+String.valueOf((char)(che9))+person_info+String.valueOf((char)(che9))+uid+String.valueOf((char)(che9));
        DataSocket.start();
    }
}
