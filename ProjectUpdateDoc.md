# Project Update & Current Status
Here I will document what has been created/done.

## Retry Retrofit Seperately | 2020 - 10 - 30 / Thursday Oct. 30th, 2020

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
