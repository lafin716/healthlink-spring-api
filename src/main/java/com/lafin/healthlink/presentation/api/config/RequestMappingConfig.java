package com.lafin.healthlink.presentation.api.config;

import com.lafin.healthlink.presentation.api.base.CustomRequestMappingHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class RequestMappingConfig extends DelegatingWebMvcConfiguration {
  @Override
  protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
    return new CustomRequestMappingHandler();
  }
}
