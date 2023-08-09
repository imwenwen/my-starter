package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

@ControllerAdvice
public class StringTrimRequestBodyAdvice extends RequestBodyAdviceAdapter {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter methodParameter, Type type,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage,
                                    MethodParameter parameter, Type targetType,
                                    Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == requestAttributes) {
            return body;
        }
        // 获取所有的字段，包括继承的字段
        Field[] fields = body.getClass().getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            boolean hasExcludeTrim = isHasExcludeTrim(annotations);
            if (!hasExcludeTrim) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(body);
                    if (value instanceof String) {
                        field.set(body, WebAdviceStringUtil.processStringValue((String) value));
                    }
                } catch (IllegalAccessException e) {
                    // ignore
                }
            }
        }
        return body;
    }

    public static boolean isHasExcludeTrim(Annotation[] annotations) {
        if(null == annotations || annotations.length == 0){
            return false;
        }
        boolean hasExcludeTrim = false;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(DoNotParamAdvice.class)) {
                hasExcludeTrim = true;
                break;
            }
        }
        return hasExcludeTrim;
    }
}
