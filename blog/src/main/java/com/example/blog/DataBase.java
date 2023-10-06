package com.example.blog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Simple Java program to connect to MySQL database running on localhost and
 * running SELECT and INSERT query to retrieve and add data.
 * @author Javin Paul
 */
public class DataBase {

    // JDBC URL, username and password of MySQL server
    private final static String url = "jdbc:mysql://localhost:3306/dog_exhebition?autoReconnect=true&useSSL=false";
    private final static String user = "root";
    private final static String password = "1234qseft";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;

    public static void main(String args[]) {
        ArrayList <Auto> auto_list = (ArrayList<Auto>) getAutos();
        for (int i = 0; i < auto_list.size(); i++)
        {
            Auto auto = auto_list.get(i);
            System.out.println(auto.getUrl());
        }
    }

    private static void GetDBConnection() {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private static void CloseDBConnection() {
        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
    }

    public static Iterable<Auto> getAutos() {
        GetDBConnection();
        String query = "select * from auto";
        ArrayList<Auto> ar_auto = new ArrayList<>();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Auto auto = new Auto();
                auto.setAuto(Integer.parseInt(rs.getString("id")), rs.getString("name"), rs.getString("comand"), rs.getString("discription"), rs.getString("url"));
                System.out.println(auto.getId());
                ar_auto.add(auto);
            }
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }finally {
            //close connection ,stmt and resultset here
            CloseDBConnection();
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            //try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return ar_auto;
    }
}