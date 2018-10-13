package com.finleap.testcase.dcforecast.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.finleap.testcase.dcforecast.domain.ForecastAverageResult;
import com.finleap.testcase.dcforecast.service.ForecastService;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * 
 * @author duveliscaraom
 *
 */
@RestController
@RequestMapping(value = "/data", produces = {APPLICATION_JSON_VALUE})
public class ForecastApi {
	
	@Autowired
	private ForecastService service;
	
	@RequestMapping(value= "/{city}",method = RequestMethod.GET)
    public ForecastAverageResult getForecastAverage(@PathVariable String city) {
        return service.getForecastAverage(city);
    }
}
