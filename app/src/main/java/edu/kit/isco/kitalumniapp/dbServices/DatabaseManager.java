package edu.kit.isco.kitalumniapp.dbServices;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Max on 20.02.2015.
 */
public class DatabaseManager {

    private static DatabaseManager instance;
    private static SQLiteOpenHelper helper;
    private AtomicInteger openCounter = new AtomicInteger();
    private SQLiteDatabase database;

    public static synchronized void initializeInstance(SQLiteOpenHelper sqLiteOpenHelper) {
        if (instance == null) {
            instance = new DatabaseManager();
            helper = sqLiteOpenHelper;
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException(DatabaseManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        } else {
            return instance;
        }
    }

    public synchronized SQLiteDatabase openDatabase() {
        if(openCounter.incrementAndGet() == 1) {
            // Opening new database
            database = helper.getWritableDatabase();
        }
        return database;
    }

    public synchronized void closeDatabase() {
        if(openCounter.decrementAndGet() == 0) {
            // Closing database
            database.close();
        }
    }

}
