package com.example.covid19appretrotest.retrofit;

import com.google.gson.annotations.SerializedName;

public class CountryModel {

    private String country;

    @SerializedName("cases")
    private int totalCases;

    @SerializedName("deaths")
    private int totalDeaths;

    @SerializedName("recovered")
    private int totalRecovered;

    private int todayCases,
            todayRecovered,
            todayDeaths;

    private int active;

    @SerializedName("updated")
    private long APIupdated;

    private long population;
    private long tests;

    public int getActive() {
        return active;
    }
    public void setActive(int active) {
        this.active = active;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalCases() {
        return totalCases;
    }
    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }
    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }
    public void setTotalRecovered(int totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public int getTodayCases() {
        return todayCases;
    }
    public void setTodayCases(int todayCases) {
        this.todayCases = todayCases;
    }

    public int getTodayRecovered() {
        return todayRecovered;
    }
    public void setTodayRecovered(int todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }
    public void setTodayDeaths(int todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public long getAPIupdated() {        return APIupdated;    }
    public void setAPIupdated(long APIupdated) {        this.APIupdated = APIupdated;    }

    public long getPopulation() {        return population;    }
    public void setPopulation(long population) {        this.population = population;    }

    public long getTests() {        return tests;    }
    public void setTests(long tests) {        this.tests = tests;    }

    //don't care about these rn
    private float casesPerOneMillion,
            deathsPerOneMillion,
            activePerOneMillion,
            recoveredPerOneMillion,
            criticalPerOneMillion;

    private double testsPerOneMillion;

    private int critical,
            oneCasePerPeople,
            oneDeathPerPeople,
            oneTestPerPeople,
            affectedCountries;

    private String continent;
    private CountryInfo countryInfo;

    public CountryInfo getCountryInfo() {
        return countryInfo;
    }

    public void setCountryInfo(CountryInfo countryInfo) {
        this.countryInfo = countryInfo;
    }

    //nested json object
    public class CountryInfo{
        private int _id;
        private String iso2, iso3;

        @SerializedName("flag")
        private String flagURL;

        @SerializedName("long")
        private float longitude;

        private float lat;
        public int get_id() {
            return _id;
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
    }


}

