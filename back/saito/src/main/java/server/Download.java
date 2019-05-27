package server;

public class Download {

    public String book;
    public int user;
    public int numPackages;
    public Download(int user,String book)
    {
        this.book=book;
        this.user=user;
    }
    public String getPath()
    {

        return "./books/1";
    }

}
