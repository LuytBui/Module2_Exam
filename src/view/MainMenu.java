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
        System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ ----");
        System.out.println("1. Xem danh sách");
        System.out.println("2. Thêm mới");
        System.out.println("3. Cập nhật");
        System.out.println("4. Xóa");
        System.out.println("5. Tìm kiếm");
        System.out.println("6. Đọc từ file");
        System.out.println("7. Ghi vào file");
        System.out.println("8. Thoát");
        System.out.println("Chọn chức năng: ");
    }

    private void showAddContact() {
        Contact contact = showInputContact();
        PhoneBook.getInstance().add(contact);
        System.out.println("Đã thêm 1 liên hệ.");
    }


    private void showSearchContact() {
        SearchMenu searchMenu = new SearchMenu();
        searchMenu.run();
        Contact searchResult = searchMenu.getSearchResult();

        if (searchResult == null) {
            System.out.println("Không tìm thấy liên hệ!");
            return;
        }

        System.out.println("Kết quả : ");
        System.out.println(searchResult);

    }

    private static void showUpdateContact() {
        SearchMenu searchMenu = new SearchMenu();
        searchMenu.run();
        Contact searchResult = searchMenu.getSearchResult();

        if (searchResult == null) {
            System.out.println("Không tìm thấy liên hệ!");
            return;
        }
        System.out.println("Cập nhật lại thông tin: ");
        Contact newContact = showInputContact();
        searchResult.copyInfo(newContact);
        System.out.println("Đã cập nhật thông tin!");
    }

    private static void showDeleteContact() {
        SearchMenu searchMenu = new SearchMenu();
        searchMenu.run();
        Contact searchResult = searchMenu.getSearchResult();

        if (searchResult == null) {
            System.out.println("Không tìm thấy liên hệ!");
            return;
        }

        PhoneBook.getInstance().remove(searchResult);
        System.out.println("Đã xóa liên hệ");
    }

    private void showReadFile() {
        System.out.println("Thao tác sẽ xóa toàn bộ danh bạ hiện tại và cập nhật toàn bộ từ file có sẵn!!");
        System.out.println("Đồng ý chạy?   (Y / N)");
        String input = scanner.nextLine();
        char answer = input.charAt(0);
        char agree = 'y';
        if (answer == agree) {
            List<Contact> list = ReadFileService.readFile();
            PhoneBook.getInstance().clearList();
            PhoneBook.getInstance().add(list);
            System.out.println("Thao tác thành công.");
        } else {
            System.out.println("Hủy thao tác.");
        }
    }

    public void showWriteFile() {
        System.out.println("Ghi danh bạ ra file");
        WriteFileService.writeToFIle();
        System.out.println("Thao tác thành công.");
    }

    private static Contact showInputContact() {
        String firstName, lastName, phone, address, email, facebook;
        boolean firstNameValid, lastNameValid, phoneValid, emailValid;

        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        do {
            System.out.print("Nhập họ (bắt buộc): ");
            firstName = scanner.nextLine();
            firstNameValid = firstName.length() > 0;
        } while (!firstNameValid);

        do {
            System.out.print("Nhập tên (bắt buộc): ");
            lastName = scanner.nextLine();
            lastNameValid = lastName.length() > 0;
        } while (!lastNameValid);


        do {
            System.out.print("Nhập số điện thoại (bắt buộc): ");
            phone = scanner.nextLine();
            phoneValid = phone.length() > 0; /// phát triển thêm
        } while (!phoneValid);

        System.out.print("Nhập địa chỉ (có thể bỏ trống):");
        address = scanner.nextLine();
        do {
            System.out.print("Nhập email (có thể bỏ trống):");
            email = scanner.nextLine();
            Matcher matcher = emailPattern.matcher(email);
            emailValid = email.length() == 0
                    || matcher.matches();
        } while (!emailValid);

        System.out.print("Nhập facebook (có thể bỏ trống):");
        facebook = scanner.nextLine();

        return new Contact(firstName, lastName, phone, address, email, facebook);
    }
}
