package com.manager.test;

import com.manager.factory.InjectProperty;
import com.manager.test.Recommendator;

public class RecommendatorImpl implements Recommendator {

    @InjectProperty
    private String company;
    @Override
    public void recomend() {
        System.out.println("The best company is " + company);
    }
}
