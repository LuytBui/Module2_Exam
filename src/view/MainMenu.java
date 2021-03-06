package view;

import controller.PhoneBook;
import controller.ReadFileService;
import controller.WriteFileService;
import model.Contact;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu {

    static Scanner scanner = new Scanner(System.in);
    static final int OPTION_SHOW_ALL = 1;
    static final int OPTION_ADD = 2;
    static final int OPTION_UPDATE = 3;
    static final int OPTION_DELETE = 4;
    static final int OPTION_SEARCH = 5;
    static final int OPTION_READ_FILE = 6;
    static final int OPTION_WRITE_FILE = 7;
    static final int OPTION_EXIT = 8;
    static final int OPTION_DEFAULT = -1;

    public void run() {
        int choice;
        boolean exit;
        do {
            printMenu();
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                choice = OPTION_DEFAULT;
            } finally {
                scanner.nextLine();
            }

            switch (choice) {
                case OPTION_SHOW_ALL: {
                    PhoneBook.getInstance().printAllContacts();
                    break;
                }
                case OPTION_ADD: {
                    showAddContact();
                    break;
                }
                case OPTION_UPDATE: {
                    showUpdateContact();
                    break;
                }

                case OPTION_DELETE: {
                    showDeleteContact();
                    break;
                }
                case OPTION_SEARCH: {
                    showSearchContact();
                    break;
                }

                case OPTION_READ_FILE: {
                    showReadFile();
                    break;
                }
                case OPTION_WRITE_FILE: {
                    showWriteFile();
                    break;
                }

                case OPTION_DEFAULT:
                    // do nothing
                    break;
            }

            exit = choice == OPTION_EXIT;
        } while (!exit);
        System.out.println("Goodbye!");
    }


    public void printMenu() {
        System.out.println("---- CH????NG TR??NH QU???N L?? DANH B??? ----");
        System.out.println("1. Xem danh s??ch");
        System.out.println("2. Th??m m???i");
        System.out.println("3. C???p nh???t");
        System.out.println("4. X??a");
        System.out.println("5. T??m ki???m");
        System.out.println("6. ?????c t??? file");
        System.out.println("7. Ghi v??o file");
        System.out.println("8. Tho??t");
        System.out.println("Ch???n ch???c n??ng: ");
    }

    private void showAddContact() {
        Contact contact = showInputContact();
        PhoneBook.getInstance().add(contact);
        System.out.println("???? th??m 1 li??n h???.");
    }


    private void showSearchContact() {
        SearchMenu searchMenu = new SearchMenu();
        searchMenu.run();
        Contact searchResult = searchMenu.getSearchResult();

        if (searchResult == null) {
            System.out.println("Kh??ng t??m th???y li??n h???!");
            return;
        }

        System.out.println("K???t qu??? : ");
        System.out.println(searchResult);

    }

    private static void showUpdateContact() {
        SearchMenu searchMenu = new SearchMenu();
        searchMenu.run();
        Contact searchResult = searchMenu.getSearchResult();

        if (searchResult == null) {
            System.out.println("Kh??ng t??m th???y li??n h???!");
            return;
        }
        System.out.println("C???p nh???t l???i th??ng tin: ");
        Contact newContact = showInputContact();
        searchResult.copyInfo(newContact);
        System.out.println("???? c???p nh???t th??ng tin!");
    }

    private static void showDeleteContact() {
        SearchMenu searchMenu = new SearchMenu();
        searchMenu.run();
        Contact searchResult = searchMenu.getSearchResult();

        if (searchResult == null) {
            System.out.println("Kh??ng t??m th???y li??n h???!");
            return;
        }

        PhoneBook.getInstance().remove(searchResult);
        System.out.println("???? x??a li??n h???");
    }

    private void showReadFile() {
        System.out.println("Thao t??c s??? x??a to??n b??? danh b??? hi???n t???i v?? c???p nh???t to??n b??? t??? file c?? s???n!!");
        System.out.println("?????ng ?? ch???y?   (Y / N)");
        String input = scanner.nextLine();
        char answer = input.charAt(0);
        char agree = 'y';
        if (answer == agree) {
            List<Contact> list = ReadFileService.readFile();
            PhoneBook.getInstance().clearList();
            PhoneBook.getInstance().add(list);
            System.out.println("Thao t??c th??nh c??ng.");
        } else {
            System.out.println("H???y thao t??c.");
        }
    }

    public void showWriteFile() {
        System.out.println("Ghi danh b??? ra file");
        WriteFileService.writeToFIle();
        System.out.println("Thao t??c th??nh c??ng.");
    }

    private static Contact showInputContact() {
        String firstName, lastName, phone, address, email, facebook;
        boolean firstNameValid, lastNameValid, phoneValid, emailValid;

        Pattern PHONE_PATTERN = Pattern.compile("0[\\d]{9}");
        Pattern NAME_PATTERN = Pattern.compile("[\\w\\s]+");
        Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


        do {
            System.out.print("Nh???p h??? (b???t bu???c): ");
            firstName = scanner.nextLine();
            Matcher matcher = NAME_PATTERN.matcher(firstName);
            firstNameValid = matcher.matches();
            if (!firstNameValid)
                System.out.println("Nh???p t??n kh??ng h???p l???");

        } while (!firstNameValid);

        do {
            System.out.print("Nh???p t??n (b???t bu???c): ");
            lastName = scanner.nextLine();
            Matcher matcher = NAME_PATTERN.matcher(lastName);
            lastNameValid = matcher.matches();
            if (!lastNameValid)
                System.out.println("Nh???p t??n kh??ng h???p l???");
        } while (!lastNameValid);


        do {
            System.out.print("Nh???p s??? ??i???n tho???i (b???t bu???c): ");
            phone = scanner.nextLine();
            Matcher matcher = PHONE_PATTERN.matcher(phone);
            phoneValid = matcher.matches();
            if (!phoneValid)
                System.out.println("S??? ??i???n tho???i c?? 10 ch??? s???, b???t ?????u b???ng s??? 0");
        } while (!phoneValid);

        System.out.print("Nh???p ?????a ch??? (c?? th??? b??? tr???ng):");
        address = scanner.nextLine();
        do {
            System.out.print("Nh???p email (c?? th??? b??? tr???ng):");
            email = scanner.nextLine();
            Matcher matcher = EMAIL_PATTERN.matcher(email);
            emailValid = email.length() == 0
                    || matcher.matches();
        } while (!emailValid);

        System.out.print("Nh???p facebook (c?? th??? b??? tr???ng):");
        facebook = scanner.nextLine();

        return new Contact(firstName, lastName, phone, address, email, facebook);
    }
}
