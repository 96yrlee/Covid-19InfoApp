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

**Webservice API:** [Link to API. ](http://disease.sh/v3/covid-19/) [Link to GitHub. ](https://github.com/disease-sh/API) The webservice API is disease.sh's Covid19 specific API. It provides an HTTP protocol and provides a json output; no authentication key is required. They use a variety of sources, including Worldometer, John Hopkins University, and several countries' own government provided data. There are also other data, but they are not of interest for this project.

**Activities:** The main activity will show today's global data (i.e. total and new cases). Ideally, it will also offer a space to see a certain country/province's data.

**Service(s):** It will handle requesting and downloading the API data via Retrofit, and then perform various data calculation on them (ex. figuring out new cases for a certain date requires subtracting the total for the date and the previous day) before sending the data to the activity that requested it. 
If graphing is possible, they will be done via a service. Another service will also be used to save the data to the database - while Room + LiveData should be async, this will just ensure it is done in the background. While IntentService was taught, it has been depreciated for JobIntentService and so it will be used.

**BroadcastReceiver:** They will be used to start the service - no other actions should be performed. One will listen for a custom event requesting an update, and start the service involving the API. Another will check for for a custom event to save data to the database and start that event.

**ContentProvider:** To provide information to outside of the app, the content provider will be implemented. HOWEVER, room will be the primary database wrapper.


### Further App Details

**Other Android Components / 3rd Party Add-ons:** As already mentioned, Retrofit will be used to handle the API webservice, and Room will be used to abstract the SQLite databse. Plus, LiveData + Viewmodel + ListAdator Room architecture will be used. It is true that, as a result, the services, receivers and content provider are not required - however, they provide some additional benefits, are needed for the course, and content provider has its own unique use. While Room + retrofit may complicate, they are common and useful abstractions and thus will be used.

## Credits & Licensing

The MIT license is used - if you want to use this app or improve it, go ahead.
Credits to disease.sh for the free API, which provides all the data.
