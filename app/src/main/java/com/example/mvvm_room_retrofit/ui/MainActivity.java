package com.example.mvvm_room_retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mvvm_room_retrofit.R;
import com.example.mvvm_room_retrofit.dao.OrdersWithUserEntity;
import com.example.mvvm_room_retrofit.response.RandomPoem;
import com.example.mvvm_room_retrofit.viewModel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mvvm";

    MainViewModel mainViewModel;

    Button testButton, addRecordButton, poetryButton;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        testButton = findViewById(R.id.db_button);
        resultText = findViewById(R.id.result_text);
        addRecordButton = findViewById(R.id.add_record_button);
        poetryButton = findViewById(R.id.poetrydb_button);

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Log.d(TAG, "users");
                mainViewModel.getAllOrdersWithUsers().observe(MainActivity.this, new Observer<List<OrdersWithUserEntity>>() {
                    @Override
                    public void onChanged(List<OrdersWithUserEntity> list) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (OrdersWithUserEntity order: list) {
                            stringBuilder.append(order.userEntity.name+ "id: " + order.userEntity.getId() +"userId: " +order.ordersEntity.userId + "\n" + order.ordersEntity.orderInfo +  " " + order.ordersEntity.date);
                            stringBuilder.append("\n\n");
                        }
                        resultText.setText(stringBuilder);
                    }
                });
            }
        });

        addRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddRecordActivity.class));
            }
        });

        poetryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.getPoems().observe(MainActivity.this, new Observer<List<RandomPoem>>() {
                    @Override
                    public void onChanged(List<RandomPoem> list) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (RandomPoem poem : list) {
                            stringBuilder
                                    .append("автор:").append("\n\n")
                                    .append(poem.author)
                                    .append("\n\n")
                                    .append("название:").append("\n\n")
                                    .append(poem.title)
                                    .append("\n\n")
                                    .append("количество строк:")
                                    .append(poem.linecount)
                                    .append("\n\n")
                                    .append("поэма:").append("\n\n")
                                    .append(poem.getPoemText());
                        }
                        resultText.setText(stringBuilder);
                    }
                });
            }
        });


    }
}