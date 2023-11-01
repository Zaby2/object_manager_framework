package com.manager.factory;

import com.manager.test.Controller;
import com.manager.test.ControllerDownloaderImpl;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class ObjectFactory {

    // scanning for realisations of the ifc
    private Config config;

    private List<ObjectConfigurator> configurators = new ArrayList<>();
    // Singleton part
    private static ObjectFactory ourInstance = new ObjectFactory();
    public static ObjectFactory getInstance() {
        return ourInstance;
    }

    @SneakyThrows
    private ObjectFactory() {
        config = new ConfigImpl("com.manager", new HashMap<>(Map.of(Controller.class, ControllerDownloaderImpl.class))); // in future we dont actually need to hardcode this line
        for (Class<? extends ObjectConfigurator> aClass : config.getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
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
        // chains of responsibility pattern (many handlers for each object)
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t));

        return t;
    }
}
