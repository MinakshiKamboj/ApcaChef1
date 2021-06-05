package com.apcachef.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.apcachef.R;
import com.apcachef.adapter.MultiViewTypeAdapter;
import com.apcachef.base.BaseActivity;
import com.apcachef.model.home.ChefsItem;

import java.util.ArrayList;

public class OurInstructorsActivity extends BaseActivity {
    ImageView img_back;
    TextView tv_title;
    MultiViewTypeAdapter multiViewTypeAdapter;
    RecyclerView recycler_instructors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_instructors);

        img_back = findViewById(R.id.img_back);
        tv_title = findViewById(R.id.tv_title);
        recycler_instructors = findViewById(R.id.recycler_instructors);
        tv_title.setText("Our Instructors");
        img_back.setOnClickListener(view -> finish());

        Bundle bundle = getIntent().getExtras();
        ArrayList<ChefsItem> chefsList = bundle.getParcelableArrayList("chefsList");

        recycler_instructors = findViewById(R.id.recycler_instructors);
        multiViewTypeAdapter = new MultiViewTypeAdapter(this, chefsList, true);
        recycler_instructors.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recycler_instructors.setAdapter(multiViewTypeAdapter);

    }
}