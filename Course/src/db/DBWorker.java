package db;

import Data.Obj;
import Data.Product;
import History.HistoryObj;
import History.ProductHistory;

import java.sql.*;
import java.util.ArrayList;

public class DBWorker {
    public static final String PATH_TO_DB_FILE = "mp.db";
    public static final String URL = "jdbc:sqlite:" + PATH_TO_DB_FILE;
    public static Connection connection;

    public static void initDB() throws SQLException{
            connection = DriverManager.getConnection(URL);
            if(connection!=null){
                createDB();
            }
    }

    public static void closeDB() throws SQLException {
            connection.close();
    }

    public static void createDB() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE if not exists 'objects' ('id' INTEGER PRIMARY KEY,'Name' text, 'Price' text, 'URL' text, 'Market' INTEGER);");
        statement.execute("CREATE TABLE if not exists 'history' ('id' INTEGER, 'Date' text, 'CurrPrice' text, 'PrevPrice' text, 'Name' text, 'obj_id' INTEGER NOT NULL, FOREIGN KEY (obj_id) REFERENCES objects (id));");
        statement.close();
    }

    public static void addObj(int id, String Name, String Price, String URL, int Market) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO objects(id, Name, Price, URL, Market) VALUES(?,?,?,?,?);");
        statement.setInt(1, id);
        statement.setString(2, Name);
        statement.setString(3, Price);
        statement.setString(4, URL);
        statement.setInt(5, Market);
        statement.execute();
        statement.close();
    }

    public static ArrayList<Obj> getObj() throws SQLException {
        Statement statement = connection.createStatement();
        ArrayList<Obj> obj = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT objects.id, objects.Name, objects.Price, objects.URL, objects.Market FROM objects");
        while (resultSet.next())
            obj.add(new Product(
                    resultSet.getInt   ("id"),
                    resultSet.getString("Name"),
                    resultSet.getString("Price"),
                    resultSet.getString("URL"),
                    resultSet.getInt   ("Market")));
        statement.close();
        return obj;
    }

    public static void addHistory(int id, String Date, String CurrPrice, String PrevPrice,  String Name, int obj_id) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("INSERT INTO history(id, Date, CurrPrice, PrevPrice, Name, obj_id) VALUES(?,?,?,?,?,?);");
        statement.setInt(1, id);
        statement.setString(2, Date);
        statement.setString(3, CurrPrice);
        statement.setString(4, PrevPrice);
        statement.setString(5, Name);
        statement.setInt(6, obj_id);
        statement.execute();
        statement.close();
    }

    public static ArrayList<HistoryObj> getHistory() throws SQLException {

        Statement statement = connection.createStatement();
        ArrayList<HistoryObj> history = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT history.id, history.Date, history.CurrPrice, history.PrevPrice, history.Name FROM history");
        while (resultSet.next())
            history.add(new ProductHistory(
                    resultSet.getInt("id"),
                    resultSet.getString("Date"),
                    resultSet.getString("CurrPrice"),
                    resultSet.getString("PrevPrice"),
                    resultSet.getString("Name")
            ));
        statement.close();
        return history;
    }

    public static void delete(int id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM objects WHERE objects.id =" + id);
        statement.execute("DELETE FROM history WHERE history.obj_id ="+id);
        statement.close();
    }

    public static void deleteDB() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM objects");
        statement.execute("DELETE FROM history");
        statement.close();
    }

    public static void setPrice(int id, String price) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("UPDATE objects SET Price = '?' WHERE id = ?;");
            statement.setString(1, price);
            statement.setInt(2, id);
            statement.execute();
        statement.close();
    }
}
