package com.example.jadwalkuliahapp;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Addrabu extends AppCompatActivity {

    private EditText EdtMatkul,EdtJamkul;
    private FloatingActionButton flo;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsenin);
        EdtMatkul = findViewById(R.id.EdtMatkul);
        EdtJamkul = findViewById(R.id.EdtJamkul);
        flo = findViewById(R.id.save1);

        progressDialog = new ProgressDialog(Addrabu.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Menyimpan...");

        flo.setOnClickListener(v->{
            if (EdtMatkul.getText().length() > 0 && EdtJamkul.getText().length() > 0 ){

                saveData(EdtMatkul.getText().toString(), EdtJamkul.getText().toString());
            }else {
                Toast.makeText(this, "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        if (intent!= null){
            id = intent.getStringExtra("id");
            EdtMatkul.setText(intent.getStringExtra("matkul"));
            EdtJamkul.setText(intent.getStringExtra("jamkul"));
        }
    }

    private void saveData(String matkul, String jamkul) {
        Map<String, Object> user = new HashMap<>();
        user.put("matkul", matkul);
        user.put("jamkul", jamkul);

        progressDialog.show();

        if (id != null){

            db.collection("rabu").document(id)
                    .set(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Addrabu.this, "Berhasil", Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Toast.makeText(Addrabu.this, "Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else {

            db.collection("rabu")
                    .add(user)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(Addrabu.this, "Berhasil di simpan", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Addrabu.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
        }
    }}