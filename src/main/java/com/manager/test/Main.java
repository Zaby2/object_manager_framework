package com.manager.test;

import com.manager.factory.ObjectFactory;

public class Main {
    public static void main(String[] args) {
        MusicPlayer player = ObjectFactory.getInstance().createObject(MusicPlayer.class);
        player.startPlayingMusic(new Music());
    }
}
