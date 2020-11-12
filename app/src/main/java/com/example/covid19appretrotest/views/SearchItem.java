package com.example.covid19appretrotest.views;

public class SearchItem {

    private String imageURL;
    private String countryText;
    private String subtitleText;
/*
    public SearchItem(String imageURL, String countryText, String subtitleText) {
        this.imageURL = imageURL;
        this.countryText = countryText;
        this.subtitleText = subtitleText;
    }
 */

    public SearchItem(String countryText, String subtitleText) {
        this.countryText = countryText;
        this.subtitleText = subtitleText;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCountryText() {
        return countryText;
    }

    public void setCountryText(String countryText) {
        this.countryText = countryText;
    }

    public String getSubtitleText() {
        return subtitleText;
    }

    public void setSubtitleText(String subtitleText) {
        this.subtitleText = subtitleText;
    }
}
