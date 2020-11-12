package com.example.covid19appretrotest.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid19appretrotest.R;
import com.example.covid19appretrotest.database.Zone;
import com.example.covid19appretrotest.viewmodel.ZoneActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class ZoneActivity extends AppCompatActivity {

    public static final String ZONE_NAME = "ZONE_NAME_VIA_INTENT";

    private ZoneActivityViewModel viewModel;
    private RecyclerView recyclerView;

   ZoneActivityAdaptor mAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_zone);

        Intent intent = getIntent();
        String zoneName = intent.getStringExtra(ZONE_NAME);

        recyclerView = findViewById(R.id.ZoneActivityRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();

        mAdaptor = new ZoneActivityAdaptor();
        recyclerView.setAdapter(mAdaptor);

        viewModel = new ViewModelProvider(this).get(ZoneActivityViewModel.class);
        viewModel.getZone(zoneName).observe(this, new Observer<Zone>() {
            @Override
            public void onChanged(Zone zone) {
                List<Zone> zoneList = new ArrayList<>();
                zoneList.add(zone);
                mAdaptor.submitList(zoneList);
            }
        });
    }
}