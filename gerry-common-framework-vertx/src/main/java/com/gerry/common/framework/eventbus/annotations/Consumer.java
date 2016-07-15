package com.gerry.common.framework.eventbus.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.gerry.common.framework.eventbus.enums.ConsumerEnums;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Consumer {

	ConsumerEnums type() default ConsumerEnums.SEND;
}
