# Covid-19InfoApp
Android App project for Coursera Capstone. Provides updated Covid-19 data from webservice API disease.sh

## Introduction

### Course Info
Coursera Certificate "Android App Development" ends with a capstone project. It requires using at least one activity, one service, one broadcast receiver, one content provider, interact with at least one remotely hosted webservice over the network via HTTP, and at least 2 interface screens.

### App Basic Idea
This app will provide with a simple data tracker for the Covid-19 crisis. It will provide given data (i.e. confirmed, recovered, dead) for the world, certain countries and the state/provinces of certain countries. The user will be able to save one country/province they wish to see automatically.

At first, only the updated daily total and daily new cases will be provided for each, and this data can be saved to the phone to be view again. Ideally, the app will also provide all past total and new cases, which again can be saved to the phone. The next goal is to provide graphs to visual show trends of these data points. 


## App Details

### Coursera Capstone Requirements

**Webservice API:** 
[Link to API. ](http://disease.sh/v3/covid-19/) [Link to GitHub. ](https://github.com/disease-sh/API) 
The webservice API is disease.sh's Covid19 specific API. It provides an HTTP protocol and provides a json output; no authentication key is required. They use a variety of sources, including Worldometer, John Hopkins University, and several countries' own government provided data. There are also other data, but they are not of interest for this project.

**Activities:** The main activity will show today's global data (i.e. total and new cases); Ideally, it will also offer a space to see a certain country/province's data. Global/country boxes should be clickable and lead to the 3rd activity to show more options/details.

The second activity let you search a specific country or global, and lead to the 3rd activity. 

The last (3rd) activity will show the details of a specific category you searched or clicked from main; categories being the global data, a country's data or a province/state's data. The country form of this activity, if state/province data is avalible, should show the summaries of each after the main data and be clickable to refresh the 3rd activity and show that specific state/province. There should be option to search for the total & daily case count for a specific date and to search any saved/past searched data. Here, total & new cases for the day but also the past 7 days, past 30, and since the beginnin should ideally be shown in graphs - otherwise omit.


**Service(s):** ~~It will handle requesting, downloading, and saving the data via API/Retrofit or the the database/Room, and then perform various data calculation on them (ex. figuring out new cases for a certain date requires subtracting the total for the date and the previous day) before sending the data to the activity or service that requested it.~~

~~If graphing is possible, they will be done via a service. Another service will also be used to save the data to the database - while Room + LiveData should be async, this will just ensure it is done in the background.~~

While IntentService was taught, it has been depreciated for JobIntentService which will be used.

EDIT: v02 - this will be used for a notification/alarm to open the app

**BroadcastReceiver:** They will be used to start the service ~~no other actions should be performed. One will listen for a custom event requesting data, and start the service involving the API.~~

EDIT: v02 - this will be used for a notification/alarm to open the app. more specifically, to start the service that will notify + to create the alarm

**ContentProvider:** To provide information to outside of the app, the content provider will be implemented. HOWEVER, room will be the primary database wrapper. The columns are:
- date
- country
- province/state
- confirmed_ttl
- deaths_ttl
- recovered_ttl
- confirmed_new
- deaths_new
- recovered_new

### Further App Details

**Other Android Components / 3rd Party Add-ons:** 
As already mentioned, Retrofit will be used to handle the API webservice, and Room will be used to abstract the SQLite databse. Plus, LiveData + Viewmodel + ListAdator Room architecture will be used. 

It is true that, as a result, the services, receivers and content provider are not required - however, they provide some additional benefits, are needed for the course, and content provider has its own unique use. While Room + retrofit may complicate/add work, they are common and useful abstractions and thus will be used for learning.

**Background details:**

All data called from the API will be saved to the database. This information should be made available to view and should be called instead of the API if it's already saved.

~~The exact details on how the services and Breceivers will be used is still shaky. So that detail may be changed.~~ EDIT: It has changed, see above


## Credits & Licensing

The MIT license is used - if you want to use this app or improve it, go ahead.
Credits to disease.sh for the free API, which provides the webservice, and to John Hopkins and WHO for the actually data, and the api's other sources.

Note: Google and Apple both forbid individually made covid-19 apps, and rightfully so - profitteerin, misinformation, etc are legit worries. Best to leave that to official Orgs.
So, if for some reason you actually want to use this, you'll need to get android studio to build and upload the app.
