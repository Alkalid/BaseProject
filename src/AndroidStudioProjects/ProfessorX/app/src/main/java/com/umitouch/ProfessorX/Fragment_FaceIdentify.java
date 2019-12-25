package com.umitouch.ProfessorX;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.internal.http.multipart.MultipartEntity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;


public class Fragment_FaceIdentify extends Fragment {
    View view;
    private MainActivity MA;
    private Client_FaceIdentify DataSocket ;
    private Button UMi_Button;



    private EditText URL_EditText;
    private TextView PersonName;
    private TextView PersonInfo;
    private TextView PersonFB;
    private TextView PersonIG;
    private ViewPager PersonImage_ViewPager;

    private LinearLayout Main_LinerLayout;
    private LinearLayout Searching_LinerLayout;
    private LinearLayout IdentifyResult_LinerLayout;//

    private String UID;
    private String URL;
    private String accessToken;
    private String refreshToken;

    private String picturePath = "";
    private Button send;

    private String uploadedImageUrl = "";
    private int che9 = 9;
    private int che8 = 8;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_faceidentify, container, false);
        Main_LinerLayout = (LinearLayout) view.findViewById(R.id.Main_LinerLayout);
        Searching_LinerLayout = (LinearLayout) view.findViewById(R.id.Searching_LinerLayout);
        IdentifyResult_LinerLayout = (LinearLayout) view.findViewById(R.id.IdentifyResult_LinerLayout);
        PersonName = (TextView) view.findViewById(R.id.PersonName_TextView);
        PersonInfo = (TextView) view.findViewById(R.id.PersonInfo_TextView);
        PersonFB = (TextView) view.findViewById(R.id.PersonFB_TextView);
        PersonIG= (TextView) view.findViewById(R.id.PersonIG_TextView);
        PersonImage_ViewPager = (ViewPager) view.findViewById(R.id.PersonImage_ViewPager);
        URL_EditText = (EditText) view.findViewById(R.id.URL_EditText);
        UMi_Button = (Button) view.findViewById(R.id.button);
        UMi_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartIdentify();
            }
        });//



        init();


        //show();
        return view;
    }

    public void setSource(MainActivity MA)
    {
        this.MA = MA;
    }

    public void init()
    {
        UID = MA.UID;
    }

    public void StartIdentify()
    {
        Log.d("TestIdentify:" , "StartIdentify ");
        Main_LinerLayout.setVisibility(View.GONE);
        Searching_LinerLayout.setVisibility(View.VISIBLE);
        URL = URL_EditText.getText().toString();
        Identify( URL );
    }

    public void IdentifySuccess(String[] IdentifyResult )
    {
        Log.d("TestIdentify:" , IdentifyResult.toString());
        Searching_LinerLayout.setVisibility(View.GONE);
        IdentifyResult_LinerLayout.setVisibility(View.VISIBLE);
        PersonName.setText(IdentifyResult[1] );
        PersonFB.setText(IdentifyResult[2]);
        PersonIG.setText(IdentifyResult[3]);
        PersonInfo.setText(IdentifyResult[4]);
        show(IdentifyResult[5].split(String.valueOf((char)(che8))));
        Log.d("TestIdentify:" , IdentifyResult[5].toString());
    }

    public void show(String[] FaceList)
    {
        //String[] image = {"https://images.chinatimes.com/newsphoto/2018-12-29/900/20181229000914.jpg","https://cw1.tw/CW/images/article/C1415940973254.jpg"};

        ViewPagerAdapter adapter = new ViewPagerAdapter(Fragment_FaceIdentify.this,FaceList);
        PersonImage_ViewPager.setAdapter(adapter);
    }

    public void IdentifyFail(String FailMessenge)
    {
        Searching_LinerLayout.setVisibility(View.GONE);
        Main_LinerLayout.setVisibility(View.VISIBLE);
        if(FailMessenge.equals("fail_size"))
        {
            Log.d("TestIdentify:" , "fail_size");
            Toast.makeText(getActivity().getBaseContext(),"圖片大小錯誤",Toast.LENGTH_SHORT).show();
        }
        else if(FailMessenge.equals("fail"))
        {
            Toast.makeText(getActivity().getBaseContext(),"圖片辨識失敗，請提供其他相片辨識",Toast.LENGTH_SHORT).show();
        }

    }

    public void ShowResolution(String Resolution)
    {
        Toast.makeText(getActivity().getBaseContext(),"長*寬:",Toast.LENGTH_SHORT).show();
    }

    public void Identify(String URL )//
    {
        DataSocket = new Client_FaceIdentify();
        DataSocket.setSource(this);
        DataSocket.SocketInstruct = "Identify "+UID+String.valueOf((char)(che9))+URL+String.valueOf((char)(che9)) ;
        DataSocket.start();
    }

    public void CreatePerson(String URL,String person_name,String person_fb,String person_ig ,String person_info ,String uid  )
    {
        DataSocket = new Client_FaceIdentify();
        DataSocket.setSource(this);
        DataSocket.SocketInstruct = "CreatePerson "+URL+String.valueOf((char)(che9))+person_name+String.valueOf((char)(che9))+person_fb+String.valueOf((char)(che9))+person_ig+String.valueOf((char)(che9))+person_info+String.valueOf((char)(che9))+uid+String.valueOf((char)(che9));
        DataSocket.start();
    }


    /*private void getSelectImage(Intent data){
        //從 onActivityResult 傳入的data中，取得圖檔路徑
        Uri selectedImage = data.getData();
        String[] filePathColumn = { MediaStore.Images.Media.DATA };

        Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        if(cursor==null){ return; }
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String imagePath = cursor.getString(columnIndex);
        cursor.close();
        //Log.d("editor","image:"+imagePath);

        //使用圖檔路徑產生調整過大小的Bitmap
        Bitmap bitmap = getResizedBitmap(imagePath); //程式寫在後面

        //將 Bitmap 轉為 base64 字串
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bitmapData = bos.toByteArray();
        String imageBase64 = Base64.encodeToString(bitmapData, Base64.DEFAULT);
        //Log.d("editor",imageBase64);

        //將圖檔上傳至 Imgur，將取得的圖檔網址插入文字輸入框
        imgurUpload(imageBase64); //程式寫在後面
    }

    private Bitmap getResizedBitmap(String imagePath) {
        // 取得原始圖檔的bitmap與寬高
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
        int width = options.outWidth, height = options.outHeight;

        // 將圖檔等比例縮小至寬度為1024
        final int MAX_WIDTH = 1024;
        float resize = 1; // 縮小值 resize 可為任意小數
        if(width>MAX_WIDTH){
            resize = ((float) MAX_WIDTH) / width;
        }

        // 產生縮圖需要的參數 matrix
        Matrix matrix = new Matrix();
        matrix.postScale(resize, resize); // 設定寬高的縮放比例

        // 產生縮小後的圖
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return resizedBitmap;
    }

    private void imgurUpload(final String image){ //插入圖片
        String urlString = "https://imgur-apiv3.p.mashape.com/3/image/";
        String mashapeKey = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX"; //設定自己的 Mashape Key
        String clientId = "XXXXXXXXXXX"; //設定自己的 Clinet ID
        String titleString = ""; //設定圖片的標題
        showLoadingDialog("上傳中...");

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("X-Mashape-Key", mashapeKey);
        client.addHeader("Authorization", "Client-ID "+clientId);
        client.addHeader("Content-Type", "application/x-www-form-urlencoded");
        RequestParams params = new RequestParams();
        params.put("title", titleString);
        params.put("image", image);
        client.post(urlString, params, new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONObject response) {
                dismissLoadingDialog();
                if (!response.optBoolean("success") || !response.has("data")) {
                    Log.d("editor", "response: "+response.toString());
                    return;
                }
                JSONObject data = response.optJSONObject("data");
                //Log.d("editor","link: "+data.optString("link"));
                String link = data.optString("link","");
                int width = data.optInt("width",0);
                int height = data.optInt("height",0);
                String bbcode = "[img="+width+"x"+height+"]"+link+"[/img]";
                textInsertString(bbcode); //將文字插入輸入框的程式 寫在後面
            }
            @Override
            public void onFailure(int statusCode, PreferenceActivity.Header[] headers, Throwable e, JSONObject error) {
                dismissLoadingDialog();
                //Log.d("editor","error: "+error.toString());
                if (error.has("data")) {
                    JSONObject data = error.optJSONObject("data");
                    AlertDialog dialog = new AlertDialog.Builder(mContext)
                            .setTitle("Error: " + statusCode + " " + e.getMessage())
                            .setMessage(data.optString("error",""))
                            .setPositiveButton("確定", null)
                            .create();
                    dialog.show();
                }
            }
        });
    }

    private void showLoadingDialog(String message){
        if(message==null){
            message = "載入中...";
        }
        if(mLoadingDialog==null){
            mLoadingDialog = new ProgressDialog(this);
            mLoadingDialog.setMessage(message);
        }
        mLoadingDialog.show();
    }

    private void dismissLoadingDialog(){
        if(mLoadingDialog!=null) {
            mLoadingDialog.dismiss();
        }
    }
    private void textInsertString(String insertString){
        int start = mTextEditText.getSelectionStart();
        mTextEditText.getText().insert(start, insertString);
    }*/
    public void TRANSFER()
    {
        //Bitmap photo = (Bitmap) data.getExtras().get("data");

        //new UploadToImgurTask().execute(picturePath);

        /*// CALL THIS METHOD TO GET THE URI FROM THE BITMAP
        Uri tempUri = getImageUri(getActivity().getApplicationContext(), photo);

        // CALL THIS METHOD TO GET THE ACTUAL PATH
        File finalFile = new File(getRealPathFromURI(tempUri));*/
    }
/*
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
*/
/*
    class UploadToImgurTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            final String upload_to = "https://api.imgur.com/3/upload";

            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpPost httpPost = new HttpPost(upload_to);

            try {
                HttpEntity entity = MultipartEntityBuilder.create()
                        .addPart("image", new FileBody(new File(params[0])))
                        .build();

                httpPost.setHeader("Authorization", "Bearer " + accessToken);
                httpPost.setEntity(entity);

                final HttpResponse response = httpClient.execute(httpPost,
                        localContext);

                final String response_string = EntityUtils.toString(response
                        .getEntity());

                final JSONObject json = new JSONObject(response_string);

                Log.d("tag", json.toString());

                JSONObject data = json.optJSONObject("data");
                uploadedImageUrl = data.optString("link");
                Log.d("tag", "uploaded image url : " + uploadedImageUrl);

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean.booleanValue()) { // after sucessful uploading, show the image in web browser
                Button openBrowser = new Button(OAuthTestActivity.this);
                rootView.addView(openBrowser);
                openBrowser.setText("Open Browser");
                openBrowser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setData(Uri.parse(uploadedImageUrl));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
            }
        }
    }*/




}


