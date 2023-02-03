package com.example.mvvm_room_retrofit.dao;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;

public class DatabaseManager {

    private DataBaseHelper db;
    private static DatabaseManager instance;

    private DatabaseManager(Context context){
        db = Room.databaseBuilder(context,
                DataBaseHelper.class,DataBaseHelper.DATABASE_NAME)
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {

                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                initData(context);
                            }
                        });
                    }
                })
                .build();
    }

    public static DatabaseManager getInstance(Context context){
        if (instance == null){
            instance = new DatabaseManager(context.getApplicationContext());
        }
        return instance;
    }

    public DaoInt getDaoInt(){
        return db.daoInt();
    }

    private void initData(Context context){
        List<UserEntity> users = new ArrayList<>();
        UserEntity user = new UserEntity();

        user.name = "User1";
        user.pswrd = "123";
        users.add(user);

        user = new UserEntity();
        user.name = "User2";
        user.pswrd = "456";
        users.add(user);

        user = new UserEntity();
        user.name = "User3";
        user.pswrd = "789";
        users.add(user);

        DatabaseManager.getInstance(context).getDaoInt().insertUser(users);

        List<OrdersEntity> orders = new ArrayList<>();

        OrdersEntity order = new OrdersEntity();
        order.orderInfo = "info 1 user1";
        order.userId = 1;
        order.date = dateFromString("2023-01-31 11:08");
        orders.add(order);

        order = new OrdersEntity();
        order.orderInfo = "info 2 user 1";
        order.userId = 1;
        order.date = dateFromString("2023-01-31 10:08");
        orders.add(order);

        order = new OrdersEntity();
        order.orderInfo = "info 1 user 2";
        order.userId = 2;
        order.date = dateFromString("2022-01-31 10:08");
        orders.add(order);

        DatabaseManager.getInstance(context).getDaoInt().insertOrder(orders);


    }

    private Date dateFromString(String val){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        try {
            return simpleDateFormat.parse(val);
        }catch (ParseException e){

        }
        return null;
    }
}
