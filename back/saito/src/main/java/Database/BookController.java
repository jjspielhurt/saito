package Database;

import server.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookController {
    public int getRating(int id){
        String query="select average_rating from books where book_id=" + id;

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
            rst.next();
            return rst.getInt("average_rating");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void add_rating(int rate,int id) {
        double avr=getRating(id);
        avr=avr+rate/2;
        String query="update  books avarage_rating" +avr +  "where book_id=" + id;
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


    }

    ArrayList<String> searchBooks (String name){
        String query="select title from books where title like %"+name+"%";
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
            ResultSet rst= stm.getResultSet();
            while(rst.next()){
                list.add(rst.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    Book getInfo(String name){
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
            Book book = new Book(rst.getInt("book_id"),rst.getString("genre"),rst.getString("title"),rst.getInt("author_id"),rst.getInt("publish_year"),rst.getDouble("average_rating"));
             return book;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
