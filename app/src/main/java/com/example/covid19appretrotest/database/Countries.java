package com.example.covid19appretrotest.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "countries_list_table")
public class Countries {
    public Countries(String country, String flagURL, int ISOid, String iso2, String iso3,
                     int totalCases, int totalDeaths, int totalRecovered, int totalActive,
                     int todayCases, int todayRecovered, int todayDeaths,
                     long webserviceUpdated, long population, long tests) {
        this.ISOid = ISOid;
        this.country = country;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.flagURL = flagURL;
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

    /**
     * Constructor
     * Param: Every single one here EXCEPT for ID, which is "set" later
     */

    // create the row ID and creates a new one for each new ID
    @PrimaryKey(autoGenerate = true)
    private int ID;

    /**
     * all of the following are identical to countryModel's variables
     * but instead of "calling" them, to keep Room + Retrofit separate, they are made again
     * As well, I don't include the ones I believe i'll never use
     */
    private String country;
    private int totalCases;
    private int totalDeaths;
    private int totalRecovered;
    private int totalActive;

    private int todayCases;
    private int todayRecovered;
    private int todayDeaths;

    private long webserviceUpdated, // aka updated from api json
            population, //use this to calculate any stat/1000 people
            tests;

    // the following come the country info inner class
    private int ISOid;
    private String iso2, iso3;
    private String flagURL;

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
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

    public String getCountry() {
        return country;
    }

    public int getISOid() {
        return ISOid;
    }

    public String getIso2() {
        return iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public String getFlagURL() {
        return flagURL;
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

    public void setCountry(String country) {
        this.country = country;
    }

    public void setISOid(int ISOid) {
        this.ISOid = ISOid;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public void setFlagURL(String flagURL) {
        this.flagURL = flagURL;
    }
}
