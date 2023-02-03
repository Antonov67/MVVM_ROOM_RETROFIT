package com.example.mvvm_room_retrofit.response;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface PoetryResponseInt {
    LiveData<List<RandomPoem>> getPoems();

    LiveData<Integer> getInt();
}
