package com.manager.factory;

public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> ifc);
}
