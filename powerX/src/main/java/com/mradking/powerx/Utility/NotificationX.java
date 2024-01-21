package com.mradking.powerx.Utility;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;

import com.deeplabstudio.fcmsend.FCMSend;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

public class NotificationX {
    private static String serverKey = "AAAAotimY4c:APA91bGI8hVl7HdHyao_JGVCM_pw3GCBiVhuf-miCQzSTwS2oUq2O7OIWYvhg9Ob66146rEdXgM6pSA13xFBYXh8tOcGqJ1d_CtFGo3XX_JdNm48AcxBUo3ZExuq3Tl2uA7aQJv3qy93";
    public   static final String CHANNEL_ID="IPServiceChannel";
    private static final String CHANNEL_NAME= "IPService";
    private static final String CHANNEL_DESC= "IPService notification";

    public static void getNotificationToken(token_call call) {

        try {
            // Get the FCM token

            FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
                @Override
                public void onSuccess(String s) {

                    call.on_susess(s);
                    Constanet.fmc_token=s;


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    call.on_failed(e.toString());

                }
            });



        } catch (Exception e) {
            call.on_failed(e.toString());
          Log.e("afdfsfaf",e.toString()) ;
        }

    }


    public  static  void send_notification(Context context,String device_token,String Title,String message,Notification_Status_Call call){

        FCMSend.SetServerKey(serverKey);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription(CHANNEL_DESC);

            NotificationManager notificationManager=context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

        }


            FCMSend.Builder build1 = new FCMSend.Builder(device_token)
                    .setTitle(Title)
                    .setBody(message);

            build1.send();

        String result2 = build1.send().Result();


        if(ChekSuccess( result2).contentEquals("Success")){

            call.on_susess("Message Sended");

        }else {
            call.on_susess("Message Not Sended ");

        }



    }

    private static String ChekSuccess(String result) {
        try {
            JSONObject object = new JSONObject(result);
            String success = object.getString("success");
            if (success.equals("1")) {
                return "Success";
            } else if (success.equals("0")) {
                return "Unsuccessful";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Unsuccessful";
    }

}



