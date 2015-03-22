package edu.kit.isco.kitalumniapp;

/**
 * Created by Yannick on 15.02.15 | KW 7.
 * Class to store information and the type of information from a child of an Contact
 */
public class ContactChildItem {

    private String content;
    private int id;

    /**
     * Constructor to init a new Child
     * @param content The information which will later be displayed and used for intent
     * @param id Information of the type
     */
    public ContactChildItem(String content, int id) {
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
