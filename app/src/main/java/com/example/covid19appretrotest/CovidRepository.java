package com.example.covid19appretrotest;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.covid19appretrotest.database.*;
import com.example.covid19appretrotest.retrofit.ApiService;
import com.example.covid19appretrotest.retrofit.CountryModel;
import com.example.covid19appretrotest.retrofit.CovidApi;
import com.example.covid19appretrotest.retrofit.GlobalModel;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidRepository {

    static final String TAG = "CovidRepository";
    static final String GLOBAL_ZONE_NAME = Zone.GLOBAL_ZONENAME; // global

    public CovidRepository(Application application){
        CovidDatabase covidDatabase = CovidDatabase.getInstance(application);
        mZoneDao = covidDatabase.mZoneDao();
        mCountryDao = covidDatabase.mCountryDao();
        covidApi = ApiService.createApi(CovidApi.class); //create retrofit class
//        countryNameList = mZoneDao.getCountryNames(Zone.GLOBAL_ZONENAME);
    }

    /**
     * Room variables
     */
    private ZoneDao mZoneDao;
    private LiveData<Zone> zoneTodayData;
    private LiveData<List<Zone>> allZoneAndDaysData;

    private CountriesDao mCountryDao;
    private LiveData<List<Countries>> allCountriesList;
    private List<String> countryNameList;

    /**
     * reporsitory variables + inner methods
     */
    CovidApi covidApi;

    public LiveData<List<Zone>> getTaggedZones(String date){
        Log.d("TAG", "getTaggedZones is called");
        refreshGlobal(date);
        refreshCountries(date);
        return getAllZoneAndDays();
    }

    public void insertCountry(String country, String date){
        CovidDatabase.databaseWriteExecutor.execute( () -> {

            Call<CountryModel> countryCall = covidApi.getCountryStats(country);

            countryCall.enqueue(new Callback<CountryModel>() {
                @Override
                public void onResponse(Call<CountryModel> call, Response<CountryModel> response) {
                    Log.d(TAG, "retrofit COUNTRY call from insertCountry ");

                    //fill the retrofit global model with data
                    CountryModel apiCountry = response.body();

                    //fill the database entity with data + insert into database
                    zoneInsert(fillZoneFromCountryAPI(apiCountry, date));
                }

                @Override
                public void onFailure(Call<CountryModel> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });
        });
    }

    public void refreshGlobal(String date){
        CovidDatabase.databaseWriteExecutor.execute( () -> {
            //for loop if I ever get to adding multiple countries

            Log.d("TAG", "refreshGlobal called");

            String globalName = Zone.GLOBAL_ZONENAME;

            int recentlyUpdated = mZoneDao.hasTimedZone(globalName, getMaxRefreshPoint());

            if(recentlyUpdated == 0){
                Log.d("TAG", "GLOBAL + RECENT UPDATE: UPDATE WITH NEW DATA FROM NETWORK");

                Call<GlobalModel> globalCall = covidApi.getGlobalStats(); //GET call for global route

                globalCall.enqueue(new Callback<GlobalModel>() { //async
                    @Override
                    public void onResponse(Call<GlobalModel> call, Response<GlobalModel> response) {
                        if (!response.isSuccessful()) {
                            // why fail basically
                            Log.d(TAG, "onResponse: Code: " + response.code());
                            return;
                        }
                        Log.d(TAG, "retrofit call NEW UPDATE ");

                        //fill the retrofit global model with data
                        GlobalModel apiGlobal = response.body();

                        //fill the database entity with data + update into database
                        zoneInsert(fillZoneFromAPI(apiGlobal, date));
                    }

                    @Override
                    public void onFailure(Call<GlobalModel> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });
            }
        });// end executor
    }//END refreshGlobal

    private void refreshCountries(String date){
        CovidDatabase.databaseWriteExecutor.execute( () -> {

            List<String> mCountryList = mZoneDao.getCountryNames(Zone.GLOBAL_ZONENAME);

            for( int i = 0; i < mCountryList.size(); i++){

                String country = mCountryList.get(i);

                int recentlyUpdated = mZoneDao.hasTimedZone(country, getMaxRefreshPoint());

                if(recentlyUpdated == 0){
                    Log.d("TAG", "COUNTRY: " + country + " RECENT UPDATE: UPDATE WITH NEW DATA FROM NETWORK");

                    Call<CountryModel> countryCall = covidApi.getCountryStats(country);

                    countryCall.enqueue(new Callback<CountryModel>() {
                        @Override
                        public void onResponse(Call<CountryModel> call, Response<CountryModel> response) {
                            Log.d(TAG, "retrofit COUNTRY call NEWDAY ");

                            //fill the retrofit global model with data
                            CountryModel apiCountry = response.body();

                            //fill the database entity with data + insert into database
                            zoneInsert(fillZoneFromCountryAPI(apiCountry, date));
                        }

                        @Override
                        public void onFailure(Call<CountryModel> call, Throwable t) {
                            Log.d(TAG, "onFailure: " + t.getMessage());
                        }
                    });
                }
            }//for loop end
        });
    }

    private Zone fillZoneFromAPI(GlobalModel apiGlobal, String date){
        Zone globalZone = new Zone(date, GLOBAL_ZONE_NAME, getTimeStamp(),
                apiGlobal.getTotalCases(), apiGlobal.getTotalDeaths(), apiGlobal.getTotalRecovered(), apiGlobal.getActive(),
                apiGlobal.getTodayCases(), apiGlobal.getTodayRecovered(), apiGlobal.getTodayDeaths(),
                apiGlobal.getAPIupdated(), apiGlobal.getPopulation(), apiGlobal.getTests() );
        return globalZone;
    }

    // essentially, if its a country model it fill use this one instead
    private Zone fillZoneFromCountryAPI(CountryModel apiCountry, String date){
        Zone countryZone = new Zone(date, apiCountry.getCountry(), getTimeStamp(),
                apiCountry.getTotalCases(), apiCountry.getTotalDeaths(), apiCountry.getTotalRecovered(), apiCountry.getActive(),
                apiCountry.getTodayCases(), apiCountry.getTodayRecovered(), apiCountry.getTodayDeaths(),
                apiCountry.getAPIupdated(), apiCountry.getPopulation(), apiCountry.getTests() );
        return countryZone;
    }

    private long getMaxRefreshPoint(){
        //gets now and subtracts 2 hours // 1min for testing
        LocalTime refreshTimePoint = LocalTime.now().minusMinutes(1);//.minusHours(2);
        // get the length of time from 00:00 to refresh point in minutes
        return Duration.between(LocalTime.MIN, refreshTimePoint).toMinutes();
    }

    private long getTimeStamp(){
        //get current time as a local time
        LocalTime now = LocalTime.now();
        //convert to minutes, AKA length of time from 00:00 to now in minutes
        return Duration.between(LocalTime.MIN, now).toMinutes();
    }

    /**
     * refreshTaggedZones inner methods
     *
     * CAN'T DO API CALL IN AN INNER METHOD - FOR SOME REASON IT THROWS OFF THE THREAD HANDLER ORDER
     */



    /**
* Room database methods
*/

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Zone>> getAllZoneAndDays(){
        allZoneAndDaysData = mZoneDao.getAllZonesAndDays();
        return allZoneAndDaysData;
    }
    // gets all zones, global + countries, for a date
    public LiveData<List<Zone>> getAllDatedZones(String date){
        return mZoneDao.getAllDatedZones(date);
    }
    // return only 1 row, a specified zone + date
    public LiveData<Zone> getSpecificZone(String zoneName){
        return mZoneDao.getSpecificZone(zoneName);
    }
    // will return an object only if the object was made before the refresh period
    public int isZoneFresh(String zoneName, long maxRefreshTime){
        return mZoneDao.hasTimedZone(zoneName, maxRefreshTime);
    }

    /**
     * codelab rec ver
     * @param mZone
     */
    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void zoneInsert(Zone mZone){
        CovidDatabase.databaseWriteExecutor.execute( () -> {
            Log.d(TAG, "inserting " + mZone.getZoneName());
            mZoneDao.insert(mZone);
        });
    }
    public void zoneUpdate(Zone mZone){
        CovidDatabase.databaseWriteExecutor.execute( () -> {
            Log.d(TAG, "updating " + mZone.getZoneName());
            int count = mZoneDao.update(mZone);
        });
    }

    public void deleteZone(String zoneName){
        CovidDatabase.databaseWriteExecutor.execute( () -> {
            Log.d(TAG, "deleting " + zoneName);
            mZoneDao.deleteZone(zoneName);
        });
    }
    public void deleteAllZones(){
        CovidDatabase.databaseWriteExecutor.execute( () -> {
            Log.d(TAG, "deleting entire database ");
            mZoneDao.deleteAll();
        });
    }

    public List<String> getCountryNames(){
        return countryNameList;
    }
    /**
     * countries methods
     */



}
