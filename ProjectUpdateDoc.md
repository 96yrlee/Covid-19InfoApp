# Project Update & Current Status
Here I will document what has been created/done.

## Figuring Out LocalDate and LocalTime| 2020 - 11 - 04 and 05 / Wed. and Thurs Nov. 3rd/4th, 2020
I got sucked into the elections, so I didn't work on this as much as I should have.

* Figuring out how to use Java.time library took longer,
  * Desugared Java 8+ to use the library in sdk <26
  * learned about DateTimeFormatter and duration
* first, check whether it is the first call of the day
  * if YES (new day), delete the entire database
  * call webservice and insert the data into it with the appriate date and time columns
  * to not go through the waste of a time check, even tho it will auto pass, just return here
  * if NO, no action, check time as below
* to check is zone has been updated in the last 2 hr period, will store long timeStamp in database, which records the time in minutes AKA 7am = 7hr * 60mins = 420
  * method will get current time, subtract 2 hrs, then convert to minutes -> this is the maximum update time point, everything older or smaller is >2hr ago
  * query will search for both the zoneName and that the stored timestamp is "younger" or larger than the "maximum update time point"
  * if query pulls null, or boolean false, then delete the row with the right name + date, pull API webservice and insert the data OR do update (no deletes needed) 
  * if it exists, then skip
* if both date and time is skipped AKA SAME DAY and LAST UPDATE <2HRS AGO
  * a normal room query for data is fine
* if either date or time is checked and thus webservice is called
  * since they both updated the room, a normal query is fine


## Integrate Retrofit and Room| 2020 - 11 - 03 / Tues. Nov. 3rd, 2020
* Will try to make an api service class through the ``` public static <S> S createService(Class<S> serviceClass) ``` way
  * NOTE: I discovered that you can't reqassign a variable inside the file class, you need to create another method/class to do anything.
  * so the OkHTTP logging interceptor, I made it into a class that returns an OkClient thingy
  * IT WORKS
  
I need to figure out the logic for getting webservice data, showing, and saving
* Global is always shown; Countries can be added to be seen via a search
* Currently I use One recyclerView and One List to show all items
  * if I keep using one RV and list, then I use getAllDatedZones(String date) to get a list of zones with the current date
  * but how to I see if I get all the requested zones as well?
* THEREFORE I need to send to this getZoneObjects method a list of zones names + the wanted date
* so this method is called, asking for the list of zonesNames + date
* for (each [] of zoneNamesList)
  *  I check the database with getSpecificDatedZones(String zoneName, String date), zoneNAme is pulled from []zonenameList
  *  If (database doesn't have the zone AND date)
  *  THEN call retrofit to get the data (get all or country), and save(insert) the data in room
  *  If it does have the speified zone +date, then it skips the IF{} stuff calling retrofit and goes to the NEXT FOR LOOP
*  After the for loop ensure all zones + date exist, query room to get the zone object via getAllDatedZones(String date)
* ultimatly, I will return a list of zone objects to view
* NOTE: I will also incorporate jetpack's example of a refresh rate. Instead of one day, I will use 1 hr.

I also need to find out how to save the date automatically

## Testing Room Pt.2| 2020 - 11 - 02 / Mon Nov. 2nd, 2020
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
* got it to click and start a new activity ok
* NEXT somehow send over the data needed to create a card, but that also requires viewmodel etc so AFTER SEARCH

Update to LiveAdaptor!
* Codelabs updated to it in their github, but their tutorial + coding flow uses adaptor.dkjsahdak
  * coding flow changed added a way to refactor to liveAdaptor! so I will too
* BENEFIT: no longer have to use notifyDataHasChanged
* CON: it is tied directly to a single entity
  * multiple adaptors?
  * change globalEntiry to be more generic + thus hold global, countries + states all in one table? BUT if I decide to use JH's for the historical I'll STILL have to make a new table just for modularity
* REFACTORED: MADE GLOBAL INTO MORE GENERIC ZONE + REMOVED PERMILLION INTERNAL COLUMNS also refactored the retrofit related files
  * if i ever get to getting historical data from JH, I'll deal with the seperate adaptor then

Now to see how to put Retrofit + room together
* all examples say to make a retrofit builder class in another file
* then when creating the retrofit API interface, to call apon this builder
  * this should be done inside repository/viewholder?
  
LOGIC for daily, for each zone (country or global):
1) call retrofit get for today's stats
2) after getting the response data, save it into database
3) ensure viewholder pulls from database


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
