package io.github.zkhan93.contactpage.model;

/**
 * Created by Zeeshan Khan on 1/9/2016.
 */
public class Contact {
    private long id;
    private String name,number;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
