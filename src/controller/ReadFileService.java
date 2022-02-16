package controller;

import model.Contact;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFileService {
    static final String DATA_FILE = "src/data/contacts.csv";

    public static List<Contact> readFile(){
        List<Contact> inputList = new ArrayList<>();

        try{
            FileReader fileReader = new FileReader(DATA_FILE);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null){
                String[] split = line.split(",");
                String firstName, lastName, phone, address, email, facebook;

                try {
                    firstName = split[0].trim();
                    lastName = split[1].trim();
                    phone = split[2].trim();
                    address = split[3].trim();
                    email = split[4].trim();
                    facebook = split[5].trim();
                    Contact contact = new Contact(firstName, lastName, phone, address, email, facebook);
                    inputList.add(contact);
                } catch (Exception e){
                    // do nothing
                }
            }

            bufferedReader.close();
            fileReader.close();


        } catch (Exception e){
            e.printStackTrace();
        }


        return inputList;

    }
}
