package com.mradking.powerx.Utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.Api;

public class PayX extends Activity {
    public static final int UPI_TRANSACTION_REQUEST_CODE = 123; // Choose any integer value you prefer

    public static  String result = "else";

    String api_key_st;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;


    String key_amt;
    FirebaseAuth firebaseAuth;
//    public PayX(String key) {
//        this.key = key;
//
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog=new ProgressDialog(this);
      firebaseFirestore =FirebaseFirestore.getInstance();
        api_key_st=getIntent().getExtras().getString("api_key");


         firebaseAuth=FirebaseAuth.getInstance();


         progressDialog.setMessage("Please Wait....");
         progressDialog.setCancelable(false);
         progressDialog.show();

        firebaseAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                get_set();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                progressDialog.dismiss();
                Toast.makeText(PayX.this, "try again", Toast.LENGTH_SHORT).show();
            }
        });





//
    }

    private void get_set() {
        firebaseFirestore.collection("PayX_Api_Buyer").document(getIntent().getExtras().getString("api_key")).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                DocumentSnapshot documentSnapshot=task.getResult();

                String upi_link=documentSnapshot.getString("upi_account");
                Log.e("afdsfasdfsf",upi_link);

                key_amt=documentSnapshot.getString("key");

                if(Integer.parseInt(key_amt)<=0){

                    Log.e("afdsfasdfsf","setp_1");
                    progressDialog.dismiss();
                    finish();
                    Toast.makeText(PayX.this, "Trangection Failed Your Trangection Key Have Been Finshed ", Toast.LENGTH_SHORT).show();

                }else {
                    Log.e("afdsfasdfsf","setp_2");
                    progressDialog.dismiss();

                    result="else";
                    String upiLink = upi_link+getIntent().getExtras().getString("amount");
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(upiLink));
                    startActivityForResult(intent, UPI_TRANSACTION_REQUEST_CODE);
                }

//


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                progressDialog.dismiss();

            }
        });


    }

    public static void TranctionX(Activity activity,String amount,String  Api_Key)
    {


        Intent intent = new Intent(activity, PayX.class);
        intent.putExtra("amount",amount);
        intent.putExtra("api_key", Api_Key);
        activity.startActivityForResult(intent, UPI_TRANSACTION_REQUEST_CODE);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("asdsafsafd",data.toString());

        if (requestCode == UPI_TRANSACTION_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Bundle extras = data.getExtras();
                if (extras != null) {
                    // Inspect extras for relevant data
                    // For example, you might check for specific keys or values in extras
                    Log.d("UPI_TRANSACTION", "Transaction successful. Extras: " + extras.toString());
                    String status = data.getStringExtra("response");
                    Log.e("asdsfsfdsds",status);

                  if(status.contains("Failed")){

                      result="not done";
                      finish();

                  }else {
                      Log.e("asdsfsfdsds","here_1");
                      act_ater_act();



                  }




                }else{

                    result="not done";
                    finish();
                }
//                payXCall.on_susess("done");
                // Handle successful transaction
                // You may need to parse data from the Intent returned here if any
            } else if (resultCode == RESULT_CANCELED) {
//                payXCall.on_failed("failed");
                result="not done";
                finish();

            } else {
                result="not done";
                finish();
//                payXCall.on_failed("failed");
            }
        }
    }

    private void act_ater_act() {

        int key_int=(Integer.parseInt(key_amt)-1);
        firebaseFirestore.collection("PayX_Api_Buyer")
                .document(api_key_st).update("key", String.valueOf(key_int)).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Log.e("asdsfsfdsds","here_2");

                        result="done";
                        finish();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                       Log.e("adsfasdfsf",e.toString());
                    }
                });

    }

}
