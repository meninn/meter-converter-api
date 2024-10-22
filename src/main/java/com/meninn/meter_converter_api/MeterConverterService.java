package com.meninn.meter_converter_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeterConverterService {

    @Autowired
    private MeasurementRepository repository;

    public double convertMetersToKilometers(double meters) {
        double convertedValue = meters / 1000.0;
        saveMeasurement(meters, convertedValue, "kilometers");
        return convertedValue;
    }

    public double convertMetersToCentimeters(double meters) {
        double convertedValue = meters * 100.0;
        saveMeasurement(meters, convertedValue, "centimeters");
        return convertedValue;
    }

    public double convertMetersToMillimeters(double meters) {
        double convertedValue = meters * 1000.0;
        saveMeasurement(meters, convertedValue, "millimeters");
        return convertedValue;
    }

    private void saveMeasurement(double meters, double convertedValue, String conversionType) {
        Measurement measurement = new Measurement();
        measurement.setMeters(meters);
        measurement.setConvertedValue(convertedValue);
        measurement.setConversionType(conversionType);
        repository.save(measurement);
    }
}