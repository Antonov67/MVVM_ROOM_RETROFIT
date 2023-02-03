package com.example.mvvm_room_retrofit.response;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoetryResponse implements PoetryResponseInt{

    MutableLiveData<List<RandomPoem>> poems = new MutableLiveData<>();
    MutableLiveData<Integer> myInt = new MutableLiveData<>();

    @Override
    public LiveData<List<RandomPoem>> getPoems() {
        PoetryDBService service = RetrofitConnection.getInstance().getRetrofit().create(PoetryDBService.class);
        Call<List<RandomPoem>> call = service.getRandomPoem();
        call.enqueue(new Callback<List<RandomPoem>>() {
            @Override
            public void onResponse(Call<List<RandomPoem>> call, Response<List<RandomPoem>> response) {
                poems.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<RandomPoem>> call, Throwable t) {

            }
        });

        return poems;
    }


    @Override
    public LiveData<Integer> getInt() {
        myInt.setValue(777);
        return myInt;
    }
}
