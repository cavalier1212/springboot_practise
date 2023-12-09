package com.example.bean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.stereotype.Component;

@Component("Tom")
@ConditionalOnBean(name = "Jerry")
// @Profile("dev")
@ConditionalOnNotWebApplication
// @ConditionalOnWebApplication
public class Cat {

}
