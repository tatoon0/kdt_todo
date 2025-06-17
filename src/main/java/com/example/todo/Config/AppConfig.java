package com.example.todo.Config;

import com.example.todo.Util.PaginationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PaginationUtil paginationUtil() {
        return new PaginationUtil();
    }
}
