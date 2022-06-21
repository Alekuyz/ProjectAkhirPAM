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

public class Addsenin extends AppCompatActivity {

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

        progressDialog = new ProgressDialog(Addsenin.this);
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

            db.collection("user").document(id)
                    .set(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Addsenin.this, "Berhasil", Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Toast.makeText(Addsenin.this, "Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else {

            db.collection("user")
                    .add(user)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(Addsenin.this, "Berhasil di simpan", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Addsenin.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
        }
    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.save1)
//           Matkul  = EdtMatkul.getText().toString();
//        Jamkul = EdtJamkul.getText().toString();
//
//
//
//
//        if (EdtMatkul.getText().toString().length()==0){
//        }
//        if (EdtJamkul.getText().toString().length()==0){
//
//
//        } else {
//            Toast t = Toast.makeText(getApplicationContext(),
//                    "Data Tersimpan", Toast.LENGTH_LONG);
//            t.show();
//
//            Bundle b = new Bundle();
//
//
//            Intent i = new Intent(getApplicationContext(), Senin.class);
//            i.putExtras(b);
//            startActivity(i);
//        }
//        return super.onOptionsItemSelected(item);
//    }



    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsenin);

        EdtMatkul = findViewById(R.id.EdtMatkul);
        EdtJamkul = findViewById(R.id.EdtJamkul);
        flo = findViewById(R.id.save1);


        Bundle bundle = getIntent().getExtras();

        flo = findViewById(R.id.save1);
        flo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Matkul = EdtMatkul.getText().toString();
                Jamkul = EdtJamkul.getText().toString();


                {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Data Tersimpan", Toast.LENGTH_LONG);
                    t.show();

                    Bundle b = new Bundle();

                    b.putString("c", Matkul.trim());
                    b.putString("d", Jamkul.trim());

                    Intent i = new Intent(getApplicationContext(),Senin.class);
                    i.putExtras(b);
                    startActivity(i);
                }

            }

        });


    }*/
}