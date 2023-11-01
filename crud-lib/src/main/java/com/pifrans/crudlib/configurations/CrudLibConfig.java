package com.pifrans.crudlib.configurations;

import com.pifrans.crudlib.CrudLibApplication;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(CrudLibApplication.class)
public @interface CrudLibConfig {
}
