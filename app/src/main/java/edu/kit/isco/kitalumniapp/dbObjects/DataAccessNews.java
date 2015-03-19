package edu.kit.isco.kitalumniapp.dbObjects;

import android.content.ContentValues;

import edu.kit.isco.kitalumniapp.dbServices.NewsTable;

/**
 * Dao for News.
 * Created by Andre on 04.02.2015.
 */
public class DataAccessNews implements DataAccessObject {

    private long id;
    private String title;
    private String shortInfo;
    private String text;
    private String url;
    private String imageUrl;
    private String date;

    public DataAccessNews() {
    }

    public DataAccessNews(long id, String title, String shortInfo, String text, String url, String imageUrl, String date) {
        this.id = id;
        this.title = title;
        this.shortInfo = shortInfo;
        this.text = text;
        this.url = url;
        this.imageUrl = imageUrl;
        this.date = date;
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

    public String getShortDescription() {
        return shortInfo;
    }

    public void setShortDescription(String shortDescription) {
        this.shortInfo = shortDescription;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return new StringBuffer(" ID : ").append(this.getId())
                .append(" Title : ").append(this.getTitle())
                .append(" shortDescription: ").append(this.getShortDescription())
                .append(" fullText : ").append(this.getText())
                .append(" url : ").append(this.getUrl())
                .append(" imageUrl : ").append(this.getImageUrl())
                .toString();
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(NewsTable.ID, id);
        values.put(NewsTable.TITLE, title);
        values.put(NewsTable.SHORT_INFO, shortInfo);
        values.put(NewsTable.FULL_TEXT, text);
        values.put(NewsTable.URL, url);
        values.put(NewsTable.IMAGE_URL, imageUrl);
        values.put(NewsTable.DATE, date);
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataAccessNews that = (DataAccessNews) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
