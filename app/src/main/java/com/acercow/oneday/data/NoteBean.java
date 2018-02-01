package com.acercow.oneday.data;

import java.util.Objects;

/**
 * Created by zhaosen on 2018/2/1.
 */

public class NoteBean {

    private String id;
    private int weather;
    private int emotion;
    private int color;

    private long date;
    private long location;
    private String author;
    private String title;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWeather() {
        return weather;
    }

    public void setWeather(int weather) {
        this.weather = weather;
    }

    public int getEmotion() {
        return emotion;
    }

    public void setEmotion(int emotion) {
        this.emotion = emotion;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getLocation() {
        return location;
    }

    public void setLocation(long location) {
        this.location = location;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NoteBean noteBean = (NoteBean) obj;
//        return Objects.equals(this.getId(), noteBean.getId());
        return Objects.equals(id, noteBean.id) &&
               Objects.equals(title, noteBean.title) &&
                Objects.equals(content, noteBean.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content);
    }
}
