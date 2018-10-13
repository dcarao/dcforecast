package com.finleap.testcase.dcforecast.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.finleap.testcase.dcforecast.domain.ForecastAverageResult;
import com.finleap.testcase.dcforecast.domain.WeatherReponse;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ForecastServiceTest {
	
	@Autowired
	private ForecastService service;
	
	@MockBean
	RestTemplate restTemplate;
	
	@Test
    public void testCalculateAverage() {
		WeatherReponse response = new WeatherReponse();
		when(restTemplate.getForObject(any(String.class), any(Class.class), any(Object.class))).thenReturn(response);
		ForecastAverageResult result = service.getForecastAverage("berlin");
		assertThat(result).isNotNull();
	}

}
