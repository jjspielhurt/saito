package server;

import Database.BookController;

import java.util.ArrayList;

public class Search {
    public ArrayList<String> bookList=new ArrayList<String>();
    public int numBooks=0;
    String query;
    public Search(String query)
    {
        this.query=query;
    }

    public ArrayList<String> getBookList() {
        BookController db=new BookController();
        bookList=db.searchBooks(query);
        return bookList;
    }
}
