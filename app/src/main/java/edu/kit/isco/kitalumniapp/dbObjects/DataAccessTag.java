package edu.kit.isco.kitalumniapp.dbObjects;

import android.content.ContentValues;

import edu.kit.isco.kitalumniapp.dbServices.TagTable;

/**
 * Dao for Tags
 * Created by Andre on 04.02.2015.
 */
public class DataAccessTag implements DataAccessObject {

    private long id;
    private String name;

    public DataAccessTag() {
    }

    public DataAccessTag(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(TagTable.ID, id);
        values.put(TagTable.NAME, name);
        return values;
    }
}
