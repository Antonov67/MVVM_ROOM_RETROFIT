package com.example.mvvm_room_retrofit.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mvvm_room_retrofit.dao.DaoInt;
import com.example.mvvm_room_retrofit.dao.DatabaseManager;
import com.example.mvvm_room_retrofit.dao.OrdersEntity;
import com.example.mvvm_room_retrofit.dao.OrdersWithUserEntity;
import com.example.mvvm_room_retrofit.dao.UserEntity;
import com.example.mvvm_room_retrofit.response.PoetryResponse;
import com.example.mvvm_room_retrofit.response.PoetryResponseInt;
import com.example.mvvm_room_retrofit.response.RandomPoem;

import java.util.List;

public class Repository {
    private DatabaseManager databaseManager;
    private DaoInt dao;
    private PoetryResponseInt poetryResponse;

    public Repository(Context context) {
        databaseManager = DatabaseManager.getInstance(context);
        dao = databaseManager.getDaoInt();
        poetryResponse = new PoetryResponse();
    }

    public LiveData<List<UserEntity>> getAllUsers(){
        return dao.getAllUsers();
    }

    public long insertOneUser(UserEntity data){
        return dao.insertOneUser(data);
    }

    public LiveData<List<OrdersWithUserEntity>> getAllOrdersWithUsers(){
        return dao.getAllOrdersWithUsers();
    }

    public LiveData<List<OrdersWithUserEntity>> getAllOrdersWithUsers(int id){
        return dao.getAllOrdersWithUsers(id);
    }

    public void insertOrder(List<OrdersEntity> data){
        dao.insertOrder(data);
    }

   public LiveData<List<RandomPoem>> getPoems(){
       return poetryResponse.getPoems();
    }


}
