package edu.kit.isco.kitalumniapp;

import android.test.AndroidTestCase;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessNews;

public class DataAccessNewsTest extends AndroidTestCase {

    DataAccessNews news;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        news = new DataAccessNews(1, "test title", "short info", "text", "http://example.com/", "http://example.com/image.png", "20.03.2015");
    }

    public void testGetters() {
        assertEquals(1, news.getId());
        assertEquals("test title", news.getTitle());
        assertEquals("short info", news.getShortDescription());
        assertEquals("http://example.com/", news.getUrl());
        assertEquals("http://example.com/image.png", news.getImageUrl());
        assertEquals("20.03.2015", news.getDate());
    }
}
