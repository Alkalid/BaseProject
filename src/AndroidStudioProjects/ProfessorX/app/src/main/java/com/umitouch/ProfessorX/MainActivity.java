package com.umitouch.ProfessorX;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    public TextView Text = null;
    public Button JAMES;

    private Fragment_FaceIdentify Fragment_FaceIdentify = null;
    private Fragment_AddFace Fragment_AddFace = null;
    private Fragment_Notification Fragment_Notification = null;
    public qrcode_camera qrcode_camera = null;
    private Barcode_camera Barcode_camera = null;

    private MainClient MC;
    private String SocketInstruct = "";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        JAMES = (Button)findViewById(R.id.JAMES);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Text = (TextView)findViewById(R.id.MyText);

        Fragment_FaceIdentify = new Fragment_FaceIdentify();
        Fragment_AddFace = new Fragment_AddFace();
        Fragment_Notification = new Fragment_Notification();

        qrcode_camera = new qrcode_camera();
        Barcode_camera = new Barcode_camera();


        Fragment_FaceIdentify.setSource(this);
        Fragment_AddFace.setSource(this);
        Fragment_Notification.setSource(this);
        qrcode_camera.setSource(this);
        Barcode_camera.setSource(this);


    }

    public void CallJames(View v)
    {
        this.SocketInstruct = "qrcodeTest ";
        Log.d("socketTest", "CallJames: ");
        connect();
    }


////   Qrcode的值
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!= null)
        {
            if (result.getContents()==null)
            {
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this,result.getContents(),Toast.LENGTH_SHORT).show();
            }

            Log.d("socketTest2", "CallJames: ");
            this.SocketInstruct = "qrcodeTest ";
            connect();
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    public void setData(String data)
    {
        Text.setText(data);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {
            if (!Fragment_FaceIdentify.isAdded())
            {
                this.getSupportFragmentManager().beginTransaction().replace(R.id.container, Fragment_FaceIdentify).addToBackStack(null).commit();
            }
        }
        else if (id == R.id.nav_gallery)
        {
            if (!Fragment_AddFace.isAdded())
            {
                this.getSupportFragmentManager().beginTransaction().replace(R.id.container, Fragment_AddFace).addToBackStack(null).commit();
            }
        }
        else if (id == R.id.nav_slideshow)
        {
            if (!Fragment_Notification.isAdded())
            {
                this.getSupportFragmentManager().beginTransaction().replace(R.id.container, Fragment_Notification).addToBackStack(null).commit();
            }
        }

        else if (id == R.id.nav_manage)
        {
            if (!Barcode_camera.isAdded())
            {
                this.getSupportFragmentManager().beginTransaction().replace(R.id.container, Barcode_camera).addToBackStack(null).commit();
            }
        }

        else if (id == R.id.nav_share)
        {

        }

        else if (id == R.id.nav_send)
        {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void connect()
    {
        MC = new MainClient();
        MC.setSource(this);
        MC.SocketInstruct = SocketInstruct;
        MC.start();
    }
}
