package com.manager.factory;

import lombok.SneakyThrows;

import java.lang.reflect.Field;


// Aka Autowired
public class InjectByTypeConfiguratorImpl implements ObjectConfigurator {
    @SneakyThrows
    @Override
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(InjectByType.class)) {
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                field.set(t, object);
            }

        }

    }
}
