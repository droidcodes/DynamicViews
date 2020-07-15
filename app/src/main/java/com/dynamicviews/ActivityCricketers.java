package com.dynamicviews;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActivityCricketers extends AppCompatActivity {

    RecyclerView recyclerCricketers;
    ArrayList<Cricketer> cricketersList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricketers);

        recyclerCricketers = findViewById(R.id.recycler_cricketers);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerCricketers.setLayoutManager(layoutManager);

        cricketersList = (ArrayList<Cricketer>) getIntent().getExtras().getSerializable("list");

        recyclerCricketers.setAdapter(new CricketerAdapter(cricketersList));

    }
}
