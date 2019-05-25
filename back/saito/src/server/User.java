package server;

public class User {
    int user_id;
    String username;
    String password;
    String mail;

    Login userLogin=new Login();
    Register userRegister=new Register();
    Recommend userRecommend=new Recommend();
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
    //Functions
    public boolean login()
    {
        return userLogin.login(username,password);
    }
    public boolean register()
    {
        return userRegister.register(username,mail,password);
    }
    public void recommend()
    {
        userRecommend.recommend(user_id);
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
