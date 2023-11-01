package com.manager.factory;

import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

public class ConfigImpl implements Config {
    private Reflections scanner;
    private Map<Class, Class> ifc2implClass;

    public ConfigImpl(String packageToScan,Map<Class, Class> ifc2implClass ) {
        this.scanner = new Reflections(packageToScan);
        this.ifc2implClass = ifc2implClass;
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        // 1st - key , second - lambda if no key;

        return ifc2implClass.computeIfAbsent(ifc, aClass -> {
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
            if (classes.size() != 1) {
                throw new RuntimeException(ifc + "more then one ralisation ");
            }
            return classes.iterator().next();
        });
    }
}
