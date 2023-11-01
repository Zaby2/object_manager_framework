package com.manager.test;

import com.manager.factory.ObjectFactory;
import com.manager.test.Announcer;
import com.manager.test.Controller;
import com.manager.test.Music;

public class MusicPlayer {

    private Announcer announcer  = ObjectFactory.getInstance().createObject(Announcer.class);
    private Controller controller = ObjectFactory.getInstance().createObject(Controller.class);

    public void startPlayingMusic(Music music) {
        announcer.announce("Music is downloading");
        controller.makePlayerStopPlayingPrev();

        playMusic(music);
        announcer.announce("Music was played");
    }

    private void playMusic(Music music) {
        System.out.println("Music is playing");
    }
}
