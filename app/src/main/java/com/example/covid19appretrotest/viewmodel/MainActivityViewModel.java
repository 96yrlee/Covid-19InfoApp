package com.example.covid19appretrotest.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.covid19appretrotest.CovidRepository;
import com.example.covid19appretrotest.database.*;

import java.util.ArrayList;
import java.util.List;

/**
 * By extending AndroidViewModel, we get a handle to the application context,
 * which we then use to instantiate our RoomDatabase.
 */
public class MainActivityViewModel extends AndroidViewModel {
    private CovidRepository mRepository;

    //from room
    private LiveData<List<Zone>> allZoneStats;
    private LiveData<List<Zone>> taggedZones;

    /**
     * VIEWMODEL CONSTRUCTOR
     */
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mRepository = new CovidRepository(application);
        //allZoneStats = mRepository.getAllZoneAndDays();

    }

    // kinda like a getter
    public LiveData<List<Zone>> getAllZoneStats(){
        return allZoneStats;
    }

    public LiveData<List<Zone>> getTaggedZones( String date){
        taggedZones = mRepository.getTaggedZones(date);
        return taggedZones;
    }

    public void insertCountry(String countryName, String date){

        if( countryName.equals("GLOBAL") ) {
            mRepository.refreshGlobal(date);
        }
        else if( !(countryName.equals("GLOBAL")) ){

            mRepository.insertCountry(countryName, date);
        }
    }

    public void deleteCountryZone(Zone mZone){
        mRepository.deleteZone(mZone.getZoneName());
    }
    public void insert(Zone mZone){
        mRepository.zoneInsert(mZone);
    }


}
