package com.manager.test;

import com.manager.factory.InjectProperty;
import com.manager.factory.Singleton;
import com.manager.test.Recommendator;


@Singleton
public class RecommendatorImpl implements Recommendator {

    @InjectProperty("wiskey")
    private String company;
    @Override
    public void recomend() {
        System.out.println("The best company is " + company);
    }
}
