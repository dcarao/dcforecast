package com.finleap.testcase.dcforecast.service;

import com.finleap.testcase.dcforecast.ForecastException;
import com.finleap.testcase.dcforecast.domain.ForecastAverageResult;
/**
 * 
 * @author duveliscaraom
 *
 */
public interface ForecastService {
	
	/**
	 * 
	 * @param cityName
	 * @return
	 */
	ForecastAverageResult getForecastAverage(String cityName) throws ForecastException;

}
