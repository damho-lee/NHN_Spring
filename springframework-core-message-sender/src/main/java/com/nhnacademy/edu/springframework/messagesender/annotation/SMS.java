package com.nhnacademy.edu.springframework.messagesender.annotation;

import com.nhnacademy.edu.springframework.messagesender.Means;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.beans.factory.annotation.Qualifier;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface SMS {
    Means means();
}
