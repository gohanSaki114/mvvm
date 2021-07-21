package com.unixsoftect.mvvm;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                   .addCallback(roomCallback)   //passing method as an argument to antoher method ....this room callback will populate the database on onCreate
                   .build();
    }
    return instance;
}
//callback methods
private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);
        new PopulateDbAsyncTask(instance).execute();
    }
};
private static class PopulateDbAsyncTask extends AsyncTask<Void, Void , Void>
{
    private final NoteDao noteDao;
    private PopulateDbAsyncTask(NoteDatabse db){
        noteDao = db.noteDao();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        noteDao.insert(new Note("Title1","Description",1));
        noteDao.insert(new Note("Title1","Description",2));
        noteDao.insert(new Note("Title1","Description",3));
        return null;
    }
}
}
