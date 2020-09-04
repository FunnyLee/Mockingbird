package com.mocking.bird.annotation;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;

import java.lang.reflect.Field;

/**
 * Author: Funny
 * Time: 2020/9/3
 * Description: This is AutowiredUtils
 */
public class AutowiredUtils {

    public static void inject(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();

        Bundle extras = activity.getIntent().getExtras();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Autowired annotation = field.getAnnotation(Autowired.class);
                String value = annotation.value();
                String key = TextUtils.isEmpty(value) ? field.getName() : annotation.value();

                if (extras.containsKey(key)) {
                    Object obj = extras.get(key);


                    field.setAccessible(true);
                    try {
                        field.set(activity, obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
