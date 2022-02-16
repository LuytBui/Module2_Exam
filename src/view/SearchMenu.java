package view;

import controller.PhoneBook;
import model.Contact;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SearchMenu {
    private Contact searchResult;
    static Scanner scanner = new Scanner(System.in);
    static final int OPTION_SEARCH_BY_NAME = 1;
    static final int OPTION_SEARCH_BY_PHONE = 2;
    static final int OPTION_EXIT = 0;
    static final int OPTION_DEFAULT = -1;

    public SearchMenu() {
        searchResult = null;
    }

    public void run() {
        int choice;
        printMenu();

        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            choice = OPTION_DEFAULT;
        } finally {
            scanner.nextLine();
        }

        switch (choice) {
            case OPTION_SEARCH_BY_NAME: {
                searchByName();
                break;
            }
            case OPTION_SEARCH_BY_PHONE: {
                searchByPhone();
                break;
            }
            case OPTION_EXIT:
            default:
                break;
        }
    }

    public void printMenu() {
        System.out.println("   Tìm kiếm liên hệ");
        System.out.println("1. Tìm theo tên");
        System.out.println("2. Tìm theo số điện thoại");
        System.out.println("0. Thoát");
        System.out.println("Nhập lựa chọn: ");
    }

    private void searchByName() {
        System.out.println("Nhập Full name để tìm kiếm");
        String searchName = scanner.nextLine();
        searchResult = PhoneBook.getInstance().searchByFullName(searchName);
    }

    private void searchByPhone() {
        System.out.println("Nhập SĐT để tìm kiếm");
        String phone = scanner.nextLine();
        searchResult = PhoneBook.getInstance().searchByPhone(phone);
    }

    public Contact getSearchResult() {
        return searchResult;
    }
}
