package com.example.task.model;

import java.util.Random;

public class Task {
    private String mName;

    private enum States {
        TODO, DOING, DONE
    }

    public States getState() {
        int pick = new Random().nextInt(States.values().length);
        return States.values()[pick];
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Task(String name) {
        mName = name;
    }
}
