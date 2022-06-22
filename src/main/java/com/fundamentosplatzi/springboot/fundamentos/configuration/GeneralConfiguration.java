package com.fundamentosplatzi.springboot.fundamentos.configuration;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperty;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithPropertyImplement;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {

    @Value("${value.name}")
    private String name;

    @Value("${value.apellido}")
    private String apellido;

    @Value("${value.random}")
    private String random;

    @Bean
    public MyBeanWithProperty function(){
        return new MyBeanWithPropertyImplement(name, apellido);
    }

}
