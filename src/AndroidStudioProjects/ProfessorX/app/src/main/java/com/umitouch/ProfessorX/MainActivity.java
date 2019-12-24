package com.umitouch.ProfessorX;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private Fragment_Login Fragment_Login = null;
    private Fragment_Calender Fragment_Calender  = null;
    private Fragment_Clock Fragment_Clock = null;
    public qrcode_camera qrcode_camera = null;
    private Barcode_camera Barcode_camera = null;

    private Boolean LogIning = false;
    public String UID;
    public String UserName;
    public String Account;


    private MainClient MC;
    private String SocketInstruct = "";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d("TestMain:" , "01 ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Text = (TextView)findViewById(R.id.MyText);

        Fragment_FaceIdentify = new Fragment_FaceIdentify();
        Fragment_AddFace = new Fragment_AddFace();
        Fragment_Notification = new Fragment_Notification();
        Fragment_Calender  = new Fragment_Calender();
        Fragment_Clock = new Fragment_Clock();
        qrcode_camera = new qrcode_camera();
        Barcode_camera = new Barcode_camera();
        Fragment_Login = new Fragment_Login();

        Fragment_FaceIdentify.setSource(this);
        Fragment_AddFace.setSource(this);
        Fragment_Notification.setSource(this);
        Fragment_Calender.setSource(this);
        qrcode_camera.setSource(this);
        Barcode_camera.setSource(this);
        Fragment_Login.setSource(this);
        Fragment_Clock.setSource(this);
        Log.d("TestMain:" , "02 ");
        //CheckLogin();
    }

    public void CallJames(View v)
    {
        this.SocketInstruct = "qrcodeTest ";
        Log.d("socketTest", "CallJames: ");
        connect();
    }

    public void SetUserData()   //初次登入  設定登入資料 這樣下次就能自動登入
    {
        LogIning = true;
        //qrcode_camera.UID=UID;

        ShowUserData();
        SharedPreferences userInfo = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();
        editor.putString("UserID", UID);
        editor.putString("UserName",   UserName  );
        editor.putString("UserAccount",   Account  );
        editor.commit();
        Log.d("TestMain:" , "保存用户資訊");
        //this.getSupportFragmentManager().beginTransaction().hide(qrcode_camera).hide(Fragment_Login).hide(Fragment_1).addToBackStack(null).commit();
        //this.getSupportFragmentManager().beginTransaction().replace(R.id.container, qrcode_camera).addToBackStack(null).commit();
    }
    private void ShowUserData()//設定左邊選單的用戶資料
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView UserName = (TextView) headerView.findViewById(R.id.UserName);
        TextView UserAccount = (TextView) headerView.findViewById(R.id.UserAccount);
        UserName.setText(this.UserName);
        UserAccount.setText(this.Account);
    }

    public void StoreClockData(String Data)   //初次登入  設定登入資料 這樣下次就能自動登入
    {

        SharedPreferences userInfo = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();
        editor.putString("UserID", Data);

        editor.commit();
        Log.d("TestMain:" , "保存鬧鐘資訊");
        //this.getSupportFragmentManager().beginTransaction().hide(qrcode_camera).hide(Fragment_Login).hide(Fragment_1).addToBackStack(null).commit();
        //this.getSupportFragmentManager().beginTransaction().replace(R.id.container, qrcode_camera).addToBackStack(null).commit();
    }
    public String getClockData()   //初次登入  設定登入資料 這樣下次就能自動登入
    {
        SharedPreferences userInfo = getSharedPreferences("data", MODE_PRIVATE);
        String UserID = userInfo.getString("UserID", null);//读取username



        return UserID;
    }

    private void LogOut()
    {
        //getSupportActionBar().setTitle("登入");
        LogIning = false;
        SharedPreferences userInfo = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();//获取Editor
        //得到Editor后，写入需要保存的数据
        editor.putString("UserID", null);
        editor.commit();//提交修改
        Log.d("TestMain:" , "登出");
    }

    public void CheckLogin()      //先取得登入資料
    {
        SharedPreferences userInfo = getSharedPreferences("data", MODE_PRIVATE);
        String UserID = userInfo.getString("UserID", null);//读取username
        String UserName = userInfo.getString("UserName", null);//读取username
        String Account = userInfo.getString("UserAccount", null);//读取username

        if(UserID == null)  //沒登入 跳轉登入頁面
        {
            //getSupportActionBar().setTitle("登入");
            Log.d("TestMain:" , "change to login");
            this.getSupportFragmentManager().beginTransaction().add(R.id.container, Fragment_Login).addToBackStack(null).commit();

        }
        else //登入
        {
            Log.d("TestMain:" , "用戶已登入 "+UserID+"   "+UserName);
            this.UID = UserID;
            this.UserName = UserName;
            this.Account = Account;
            ShowUserData();
            LogIning=true;
        }
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
            if (!Fragment_Clock.isAdded())
            {
                this.getSupportFragmentManager().beginTransaction().replace(R.id.container, Fragment_Clock).addToBackStack(null).commit();
            }
        }

        else if (id == R.id.nav_share)
        {
            if (!Fragment_Calender.isAdded())
            {
                this.getSupportFragmentManager().beginTransaction().replace(R.id.container, Fragment_Calender).addToBackStack(null).commit();
            }
        }

        else if (id == R.id.nav_send)
        {
            LogOut();
            this.getSupportFragmentManager().beginTransaction().replace(R.id.container, Fragment_Login).addToBackStack(null).commit();
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
