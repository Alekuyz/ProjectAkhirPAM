package com.example.jadwalkuliahapp.model;

public class User {
    private String id,tvmatkul,tvjamkul;

    public User(){

    }
    public User(String tvmatkul, String tvjamkul){
        this.tvmatkul = tvmatkul;
        this.tvjamkul = tvjamkul;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getTvmatkul(){
        return tvmatkul;
    }
    public void  setTvmatkul(String tvmatkul){
        this.tvmatkul = tvmatkul;
    }
    public String getTvjamkul(){
        return tvjamkul;
    }
    public void setTvjamkul(String tvjamkul){
        this.tvjamkul = tvjamkul;
    }
}
