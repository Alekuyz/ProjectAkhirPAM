package com.example.jadwalkuliahapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jadwalkuliahapp.adapter.UserAdapter;
import com.example.jadwalkuliahapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Senin extends AppCompatActivity {


    private RecyclerView recyclerView;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private List<User> list = new ArrayList<>();
    private UserAdapter UserAdapter;
    private ProgressDialog progressDialog;
    private FloatingActionButton flo;
    private Button btt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senin);
        recyclerView = findViewById(R.id.recycler_view);
        flo = findViewById(R.id.tambah);
        btt = findViewById(R.id.kembali);

        btt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(getApplicationContext(), JadwalKuliah.class);
                startActivity(o);
            }
        });

        flo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Addsenin.class);
                startActivity(i);
            }
        });

        progressDialog = new ProgressDialog(Senin.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil Data...");
        UserAdapter = new UserAdapter(getApplicationContext(), list);
        UserAdapter.setDialog(new UserAdapter.Dialog() {



            @Override
            public void onClick(int pos) {
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(Senin.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(getApplicationContext(), Addsenin.class);
                                intent.putExtra("id", list.get(pos).getId());
                                intent.putExtra("matkul", list.get(pos).getTvmatkul());
                                intent.putExtra("jamkul", list.get(pos).getTvjamkul());
                                startActivity(intent);
                                break;
                            case 1:
                                deleteData(list.get(pos).getId());
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(UserAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }

    private void getData(){
        progressDialog.show();
        db.collection("user")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                User user = new User(document.getString("matkul"), document.getString("jamkul"));
                                user.setId(document.getId());
                                list.add(user);
                            }
                            UserAdapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(getApplicationContext(), "Data gagal di ambil!", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    private void deleteData(String id) {
        progressDialog.show();
        db.collection("user").document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Data gagal di hapus!", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                        getData();
                    }
                });
    }
}