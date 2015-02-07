package edu.kit.isco.kitalumniapp.dbObjects;

import java.util.Calendar;

/**
 * Created by Andre on 04.02.2015.
 */
public class DataAccessNews implements DataAccessObject {

    private long id;
    private String title;
    private String short_info;
    private String text;
    private String url;
    private String image_url;
    private String date;

    public DataAccessNews() {
    }

    public DataAccessNews(long id, String title, String short_info, String text, String url, String image_url, String date) {
        this.id = id;
        this.title = title;
        this.short_info = short_info;
        this.text = text;
        this.url = url;
        this.image_url = image_url;
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

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

    public String getImage_url() {
        return image_url;
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

    public void setText(String text) {
        this.text = text;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
