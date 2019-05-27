package Database;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {

    public boolean login(String user, String pass){

        String call="{call login(" + user + ", " + pass + ")}";
        try {
            CallableStatement stm = Database.getConnection().prepareCall(call);
            stm.executeQuery();
            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
    public boolean register(String name, String pass){
        String call="{call login(" + name + ", " + pass + ")}";
        try {
            CallableStatement stm=Database.getConnection().prepareCall(call);
            stm.executeQuery();
            return login(name,pass);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getFolowers(int id){
        String query="select count (*) from followers user_id_followed=" +id;

        try {
            PreparedStatement stm=Database.getConnection().prepareStatement(query);
            stm.executeQuery();
            ResultSet rst=stm.getResultSet();
            rst.next();
            return rst.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;

    }
    public int getFolowing(int id){
        String query="select count (*) from followers user_id=" +id;

        try {
            PreparedStatement stm=Database.getConnection().prepareStatement(query);
            stm.executeQuery();
            ResultSet rst=stm.getResultSet();
            rst.next();
            return rst.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;

    }

}
