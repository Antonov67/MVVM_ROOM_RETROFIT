package com.example.mvvm_room_retrofit.dao;

import androidx.room.Embedded;
import androidx.room.Relation;

public class OrdersWithUserEntity {
    @Embedded
    public OrdersEntity ordersEntity;
    @Relation(
            parentColumn = "user_id",
            entityColumn = "id"
    )
    public UserEntity userEntity;
}
