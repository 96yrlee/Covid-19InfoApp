package com.example.covid19appretrotest.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.covid19appretrotest.CovidRepository;
import com.example.covid19appretrotest.database.CovidDatabase;
import com.example.covid19appretrotest.database.Zone;

public class ZoneActivityViewModel extends AndroidViewModel {
    private CovidRepository repository;
    private LiveData<Zone> cZone;

    public ZoneActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new CovidRepository(application);
    }

    public LiveData<Zone> getZone(String countryName){
        cZone = repository.getSpecificZone(countryName);
        return cZone;
    }
}
