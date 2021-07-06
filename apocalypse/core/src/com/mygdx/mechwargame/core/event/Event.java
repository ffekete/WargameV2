package com.mygdx.mechwargame.core.event;

public interface Event {

    void setUp();

    void start();

    void progress();

    void isFinished();

    void finish();

}
