package server;

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

        bookList.add("hi book1");
        bookList.add("hi book2");
        numBooks=2;
        return bookList;
    }
}
