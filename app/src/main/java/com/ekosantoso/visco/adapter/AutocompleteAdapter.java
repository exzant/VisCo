package com.ekosantoso.visco.adapter;

/**
 * Created by Eko PS on 10/28/2017.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.ekosantoso.visco.R;
import com.ekosantoso.visco.model.Daerah;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

public class AutocompleteAdapter extends ArrayAdapter implements Filterable {
    private ArrayList aDaerah;
    private String URL_ALAMAT = "http://apiunggun.ga/desa.php?filter=";

    public AutocompleteAdapter(Context context, int resource) {
        super(context, resource);
        aDaerah = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return aDaerah.size();
    }

    @Override
    public Daerah getItem(int position) {
        return (Daerah) aDaerah.get(position);
    }

    @Override
    public Filter getFilter() {
        Filter myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint != null){
                    try{
                        //get data from the web
                        String term = constraint.toString();
                        aDaerah = (ArrayList) new DownloadAlamat().execute(term).get();
                    }catch (Exception e){
                        Log.d("HUS","EXCEPTION "+e);
                    }
                    filterResults.values = aDaerah;
                    filterResults.count = aDaerah.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if(results != null && results.count > 0){
                    notifyDataSetChanged();
                }else{
                    notifyDataSetInvalidated();
                }
            }
        };

        return myFilter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.auto_complete_layout,parent,false);

        //get Alamat
        Daerah daerah = (Daerah) aDaerah.get(position);

        TextView etDaerah = (TextView) view.findViewById(R.id.etDaerah);

        etDaerah.setText(daerah.getNama());

        return view;
    }

    //download aDaerah list
    private class DownloadAlamat extends AsyncTask<String, String, ArrayList> {

        @Override
        protected ArrayList doInBackground(String... params) {
            try {
                //Create a new daerah SEARCH url Ex "search.php?term=india"
                /*String NEW_URL = URL_ALAMAT + URLEncoder.encode(params[0],"UTF-8");
                Log.d("HUS", "JSON RESPONSE URL " + NEW_URL);

                URL url = new URL(NEW_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                InputStream in = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null){
                    sb.append(line).append("\n");
                }

                //parse JSON and store it in the list
                String jsonString =  sb.toString();

                JSONObject jsonObject = new JSONObject(jsonString);*/
                JSONObject jsonObject = new JSONObject(loadJSONfromAssets(getContext()));

                JSONArray jsonArray = jsonObject.getJSONArray("result");
                ArrayList daerahList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jo = jsonArray.getJSONObject(i);
                    //store the daerah name
                    if(jo.getString("daerah").matches("(?i).*" + params[0].replaceAll("[^a-zA-Z0-9]", ".*") + ".*")) {
                        Daerah daerah = new Daerah();
                        daerah.setNama(jo.getString("daerah"));
                        daerahList.add(daerah);
                    }
                }
                //return the daerahList
                return daerahList;

            } catch (Exception e) {
                Log.d("HUS", "EXCEPTION " + e);
                return null;
            }
        }
    }

    public static String loadJSONfromAssets(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("daerah.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}