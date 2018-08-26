package com.finleap.rahulshekharweather.config;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.concurrent.TimeUnit;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class Config {

    @Autowired
    private Environment environment;

    @Value("${weather.forecast.url}")
    private String weatherForecastURL;

    @Value("${weather.forecast.api.key}")
    private String weatherForeCastAPIKey;

    @Value("${google.timezone.api}")
    private String timezoneAPIUrl;

    public String getWeatherForecastURL() {
        return weatherForecastURL;
    }

    public void setWeatherForecastURL(String weatherForecastURL) {
        this.weatherForecastURL = weatherForecastURL;
    }

    public String getWeatherForeCastAPIKey() {
        return weatherForeCastAPIKey;
    }

    public void setWeatherForeCastAPIKey(String weatherForeCastAPIKey) {
        this.weatherForeCastAPIKey = weatherForeCastAPIKey;
    }

    public String getTimezoneAPIUrl() {
        return timezoneAPIUrl;
    }

    public void setTimezoneAPIUrl(String timezoneAPIUrl) {
        this.timezoneAPIUrl = timezoneAPIUrl;
    }

    @Bean
    public Gson gson(){
        return new Gson();
    }

    @Bean
    public CacheManager cacheManager() {
        GuavaCacheManager guavaCacheManager =  new GuavaCacheManager();
        guavaCacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES));
        return guavaCacheManager;
    }

    @Bean
    public Docket productAPI(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.finleap.rahulshekharweather"))
                .build();
    }
}
