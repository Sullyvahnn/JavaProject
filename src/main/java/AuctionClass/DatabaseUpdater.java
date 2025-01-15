package AuctionClass;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseUpdater {
    private static Connection con;
    private static final String URL = "jdbc:mariadb://mysql.agh.edu.pl:3306/awojnar";
    private static final String USER = "awojnar";
    private static final String PASSWORD = "kPk2n9pHLJQ7ktNz";

    public static void updateUserSalary(int id, double newSalary) {
        String updateQuery = "UPDATE userTable SET user_money = ? WHERE user_id = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
            preparedStatement.setDouble(1, newSalary); // Set the new salary
            preparedStatement.setInt(2, id);          // Specify the employee ID
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ObservableList<User> getUsersData() {
        ObservableList<User> users = FXCollections.observableArrayList();

        try {
            Statement statement = con.createStatement();
            String query = "SELECT * FROM userTable";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("user_name"),
                        resultSet.getDouble("user_money")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
    public static void establishConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established successfully!");

        } catch (SQLException e) {
            System.out.println("Error establishing connection.");
            e.printStackTrace();
        }

        con = connection;
    }
    public static ObservableList<Auction> getAuctions() {
        ObservableList<Auction> auctions = FXCollections.observableArrayList();

        try {
            Statement statement = con.createStatement();
            String query = "SELECT * FROM Auctions";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                auctions.add(new Auction(
                        resultSet.getInt("auction_id"),
                        resultSet.getString("auction_title"),
                        resultSet.getDouble("auction_price"),
                        resultSet.getInt("auction_timeout")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return auctions;
    }
}
