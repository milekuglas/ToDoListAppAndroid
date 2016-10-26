package com.example.micko.todolistapplication.model;

/**
 * Created by Micko on 22-Oct-16.
 */

public class Task {

    private int ID;
    private String title;
    private String description;
    private boolean done;

    public Task(int ID, String title, String description, boolean done) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.done = done;
    }

    public Task(String title, String description, boolean done) {
        this.title = title;
        this.description = description;
        this.done = done;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
