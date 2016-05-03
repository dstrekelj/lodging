package io.github.dstrekelj.smjestaj.models;

import java.util.List;

/**
 * Represents an entry in the lodgings data JSON file.
 */
public class LodgingModel {
    /**
     * Shorthand for the class name. Useful for logging.
     */
    public static final String TAG = LodgingModel.class.getSimpleName();

    /**
     * Lodging name.
     */
    private String name;

    /**
     * Lodging address (street name and house number).
     */
    private String address;

    /**
     * Lodging postcode.
     */
    private int postcode;

    /**
     * Lodging city.
     */
    private String city;

    /**
     * Lodging rating (out of five).
     */
    private int rating;

    /**
     * Lodging description.
     */
    private String description;

    /**
     * Lodging images. Only the first four are handled by this class, the first being the banner
     * image and the others gallery images.
     */
    private List<String> images;

    /**
     * Default constructor.
     */
    public LodgingModel() {}

    /**
     * Parametrised constructor.
     *
     * @param name          Lodging name
     * @param address       Lodging address (street name and house address)
     * @param postcode      Lodging postcode
     * @param city          Lodging city
     * @param rating        Lodging rating
     * @param description   Lodging description
     * @param images        Lodging images
     */
    public LodgingModel(String name, String address, int postcode, String city, int rating, String description, List<String> images) {
        this.name = name;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.rating = rating;
        this.description = description;
        this.images = images;
    }

    /**
     * Gets lodging name.
     *
     * @return Lodging name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets lodging name.
     *
     * @param name  Lodging name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets lodging address.
     *
     * @return Lodging address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets lodging address.
     *
     * @param address   Lodging address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets lodging postcode.
     *
     * @return Lodging postcode
     */
    public int getPostcode() {
        return postcode;
    }

    /**
     * Sets lodging postcode.
     *
     * @param postcode  Lodging postcode
     */
    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    /**
     * Gets lodging city.
     *
     * @return Lodging city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets lodging city.
     *
     * @param city  Lodging city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets lodging rating.
     *
     * @return Lodging rating.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets lodging rating.
     *
     * @param rating    Lodging rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Gets lodging description.
     *
     * @return Lodging description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets lodging description.
     *
     * @param description   Lodging description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets lodging images.
     *
     * @return Lodging images
     */
    public List<String> getImages() {
        return images;
    }

    /**
     * Sets lodging images.
     *
     * @param images    Lodging images
     */
    public void setImages(List<String> images) {
        this.images = images;
    }

    /**
     * Formats `address`, `postcode`, and `city` into a full address conforming to Croatian
     * national standard.
     *
     * @return Formatted full address
     */
    public String getFullAddress() { return address + "\n" + postcode + " " + city; }

    /**
     * Returns the first image asset path in the `images` array as a banner for `ItemActivity`.
     * Returns `null` if no images are available.
     *
     * @return Path to first image
     */
    public String getBanner() { return images.isEmpty() ? null : images.get(0); }

    /**
     * Returns three lodging images for the gallery (2nd to 4th images, inclusive). Returns `null`
     * if no images are available.
     *
     * @return List of gallery image paths
     */
    public List<String> getGallery() { return images.size() < 4 ? null : images.subList(1, 4); }
}
