package com.example.project_5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageButton chicagoButton;
    ImageButton nyButton;
    ImageButton storeOrderBtn;
    ImageButton currOrderBtn;
    public static Order currentOrder = new Order();
    public static int counter = 0;
    public static StoreOrder storeOrder = new StoreOrder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chicagoButton = findViewById(R.id.chicagoButton);
        nyButton = findViewById(R.id.nyButton);
        storeOrderBtn = findViewById(R.id.storeOrderButton);
        currOrderBtn = findViewById(R.id.currentOrderButton);

        chicagoButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChicagoStyleActivity.class);
            startActivity(intent);
        });

        nyButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NYStyleActivity.class);
            startActivity(intent);
        });

        storeOrderBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StoreOrderActivity.class);
            startActivity(intent);
        });

        currOrderBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CurrentOrderActivity.class);
            startActivity(intent);
        });

    }
}