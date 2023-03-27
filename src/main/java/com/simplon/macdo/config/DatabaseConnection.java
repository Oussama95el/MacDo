package com.simplon.macdo.config;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


class DatabaseConnection {

    @FXML
    private Label myLabel;

    public void initialize() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mcdonald", "root", "");

            // Do something with the connection
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM menu_item");
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                myLabel.setText("There are " + count + " rows in the table.");
            }

            // Close the connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}