package com.example.jadwalkuliahapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Regis extends AppCompatActivity {
    EditText ednama, edemail, edpass, edrepass;
    Button btnregis;
    String nama, email, password, repass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);


        btnregis = findViewById(R.id.btnRegister);
        ednama = findViewById(R.id.edNama);
        edemail = findViewById(R.id.edemail);
        edpass = findViewById(R.id.edPassword);
        edrepass = findViewById(R.id.edRepassword);

        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nama = ednama.getText().toString();
                email = edemail.getText().toString();
                password = edpass.getText().toString();
                repass = edrepass.getText().toString();

                if (ednama.getText().toString().length()==0){
                    ednama.setError("Nama Wajib Di isi!!");
                }
                if (edemail.getText().toString().length()==0){
                    edemail.setError("Email Wajib Di isi!!");
                }
                if (edpass.getText().toString().length()==0){
                    edpass.setError("Password Wajib Di isi!!");
                }
                if (edrepass.getText().toString().length()==0){
                    edrepass.setError("Re-Password Wajib Di isi!!!");
                }
                else {
                    if (edpass.getText().toString().equals(edrepass.getText().toString())) {
                        Toast t = Toast.makeText(getApplicationContext(),
                                "Pendaftaran Berhasil...", Toast.LENGTH_LONG);
                        t.show();

                        Bundle b = new Bundle();
                        b.putString("a", nama.trim());

                        Intent i = new Intent(getApplicationContext(), JadwalKuliah.class);
                        i.putExtras(b);
                        startActivity(i);
                    } else {
                        Snackbar.make(view, "Password dan Re Password harus sama!!!",
                                Snackbar.LENGTH_LONG).show();
                    }

                }
            }

        });
    }
}
