package com.ekosantoso.visco;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ekosantoso.visco.adapter.AutocompleteAdapter;

import com.ekosantoso.visco.model.DataModel;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ShowData extends AppCompatActivity {
    EditText kd_cus, nm_cus, nm_pasar, lok_pasar, al_cus, no, rt, rw, nm_pemilik, no_hp;
    String key, tp_lok;
    RadioGroup rg_Pasar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DataModel dm;
    AutoCompleteTextView et_Daerah;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Customer");
        key = databaseReference.push().getKey();

        getSupportActionBar().setTitle("Identitas Customer");

        kd_cus = (EditText) findViewById(R.id.et_KdCus);
        nm_cus = (EditText) findViewById(R.id.et_NmCus);
        nm_pasar = (EditText) findViewById(R.id.et_NmPasar);
        lok_pasar = (EditText) findViewById(R.id.et_LokPasar);
        al_cus = (EditText) findViewById(R.id.et_Jalan);
        no = (EditText) findViewById(R.id.et_No);
        rt = (EditText) findViewById(R.id.et_RT);
        rw = (EditText) findViewById(R.id.et_RW);
        nm_pemilik = (EditText) findViewById(R.id.et_NmPemilik);
        no_hp = (EditText) findViewById(R.id.et_NmHPTelp);
        rg_Pasar = (RadioGroup) findViewById(R.id.rg_Pasar);

        final AutoCompleteTextView CariAlamat = (AutoCompleteTextView) findViewById(R.id.et_Daerah);
        final AutocompleteAdapter adapterAlamat = new AutocompleteAdapter(this,android.R.layout.simple_dropdown_item_1line);
        CariAlamat.setAdapter(adapterAlamat);

        //when autocomplete is clicked
        CariAlamat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String getAlamat = adapterAlamat.getItem(position).getNama();
                CariAlamat.setText(getAlamat);
            }
        });

        findViewById(R.id.btn_Simpan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioButtonID = rg_Pasar.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioButtonID);
                tp_lok = selectedRadioButton.getText().toString();

                dm = new DataModel(key,
                        kd_cus.getText().toString().trim(),
                        nm_cus.getText().toString().trim(),
                        tp_lok,
                        nm_pasar.getText().toString().trim(),
                        lok_pasar.getText().toString().trim(),
                        al_cus.getText().toString().trim(),
                        no.getText().toString().trim(),
                        rt.getText().toString().trim(),
                        rw.getText().toString().trim(),
                        CariAlamat.getText().toString().trim(),
                        nm_pemilik.getText().toString().trim(),
                        no_hp.getText().toString().trim()
                )
                                ;
                databaseReference.child(dm.getKey()).setValue(dm);
                Toast.makeText(getApplicationContext(), tp_lok, Toast.LENGTH_SHORT).show();
            }
        });


        Intent data = getIntent();
        final String iddata = data.getStringExtra("kd_cus");
        if(iddata != null) {
            kd_cus.setText(data.getStringExtra("kd_cus"));
            nm_cus.setText(data.getStringExtra("nm_cus"));
            al_cus.setText(data.getStringExtra("al_cus"));
        }
    }

    public void onRadioButtonClicked(View v) {
        RadioButton rb1 = (RadioButton) findViewById(R.id.rb_nonpasar);
        RadioButton rb2 = (RadioButton) findViewById(R.id.rb_pasar);
        EditText et_NmPasar = (EditText) findViewById(R.id.et_NmPasar);
        EditText et_LosPasar = (EditText) findViewById(R.id.et_LokPasar);
        boolean  checked = ((RadioButton) v).isChecked();
        switch(v.getId()){
            case R.id.rb_nonpasar:
                if(checked)
                    et_NmPasar.setVisibility(View.INVISIBLE);
                et_LosPasar.setVisibility(View.INVISIBLE);
                break;

            case R.id.rb_pasar:
                if(checked)
                    et_NmPasar.setVisibility(View.VISIBLE);
                et_LosPasar.setVisibility(View.VISIBLE);
                break;
        }
    }
}
