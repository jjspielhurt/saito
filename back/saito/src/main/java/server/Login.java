package server;

import Database.UserController;

//return user_id
public class Login {
    public int login(String username,String password)
    {
        UserController db=new UserController();
        if(db.login(username,password))
            return db.getUserId(username);
        else return -1;
    }
}
