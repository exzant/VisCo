package com.ekosantoso.visco;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.ServiceWorkerClient;
import android.widget.Toast;

import com.ekosantoso.visco.adapter.AdapterData;
import com.ekosantoso.visco.api.ApiRequest;
import com.ekosantoso.visco.api.RetroServer;
import com.ekosantoso.visco.model.DataModel;
import com.ekosantoso.visco.model.ResponsModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<DataModel> mItems = new ArrayList<>();
    private SwipeRefreshLayout sr;
    ProgressDialog pd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Run();

        sr = (SwipeRefreshLayout) findViewById(R.id.sr);
        sr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sr.setRefreshing(false);
                Run();
            }
        });
    }

    public void Run() {
        pd = new ProgressDialog(MainActivity.this);
        mRecycler = (RecyclerView) findViewById(R.id.rvTemp);
        mManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(mManager);

        pd.setMessage("Loading..");
        pd.setCancelable(false);
        pd.show();

        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponsModel> getdata = api.GetData();
        getdata.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                pd.hide();
                Log.d("Retro", "Response : " + response.body().getKode());
                mItems = response.body().getResult();

                mAdapter = new AdapterData(MainActivity.this, mItems);
                mRecycler.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                pd.hide();
                Log.d("Retro", "Response : Gagal" + t.toString());
                Toast.makeText(MainActivity.this, "Failed Response" + t.toString(), Toast.LENGTH_LONG).show();
                if (checkInternet()){
                    Toast.makeText(MainActivity.this, "Internet Connected", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Can't connect to Internet", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean checkInternet(){
        boolean connectStatus = true;
        ConnectivityManager ConnectionManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()==true ) {
            connectStatus = true;
        }
        else {
            connectStatus = false;
        }
        return connectStatus;
    }
}
