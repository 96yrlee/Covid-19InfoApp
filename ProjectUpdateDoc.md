# Project Update & Current Status
Here I will document what has been created/done.

## Testing Room Pt.2| 2020 - 11 - 02 / Sun Nov. 2nd, 2020
*  incountered an SQLite issue, where it asked me to increae the ver num each time i updated the emulator
  * but since i'm in testing, I just changed ```android:allowBackup="false"``` in the manifest and uninstalled from emulator
  * might have to see how this works later tho...
* Also forgot to change how the texView and recyclerViews were assigned, to show both my retrofit + database stuff

so far it all works, will try to add stuff into database to see if it works properly...

* Issue with pre population dummy test
  * it didnt prepopulate
  * again, because it happened in onCreate, thus I needed to reinstall
  * FIXED!
  
Let's add onItemClickListener to open up a new activity on the info card


Now to see how to put Retrofit + room together



## Testing Room | 2020 - 11 - 01 / Sun Nov. 1st, 2020
* Creating Room files:
  * entity, Dao, RoomDatabse, Repository, ViewModel + most of recycler
* AsyncTask is depreciated, so I used CodeLab's rec'ed ver
  * create an ExecutorService in RoomDatabase file, which gets called on to perform in the background inside Repository

## Retry Retrofit Seperately | 2020 - 10 - 31 / Sat Oct. 31st, 2020
* Created a seperate Project to solely test compoenets
* Watched tutorial againn and noticed...
  * I missed a user permission for internet
  * didn't fix
* checked google for fatal exception at baseUrl()
  * didn't get my specific
* checked for ``` main Process: com.example.covid19infoandroidapp, PID: 13089 java.lang.BootstrapMethodError: Exception from call site #4 bootstrap method ```
  * got the correct fix!!!
   * needed to add ```  compileOptions { sourceCompatibility JavaVersion.VERSION_1_8   targetCompatibility JavaVersion.VERSION_1_8 }  ``` to my gradle and it worked!
* then I got an error via my call onFailure about wrong data types
  * i forgot to check for decimal and how large some values were... 
  * I needed long for updated, tests and population, floats for all the <>PerMillions, and double for testsPerMillion

TEST PROJECT WORKS! Now:
* I will apply all fixes to the main project and see how it runs.
* if it breaks OR works:
  * I will try to test build room in test project instead.
* UPDATE: api still doesn't work in main project, but no fatal, just a crash and logs don't tell me why.
  * removing the API/repositry/liveview and using the old stuff still works

## Implement Retrofit | 2020 - 10 - 29 / Thursday Oct. 29th, 2020
* added Retrofit to gradle and the files
 * apiservice + repository
 * covidApi interface
 * worldomoeterGlobalModel, worldometerCountryModel
 * MainAcitivity viewmodel
 * tried to call viewmodel to give me the api data...
 
HOWEVER, 
* major issues with retrofit. Can't tell exactly what's going on, but seems to start from the covidApi interface, and the retrofit call.
* will create a seperate project to test retrofit alone before putting into project
 * see what retrofit issues are, if it stems from it or from interacting with other files
 
## Create Android Studio Project | 2020 - 10 - 26 / Monday Oct. 26th, 2020
* created MainActivity
  * InfoCardItem.java, InfoCardItem.xml, InfoCardAdaptor.java and recyclerView
* SpecifiedZoneActivity.java
  * blank, just made to link to the onClick from MainActivity
* NotificationService.java, NotificationReceiver.java, NotificationApplication.java
  * onClick to open app/mainActivity 
  * implemented an alarm, very simple 5sec later: make it a daily or timepicker later
