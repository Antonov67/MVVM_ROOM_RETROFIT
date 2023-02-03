package com.example.mvvm_room_retrofit.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {UserEntity.class, OrdersEntity.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class DataBaseHelper extends RoomDatabase {
    public static final String DATABASE_NAME = "db";

    public abstract DaoInt daoInt();
}
