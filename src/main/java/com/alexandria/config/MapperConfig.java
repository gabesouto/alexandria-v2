package com.alexandria.config;

import org.modelmapper.*;
import org.modelmapper.convention.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

@Component
public class MapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    // Customize the mappings here
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    return modelMapper;
  }
}
