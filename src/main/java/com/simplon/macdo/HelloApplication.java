package com.simplon.macdo;

import com.simplon.macdo.model.Menu;
import com.simplon.macdo.model.MenuItem;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;



public class HelloApplication extends Application {

    double totalValue = 0;


    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("McDonald's Menu");

        // display an image logo of McDonald in the top left corner
        var logo = new Label("McDonald's");
        logo.setPrefWidth(200);
        logo.setPrefHeight(100);
        logo.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #fdca6b; -fx-font-size: 24px; -fx-alignment: center;");
        logo.setAlignment(Pos.CENTER);


        var items = Menu.getItems();

        // display the menu items
        var grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        var row = 0;
        for (MenuItem item : items) {
            var label = new Label(item.name());
            label.setPrefWidth(200);
            grid.add(label, 0, row);

            var spinner = new Spinner<Integer>();
            spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
            spinner.setEditable(true);
            grid.add(spinner, 1, row);

            var price = new Label(String.format("%.2f", item.price()));
            price.setPrefWidth(100);
            grid.add(price, 2, row);

            var discount = new Label(item.hasDiscount() ? "Yes" : "No");
            discount.setPrefWidth(100);
            grid.add(discount, 3, row);

            var total = new Label("0.00");
            total.setPrefWidth(100);
            grid.add(total, 4, row);





            spinner.valueProperty().addListener((obs, oldValue, newValue) -> {
                total.setText(String.format("%.2f", newValue * item.price()));
            });



            // add event listener when total is changed to update totalValue
            spinner.valueProperty().addListener((obs, oldValue, newValue) -> {

                // then, we need to update the totalValue
                totalValue = totalValue + (newValue * item.price());

            });



            row++;
        }
        // divider line
        var divider = new Label();
        divider.setPrefWidth(600);
        divider.setPrefHeight(0.2);
        divider.setStyle("-fx-background-color: #000000;");

        Button placeOrderButton = new Button("Place Order");
        Label grandTotalLabel = new Label();

        placeOrderButton.setOnAction(e -> {
            // loop through all the items and get the total
            grandTotalLabel.setText("Grand Total: " + totalValue);
            // reset totalValue
            totalValue = 0;
        });





        var root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        root.add(logo, 0, 0);
        root.add(grid, 0, 1);
        root.add(divider, 0, 2);
        root.add(placeOrderButton, 0, 3);
        root.add(grandTotalLabel, 0, 4);



        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();








    }

    public static void main(String[] args) {
        launch();
    }
}