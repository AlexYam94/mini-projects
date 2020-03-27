package com.company;

public class Contact {
    private String name;
    private String number;

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public Contact(String name, String number) {

        this.name = name;
        this.number = number;
    }

    public static Contact creatContact(String name, String phonenumber){
        return new Contact(name,phonenumber);
    }
}
