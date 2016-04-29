package com.rent_app.rentb;

/**
 * Created by ccrodrig on 2/1/2016.
 */
public class Bike {
    private String name;
    private String filename;

    public Bike(String name, String filename) {
        this.name = name;
        this.filename = filename;
    }

    public String getName() {
        return name;
    }

    public String getFilename() {
        return filename;
    }
}
