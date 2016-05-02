package io.github.dstrekelj.smjestaj.models;

import java.util.List;

/**
 * Represents an entry in `assets/lodgings.json`.
 */
public class LodgingModel {
    public static final String TAG = LodgingModel.class.getSimpleName();

    private String name;
    private String address;
    private int postcode;
    private String city;
    private int rating;
    private String description;
    private List<String> images;

    public LodgingModel() {}

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

    /**
     * Formats `address`, `postcode`, and `city` into a full address conforming to Croatian
     * national standard.
     * @return String formatted full address
     */
    public String getFullAddress() { return address + "\n" + postcode + " " + city; }

    /**
     * Returns the first image asset path in the `images` array as a banner for `ItemActivity`.
     * @return String path to first image
     */
    public String getBanner() { return images.isEmpty() ? null : images.get(0); }

    public List<String> getGallery() { return images.size() < 4 ? null : images.subList(1, 4); }
}
