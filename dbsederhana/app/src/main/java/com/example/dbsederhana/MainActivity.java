package com.example.dbsederhana;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dbsederhana.R;


public class MainActivity extends AppCompatActivity {
    private EditText nrp, nama;
    private Button simpan, ambildata, update, hapus;
    private SQLiteDatabase dbku;
    private SQLiteOpenHelper Opendb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nrp = (EditText) findViewById(R.id.nrp);
        nama = (EditText) findViewById(R.id.nama);
        simpan = (Button) findViewById(R.id.Simpan);
        ambildata = (Button) findViewById(R.id.ambildata);
        update = (Button) findViewById(R.id.update);
        hapus = (Button) findViewById(R.id.hapus);
        simpan.setOnClickListener(operasi);
        ambildata.setOnClickListener(operasi);
        update.setOnClickListener(operasi);
        hapus.setOnClickListener(operasi);

        Opendb = new SQLiteOpenHelper(this, "db.sql", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                // Create table if not exists
                db.execSQL("CREATE TABLE IF NOT EXISTS mhs(nrp TEXT, nama TEXT);");
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                // Handle database upgrades here
            }
        };
        dbku = Opendb.getWritableDatabase();
    }

    @Override
    protected void onStop() {
        dbku.close();
        Opendb.close();
        super.onStop();
    }

    View.OnClickListener operasi = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.Simpan) {
                simpan();
            } else if (v.getId() == R.id.ambildata) {
                ambildata();
            } else if (v.getId() == R.id.update) {
                update();
            } else if (v.getId() == R.id.hapus) {
                hapus();
            }
        }
    };

    private void simpan() {
        ContentValues dataku = new ContentValues();

        dataku.put("nrp", nrp.getText().toString());
        dataku.put("nama", nama.getText().toString());
        try {
            dbku.insert("mhs", null, dataku);
            Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Gagal menyimpan data", Toast.LENGTH_LONG).show();
        }
    }

    private void ambildata() {
        Cursor cur = dbku.rawQuery("SELECT * FROM mhs WHERE nrp=?", new String[]{nrp.getText().toString()});

        if (cur != null && cur.getCount() > 0) {
            Toast.makeText(this, "Data Ditemukan Sejumlah " +
                    cur.getCount(), Toast.LENGTH_LONG).show();

            cur.moveToFirst();
            int namaIndex = cur.getColumnIndex("nama");
            if (namaIndex != -1) {
                nama.setText(cur.getString(namaIndex));
            } else {
                Toast.makeText(this, "Column 'nama' not found", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Data Tidak Ditemukan", Toast.LENGTH_LONG).show();
        }

        if (cur != null) {
            cur.close();
        }
    }

    private void update()
    {
        ContentValues dataku = new ContentValues();

        dataku.put("nrp",nrp.getText().toString());
        dataku.put("nama",nama.getText().toString());
        dbku.update("mhs",dataku,"nrp='"+nrp.getText().toString()+"'",null);
        Toast.makeText(this,"Data Terupdate",Toast.LENGTH_LONG).show();
    }

    private void hapus()
    {
        dbku.delete("mhs","nrp='"+nrp.getText().toString()+"'",null);
        Toast.makeText(this,"Data Terhapus",Toast.LENGTH_LONG).show();
    }

}
