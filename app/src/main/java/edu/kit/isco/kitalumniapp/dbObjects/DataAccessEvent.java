package edu.kit.isco.kitalumniapp.dbObjects;

import android.content.ContentValues;

import java.util.Calendar;

import edu.kit.isco.kitalumniapp.dbServices.EventTable;

/**
 * Created by Andre on 04.02.2015.
 */
public class DataAccessEvent implements DataAccessObject {

    private long id;
    private String title;
    private String short_info;
    private String allText;
    private String url;
    private String date;

    public DataAccessEvent() {
    }

    public DataAccessEvent(long id, String title, String short_info, String allText, String url, String date) {
        this.id = id;
        this.title = title;
        this.short_info = short_info;
        this.allText = allText;
        this.url = url;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShort_info() {
        return short_info;
    }

    public String getAllText() {
        return allText;
    }

    public String getUrl() {
        return url;
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

    public void setShort_info(String short_info) {
        this.short_info = short_info;
    }

    public void setText(String allText) {
        this.allText = allText;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return new StringBuffer(" ID : ").append(this.getId())
                .append(" Title : ").append(this.getTitle())
                .append(" shortDescription: ").append(this.getShort_info())
                .append(" fullText : ").append(this.getAllText())
                .append(" url : ").append(this.getUrl())
                .toString();
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(EventTable.ID, id);
        values.put(EventTable.SHORT_INFO, short_info);
        values.put(EventTable.FULL_TEXT, allText);
        values.put(EventTable.URL, url);
        values.put(EventTable.DATE, date);
        return values;
    }

}