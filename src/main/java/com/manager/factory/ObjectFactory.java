package com.manager.factory;

import com.manager.test.Controller;
import com.manager.test.ControllerDownloaderImpl;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class ObjectFactory {

    // scanning for realisations of the ifc
    private Config config;


    // Singleton part
    private static ObjectFactory ourInstance = new ObjectFactory();
    public static ObjectFactory getInstance() {
        return ourInstance;
    }
    private ObjectFactory() {
        config = new ConfigImpl("com.manager", new HashMap<>(Map.of(Controller.class, ControllerDownloaderImpl.class)));
    }




   // creating object of any type

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if(type.isInterface()) {
            implClass = config.getImplClass(type);
        }

        T t = implClass.getDeclaredConstructor().newInstance();

        // we need to set our object before returning

        for (Field field : implClass.getDeclaredFields()) {
            InjectProperty annotation = field.getAnnotation(InjectProperty.class);
            String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
            Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
            Map<String, String> propertyMap = lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));
            if(annotation != null) {
                String value = annotation.value().isEmpty() ? propertyMap.get(field.getName()) : propertyMap.get(annotation.value());
                field.setAccessible(true);
                field.set(t, value);
            }
        }
        return t;
    }
}
