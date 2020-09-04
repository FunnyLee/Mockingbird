package com.mocking.bird.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.IdRes;

/**
 * Author: Funny
 * Time: 2020/9/3
 * Description: This is FindView
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindView {
    @IdRes int value();
}
