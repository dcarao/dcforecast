package com.finleap.testcase.dcforecast.rest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.finleap.testcase.dcforecast.domain.ForecastAverageResult;
import com.finleap.testcase.dcforecast.service.ForecastService;

/**
 * 
 * @author duveliscaraom
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ForecastApiTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ForecastService service;

	/**
	 * Test case: Response 200
	 * @throws Exception
	 */
	@Test
	public void getForecastAverage() throws Exception {

		ForecastAverageResult result = new ForecastAverageResult(1, 2, 3);
		given(service.getForecastAverage("berlin")).willReturn(result);

		mvc.perform(MockMvcRequestBuilders.get("/data/berlin").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json("{\"dailyAvgTemperature\":1,\"nightlyAvgTemperature\":2,\"pressureAvg\":3}"));
	}
	
	/**
	 * Test case: Response 404
	 * @throws Exception
	 */
	@Test
	public void getForecastAverageBadRequest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/BAD_URL/berlin").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}


}
