package com.example.covid19appretrotest.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.covid19appretrotest.R;
import com.example.covid19appretrotest.database.*;
import com.example.covid19appretrotest.notification.* ;
import com.example.covid19appretrotest.retrofit.*;
import com.example.covid19appretrotest.viewmodel.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
                        implements TimePickerDialog.OnTimeSetListener {

    private TextView mTextView;
    private MainActivityViewModel mViewModel;
    private RecyclerView recyclerView;

    MainActivityRecyclerAdaptor mAdaptor;

    private ArrayList<String> zoneNameList = new ArrayList<String>(){};

    Button alarmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> zones = new ArrayList<>();

        recyclerView = findViewById(R.id.mainActivityRecyclerView);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        //recyclerView.setHasFixedSize(true); //if the # of items in the list wont change for better performance

        mAdaptor = new MainActivityRecyclerAdaptor();
        recyclerView.setAdapter(mAdaptor);

        setRoom(); // calls upon the viewmodel/livedata

        FloatingActionButton searchButton = findViewById(R.id.floatingButton);
        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                //startActivity(intent);

                startActivityForResult(intent, SearchActivity.SEARCH_REQUEST);
            }
        });

        // swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) { // swipe left or right to delete
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Zone deleteZone = mAdaptor.getZoneAt(viewHolder.getAdapterPosition());

                if(deleteZone.getZoneName() != Zone.GLOBAL_ZONENAME){
                    mViewModel.deleteCountryZone(deleteZone);
                    Toast.makeText(MainActivity.this, "Deleting: " + deleteZone.getZoneName(), Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(MainActivity.this, "Can't delete global", Toast.LENGTH_LONG).show();
                }
            }
        }).attachToRecyclerView(recyclerView);

        //install the onCLick
        mAdaptor.setOnZoneItemClickListener(new MainActivityRecyclerAdaptor.OnZoneItemClickListener() {
            @Override
            public void onItemClick(Zone mZone) {
                Intent intent = new Intent(MainActivity.this, ZoneActivity.class);
                intent.putExtra(ZoneActivity.ZONE_NAME, mZone.getZoneName());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SearchActivity.SEARCH_REQUEST && resultCode == RESULT_OK){
            String countrySelect = data.getStringExtra(SearchActivity.COUNTRY_NAME);

            setListOfZonesToLoad(countrySelect);
            mViewModel.insertCountry(countrySelect, getDateText());
        }
        else{
            Toast.makeText(MainActivity.this, "Country wasn't added", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * The following 3 methods are for the menu button setting the notification alarm
     * and setting the time picker fragment
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if( item.getItemId() == R.id.alarm_bar_button ) {
            //show time picker
            DialogFragment timepicker = new TimePickerFragment();
            timepicker.show(getSupportFragmentManager(), "Time Picker");

            //onTimeSet will get the hour and min and use it
            return true;
        }
        if( item.getItemId() == R.id.cancel_alarm_button){
            //cancel the alarm
            Toast.makeText(MainActivity.this, "Notification Alarm Canceled", Toast.LENGTH_LONG ).show();
            NotificationReceiver.cancelAlarm(MainActivity.this);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        // get the hour and min form timepicker
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        NotificationReceiver.startNotificationAlarm(MainActivity.this, c);
    }
    /**
     * END NOTIFICATION ALARM METHODS
     */

    private void setRoom() {
        //use this if the implementations get out of wack again
        // mViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(MainActivityViewModel.class);

        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        setListOfZonesToLoad(Zone.GLOBAL_ZONENAME);
        setListOfZonesToLoad("canada");

        mViewModel.getTaggedZones( getDateText() ).observe(this, new Observer<List<Zone>>() {
            @Override
            public void onChanged(List<Zone> mZoneList) {
                //update recycler view

                Toast.makeText(MainActivity.this, "Room has updated the card", Toast.LENGTH_SHORT).show();
                mAdaptor.submitList(mZoneList);

            }
        });
    }

    public String getDateText() {
        // the number format for storing in the database "2011-12-03" format
        DateTimeFormatter dbFormatter = DateTimeFormatter.ISO_LOCAL_DATE;


        String todayDate = LocalDate.now() // get the current date
                .format(dbFormatter); //and convert it into a string

        return todayDate;
    }

    private void setListOfZonesToLoad(String zoneName) {
        if(zoneName.toLowerCase() == Zone.GLOBAL_ZONENAME.toLowerCase()){
            zoneNameList.add(Zone.GLOBAL_ZONENAME);
        }

        zoneNameList.add(zoneName);

    }
}