package com.umitouch.ProfessorX;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Fragment_Calender extends Fragment
{
    View view;
    private MainActivity MA;
    private LinearLayout Main_LinearLayout;

    private ListView DataListView;
    private Button UMi_Button;
    private Button aaa_Button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) //認親//
    {
        Log.d("TestMain:" , "notify   01  ");
        view = inflater.inflate(R.layout.fragment_calender, container, false);
        Main_LinearLayout = (LinearLayout)view.findViewById(R.id.Main);
        DataListView = (ListView) view.findViewById(R.id.buckysListView);


        Log.d("TestMain:" , "notify   02  ");
        aaa_Button = (Button)view.findViewById(R.id.aaa);    // 認親
        aaa_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press();
                show();
            }
        });//


        UMi_Button = (Button)view.findViewById(R.id.button);    // 認親
        Log.d("TestMain:" , "notify   03  ");
        UMi_Button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        MA.setData("123");
        }});




        init();

        return view;
    }

    private void init()
    {
        Log.d("TestMain:" , "notify   init  ");
    }

    private void press()
    {
        Main_LinearLayout.setVisibility(View.VISIBLE);
    }

    private void show()
    {
        String Calender[] = {"01" , "02"};



        ListAdapter DataListViewAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1 , Calender);
        DataListView.setAdapter(DataListViewAdapter);
        DataListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                //點下去裡面會做的事在這
                //String music = String.valueOf(parent.getItemAtPosition(position));

            }
        });
    }
    public void setSource(MainActivity MA)
    {
        this.MA = MA;
    }
}

