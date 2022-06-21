package com.example.jadwalkuliahapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JadwalKuliah extends AppCompatActivity {

    Button Bmon,Btue,Bwed,Bthu,Bfri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_kuliah);

        Bmon = findViewById(R.id.Bsenin);
        Btue = findViewById(R.id.BSelasa);
        Bwed = findViewById(R.id.Brabu);
        Bthu = findViewById(R.id.Bkamis);
        Bfri = findViewById(R.id.Bjumat);



        Bmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), Senin.class);
                startActivity(i);
            }

        });
        Btue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent h = new Intent(getApplicationContext(), Selasa.class);
                startActivity(h);
            }

    });
        Bwed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent p = new Intent(getApplicationContext(), Rabu.class);
                startActivity(p);
            }

        });
        Bthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent g = new Intent(getApplicationContext(), Kamis.class);
                startActivity(g);
            }

        });
        Bfri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent t = new Intent(getApplicationContext(), Jumat.class);
                startActivity(t);
            }

        });
}}