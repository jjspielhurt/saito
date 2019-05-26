package server;

import java.util.ArrayList;

public class User {
    int user_id;
    String username;
    String password;
    String mail;
    public ArrayList<String> bookList=new ArrayList<String>();
    public int numRecommendations;

    Login userLogin=new Login();
    Register userRegister=new Register();
    Recommend userRecommend=new Recommend();
    Rate userRate=new Rate();

    //Setters
    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumRecommendations() {
        return numRecommendations;
    }

    public ArrayList<String> getBookList() {
        return bookList;
    }
    public boolean rate(int bookID,int rating)
    {
        return userRate.rate(user_id,bookID,rating);
    }

    //Functions
    public boolean login()
    {
        int user=userLogin.login(username,password);
        if(user==-1) return false;
        else
        {
            setUser_id(user);
            return true;
        }
    }
    public boolean register()
    {
        return userRegister.register(username,mail,password);
    }
    public void recommend()
    {
        userRecommend.recommend(user_id);
        this.bookList=userRecommend.bookList;
        this.numRecommendations=userRecommend.numRecommendations;

    }
    //Constructors
    public User(String username,String pass,String mail)
    {
        if (!username.isEmpty())
        {
            this.username=username;
            if(!pass.isEmpty())
            {
                this.password=pass;

            }
        }
    }
    public User()
    {

    }


}
