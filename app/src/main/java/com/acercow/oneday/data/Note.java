package com.acercow.oneday.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Objects;

/**
 * Created by zhaosen on 2018/2/1.
 */

@Entity
public class Note {
    @PrimaryKey
    private String id;

    @ColumnInfo
    private int weather;
    @ColumnInfo
    private int emotion;
    @ColumnInfo
    private int color;
    @ColumnInfo
    private long date;
    @ColumnInfo
    private long location;
    @ColumnInfo
    private String author;
    @ColumnInfo
    private String title;
    @ColumnInfo
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
        Note note = (Note) obj;
//        return Objects.equals(this.getId(), note.getId());
        return Objects.equals(id, note.id) &&
               Objects.equals(title, note.title) &&
                Objects.equals(content, note.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content);
    }
}
