package com.example.kalkulator_sederhana;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText editbil1, editbil2;
    private TextView textHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editbil1=(EditText) findViewById(R.id.editTextBil1);
        editbil2=(EditText) findViewById(R.id.editTextBil2);
        textHasil=(TextView) findViewById(R.id.textViewHasil);
    }
    public void tambah(View v){
        float bil1, bil2, hasil;

        bil1=Float.parseFloat(editbil1.getText().toString());
        bil2=Float.parseFloat(editbil2.getText().toString());
        hasil=bil1+bil2;

        textHasil.setText(bil1+"+"+bil2+" = "+hasil);
    }
    public void kurang(View v){
        float bil1, bil2, hasil;

        bil1=Float.parseFloat(editbil1.getText().toString());
        bil2=Float.parseFloat(editbil2.getText().toString());
        hasil=bil1-bil2;

        textHasil.setText(bil1+"-"+bil2+" = "+hasil);
    }
    public void bagi(View v){
        float bil1, bil2, hasil;

        bil1=Float.parseFloat(editbil1.getText().toString());
        bil2=Float.parseFloat(editbil2.getText().toString());
        hasil=bil1/bil2;

        textHasil.setText(bil1+"/"+bil2+" = "+hasil);
    }
    public void kali(View v){
        float bil1, bil2, hasil;

        bil1=Float.parseFloat(editbil1.getText().toString());
        bil2=Float.parseFloat(editbil2.getText().toString());
        hasil=bil1*bil2;

        textHasil.setText(bil1+"*"+bil2+" = "+hasil);
    }
}