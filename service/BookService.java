package smallProject.service;

import smallProject.model.Book;
import smallProject.model.User;
import smallProject.storage.BookStorage;

public class BookService {
    private final BookStorage bookStorage = new BookStorage();

    public void add(Book book) {
        bookStorage.add(book);
    }

    public Book[] getByUser(User currentUser) {
        Book[] byUser = bookStorage.getByUser(currentUser);
        for (int i = 0; i < byUser.length; i++) {
            if(new User(currentUser).equals(byUser[i]))
                break;
        }


        return byUser; //todo call from bookStorage
    }

    public Book[] getAllBooks(User currentUser) {

        return bookStorage.getAllBooks(currentUser);
    }

}

