package com.invoice.generator;

import java.util.ArrayList;

public class InvoiceService {
    private static final double NORMAL_MINIMUM_COST_PER_KILOMETER = 10;
    private static final int NORMAL_COST_PER_TIME = 1;
    private static final double NORMAL_MINFAIR = 5;
    private static final double PREMIUM_MINIMUM_COST_PER_KILOMETER = 15;
    private static final int PREMIUM_COST_PER_TIME = 2;
    private static final double PREMIUM_MINFAIR = 20;
    private RideRepository rideRepository;


    public InvoiceService() {
        this.rideRepository = new RideRepository();
    }

    public double calculateFare(RidesCategories categories, double distance, int time) {

        if (categories.equals(RidesCategories.NORMAL_RIDE)) {
            double totalFare = distance * NORMAL_MINIMUM_COST_PER_KILOMETER + time * NORMAL_COST_PER_TIME;
            if (NORMAL_MINFAIR > totalFare) {
                return NORMAL_MINFAIR;
            }
            return totalFare;
        } else {
            double totalFare = distance * PREMIUM_MINIMUM_COST_PER_KILOMETER + time * PREMIUM_COST_PER_TIME;
            if (PREMIUM_MINFAIR > totalFare) {
                return PREMIUM_MINFAIR;
            }
            return totalFare;
        }
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(RidesCategories.NORMAL_RIDE, ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void calculateFare(String userId, Ride[] rides) {
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }
}
