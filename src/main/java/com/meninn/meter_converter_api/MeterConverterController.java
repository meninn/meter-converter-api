package com.meninn.meter_converter_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeterConverterController {

    @Autowired
    private MeterConverterService converterService;

    @GetMapping("/convert/meter-to-kilometer")
    public double convertToKilometers(@RequestParam double meters) {
        return converterService.convertMetersToKilometers(meters);
    }

    @GetMapping("/convert/meter-to-centimeter")
    public double convertToCentimeters(@RequestParam double meters) {
        return converterService.convertMetersToCentimeters(meters);
    }

    @GetMapping("/convert/meter-to-millimeter")
    public double convertToMillimeters(@RequestParam double meters) {
        return converterService.convertMetersToMillimeters(meters);
    }
}