package edu.kit.isco.kitalumniapp;

/**
 * Created by Yannick on 15.02.15 | KW 7.
 * Class to store information and the type of information
 */
public class Child {

    private String content;
    private int id;

    public Child (String content, int id) {
        this.content = content;
        this.id = id;
    }

    /**
     * Returns the Information
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns the type of information
     * @return
     */
    public int getId() {
        return id;
    }






}
