package com.lefer.demo4doc.common;

/**
 * @author fang
 * @creatdate 17-7-28
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JSONS {
    JSON [] value();
}