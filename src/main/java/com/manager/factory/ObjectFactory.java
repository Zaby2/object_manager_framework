package com.manager.factory;

import com.manager.test.Controller;
import com.manager.test.ControllerDownloaderImpl;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ObjectFactory {

    private final ApplicationContext context;

    // scanning for realisations of the ifc


    private List<ObjectConfigurator> configurators = new ArrayList<>();
    private static ObjectFactory ourInstance;

//"com.manager", new HashMap<>(Map.of(Controller.class, ControllerDownloaderImpl.class))
    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }


   // creating object of any type

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {
        T t = implClass.getDeclaredConstructor().newInstance();

        // we need to set our object before returning
        // chains of responsibility pattern (many handlers for each object)

        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));
        return t;
    }
}
