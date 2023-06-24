package Utilities;

import java.sql.*;
public class ConnectionToBDD {
    private static String url = "jdbc:mysql://localhost:3307/firstswingapp";
    private static String user = "root";

    private static String passwd = "root";


    private static Connection connect;

    public static Connection getInstance(){
        if(connect == null){
            try {
                connect = DriverManager.getConnection(url, user, passwd);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connect;
    }

  }
