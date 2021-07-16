package com.unixsoftect.mvvm;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabse extends RoomDatabase{
private static NoteDatabse instance;
public abstract NoteDao noteDao();

public static synchronized NoteDatabse getInstance(Context context)
{
    if (instance == null)
    {
        instance = Room.databaseBuilder(context.getApplicationContext(),
                   NoteDatabse.class,"note_database")
                   .fallbackToDestructiveMigration()
                   .build();
    }
    return instance;
}
}
