package com.example.covid19appretrotest.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * DAO for countries.java
 */
@Dao
public interface CountriesDao {

    @Insert
    void insert(Countries mCountries);

    @Insert
    void insert(List<Countries> countriesList);

    // first call of the day, new date? delete old table
    @Query("DELETE FROM countries_list_table")
    void deleteAll();

    @Query("SELECT * FROM countries_list_table ORDER BY country DESC")
    LiveData<List<Countries>> getAllCountries();

    @Query("SELECT * FROM countries_list_table WHERE country LIKE :search")
    List<Countries> getAllSearch(String search);
}
