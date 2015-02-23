package edu.kit.isco.kitalumniapp;

/**
 * Created by Andre on 22.02.2015.
 */
public class OverviewListItem<DataAccesObject> {

    Object object;
    int id;

    public OverviewListItem (Object object, int id) {
        this.object = object;
        this.id = id;
    }

    public Object getObject() {
        return object;
    }

    public int getId() {
        return id;
    }
}
