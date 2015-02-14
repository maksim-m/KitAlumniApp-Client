package edu.kit.isco.kitalumniapp;

/**
 * Created by Yannick on 14.02.15 | KW 7.
 */
public class Contact {

    public String name;
    public String shortDescription;
    public String phoneNumber;
    public String mailAddress;

    public Contact (String name, String shortDescription, String phoneNumber, String mailAddress) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.phoneNumber = phoneNumber;
        this.mailAddress = mailAddress;
    }
}
