package edu.kit.isco.kitalumniapp.dbObjects;

import android.content.ContentValues;

import java.util.List;

import edu.kit.isco.kitalumniapp.dbServices.JobTable;

/**
 * DAO for Job
 * Created by Andre on 04.02.2015.
 */
public class DataAccessJob implements DataAccessObject {

    private long id;
    private String title;
    private String shortInfo;
    private String allText;
    private String url;
    private List<DataAccessTag> tags;
    private boolean star = false;

    public DataAccessJob() {
    }

    public DataAccessJob(List<DataAccessTag> tags, String title, String shortInfo, String url) {
        this.title = title;
        this.shortInfo = shortInfo;
        this.url = url;
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortInfo() {
        return shortInfo;
    }

    public String getAllText() {
        return allText;
    }

    public void setAllText(String allText) {
        this.allText = allText;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<DataAccessTag> getTags() {
        return tags;
    }

    public void setTags(List<DataAccessTag> tags) {
        this.tags = tags;
    }

    public void setShortDescription(String short_info) {
        this.shortInfo = short_info;
    }

    public boolean isStar() {
        return star;
    }

    public void setStar(boolean star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return new StringBuffer(" ID : ").append(this.getId())
                .append(" Title : ").append(this.getTitle())
                .append(" shortDescription: ").append(this.getShortInfo())
                .append(" fullText : ").append(this.getAllText())
                .append(" url : ").append(this.getUrl())
                .toString();
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(JobTable.ID, id);
        values.put(JobTable.TITLE, title);
        values.put(JobTable.SHORT_INFO, shortInfo);
        values.put(JobTable.FULL_TEXT, allText);
        values.put(JobTable.URL, url);
        values.put(JobTable.STAR, (star ? 1 : 0));
        return values;
    }

}
