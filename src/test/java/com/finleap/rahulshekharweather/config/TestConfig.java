package com.finleap.rahulshekharweather.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private Environment environment;

}
