package edu.kit.isco.kitalumniapp;

/**
 * Created by Andre on 22.02.2015.
 *
 * Container class to store objects shown in OverViewFragment
 */
public class OverviewListItem<DataAccesObject> {

    //stored object
    //MUST contain DataAccessNews, DataAccessEvent, DataAccessJob or String only!
    Object object;

    //ID to identify the object
    //0 = DataAccessNews
    //1 = DataAccessJobs
    //2 = DataAccessEvents
    //3 = String
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
