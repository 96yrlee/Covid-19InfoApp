# Project Update & Current Status
Here I will document what has been created/done.

## Future work as of | 2020 - 11 - 11 / Wed. Nov. 11th, 2020

* Add unit and auto tests
  * it's important to learn how! QA is a thing!
* Clean UI
* Add more features mentioned before
  * retrofit/room linked search activity
  * offer past data queries
  * show trends + graphs
* check over content provider

## ViewModel, Repository, Search Activity, and finish | 2020 - 11 - 09 & 10 / Mon. 9th and Tuesday 10th, Nov. 2020

I can't recall what I did on monday and what for tuesday.

* I cleaned up my repository method. My refresh user is now refresh global and refresh country, so it does global first (to always have it) then go through all countries already in the database and add them. 

Search Activity:
* I tried to query retrofit, add all to another room table, and try to connect that with a EditText+recycler search live update as you text. However, I could not figure out connecting the ViewModel and adaptor to the activity so that it doesn't use live data. 
* My idea is that I would call retrofit once a day, and fill the room. Then i would query the room to get all the country names + total cases.
* However, the editext search methods requires that you have the OG list of object and a new list of objects created each time the user changes the EditText. 
* I couldn't figure out how to query room, to get that list of countries and then create a new list live.
* Also, doing the retrofit call was immensely slow. 230+ countries and all their data is not small.

So, I scapped using room, and just made a pre-loaded class of all countries + their iso IDs
* the acitivty would call this call to create the list of names, then use it as the base to editText live filter and create a new list to show on the recyclerView

New issue, the position would refer to the old list when clicked, and thus never actually give what the user searched and clicked
* I fixed this via the adaptor. Had to give it the new list instead for the onClick method to get the proper object and thus name

Finally, I changed the activity to return a result, and MainActivity to call this search for startActivityForResult.
* Work perfectly, return the data I wanted.

I then created the insert country method
* I used viewmodel, which itself called the repo to insert a zone with the country name. This involed a retrofit call to get the data, then insert into Room.
* Had some wonkiness with inserting multiples, due to the LiveAdaptor and playing aorund with ZoneName or ID for checking same, but I stuck with ZoneName as the primarykey.
* Also had weirdness with it not staying when I destory the activity, same fix as above^

Next, added swipe to delete. Similar as above, Viewmodel -> repo calling deleteZone on Dao.

Next, filling out ZoneActivity
* near identicle to main, but without bells and whistels of alarm, search, etc
* just a recycler view item list, but the item_Card had some xtra info (i.e. population and test numbers)

I also remembered, to add int eh manifest, that these activities were children to Main, so back buttons were added to their Main bar.

Finally, I added a content provider. Instead of ulling from SQLDatabseHelper and contract, it pulls from the Room libraries (dao + entity classes) to do its methods. It was hellish, since I used CountryZone as the primary key and not a num of anysort
* I eventually figured, that the uri to my room would likely use my countryZone to append the uri of the table, so I found and made a method to add my countryZone name to the end of the uri.
* With that method, I also had to figure out how to get the zone name and zone for query, delete and update, but eventually I got it all. 
* However, I'm not entirely sure if it works.
* I also had to manually append each variable in the zone entity class to note its column name for the provider, and create it's special constructor. It was very painful.

In the end, my app works! It only provides current stats, and does no data analysis or offer past info, but it works good!

My only issue is how it does animations and updates the UI. I suspect I may need to change the layout file from constraint to something else. I also need to clean up a few toasts.

## Merging Room + Retrofit in Repository| 2020 - 11 - 08 / Sun. Nov. 8th, 2020
* ok, found out it didn't update my viewmodel
  * after hours of grief, it was my liveAdaptor DIFF callback. I had it compare getID's. but since I delete as insurance... so now i use zoneName
  * also, DAo. update doesn't work with it... so delete + insert it is!
* gonna try to get countries added in
  * relized my booleans only apply as long as there is just one... so a new country won't be in sync. tho, with both update and country using a delete + insert i shouldn't care. but this also brings up whether i should do a new day at all, if refresh is good enough... then again, refresh is also time base and doesn't consider time so nvrmind
  * just wish i could put in another method askjdhskadj
* Eventually, I changed my DIFF method in my LiveAdaptor. I used ID to identify same items. I changed that to use country/zone name instead. I then completeply removed zone ID from the Room db methods. I also removed checking for new day, and delets+insert to update query. This fixed my viewmodel refresh/live upate issue.

## Merging Room + Retrofit in Repository| 2020 - 11 - 07 / Sat. Nov. 7th, 2020
* succesfully create the new day and need to update booleans
* failed to get viewmodel to update
  * eventually, I painfully learned that, for some reason, I cannot make inner methods to clean up the code
  * when I tried to make the retrofit call it's own method
  * then do an insert or update to it,
  * for some reason, the webservice didn't fire off correctly
  * So I first noticed my booleans weren't working, so I removed one. Then I got null reference on the zone from my inner class. I move it into the main refresh method and it works???
  * trying booleans again:
  * I now notice my booleans refered to the Dao and not my inner executer threaded ones, so gonna test all this again.
  * ok, i noticed that my boolean pulls were all LiveData<zone> which apparently means its always NOT null. So I made a seperate date checker query to return a zone and switched the time checker too.
 * BOOLEANS FINALLY WORK
   * tested recentlyUpdated boolean and if bloc by changing the minusHours(2) to minusSeconds(5)
 
 Minute UI Changes:
 * reformat all nums to have commas
 * alarm notification 
   * changed to be a nav bar button
   * added a timepicker so it can be set

## RIP my week 3 marks| 2020 - 11 - 06 / Friday Nov. 6th, 2020

I forgot to check my marks and that cost me. Markers were both harsh and wrong, so I didn't get passing marks.
Needed to add unit test and espresso test - I barely understand them, beyond the simpliest. ViewMode, Room, Retrofit? beyond me.
So I mainly tested to make sure they all created themselves corrected, and did a few click tests for the mainActivity.
This time I also made a note that Retrofit was ok'd by the prof as a HTTP webservice handler we can use and get marked fully on.

Hopefully someone reviews my stuff...

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
