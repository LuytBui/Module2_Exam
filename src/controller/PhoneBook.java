package controller;

import model.Contact;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private static PhoneBook instance;
    private List<Contact> contacts;

    public static PhoneBook getInstance(){
        if (instance == null){
            instance = new PhoneBook();
        }
        return instance;
    }

    private PhoneBook() {
        contacts = new ArrayList<>();
    }

    public void add (List<Contact> list){
        contacts.addAll(list);
    }
 public void add (Contact contact){
        contacts.add(contact);
    }

    public void remove (Contact contact){
        contacts.remove(contact);
    }

    public Contact searchByFullName(String searchFullName){
        for (Contact contact : contacts){
            boolean isFound = contact.getFullName().toLowerCase().contains(searchFullName.toLowerCase());
            if (isFound)
                return contact;
        }
        return null;
    }

    public Contact searchByPhone(String phone){
        for (Contact contact : contacts){
            boolean isFound = contact.getPhone().toLowerCase().contains(phone.toLowerCase());
            if (isFound)
                return contact;
        }
        return null;
    }

    public void printListContacts(List<Contact> list){
        if (list.size() == 0){
            System.out.println("Danh sách rỗng!");
            return;
        }
        System.out.println("Có "+ list.size() + " liên hệ: ");
        for (Contact contact : contacts){
            System.out.println(contact);
        }
    }
    public void printAllContacts(){
        printListContacts(this.contacts);
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void clearList(){
        contacts.clear();;
    }

}
