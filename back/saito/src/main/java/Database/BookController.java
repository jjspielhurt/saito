package Database;

import server.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookController {
    public int getRating(String name) {
        String query = "select average_rating from books where book_name=\'" + name + "\'";

        Statement stm = null;
        try {
            stm = Database.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stm.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rst = stm.getResultSet();
            rst.next();
            return rst.getInt("average_rating");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean add_rating(int rate, String name) {
        double avr = getRating(name);
        avr = avr + rate / 2;
        String query = "update  books avarage_rating=" + avr + " where title=\'" + name + "\'";
        Statement stm = null;
        try {
            stm = Database.getConnection().createStatement();
        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
        try {
            stm.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;

    }

    public ArrayList<String> searchBooks(String name) {
        String query = "select title from books where title like %" + name + "%";
        ArrayList<String> list = new ArrayList<>();
        Statement stm = null;
        try {
            stm = Database.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stm.executeQuery(query);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        try {
            ResultSet rst = stm.getResultSet();
            while (rst.next()) {
                list.add(rst.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    public  String getInfo(String name){
        String info = "";
        String query="select * from books where name like %" +name +"%";

        Statement stm = null;
        try {
            stm = Database.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stm.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rst= stm.getResultSet();
            info= "Title: "+rst.getString("title") + "\nAuthor: "+ rst.getInt("author_id")+ "\nYear: " +rst.getInt("publish_year")+ "\nGenre: "+rst.getString("genre")+"\nRating: " +rst.getDouble("average_rating");
            return info;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
