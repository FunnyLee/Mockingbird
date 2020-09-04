package com.mocking.bird.annotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Author: Funny
 * Time: 2020/9/3
 * Description: This is FindViewUtils
 */
public class BindViewUtils {

    public static void InjectView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        //反射得到activity的所有成员变量，包括私有变量
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            //判断该变量上是否有FindView注解
            if (field.isAnnotationPresent(BindView.class)) {
                //得到注解里的value值
                BindView annotation = field.getAnnotation(BindView.class);
                int id = annotation.value();

                //查找控件
                View viewById = activity.findViewById(id);

                //通过反射，把查找到的控件值，赋值给该成员变量
                field.setAccessible(true);
                try {
                    field.set(activity, viewById);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
