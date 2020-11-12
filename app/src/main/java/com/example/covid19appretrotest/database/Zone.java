package com.example.covid19appretrotest.database;

import android.content.ContentValues;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import android.content.ContentValues;
import android.provider.BaseColumns;

import com.google.gson.annotations.SerializedName;

/**
 * Creates the table for SQLite
 * <p>
 * The general idea, due to the live updates, is to download a new version for each day
 * every time it is called. It is only when the date has changed that it will stop updating
 * that row.
 * <p>
 * NOTE: the reference above is for room to name the table in a more conventional way
 */

@Entity(tableName = "zone_stats_table")
public class Zone {

    public static final String GLOBAL_ZONENAME = "Global";

    public static final String COLUMN_ZONENAME = "COLUMN_ZONENAME";
    public static final String COLUMN_timeStamp = "COLUMN_timeStamp";
    public static final String COLUMN_date = "COLUMN_date";

    public Zone(){
    }

    public Zone(String date, String zoneName, long timeStamp,
                int totalCases, int totalDeaths, int totalRecovered, int totalActive,
                int todayCases, int todayRecovered, int todayDeaths,
                long webserviceUpdated, long population, long tests) {
        this.date = date;
        this.zoneName = zoneName;
        this.timeStamp = timeStamp;
        this.totalCases = totalCases;
        this.totalDeaths = totalDeaths;
        this.totalRecovered = totalRecovered;
        this.todayCases = todayCases;
        this.todayRecovered = todayRecovered;
        this.todayDeaths = todayDeaths;
        this.totalActive = totalActive;
        this.webserviceUpdated = webserviceUpdated;
        this.population = population;
        this.tests = tests;
    }

    @NonNull
    public static Zone fromContentValues(ContentValues values) {
        final Zone zone = new Zone();
        if (values != null && values.containsKey(COLUMN_ZONENAME)) {
            zone.zoneName = values.getAsString(COLUMN_ZONENAME);
        }
        if (values != null && values.containsKey(COLUMN_date)) {
            zone.date = values.getAsString(COLUMN_date);
        }
        if (values != null && values.containsKey(COLUMN_timeStamp)) {
            zone.timeStamp = values.getAsInteger(COLUMN_timeStamp);
        }
        if (values != null && values.containsKey("totalCases")) {
            zone.totalCases = values.getAsInteger("totalCases");
        }
        if (values != null && values.containsKey("COLUMN_totalDeaths")) {
            zone.totalDeaths = values.getAsInteger("COLUMN_totalDeaths");
        }
        if (values != null && values.containsKey("COLUMN_totalRecovered")) {
            zone.totalRecovered = values.getAsInteger("COLUMN_totalRecovered");
        }
        if (values != null && values.containsKey("COLUMN_totalActive")) {
            zone.totalActive = values.getAsInteger("COLUMN_totalActive");
        }
        if (values != null && values.containsKey("COLUMN_todayCases")) {
            zone.todayCases = values.getAsInteger("COLUMN_todayCases");
        }
        if (values != null && values.containsKey("COLUMN_todayRecovered")) {
            zone.todayRecovered = values.getAsInteger("COLUMN_todayRecovered");
        }
        if (values != null && values.containsKey("todayDeaths")) {
            zone.todayDeaths = values.getAsInteger("todayDeaths");
        }
        if (values != null && values.containsKey("COLUMN_webserviceUpdated")) {
            zone.webserviceUpdated = values.getAsLong("COLUMN_webserviceUpdated");
        }
        if (values != null && values.containsKey("COLUMN_population")) {
            zone.population = values.getAsLong("COLUMN_population");
        }
        if (values != null && values.containsKey("COLUMN_tests")) {
            zone.tests = values.getAsLong("COLUMN_tests");
        }

        return zone;
    }

    /**
     * Constructor
     * Param: Every single one here EXCEPT for ID, which is "set" later
     */

    // create the row ID and creates a new one for each new ID
    //@PrimaryKey(autoGenerate = true)
    //private int ID;

    //these 3 are SQLite only, to note WHEN the data was download and HOW OFTEN.
    @ColumnInfo(index = true, name = "COLUMN_date")
    private String date;

    @PrimaryKey
    @NonNull
    @ColumnInfo(index = true, name = "COLUMN_ZONENAME")
    public String zoneName;

    @ColumnInfo(name = "COLUMN_timeStamp")
    private long timeStamp; //for repository refresh logic

    /**
     * all of the following are identical to GlobalModel's variables
     * but instead of "calling" them, to keep Room + Retrofit separate, they are made again
     * As well, I don't include the ones I believe i'll never use
     */
    @ColumnInfo(name = "COLUMN_totalCases")
    private int totalCases;

    @ColumnInfo(name = "COLUMN_totalDeaths")
    private int totalDeaths;

    @ColumnInfo(name = "COLUMN_totalRecovered")
    private int totalRecovered;

    @ColumnInfo(name = "COLUMN_totalActive")
    private int totalActive;

    @ColumnInfo(name = "COLUMN_todayCases")
    private int todayCases;

    @ColumnInfo(name = "COLUMN_todayRecovered")
    private int todayRecovered;

    @ColumnInfo(name = "COLUMN_todayDeaths")
    private int todayDeaths;

    @ColumnInfo(name = "COLUMN_webserviceUpdated")
    private long webserviceUpdated; // aka updated from api json

    @ColumnInfo(name = "COLUMN_population")
    private long population; //use this to calculate any stat/1000 people

    @ColumnInfo(name = "COLUMN_tests")
    private long tests;

    public String getDate() {
        return date;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getZoneName() {
        return zoneName;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public int getTodayRecovered() {
        return todayRecovered;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public int getTotalActive() {
        return totalActive;
    }

    public long getWebserviceUpdated() {
        return webserviceUpdated;
    }

    public long getPopulation() {
        return population;
    }

    public long getTests() {
        return tests;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public void setTotalRecovered(int totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public void setTotalActive(int totalActive) {
        this.totalActive = totalActive;
    }

    public void setTodayCases(int todayCases) {
        this.todayCases = todayCases;
    }

    public void setTodayRecovered(int todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

    public void setTodayDeaths(int todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public void setWebserviceUpdated(long webserviceUpdated) {
        this.webserviceUpdated = webserviceUpdated;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public void setTests(long tests) {
        this.tests = tests;
    }
}
