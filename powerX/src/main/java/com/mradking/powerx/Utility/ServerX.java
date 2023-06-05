package com.mradking.powerx.Utility;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
public class ServerX {


    public  void post (String url,Map json_in_map,
                             server_result_call server_result_call ) {
        try {

            MediaType JSON = MediaType.parse("application/json; charset=utf-8");



            Gson gson = new GsonBuilder().create();
            String jsonData = gson.toJson(json_in_map);


            RequestBody requestBody = RequestBody.create(JSON, jsonData);

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String responseBody = response.body().string();
                        Log.e("respo", responseBody);

                        Handler mainHandler = new Handler(Looper.getMainLooper());
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {

                            server_result_call.on_susess(responseBody);

                            }
                        });

//

                    }
                }

                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("translate_api", "Request failed: " + e.getMessage());
                    Handler mainHandler = new Handler(Looper.getMainLooper());
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {

                            server_result_call.on_failed(e.toString());

                        }
                    });
                }
            });

        } catch (Exception e) {

            Handler mainHandler = new Handler(Looper.getMainLooper());
            mainHandler.post(new Runnable() {
                @Override
                public void run() {

                    Log.e("translate_api", e.getMessage());
                    server_result_call.on_failed(e.toString());

                }
            });


        }
    }


    public  void delete (String url,Map json_in_map,
                      server_result_call server_result_call ) {
        try {

            MediaType JSON = MediaType.parse("application/json; charset=utf-8");



            Gson gson = new GsonBuilder().create();
            String jsonData = gson.toJson(json_in_map);


            RequestBody requestBody = RequestBody.create(JSON, jsonData);

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .delete(requestBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String responseBody = response.body().string();
                        Log.e("respo", responseBody);

                        Handler mainHandler = new Handler(Looper.getMainLooper());
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {

                                server_result_call.on_susess(responseBody);

                            }
                        });

//

                    }
                }

                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("translate_api", "Request failed: " + e.getMessage());
                    Handler mainHandler = new Handler(Looper.getMainLooper());
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {

                            server_result_call.on_failed(e.toString());

                        }
                    });
                }
            });

        } catch (Exception e) {

            Handler mainHandler = new Handler(Looper.getMainLooper());
            mainHandler.post(new Runnable() {
                @Override
                public void run() {

                    Log.e("translate_api", e.getMessage());
                    server_result_call.on_failed(e.toString());

                }
            });


        }
    }


    public  void put (String url,Map json_in_map,
                       server_result_call server_result_call ) {
        try {

            MediaType JSON = MediaType.parse("application/json; charset=utf-8");



            Gson gson = new GsonBuilder().create();
            String jsonData = gson.toJson(json_in_map);


            RequestBody requestBody = RequestBody.create(JSON, jsonData);

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .put(requestBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String responseBody = response.body().string();
                        Log.e("respo", responseBody);

                        Handler mainHandler = new Handler(Looper.getMainLooper());
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {

                                server_result_call.on_susess(responseBody);

                            }
                        });

//

                    }
                }

                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("translate_api", "Request failed: " + e.getMessage());
                    Handler mainHandler = new Handler(Looper.getMainLooper());
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {

                            server_result_call.on_failed(e.toString());

                        }
                    });
                }
            });

        } catch (Exception e) {

            Handler mainHandler = new Handler(Looper.getMainLooper());
            mainHandler.post(new Runnable() {
                @Override
                public void run() {

                    Log.e("translate_api", e.getMessage());
                    server_result_call.on_failed(e.toString());

                }
            });


        }
    }


    public  void get (String url,
                      server_result_call server_result_call ) {
        try {


            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String responseBody = response.body().string();
                        Log.e("respo", responseBody);

                        Handler mainHandler = new Handler(Looper.getMainLooper());
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {

                                server_result_call.on_susess(responseBody);

                            }
                        });

//

                    }
                }

                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("translate_api", "Request failed: " + e.getMessage());
                    Handler mainHandler = new Handler(Looper.getMainLooper());
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {

                            server_result_call.on_failed(e.toString());

                        }
                    });
                }
            });

        } catch (Exception e) {

            Handler mainHandler = new Handler(Looper.getMainLooper());
            mainHandler.post(new Runnable() {
                @Override
                public void run() {

                    Log.e("translate_api", e.getMessage());
                    server_result_call.on_failed(e.toString());

                }
            });


        }
    }

}
