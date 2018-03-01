package com.acercow.oneday.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by zhaosen on 2018/2/1.
 */

@Entity(tableName = "notes")
public class Note implements Serializable {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "note_guid")
    private String noteGUID;
    @ColumnInfo(name = "note_type")
    private int noteType;
    @ColumnInfo(name = "note_author")
    private String noteAuthor;
    @ColumnInfo(name = "note_title")
    private String noteTitle;
    @ColumnInfo(name = "note_content")
    private String noteContent;
    @ColumnInfo(name = "note_abstract")
    private String noteAbstract;
    @ColumnInfo(name = "note_color")
    private int noteColor;
    @ColumnInfo(name = "note_read_count")
    private int noteReadCount;
    @ColumnInfo(name = "note_gps")
    private long noteGPS;
    @ColumnInfo(name = "note_location")
    private String noteLocation;
    @ColumnInfo(name = "note_md5")
    private String noteMD5;
    @ColumnInfo(name = "create_date")
    private String createdDate;
    @ColumnInfo(name = "modified_date")
    private String modifiedDate;
    @ColumnInfo(name = "note_date")
    private String noteDate;
    @ColumnInfo(name = "last_read_date")
    private String lastReadDate;
    @ColumnInfo(name = "sync_status")
    private int syncStatus;
    @ColumnInfo(name = "anchor")
    private int anchor;
    @ColumnInfo(name = "note_weather")
    private int noteWeather;
    @ColumnInfo(name = "note_emotion")
    private int noteEmotion;

    public String getNoteGUID() {
        return noteGUID;
    }

    public void setNoteGUID(String noteGUID) {
        this.noteGUID = noteGUID;
    }

    public int getNoteType() {
        return noteType;
    }

    public void setNoteType(int noteType) {
        this.noteType = noteType;
    }

    public String getNoteAuthor() {
        return noteAuthor;
    }

    public void setNoteAuthor(String noteAuthor) {
        this.noteAuthor = noteAuthor;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getNoteAbstract() {
        return noteAbstract;
    }

    public void setNoteAbstract(String noteAbstract) {
        this.noteAbstract = noteAbstract;
    }

    public int getNoteColor() {
        return noteColor;
    }

    public void setNoteColor(int noteColor) {
        this.noteColor = noteColor;
    }

    public int getNoteReadCount() {
        return noteReadCount;
    }

    public void setNoteReadCount(int noteReadCount) {
        this.noteReadCount = noteReadCount;
    }

    public long getNoteGPS() {
        return noteGPS;
    }

    public void setNoteGPS(long noteGPS) {
        this.noteGPS = noteGPS;
    }

    public String getNoteLocation() {
        return noteLocation;
    }

    public void setNoteLocation(String noteLocation) {
        this.noteLocation = noteLocation;
    }

    public String getNoteMD5() {
        return noteMD5;
    }

    public void setNoteMD5(String noteMD5) {
        this.noteMD5 = noteMD5;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    public String getLastReadDate() {
        return lastReadDate;
    }

    public void setLastReadDate(String lastReadDate) {
        this.lastReadDate = lastReadDate;
    }

    public int getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(int syncStatus) {
        this.syncStatus = syncStatus;
    }

    public int getAnchor() {
        return anchor;
    }

    public void setAnchor(int anchor) {
        this.anchor = anchor;
    }

    public int getNoteWeather() {
        return noteWeather;
    }

    public void setNoteWeather(int noteWeather) {
        this.noteWeather = noteWeather;
    }

    public int getNoteEmotion() {
        return noteEmotion;
    }

    public void setNoteEmotion(int noteEmotion) {
        this.noteEmotion = noteEmotion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (noteGUID != null ? !noteGUID.equals(note.noteGUID) : note.noteGUID != null)
            return false;
        if (noteTitle != null ? !noteTitle.equals(note.noteTitle) : note.noteTitle != null)
            return false;
        return noteAbstract != null ? noteAbstract.equals(note.noteAbstract) : note.noteAbstract == null;
    }

    @Override
    public int hashCode() {
        int result = noteGUID != null ? noteGUID.hashCode() : 0;
        result = 31 * result + (noteTitle != null ? noteTitle.hashCode() : 0);
        result = 31 * result + (noteAbstract != null ? noteAbstract.hashCode() : 0);
        return result;
    }

}
