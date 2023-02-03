package com.example.mvvm_room_retrofit.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvm_room_retrofit.dao.OrdersEntity;
import com.example.mvvm_room_retrofit.dao.OrdersWithUserEntity;
import com.example.mvvm_room_retrofit.dao.UserEntity;
import com.example.mvvm_room_retrofit.response.RandomPoem;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Repository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<UserEntity>> getAllUsers(){
        return repository.getAllUsers();
    }

    public long insertOneUser(UserEntity data){
        return repository.insertOneUser(data);
    }

    public LiveData<List<OrdersWithUserEntity>> getAllOrdersWithUsers(){
        return repository.getAllOrdersWithUsers();
    }

    public LiveData<List<OrdersWithUserEntity>> getAllOrdersWithUsers(int id){
        return repository.getAllOrdersWithUsers(id);
    }

    public void insertOrder(List<OrdersEntity> data){
        repository.insertOrder(data);
    }

   public LiveData<List<RandomPoem>> getPoems(){
        return repository.getPoems();
    }
}
