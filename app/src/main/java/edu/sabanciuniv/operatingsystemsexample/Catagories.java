package edu.sabanciuniv.operatingsystemsexample;

public class Catagories {
    private int id;
    private String name;

    public Catagories(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Catagories() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
