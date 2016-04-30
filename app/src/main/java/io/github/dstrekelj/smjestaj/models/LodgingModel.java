package io.github.dstrekelj.smjestaj.models;

import java.util.List;

/**
 * Created by Domagoj on 29.4.2016..
 */
public class LodgingModel {
    public static String ID = "LodgingModel";

    private String name;
    private String address;
    private int postcode;
    private String city;
    private int rating;
    private String description;
    private List<String> images;

    public LodgingModel() {
    }

    public LodgingModel(String name, String address, int postcode, String city, int rating, String description, List<String> images) {
        this.name = name;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.rating = rating;
        this.description = description;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getFullAddress() { return address + "\n" + postcode + " " + city; }

    public String getBanner() { return images.isEmpty() ? null : images.get(0); }
}
