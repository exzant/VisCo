package com.ekosantoso.visco.api;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Eko PS on 10/26/2017.
 */

public class RetroServer {
    private static final String base_url = "http://ekosantoso.com/visco/";

    private static Retrofit retrofit;

    public static Retrofit getClient(){
        if(retrofit == null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100,TimeUnit.SECONDS).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();
        }

        return retrofit;
    }
}
