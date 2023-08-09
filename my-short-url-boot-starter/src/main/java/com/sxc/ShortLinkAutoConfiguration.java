package com.sxc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses ={ShortLinkGenerator.class})
public class ShortLinkAutoConfiguration {

}
