package com.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@Configuration
@ConditionalOnProperty(value = "trim.enabled", havingValue = "true", matchIfMissing = true)
public class StringTrimAutoConfiguration {

    @Bean
    public StringTrimRequestBodyAdvice stringTrimRequestBodyAdvice() {
        return new StringTrimRequestBodyAdvice();
    }


    @ControllerAdvice
    @Configuration
    public static class ControllerStringParamTrimConfig {

        /**
         * url和form表单中的参数trim
         */
        @InitBinder
        public void initBinder(WebDataBinder binder) {

//            Object target = binder.getTarget();
//            if (null == target) {
//                return;
//            }
//            Annotation[] annotations = target.getClass().getDeclaredAnnotations();
//            if (StringTrimRequestBodyAdvice.isHasExcludeTrim(annotations)) {
//                return;
//            }
//            Field[] fields = target.getClass().getDeclaredFields();
//            for (Field field : fields) {
//                Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
//                boolean hasExcludeTrim = StringTrimRequestBodyAdvice.isHasExcludeTrim(fieldAnnotations);
//                if (hasExcludeTrim) {
//                    continue;
//                }
//                binder.registerCustomEditor(String.class, field.getName(), new StringParamAdviceEditor());
//            }
//
//        }


            binder.registerCustomEditor(String.class, new StringParamAdviceEditor());
        }
    }
}
