package edu.kit.isco.kitalumniapp.dbObjects;

import java.util.List;

/**
 * Created by Andre on 04.02.2015.
 */
public class DataAccessJob implements DataAccessObject {

    private long id;
    private String title;
    private String shortDescription;
    private String text;
    private String url;
    private List<DataAccessTag> tags;
    private String date;

    public DataAccessJob() {
    }

    public DataAccessJob(long id, String title, String shortDescription, String text, String url, List<DataAccessTag> tags, String date) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.text = text;
        this.url = url;
        this.tags = tags;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

    public List<DataAccessTag> getTags() {
        return tags;
    }

    public String getDate() {
        return date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTags(List<DataAccessTag> tags) {
        this.tags = tags;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return new StringBuffer(" ID : ").append(this.getId())
                .append(" Title : ").append(this.getTitle())
                .append(" shortDescription: ").append(this.getShortDescription())
                .append(" fullText : ").append(this.getText())
                .append(" url : ").append(this.getUrl())
                .toString();
    }

}
