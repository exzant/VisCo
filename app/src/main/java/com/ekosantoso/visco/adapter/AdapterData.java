package com.ekosantoso.visco.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ekosantoso.visco.MainActivity;
import com.ekosantoso.visco.R;
import com.ekosantoso.visco.ShowData;
import com.ekosantoso.visco.model.DataModel;
import com.ekosantoso.visco.model.ResponsModel;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by Eko PS on 10/26/2017.
 */

public class AdapterData extends RecyclerView.Adapter <AdapterData.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    public  AdapterData (Context ctx, List<DataModel> mList) {
        this.ctx = ctx;
        this.mList = mList;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list,parent,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        DataModel dm = mList.get(position);
        holder.kd_cus.setText(dm.getKd_cus());
        holder.nm_cus.setText(dm.getNm_cus());
        holder.al_cus.setText(dm.getAl_cus());
        holder.dm = dm;

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView kd_cus, nm_cus, al_cus;
        DataModel dm;

        public HolderData(View v){
            super(v);
            kd_cus = (TextView) v.findViewById(R.id.tvKdCus);
            nm_cus = (TextView) v.findViewById(R.id.tvNmCus);
            al_cus = (TextView) v.findViewById(R.id.tvAlCus);
            v.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent goInput = new Intent(ctx, ShowData.class);
                    goInput.putExtra("kd_cus", dm.getKd_cus());
                    goInput.putExtra("nm_cus", dm.getNm_cus());
                    goInput.putExtra("al_cus", dm.getAl_cus());

                    ctx.startActivity(goInput);
                }
            });
        }
    }

}
