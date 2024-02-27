package com.mradking.powerxlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mradking.powerx.Utility.Constanet;
import com.mradking.powerx.Utility.NotificationX;
import com.mradking.powerx.Utility.Notification_Status_Call;
import com.mradking.powerx.Utility.PayX;
import com.mradking.powerx.Utility.PermissionsX;
import com.mradking.powerx.Utility.ServerX;
import com.mradking.powerx.Utility.server_result_call;
import com.mradking.powerx.Utility.token_call;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        
        if(PayX.result.contentEquals("done")){

            Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();

        }else if(PayX.result.contentEquals("no done")){

            Toast.makeText(this, "not done", Toast.LENGTH_SHORT).show();


        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sent_notification_bt= findViewById(R.id.sent_notification_bt);
        Button payx_bt=findViewById(R.id.payx);

        payx_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PayX.TranctionX(MainActivity.this,"1","Bria72E5aPUjBm4vFZQMqhonVZN2");

            }
        });

        sent_notification_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send_notification_act();

            }
        });






//        PermissionsX permissionsX=new PermissionsX();
//
//        String[] permissions = {
//                "Manifest.permission.INTERNET",
//                "Manifest.permission.WRITE_EXTERNAL_STORAGE",
//                "Manifest.permission.READ_EXTERNAL_STORAGE"
//        };
//        permissionsX.requestPermissions(this,permissions);


        //////////// server request example put post get deleat//////
        ServerX serverX=new ServerX();
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("catid", "25");

        serverX.delete("https://sangamenterprises.net/api/delete_data.php"
                , dataMap, new server_result_call() {
                    @Override
                    public void on_susess(String response) {

                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void on_failed(String message) {

                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });

        /////////////////////////////////////////////////////

    }

    private void send_notification_act() {

        NotificationX.getNotificationToken(new token_call() {
            @Override
            public void on_susess(String token) {


                Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();

                NotificationX.send_notification(MainActivity.this
                        , token, "This is test", "i know this is test", new Notification_Status_Call() {
                            @Override
                            public void on_susess(String Sussess) {

                            }

                            @Override
                            public void on_failed(String message) {

                            }
                        });

            }

            @Override
            public void on_failed(String message) {

            }
        });

    }


    public void  read_json(){

        String json="{\"code\":\"200\",\"msg\":[{\"id\":\"2\",\"fb_id\":\"9079740588\",\"profile_pic\":\"upload\\/images_77222.png\",\"first_name\":\"Sunil Singh\",\"language\":\"English, Hindi\",\"charges\":\"10\",\"bio\":\"Tarot Reading,Past Life,Relationships\",\"go_live\":\"No\",\"experience\":\"3 Years\"},{\"id\":\"3\",\"fb_id\":\"9928001943\",\"profile_pic\":\"\",\"first_name\":\"Ajay Kumar\",\"language\":\"English, Hindi\",\"charges\":\"5\",\"bio\":\"Tarot Reading,Past Life,Relationships\",\"go_live\":\"\",\"experience\":\"2 Years\"},{\"id\":\"4\",\"fb_id\":\"9462314177\",\"profile_pic\":\"\",\"first_name\":\"Vashisth Kumar Yadav\",\"language\":\"English, Hindi\",\"charges\":\"3\",\"bio\":\"Tarot Reading,Past Life,Relationships\",\"go_live\":\"\",\"experience\":\"5 Years\"},{\"id\":\"5\",\"fb_id\":\"9680740557\",\"profile_pic\":\"upload\\/images_65039.png\",\"first_name\":\"Sunil Singh\",\"language\":\"English, Hindi\",\"charges\":\"7\",\"bio\":\"Tarot Reading,Past Life,Relationships\",\"go_live\":\"Yes\",\"experience\":\"5 Years\"},{\"id\":\"6\",\"fb_id\":\"9911999429\",\"profile_pic\":\"upload\\/images_11651.png\",\"first_name\":\"Manish \",\"language\":\"\",\"charges\":\"0\",\"bio\":\"\",\"go_live\":\"Yes\",\"experience\":\"\"}]}";


//        JSONArray jsonArray=new JSONArray();
        try {
            JSONObject jsonObject= new JSONObject(json);

            JSONArray jsonArray=jsonObject.getJSONArray("msg");

            for(int i=0;i<jsonArray.length();i++){

                JSONObject obj=jsonArray.getJSONObject(i);
                String name=obj.getString("first_name");
                Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}