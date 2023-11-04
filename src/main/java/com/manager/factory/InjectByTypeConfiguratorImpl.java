package com.manager.factory;

import lombok.SneakyThrows;

import java.lang.reflect.Field;


// Aka Autowired
public class InjectByTypeConfiguratorImpl implements ObjectConfigurator {
    @SneakyThrows
    @Override
    public void configure(Object t) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(InjectByType.class)) {
                field.setAccessible(true);
                Object object = ObjectFactory.getInstance().createObject(field.getType());
                field.set(t, object);
            }

        }

    }
}
