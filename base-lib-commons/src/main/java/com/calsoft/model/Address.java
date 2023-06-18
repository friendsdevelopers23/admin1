package com.calsoft.model;


public class Address {
    public final static int CITY_ATTRIBUTE_ID = 26;
    public final static int COMPANY_ATTRIBUTE_ID = 24;
    public final static int COUNTRY_ATTRIBUTE_ID = 27;
    public final static int FAX_ATTRIBUTE_ID = 32;
    public final static int FIRSTNAME_ATTRIBUTE_ID = 20;
    public final static int LASTNAME_ATTRIBUTE_ID = 22;
    public final static int POSTALCODE_ATTRIBUTE_ID = 30;
    public final static int STATE_ATTRIBUTE_ID = 28;
    public final static int STREETADDRESS_ATTRIBUTE_ID = 25;
    public final static int TELEPHONE_ATTRIBUTE_ID = 31;

    private int entityId;
    private String city;
    private String company;
    private String region;
    private String fax;
    private String firstName;
    private String lastName;
    private String postCode;
    private String state;
    private String street;
    private long telephone;

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public long getTelephone() {
        return telephone;
    }

    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }


}
