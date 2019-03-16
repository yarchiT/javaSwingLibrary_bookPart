package utils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnector {
    private static String DB_URL = "jdbc:mysql://localhost:3306/Library?serverTimezone=UTC";
    private static  String DB_USER = "";
    private static  String DB_PASSWD = "";

    private static String path = "./database.properties";

    public DatabaseConnector(){

    }

    public static void initDatabaseCredentials(){
        BufferedReader reader=null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
            reader = new BufferedReader(new InputStreamReader(fis));

            String line = reader.readLine();
            while (line != null) {
                if(line.contains("DB_URL"))
                    DB_URL = line.substring(7,line.length());

                if(line.contains("user"))
                    DB_USER = line.substring(5,line.length());

                if(line.contains("password"))
                    DB_PASSWD = line.substring(9,line.length());

                // read next line
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                reader.close();
                fis.close();
            }catch (Exception ex){

            }

        }
    }

    public static Connection GetConnection() throws SQLException
    {
        return DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
    }
}
