package com.wotc.thermometer.domain;

import java.util.function.Predicate;

public class ThermometerThreshold {

    // As descriptive name is important for notifications
    String name;

    // A predicate should be defined as,  t -> ( t ? v). Ex: Predicate<Double> myPredicate = t -> (t > 212)
   Predicate<Double> thermometerThreshold;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Predicate<Double> getThermometerThreshold() {
        return thermometerThreshold;
    }

    public void setThermometerThreshold(Predicate<Double> thermometerThreshold) {
        this.thermometerThreshold = thermometerThreshold;
    }
}
