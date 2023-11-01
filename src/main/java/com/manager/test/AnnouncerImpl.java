package com.manager.test;

import com.manager.factory.ObjectFactory;

public class AnnouncerImpl implements Announcer {
    private Recommendator rec = ObjectFactory.getInstance().createObject(Recommendator.class);

    @Override
    public void announce(String s) {
        System.out.println(s);
        rec.recomend();
    }
}
