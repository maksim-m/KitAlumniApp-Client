package edu.kit.isco.kitalumniapp;

/**
 * Created by Yannick on 14.02.15 | KW 7.
 */
public class Contact {

    private String name;
    private String shortDescription;
    private String phoneNumber;
    private String mailAddress;
    private String website;


    public Contact (String name, String shortDescription, String phoneNumber, String mailAddress, String website) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.phoneNumber = phoneNumber;
        this.mailAddress = mailAddress;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


}
