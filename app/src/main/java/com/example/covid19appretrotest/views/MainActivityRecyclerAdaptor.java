package com.example.covid19appretrotest.views;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19appretrotest.R;
import com.example.covid19appretrotest.database.*;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * inside <> we pass our holder inner class so recycler knows its the viewholder we want to use
 */
public class MainActivityRecyclerAdaptor extends ListAdapter<Zone, MainActivityRecyclerAdaptor.StatsHolder> {

    static final String TAG = "MainActivityRecyclerAdaptor";

    class StatsHolder extends RecyclerView.ViewHolder{
        // only don't need to set text for the Totals and Today's inner titles
        private TextView textViewTitle;
        private TextView textViewDate;
        private TextView textViewTotals;
        private TextView textViewTodays;


        public StatsHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.InfoCardZoneName);
            textViewDate = itemView.findViewById(R.id.InfoCardDate);
            textViewTodays = itemView.findViewById(R.id.DailyStatsText);
            textViewTotals = itemView.findViewById(R.id.TotalStatsText);

            //onClick stuff
            itemView.setOnClickListener( new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    //that one is clicked and not an invalid card
                    if(mListener !=null && position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    // initialize to avoid any nulls
    //private List<GlobalEntity> globalEntityList = new ArrayList<>();
    // remove for listAdaptor

    // for listadaptor constructor
    private static final DiffUtil.ItemCallback<Zone> DIFF_CALLBACK = new DiffUtil.ItemCallback<Zone>() {
        @Override
        public boolean areItemsTheSame(@NonNull Zone oldItem, @NonNull Zone newItem) {
            // true if they're the same entry
            Log.d("TAG", "areItemsTheSame is called");
            return oldItem.getZoneName() == newItem.getZoneName();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Zone oldItem, @NonNull Zone newItem) {
            // internals look the same? sadly check each one... all 12 of them
            // and to think i used to have MORE
            // anyways, if any of this changed, it know to update via the false
            Log.d("TAG", "areContentsTheSame is called");
            return oldItem.getZoneName().equals(newItem.getZoneName()) &&
                    oldItem.getDate().equals(newItem.getDate()) &&
                    oldItem.getTodayCases() == newItem.getTodayCases() &&
                    oldItem.getTotalCases() == newItem.getTotalCases() &&
                    oldItem.getTodayDeaths() == newItem.getTodayDeaths() &&
                    oldItem.getTotalDeaths() == newItem.getTotalDeaths() &&
                    oldItem.getTodayRecovered() == newItem.getTodayRecovered() &&
                    oldItem.getTotalRecovered() == newItem.getTotalRecovered() &&
                    oldItem.getTotalActive() == newItem.getTotalActive() &&
                    oldItem.getPopulation() == newItem.getPopulation() &&
                    oldItem.getTests() == newItem.getTests() &&
                    oldItem.getWebserviceUpdated() == newItem.getWebserviceUpdated();
        }
    };

    /**
     * CONSTRUCTOR
     */
    public MainActivityRecyclerAdaptor() {
        super(DIFF_CALLBACK);
    }


    @NonNull
    @Override
    public StatsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // parent is the recyclerview, from mainActivity
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.info_card_item, parent, false);

        return new StatsHolder(itemView);
    }

    /**
     * where we get the data from the single globalEntity java objects
     * into the views of the viewholder.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull StatsHolder holder, int position) {
        // ref to the globalentity at this position
        Zone currentZone = getItem(position);

        String totalsText = createViewStrings("total", currentZone);
        String todaysText = createViewStrings("today", currentZone);

        holder.textViewTitle.setText(currentZone.getZoneName());
        holder.textViewTotals.setText(totalsText);
        holder.textViewTodays.setText(todaysText);

        // edit date here to human readable
        String humanDate = getHumanDate( currentZone.getDate() );
        holder.textViewDate.setText(humanDate);
    }

    public Zone getZoneAt(int position){
        return getItem(position);
    }

    public String getHumanDate(String dbDate){
        // date format should be similar to "Tuesday, August 23, 2016"
        DateTimeFormatter humanFormatter = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)
                .withLocale(Locale.getDefault());

        // the number format for storing in the database
        DateTimeFormatter dbFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

        //convert the date and times from db to human
        LocalDate dbToHumanDate = LocalDate.parse(dbDate, dbFormatter); // read the dbDate into a LocalDate object
        return dbToHumanDate.format(humanFormatter); // converts the LocalDate object to a string in good format
    }

    //get Item count is taken cared by listAdaptor

    /**
     * in mainActivity we observe livedata and get a list of globalEntities
     * we pass the list through this method
     * @param globalEntities
     */
    /* remove for listadaptor
    public void setGlobalEntities(List<Zone> mZones){
        this.globalEntityList = mZones;
        notifyDataSetChanged();
    }
    */

    /**
     * To create the texts that holds the stats from the various get methods
     * @param text
     * @return
     */
    private String createViewStrings(String text, Zone mZone) {
        String outText;

        if(text == "today") {
            outText = "Confirmed: " + getFormattedNums( mZone.getTodayCases() ) +
                    "\nRecovered: " + getFormattedNums( mZone.getTodayRecovered() ) +
                    "\nDeaths: " + getFormattedNums( mZone.getTodayDeaths() );
        }
        else if(text == "total") {
            outText = "Confirmed: " + getFormattedNums( mZone.getTotalCases() )+
                    "\nActive: " + getFormattedNums( mZone.getTotalActive() )+
                    "\nRecovered: " + getFormattedNums( mZone.getTotalRecovered() )+
                    "\nDeaths: " + getFormattedNums( mZone.getTotalDeaths() );
        }
        else{
            outText = "weird error occurred making these strings";
        }
        return outText;
    }

    private String getFormattedNums(int num){
        return NumberFormat.getNumberInstance(Locale.US).format(num);
    }

    /**
     *  TO INTEGRATE THE ONCLICK LISTENER ON THE ITEM CARDS
     *  the only purpose is to open another activity
     *  but we also need the data inside that, so we pass the object
     */

    //for onItemClicks
    private OnZoneItemClickListener mListener;

    public interface OnZoneItemClickListener {
        void onItemClick(Zone mZone);
    }

    // use listener to forward our position to whatever calls it
    public  void setOnZoneItemClickListener( OnZoneItemClickListener listener ){
        mListener = listener;
    }
}
