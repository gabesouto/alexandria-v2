package com.alexandria.utils;

import org.modelmapper.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

@Component
public class MapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
