package com.finleap.testcase.dcforecast.domain;
/**
 * 
 * @author duveliscaraom
 *
 */
public class ForecastAverageResult {
	
	private double dailyAvgTemperature;
	private double nightlyAvgTemperature;
	private double pressureAvg;

	public ForecastAverageResult(double dailyAvgTemperature, double nightlyAvgTemperature, double pressureAvg) {
		this.dailyAvgTemperature = dailyAvgTemperature;
		this.nightlyAvgTemperature = nightlyAvgTemperature;
		this.pressureAvg = pressureAvg;
	}

	public double getDailyAvgTemperature() {
		return dailyAvgTemperature;
	}

	public void setDailyAvgTemperature(double dailyAvgTemperature) {
		this.dailyAvgTemperature = dailyAvgTemperature;
	}

	public double getNightlyAvgTemperature() {
		return nightlyAvgTemperature;
	}

	public void setNightlyAvgTemperature(double nightlyAvgTemperature) {
		this.nightlyAvgTemperature = nightlyAvgTemperature;
	}

	public double getPressureAvg() {
		return pressureAvg;
	}

	public void setPressureAvg(double pressureAvg) {
		this.pressureAvg = pressureAvg;
	}
	
	
	

}
