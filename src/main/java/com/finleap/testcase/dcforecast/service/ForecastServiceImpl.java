package com.finleap.testcase.dcforecast.service;

import java.util.Calendar;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.finleap.testcase.dcforecast.ForecastException;
import com.finleap.testcase.dcforecast.domain.Forecast;
import com.finleap.testcase.dcforecast.domain.ForecastAverageResult;
import com.finleap.testcase.dcforecast.domain.WeatherReponse;

/**
 * 
 * @author duveliscaraom
 *
 */
@Service
public class ForecastServiceImpl implements ForecastService{
	
	Logger logger = LogManager.getLogger(ForecastServiceImpl.class);
	
	@Value("${openweathermap.apiKey}")
	private String apiKey;
	
	@Value("${openweathermap.endpoint}")
	private String endpoint;
	
	/**
	 * 
	 */
	private final RestTemplate restTemplate;
	
	/**
	 * 
	 * @param restTemplateBuilder
	 */
	public ForecastServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	

	@Override
	@Cacheable("forecasts")
	public ForecastAverageResult getForecastAverage(String cityName) throws ForecastException{
		String url = endpoint+"?q="+cityName+"&units=metric&appid="+apiKey;
		WeatherReponse result = this.restTemplate.getForObject(url , WeatherReponse.class, new Object());
		logger.info("Calculating the average for the City: " + cityName);
		return calculateAverage(result);
	}
	
	
	/**
	 * 
	 * @param response
	 * @return
	 */
	protected ForecastAverageResult calculateAverage(WeatherReponse response) {
		double sumDaily = 0;
		double sumNightly = 0; 
		double sumPressure = 0;
		double avgDaily = 0; 
		double avgNightly = 0;
		double avgPressure = 0;
		
		int countDaily = 0;
		int countNightly = 0;
		int countPressure = 0;

		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		int nowDay = now.get(Calendar.DAY_OF_MONTH);

		for (Forecast forecast : response.getList()) {
			long dt = Long.parseLong(forecast.getDt());

			c.setTimeInMillis(dt * 1000);
			int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
			int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
			
			if((dayOfMonth - nowDay) < 3) {
				if (hourOfDay >= 6 && hourOfDay <18) {
					sumDaily += Double.parseDouble(forecast.getMain().getTemp());
					countDaily++;
				}else {
					sumNightly += Double.parseDouble(forecast.getMain().getTemp());
					countNightly++;
				}
				sumPressure += Double.parseDouble(forecast.getMain().getPressure());
				countPressure++;
			}
		}
		avgDaily = sumDaily / countDaily;
		avgNightly = sumNightly / countNightly;
		avgPressure = sumPressure / countPressure;
		
		return new ForecastAverageResult(avgDaily, avgNightly, avgPressure);
	}

}
