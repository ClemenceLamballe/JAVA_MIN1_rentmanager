package com.epf.rentmanager.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.epf.rentmanager.service", "com.epf.rentmanager.dao",
        "com.epf.rentmanager.persistence" })
public class AppConfiguration {
}