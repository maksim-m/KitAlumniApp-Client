package edu.kit.isco.kitalumniapp;

/**
 * This class represent the Contacts in ContactFragment
 * Created by Yannick on 14.02.15 | KW 7.
 */
public class Contact {

    //Name of the contact
    private String name;
    //A short description of the contact
    private String shortDescription;
    //Phone number of the contact
    private String phoneNumber;
    //Mail address of the contact
    private String mailAddress;
    //Website of the contact
    private String website;


    /**
     * A contact with different information stored
     * @param name
     * @param shortDescription
     * @param phoneNumber
     * @param mailAddress
     * @param website
     */
    public Contact (String name, String shortDescription, String phoneNumber, String mailAddress, String website) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.phoneNumber = phoneNumber;
        this.mailAddress = mailAddress;
        this.website = website;
    }

    /**
     * Returns the name of the contact.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the contact.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the short description of the contact
     * @return
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Set the short description of the contact
     * @param shortDescription
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Get the phone number of the contact.
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the phone number
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the mail address of the contact
     * @return
     */
    public String getMailAddress() {
        return mailAddress;
    }

    /**
     * Set the mail address of the contact
     * @param mailAddress
     */
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    /**
     * Get the website of the contact
     * @return
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Set the website of the contact
     * @param website
     */
    public void setWebsite(String website) {
        this.website = website;
    }


}
