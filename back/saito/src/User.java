public class User {
    int user_id;
    String username;
    String pass;
    String mail;

    Login userLogin=new Login();
    Register userRegister=new Register();
    public boolean login()
    {
        return userLogin.login(username,pass);
    }
    public boolean register()
    {
        return userRegister.register(username,mail,pass);
    }
    public void recommend()
    {

    }
}
