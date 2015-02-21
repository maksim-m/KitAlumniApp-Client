package edu.kit.isco.kitalumniapp.dbObjects;

import java.util.List;

/**
 * Dao for user.
 * Created by Stelian Stoev on 13.2.2015 г..
 */
public class DataAccessUser implements DataAccessObject {


    private String clientId;
    private List<DataAccessTag> tags;
    private String password;

    public DataAccessUser() {
    }

    public DataAccessUser(String clientId, List<DataAccessTag> tags, String password) {
        this.clientId = clientId;
        this.tags = tags;
        this.password = password;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<DataAccessTag> getTags() {
        return tags;
    }

    public void setTags(List<DataAccessTag> tags) {
        this.tags = tags;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}