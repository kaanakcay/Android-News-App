package edu.sabanciuniv.operatingsystemsexample;

public class Comments {
    private int id;
    private String news_id, text, name;

    public Comments(int id, String news_id, String text, String name) {
        this.id = id;
        this.news_id = news_id;
        this.text = text;
        this.name = name;
    }

    public Comments() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
