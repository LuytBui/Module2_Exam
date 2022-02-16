package controller;

import model.Contact;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class WriteFileService {
    static final String DATA_FILE = "src/data/contacts.csv";

    public static void writeToFIle(){

        try {
            FileWriter fileWriter = new FileWriter(DATA_FILE);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            List<Contact> list = PhoneBook.getInstance().getContacts();
            for( Contact contact : list){
                System.out.println("Đang ghi: " + contact.toCSVString());
                bufferedWriter.write(contact.toCSVString() + "\n");
            }

            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
