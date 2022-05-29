package com.trilobyte.temp_sensors.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Measure implements Comparable<Measure> {

    private Integer value;
    private Integer index;

    @Override
    public int compareTo(Measure anotherMeasure) {
        return this.value.compareTo(anotherMeasure.getValue());
    }
}
