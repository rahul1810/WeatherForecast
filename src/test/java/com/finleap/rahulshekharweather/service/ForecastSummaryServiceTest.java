package com.finleap.rahulshekharweather.service;

import com.finleap.rahulshekharweather.RahulshekharWeatherApplication;
import com.finleap.rahulshekharweather.config.Config;
import com.finleap.rahulshekharweather.manager.ForecastSummaryManager;
import com.finleap.rahulshekharweather.model.ForecastSummary;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ForecastSummaryServiceTest {

    private MockMvc mockMvc;

    @Mock
    private ForecastSummaryManager forecastSummaryManager;

    @InjectMocks
    private ForecastSummaryService forecastSummaryService;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(forecastSummaryService)
                .build();
    }

    @Test
    public void testGetForecastSummary() throws Exception {

        String city = "Cairo";
        ForecastSummary forecastSummary = new ForecastSummary(100.0, 20.0, 13.0);

        Mockito.when(forecastSummaryManager.getSummaryByCity(city)).thenReturn(forecastSummary);
        mockMvc.perform(MockMvcRequestBuilders.get("/data")
                .param("city", city)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode", org.hamcrest.Matchers.is(200)));

        Mockito.verify(forecastSummaryManager).getSummaryByCity(city);

    }

}