package com.example.covid19appretrotest.views;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19appretrotest.R;
import com.example.covid19appretrotest.database.Countries;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchAdaptor extends RecyclerView.Adapter<SearchAdaptor.StatsHolder> {
    static final String TAG = "SearchAdaptor";

    private List<SearchItem> countriesList = new ArrayList<>();

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position, List<SearchItem> filteredList);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    class StatsHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView textViewCountryName;
        public TextView textViewExtra;

        public StatsHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.countryImageView);
            textViewCountryName = itemView.findViewById(R.id.countryNameTextView);
            textViewExtra = itemView.findViewById(R.id.countryExtraTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position, countriesList);
                        }
                    }
                }
            });
        }
    }

    public SearchAdaptor(List<SearchItem> mCountriesList){
        this.countriesList = mCountriesList;
    }


    /**
     * Where I assign the item card layout to use
     */
    @NonNull
    @Override
    public StatsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.search_card_item, parent, false);
        return new StatsHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StatsHolder holder, int position) {
        SearchItem currentCountry = countriesList.get(position);
        //do this once i can bother to do picasso
        holder.mImageView.setImageResource(R.drawable.ic_baseline_flag);

        holder.textViewCountryName.setText( currentCountry.getCountryText().toUpperCase() );
        //String extraText = "Total Cases: " + getFormattedNums(currentCountry.getCountryText());
        holder.textViewExtra.setText( currentCountry.getSubtitleText() );
    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    public void setCountriesList(List<SearchItem> countriesList){
        this.countriesList = countriesList;
        notifyDataSetChanged();
    }

    private String getFormattedNums(int num){
        return NumberFormat.getNumberInstance(Locale.US).format(num);
    }

    public void filterList(List<SearchItem> filteredList){
        this.countriesList = filteredList;
        notifyDataSetChanged();

        Log.d(TAG, "filterList called: data change has been notified");
    }
}
