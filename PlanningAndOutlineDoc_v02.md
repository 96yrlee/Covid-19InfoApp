# A Document to Plan and Create the App Outline in
A more rough ver of the READ ME, essentially

# Why v02

After studing Android Jetpack and the recommended architecture, I ditched trying to cram a service + broadcast receiver into Repository pattern.
Instead, to fulfill those requirements, I will add a notification.
The content provider will still branch off of room

# General Flow / Idea of How it All Works Together

< insert the flowchart image here later >

# Componenets & Files

## Activities

### MainActivity.java:

**Function**
* Show, for now, just the Global Stats of the day
* call BR asking for global data and to GIVE IT BACK
  * BR sends over to CovidDataService, gets info, does nothing AKA we stay in mainActivity
* now, using a card.class, make the UI and update the view
* do this all in onCreate for now
* **ALSO** create a button to start SearchActivity
  * if using the live list, consider making 2 buttons, one for country and one for province



### SearchActivity.java:

**NOTE:** Worldomoter and John Hopkins have slighly different numbers. 
As well, WM doesn' have states while JH does, but as a result JH doesn't have a country total.
Plus, JH's historical doesn't have today's numbers.

*THEREFORE a province/state search must be done via JH and a country search via WM*

* [Consider using a SearchView widget](https://codinginflow.com/tutorials/android/searchview-recyclerview)
* [This does the same without a serchView, much simplier](https://codinginflow.com/tutorials/android/recyclerview-edittext-search)
  * Instead of the v1 Search wireframe, have 2 different search screens/buttons from main: country or state/province
    * country from WM, state/province from JH
    * text and API queries will differ
  * Search will show cards, and cards are clikcable to SpecifiedZoneAcitivty 
  * Create another cardItem, only showing the country name
    
**v1:** Just call the API, in this case send a broadcast to activate the service for us
* The broadcast receives the name, sends it to the service
  * the service takes it and makes a GET out of it, sends result_complete to BR
  * BR complete notice and now opens SpecifiedZoneActivity
  * if no country matches however, BR will pop toast saying an error occured


### SpecifiedZoneActivity.java:

**Function**
* very similar to main, but has added features so best to keep seperate
  * as note below, will look identical, but its a *for now* thing
* for a country with no state/provinces + a state/province,
  * show only 1 cardItem
  * call from ONLY either worldometer or JH
* for a country wih states/provinces
  * show a country cardItem, and then for all following S/P, using JH

**NOTE ABOUT JH** It has no country specific Get for the today stats, 
thus you get EVERY country no matter what. Need to figure out how to filter that out
* retrofit note, call< List< modelClass > getModelClass
**THEREFORE FOR NOW, NO STATE/PROVINCE DATA, FOCUS IN ON COUNTRIES AND GET THAT WORKING**



### Supporting UI Files:

**InfoCardItem.java**, **InfoCardItem.xml**, **InfoCardAdaptor.java** and **recyclerView**
* MainActivity and SpecifiedZoneActivity both show cards that hold the info, and depending on settings, will need to show more of one.
* These classes/layouts will generate the cards as required by the activites.
  * [Click here for a tutorial I can use](https://codinginflow.com/tutorials/android/simple-recyclerview-java/part-1-layouts-model-class)
  * [This tutorial shows how to do multiple clickable cards within one onClick, which might be applicable](https://codinginflow.com/tutorials/android/onclicklistener-for-multiple-buttons)
* Shares files with the search live idea, but needs different code


### Other Supporting Code:

* [to add a get back button](https://codinginflow.com/tutorials/android/up-button)




## Notification Alarm: Service & Broadcast Rceiver

[for tut with code dets](https://www.youtube.com/watch?v=nl-dheVpt8o)
[for tut with code - another](https://codinginflow.com/tutorials/android/alarmmanager)

Note: Might want to use this to update server data at set times
* daily is gonna get updated, supposed to be every 10mins
* also, rather than get the server data during runtime, better to do it before hand so only Room gets called
  * in this case, get all data for all countries + today, thus will need to include /v3/covid-19/countries GET


### NotificationService.javaextends JobIntentService

* Oreo onwards, best to use JobService or this 
* [this tutorials show the methods I need to implement](https://codinginflow.com/tutorials/android/jobintentservice)
  * Override onHandleWork() and extract the intent, is auto background thread
    * include a check if(isStopped) {return} somewhere so it doesn't kill it messily
  * make an intent factory maker
  * make an 'factory' enqueueWork(context context, Intent work){ enqueueWork(context, servicename.this, jobIdInt, work); }
    * call this to start the service in whatever, probably the BR
 
 **Function**
 * create the notification
 * say someting like "check the current stats
 * do it once a day at a fixed time, for now make it in-built, do a user input time later
 

### NotificationReceiver.java extend BroadcastReceiver
**Function**
* start NotificationService
  * uses alarm manager sent BR event to get BR started
  * BR then starts the service

### NotificationApplication.java
* has the channel djahskdas




## Content Provider
* connect it to room somehow via the example i found



## Room

Room (model): Entity is a table, and DAO communicated w/ SQLite
ViewModel: Connects to Acitivty/Fragment, holds User Interactions
* LiveData: activity observes it and auto updates changes
Repository (model): Manages Room and the API

### CovidRepository.java
Connects to both ROOM and C


## Webserive API / Retrofit

**BASE URL:** https://disease.sh/v3/covid-19/

[tutorial for some dependicies and method names](https://codinginflow.com/tutorials/android/retrofit/part-1-simple-get-request)

** To handle getting data from the API**
* I need a model class for all the return types GSON
  * only worldometer for now, use JH laster when I can get states in easy

### WMGlobalData.class & WMCountryData.class
* this has WMGlobal plus some extra bits that are country only

### diseaseshAPI.java
* it's an interface with all the GET methods
  * only 2 for now, global + country
  
```
@GET("all")
Call< modelClass > getModelClass()

@GET("countries/{country}?strict=true")
Call< modelClass > getModelClass(
  @Path("country") String Country
  @Query("strict") booleanstrict
)
```
### HTTPLogginInterceptor

For testing/debugging purposes, I should get this in.
* get the gradle line
* place it before calling retrofit 

### Within activity/service whatever

```
HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

OkHttpClient okHttpClient = new OkHttpClient.Builder()
  .addInterceptor(loggingInterceptor)
  .build();

Retrofit retrofit = new Retofit.Builder()
  .baseUrl("")
  .addConverterFactory(GsonConverterFactory.create())
  .build();
  
JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(diseaseshAPI.class);

Call<List< modelclass >> call = diseaseshAPI.getPosts();

call.enqueue(new Callback<List<Post>>() {

  onResponse(
    if (!response.isSuccessful()) {}
    
    list< modelClass > modelClass = response.body();
    
    for (modelClass mModelClassFor : modelClass){}
    
    //update the textview if in activity
  )
  onFailure(
    //textViewResult.setText(t.getMessage());
    // send a broadcast saying it failed
  )
  
```


