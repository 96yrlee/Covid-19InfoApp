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
import com.example.covid19appretrotest.database.Zone;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ZoneActivityAdaptor extends ListAdapter<Zone, ZoneActivityAdaptor.ZoneHolder> {

    static final String TAG = "ZoneActivityAdaptor";

    protected ZoneActivityAdaptor() {
        super(DIFF_CALLBACK);
    }

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

    class ZoneHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewDate;
        private TextView textViewTotals;
        private TextView textViewTodays;
        private TextView textViewExtras;

        public ZoneHolder(View itemView){
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.InfoCardZoneName);
            textViewDate = itemView.findViewById(R.id.InfoCardDate);
            textViewTodays = itemView.findViewById(R.id.DailyStatsText);
            textViewTotals = itemView.findViewById(R.id.TotalStatsText);
            textViewExtras = itemView.findViewById(R.id.InfoCardExtraText);
        }
    }

    @NonNull
    @Override
    public ZoneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.info_card_item, parent, false);

        return new ZoneActivityAdaptor.ZoneHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ZoneHolder holder, int position) {
        //Zone currentZone = getItem(position);
        Zone currentZone = getItem(position);

        String totalsText = createViewStrings("total", currentZone);
        String todaysText = createViewStrings("today", currentZone);
        String extraText = createExtraString(currentZone);

        holder.textViewTitle.setText(currentZone.getZoneName());
        holder.textViewTotals.setText(totalsText);
        holder.textViewTodays.setText(todaysText);
        holder.textViewExtras.setText(extraText);

        // edit date here to human readable
        String humanDate = getHumanDate( currentZone.getDate() );
        holder.textViewDate.setText(humanDate);
    }

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

    private String createExtraString(Zone mZone){
        String out;
        long population = mZone.getPopulation();

        out = "Population: " + getFormattedNums(population ) + "\n" +
              "Total Tests: " + getFormattedNums(mZone.getTests() ) + "\n" +
              "Total Cases/Population: " + getFormattedNums( (double) mZone.getTotalCases() / population);

        return out;
    }

    private String getFormattedNums(int num){
        return NumberFormat.getNumberInstance(Locale.US).format(num);
    }

    private String getFormattedNums(long num){
        return NumberFormat.getNumberInstance(Locale.US).format(num);
    }

    private String getFormattedNums(double num){
        return NumberFormat.getNumberInstance(Locale.US).format(num);
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

}
