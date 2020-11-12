package com.example.covid19appretrotest.database;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * DAO for Zone.java
 */

@Dao
public interface ZoneDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Zone mZone);

    @Update
    int update(Zone mZone);

    @Delete
    int delete(Zone mZone);

    // first call of the day, new date? delete old table
    @Query("DELETE FROM zone_stats_table")
    void deleteAll();

    // there should be only one row with that zonename, since no data from the past day is saved
    @Query("DELETE FROM zone_stats_table WHERE " + Zone.COLUMN_ZONENAME + " = :zoneName")
    void deleteZone(String zoneName);

    // get everything, global + otehr countries, for all days
    //mostly here for testing
    @Query("SELECT * FROM zone_stats_table ORDER BY " + Zone.COLUMN_ZONENAME + " ASC")
    LiveData< List<Zone> > getAllZonesAndDays();

    // get everything for the stated date, global + other countries
    @Query("SELECT * FROM zone_stats_table WHERE " + Zone.COLUMN_date + " = :date")
    LiveData<List<Zone>> getAllDatedZones(String date);

    // get a specific zone (global or country) at a specific date
    @Query("SELECT * FROM zone_stats_table WHERE " + Zone.COLUMN_ZONENAME + " = :zoneName")
    LiveData<Zone> getSpecificZone(String zoneName);

    // for the purpose of seeing how recent the timeStamp is
    // if recent, it will return the single row
    @Query("SELECT COUNT(*) FROM zone_stats_table " +
            "WHERE " + Zone.COLUMN_ZONENAME + " = :zoneName AND "+ Zone.COLUMN_timeStamp + " > :maxRefreshTime")
    int hasTimedZone(String zoneName, long maxRefreshTime );

    // get everything for the stated date, global + other countries
    @Query("SELECT COUNT(*) FROM zone_stats_table " +
            "WHERE " + Zone.COLUMN_ZONENAME + " = :zoneName AND " + Zone.COLUMN_date + " = :date " +
            "ORDER BY " + Zone.COLUMN_ZONENAME + " DESC LIMIT 1")
    int getZoneFromToday(String date, String zoneName);

    @Query("SELECT COUNT(*) FROM zone_stats_table")
    int getSize();

    @Query("SELECT " + Zone.COLUMN_ZONENAME + " FROM zone_stats_table WHERE " + Zone.COLUMN_ZONENAME + " != :global")
    List<String> getCountryNames(String global);

    //COntent Provider queries

    @Query("SELECT * FROM zone_stats_table")
    Cursor selectAll();

    @Query("SELECT * FROM zone_stats_table WHERE " + Zone.COLUMN_ZONENAME + "= :zoneName ")
    Cursor selectbyName(String zoneName);

    // get a specific zone (global or country) at a specific date
    @Query("SELECT * FROM zone_stats_table WHERE " + Zone.COLUMN_ZONENAME + " = :zoneName LIMIT 1")
    Zone getZone(String zoneName);


}
