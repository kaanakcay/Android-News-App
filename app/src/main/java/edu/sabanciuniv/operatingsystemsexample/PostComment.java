package edu.sabanciuniv.operatingsystemsexample;

public class PostComment {

    private String name, text, news_id;

    public PostComment(String name, String text, String news_id) {
        this.name = name;
        this.text = text;
        this.news_id = news_id;
    }

    public PostComment() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }
}
