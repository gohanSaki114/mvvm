package com.unixsoftect.mvvm;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Note_table")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String title;
    private final String description;
    private final int priority;

public Note(String title , String description, int priority)
{
    this.title= title;
    this.priority= priority;
    this.description = description;
}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
