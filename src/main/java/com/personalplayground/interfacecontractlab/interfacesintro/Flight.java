package com.personalplayground.interfacecontractlab.interfacesintro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Flight implements Iterable<Passenger> {

    private final List<Passenger> passengerList = new ArrayList<>();

    public void addPassenger(Passenger p) {
        passengerList.add(p);
    }

    // Required by Iterable<T>
    @Override
    public Iterator<Passenger> iterator() {
        // We return the iterator of the internal list.
        // This gives iteration BUT still keeps passengerList private.
        return passengerList.iterator();
    }

    @Override
    public String toString() {
        return "Flight with " + passengerList.size() + " passengers";
    }
}
