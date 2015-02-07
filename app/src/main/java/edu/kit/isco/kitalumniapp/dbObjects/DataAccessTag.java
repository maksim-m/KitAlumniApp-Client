package edu.kit.isco.kitalumniapp.dbObjects;

/**
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

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
