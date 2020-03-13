package com.invoice.generator;

public class Ride {
    public RidesCategories categories;
    public int time;
    public double distance;

    public Ride(RidesCategories categories, double distance, int time) {
        this.distance = distance;
        this.time = time;
        this.categories = categories;
    }
}
