package dao;

import dto.BookDto;
import utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;

public class BookDao {

    private static String insertBookQuery = "insert into Book"
            + "(name, author, type, size, amount, editionId) VALUES"
            + "(?,?,?,?,?,?)";

    public static boolean addBook (BookDto bookDto){
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = DatabaseConnector.GetConnection();
            statement = createInsertBookQuery(connection, bookDto);
            statement.executeUpdate();

        }catch (Exception ex){
            return false;
        }
        return true;
    }


    public static ArrayList<ArrayList<String>> searchBooks(String searchInput){
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        ArrayList<ArrayList<String>> data = new ArrayList<>();

        try{
            connection = DatabaseConnector.GetConnection();
            statement = connection.createStatement();
            if (searchInput.equals(""))
                rs = statement.executeQuery("SELECT * FROM Book");
            else
                rs = statement.executeQuery("SELECT * FROM Book WHERE name=\'"+searchInput+"\' OR type=\'"+searchInput+"\' OR author=\'"+searchInput+"\'");

            while (rs.next()){
                data.add((getBookValues(rs)));
            }

        }catch (SQLException ex){
            System.out.println("Error while connecting");
        }finally {
            try{
                connection.close();
                statement.close();
            }catch (SQLException ex){

            }
        }

        return data;
    }

    private static PreparedStatement createInsertBookQuery(Connection conn, BookDto bookDto) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(insertBookQuery, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, bookDto.getName());
        ps.setString(2, bookDto.getAuthor());
        ps.setString(3, bookDto.getType());
        ps.setInt(4, bookDto.getAmountOfPages());
        ps.setInt(5, bookDto.getAmountOfBooks());
        ps.setInt(6, bookDto.getEditionNumber());

        return ps;
    }

    private static ArrayList<String> getBookValues(ResultSet rs) throws SQLException {
        ArrayList<String> bookValues = new ArrayList<>();
        bookValues.add(rs.getString("name"));
        bookValues.add(rs.getString("author"));
        bookValues.add(rs.getString("type"));
        bookValues.add(rs.getString("size"));
        bookValues.add(rs.getString("amount"));
        bookValues.add(rs.getString("editionID"));

        return bookValues;
    }
}
