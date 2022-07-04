package smallProject;

import smallProject.exception.UserAlreadyExistsException;
import smallProject.exception.UserNotFoundException;
import smallProject.model.Book;
import smallProject.model.User;
import smallProject.service.BookService;
import smallProject.service.UserService;

import java.util.Scanner;

public class Application {
    private final UserService userService = new UserService();
    private final BookService bookService = new BookService();
    private Scanner scanner = new Scanner(System.in);
    private User currentUser;


    public void start() {
        boolean findNormalCommand = false;
        while (!findNormalCommand) {
            System.out.println("For registration please input 1");
            System.out.println("For login please input 2");
            String command = scanner.nextLine();
            switch (command) {
                case "1": {
                    registration();
                    findNormalCommand = true;
                    break;
                }
                case "2": {
                    login();
                    findNormalCommand = true;
                    break;
                }
                default: {
                    System.out.println("Please input only 1 or 2");
                    System.out.println("00000000000");
                    System.out.println("22222222222222");
                }
            }

        }
    }

    private void login() {
        System.out.println(" ** Login process **");

        System.out.println("Please input your email");
        String email = scanner.nextLine();

        System.out.println("Please input your password");
        String password = scanner.nextLine();

        try {
            currentUser = userService.login(email, password);
            System.out.println("Login success");
            start();   //removable

        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            start();
        }
    }

    private void registration() {
        System.out.println(" ** Registration process **");

        System.out.println("Please input your name");
        String name = scanner.nextLine();

        System.out.println("Please input your surname");
        String surname = scanner.nextLine();

        System.out.println("Please input your email");
        String email = scanner.nextLine();

        System.out.println("Please input your password");
        String password = scanner.nextLine();
        try {
            currentUser = userService.registration(new User(name, surname, email, password));
            System.out.println("Registration success");
            menu();
        } catch (UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
            start();
        }
    }


    public void menu() {
        boolean normalCommandFind = false;
        while (!normalCommandFind) {
            System.out.println("For add new book press 1");
            System.out.println("For see your books press 2");
            System.out.println("For see all books press 3");
            System.out.println("For logout press 4");
            String command = scanner.nextLine();
            switch (command) {
                case "1": {
                    addNewBook();
                    normalCommandFind = true;
                    break;
                }
                case "2": {
                    printCurrentUserBooks();
                    normalCommandFind = true;
                    break;
                }
                case "3": {
                    printAllBooks();
                    normalCommandFind = true;
                    break;
                }
                case "4": {
                    logOut();
                    normalCommandFind = true;
                    break;
                }
                default: {
                    System.out.println("Please input only 1,2,3,4 numbers");
                    break;
                }
            }
        }
    }

    private void addNewBook() {
        System.out.println("*** Add book process ***");

        System.out.println("Input book name");
        String name = scanner.nextLine();
        int page = defineNumberValue("Please input book page");
        int year = defineNumberValue("Pleas input book year");
        bookService.add(new Book(name, page, year, currentUser));
        System.out.println("Your book added");
        menu();

    }

    private void printCurrentUserBooks() {
        Book[] byUser = bookService.getByUser(new User(currentUser));
        System.out.println(" *** User Books *** ");
//        for (Book book : byUser) {
//            if ((book == null) && Arrays.equals(byUser, bookService.getByUser(currentUser))) {
//                break;
//            }
//            if(book == null && !books.equals(bookService.getByUser(currentUser))){
//                break;
//            }
//            System.out.println(book);
        for (Book book : byUser) {
            if (byUser == (bookService.getByUser(new User(currentUser))) && book != null) {
                System.out.println(book);
            } else break;
        }

        menu();

    }


    private void printAllBooks() {
        Book[] books = bookService.getAllBooks(currentUser);
        System.out.println("***All Books***");
        for (Book book : books) {
            if (book != null) {
                System.out.println(book);
            }


        }
        menu();
    }


    private void logOut() {
        System.out.println("*** New User ***");

        start();
    }


    private int defineNumberValue(String text) {
        int page = 0;
        boolean isNumberDefined = false;
        while (!isNumberDefined) {
            try {
                System.out.println(text);
                scanner = new Scanner(System.in);
                page = Integer.parseInt(scanner.nextLine());
                isNumberDefined = true;
            } catch (Exception e) {
                System.out.println("Please input only numeric value");
            }
        }
        return page;
    }


}


