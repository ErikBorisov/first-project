package smallProject.storage;

import smallProject.model.Book;
import smallProject.model.User;

import java.util.Objects;


public class BookStorage {
    private Book[] books = new Book[10];

    private int size = 0;



    public void add(Book book) {
        if (size >= books.length)
            extend();
        books[size] = book;
        size++;
    }


    private void extend() {
        Book[] newBookList = new Book[books.length + 5];
        for (int i = 0; i < books.length; i++) {
            newBookList[i] = books[i];
        }
        books = newBookList;
    }

    public Book[] getByUser(User currentUser) {
//        if(currentUser.equals(books)) {
//            for (int i = 0; i < books.length; i++) {
//               books[0] = books[i];
//               getByUser(currentUser);
//            }
//        }
//        return books;
        if (Objects.equals(new User(currentUser), books)) {
            return getByUser(currentUser);
        }
        return books;
    }


    public Book[] getAllBooks(User currentUser){

        return books;
    }




}

