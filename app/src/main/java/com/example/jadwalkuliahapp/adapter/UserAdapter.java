package com.example.jadwalkuliahapp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jadwalkuliahapp.R;
import com.example.jadwalkuliahapp.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private List<User> list;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int pos);
    }

    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }

    public UserAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowdata, parent, false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {
        holder.matkul.setText(list.get(position).getTvmatkul());
        holder.jamkul.setText(list.get(position).getTvjamkul());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView matkul, jamkul;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            matkul = itemView.findViewById(R.id.tvmatkul);
            jamkul = itemView.findViewById(R.id.tvjamkul);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(dialog != null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }}