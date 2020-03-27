package com.company;

import java.util.ArrayList;

public class MobilePhone {
    private String myNumber;
    private ArrayList<Contact> myContacts;

    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<>();
    }

    public boolean AddNewContact(Contact contact){
        if(findContact(contact.getName())>=0){
            System.out.println("Contact is already on file");
            return false;
        }
        myContacts.add(contact);
        return true;
    }

    public int findContact(Contact contact){
        return this.myContacts.indexOf(contact);
    }

    public int findContact(String name){
        for(int i=0;i<this.myContacts.size();i++){
            Contact contact = this.myContacts.get(i);
            if(contact.getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public boolean updateContace(Contact newContact, Contact oldContact){
        int foundPosition = findContact(oldContact);
        if(foundPosition<0){
            System.out.println(oldContact.getName() +" is not found");
            return false;
        }
        this.myContacts.set(foundPosition,newContact);
        System.out.println(oldContact.getName()+" was replaced");
        return true;
    }

}
