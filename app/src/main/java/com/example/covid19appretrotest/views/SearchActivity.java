package com.example.covid19appretrotest.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.covid19appretrotest.R;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    public static final int SEARCH_REQUEST = 1001;
    public static final String COUNTRY_NAME = "com.example.covid19appretrotest.COUNTRY_NAME";

    private RecyclerView mRecyclerView;
    private SearchAdaptor mAdapter;

    private List<SearchItem> searchList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        createSearchList();

        mRecyclerView = findViewById(R.id.search_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.hasFixedSize();

        mAdapter = new SearchAdaptor(searchList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new SearchAdaptor.OnItemClickListener() {
            @Override
            public void onItemClick(int position, List<SearchItem> filteredList) {
                String countryName = filteredList.get(position).getCountryText();
                Toast.makeText(SearchActivity.this, countryName, Toast.LENGTH_LONG).show();

                returnCountrySelected(countryName);
            }
        });

        EditText editText = findViewById(R.id.searchEditText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {         }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //filtering(charSequence);
                //searchCountry(charSequence.toString());
            }
            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void returnCountrySelected(String countryName) {
        Intent data = new Intent();
        data.putExtra(COUNTRY_NAME, countryName);

        setResult(RESULT_OK, data);
        finish();
    }

    private void createSearchList(){
        String[] countries = new CountryListFactory().getCountries(); // stored them all in another class for reading ease
        searchList = new ArrayList<>();

        for(int i = 0; i < countries.length; i++){
            searchList.add( new SearchItem(countries[i].toUpperCase(), countries[i]) );
        }
    }

    public void filter(String text){
        List<SearchItem> filteredList = new ArrayList<>();

        for(SearchItem item : searchList){
            if( item.getCountryText().toLowerCase().contains(text.toLowerCase()) ){
                filteredList.add(item);
            }
        }
        mAdapter.filterList(filteredList);
    }

}