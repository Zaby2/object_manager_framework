package com.manager.test;

import com.manager.factory.InjectByType;
import com.manager.factory.InjectProperty;
import com.manager.factory.ObjectFactory;

public class AnnouncerImpl implements Announcer {
    @InjectByType
    private Recommendator rec;

    @Override
    public void announce(String s) {System.out.println(s + "!!!!");
        rec.recomend();
    }
}
