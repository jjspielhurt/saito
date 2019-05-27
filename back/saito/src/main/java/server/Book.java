package server;

public class Book {
    public int id;
    public String genre;
    public int author;
    public String title;
    public int year;
    public double rating;


    public Book(int id, String genre, String title, int author, int year, double rating){
        this.rating=rating;
        this.id=id;
        this.author=author;
        this.title=title;
        this.year=year;
    }
    public Book(){}

}
