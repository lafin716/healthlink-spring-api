package com.lafin.healthlink.presentation.api.base;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CustomRequestMappingHandler extends RequestMappingHandlerMapping {
  @Override
  protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
    var mappingInfo = super.getMappingForMethod(method, handlerType);
    if (mappingInfo == null) {
      return null;
    }

    List<String> superUrlPatterns = new ArrayList<>();
    Class<?> superClass = handlerType.getSuperclass();
    for (; superClass != Object.class; superClass = superClass.getSuperclass()) {
      if (superClass.isAnnotationPresent(RequestMapping.class)) {
        superUrlPatterns.add(0, superClass.getAnnotation(RequestMapping.class).value()[0]);
      }
    }

    // 상위 클래스에 @RequestMapping이 존재했다면
    // Pattern을 기준으로 RequestMappingInfo를 생성하고, 기존 RequestMapping 정보와 결합합니다.
    if (!superUrlPatterns.isEmpty()) {
      RequestMappingInfo superclassRequestMappingInfo = RequestMappingInfo.paths(String.join("", superUrlPatterns)).build();
      return superclassRequestMappingInfo.combine(mappingInfo);
    }

    return mappingInfo;
  }
}
