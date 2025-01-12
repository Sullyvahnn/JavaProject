package AuctionClass;

import java.util.ArrayList;

public class Product {
    private String name;
    private double price;
    private ArrayList<String> categories;
    private String description;
    private ArrayList<String> images;
    public Product(String name, double price, String description,
                   ArrayList<String> categories, ArrayList<String> images) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.categories = categories;
        this.images = images;
    }
    public Product(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public ArrayList<String> getCategories() {
        return categories;
    }
    public ArrayList<String> getImages() {
        return images;
    }
    public void addTag(String tag) {
        categories.add(tag);
    }
    public void removeTag(String tag) {
        categories.remove(tag);
    }
    public void addImage(String image) {
        images.add(image);
    }
    public void removeImage(String image) {
        images.remove(image);
    }
    public String getStatus() {
        String status = "";
        status += "Name: " + name + "\n";
        status += "Price: " + price + "\n";
        status += "Description: " + description + "\n";
        status += "Categories: " + categories + "\n";
        return status;
    }
}
