package com.example.mvvm_room_retrofit.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.Date;
import java.util.List;

@Dao
public interface DaoInt {

    @Query("SELECT * FROM 'users'")
    LiveData<List<UserEntity>> getAllUsers();

    @Query("SELECT * FROM 'orders'")
    LiveData<List<OrdersEntity>> getAllOrders();

    @Transaction
    @Query("SELECT * FROM 'orders'")
    LiveData<List<OrdersWithUserEntity>> getAllOrdersWithUsers();

    @Transaction
    @Query("SELECT * FROM 'orders' WHERE user_id = :id")
    LiveData<List<OrdersWithUserEntity>> getAllOrdersWithUsers(int id);

    @Insert
    void insertUser(List<UserEntity> data);

    @Insert
    long insertOneUser(UserEntity data);

    @Insert
    void insertOrder(List<OrdersEntity> data);

    @Delete
    void deleteUser(List<UserEntity> data);

    @Delete
    void deleteOrder(List<OrdersEntity> data);

    @Query("SELECT * FROM 'orders' WHERE user_id = :id")
    LiveData<List<OrdersEntity>> getAllOrdersByUser(int id);

    @Query("SELECT * FROM 'orders' WHERE date BETWEEN :date1 and :date2")
    LiveData<List<OrdersEntity>> getAllOrdersByPeriod(Date date1, Date date2);

}
