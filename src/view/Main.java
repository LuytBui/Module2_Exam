package view;

import controller.PhoneBook;
import model.Contact;

public class Main {

    public static void main(String[] args) {
//        PhoneBook.getInstance().add(new Contact("a", "b", "566565"));
//        PhoneBook.getInstance().add(new Contact("aea", "sa", "121212", "ádf sdf", "dàeafea.sdfea@fsf.sf", "lkjfljl jasd"));
        new MainMenu().run();
    }
}
