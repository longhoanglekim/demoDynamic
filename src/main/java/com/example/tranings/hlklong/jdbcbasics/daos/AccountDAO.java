package com.example.tranings.hlklong.jdbcbasics.daos;

import com.example.tranings.hlklong.jdbcbasics.models.Account;
import com.example.tranings.hlklong.jdbcbasics.utils.DatabaseConnection;

import java.sql.*;

public class AccountDAO {


    public boolean addAccount(String firstname, String lastname, int bal, Connection connection)  {
        String query = "INSERT INTO account(firstname,lastname,bal) VALUES(?, ?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);){
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setInt(3, bal);
            int res = preparedStatement.executeUpdate();
            if (res > 0) {
                System.out.println("Account added successfully");
                return true;
            } else {
                System.out.println("Account not added");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    public ResultSet getAccounts(Connection connection) {
        String query = "SELECT * FROM account";
        try (Statement statement = connection.createStatement();) {
            System.out.println("Select all");
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Cannot get accounts");
        return null;
    }

    public Account getAccountById(Connection connection, int accno) throws SQLException {
        String query = "SELECT * FROM account WHERE accno = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);){
            preparedStatement.setInt(1, accno);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Account(resultSet.getInt("accno"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getInt("bal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

}