package com.manager.test;

import com.manager.factory.Application;
import com.manager.factory.ApplicationContext;
import com.manager.factory.ObjectFactory;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = Application.run("com.manager", new HashMap<>(Map.of(Controller.class, ControllerDownloaderImpl.class)));
        MusicPlayer player = context.getObject(MusicPlayer.class);
        player.startPlayingMusic(new Music());
    }
}
