package sample;
import java.lang.Integer;
import java.lang.NumberFormatException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;




public class GUI extends Application {

    Stage window;
    Scene main, order, overhead, employee, shipping;
    private int employee_column, manuf_column, order_column, ship_column; // For check functions

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;
        window.setTitle("Shamwow - Billing and Payment System");

        Label welcomeMsg = new Label("Welcome to Shamwow Billing and Payment System."
                + "\nYou may make new order, look up or update cost information of materials, manufacturers, shippings, overheads and employees."
                + "\n\nClick on the buttons on the left to proceed.");

		/* -----------------------------------------------------------------------------
		 Main Page
		 ----------------------------------------------------------------------------- */
        // list of buttons in the main page
        Button order_button = new Button("Order");
        order_button.setOnAction(e -> window.setScene(order));

        Button overhead_button = new Button("Overhead");
        overhead_button.setOnAction(e -> window.setScene(overhead));

        Button employee_button = new Button("Employee");
        employee_button.setOnAction(e -> window.setScene(employee));

        Button shipping_button = new Button("Shipping");
        shipping_button.setOnAction(e -> window.setScene(shipping));


        VBox button_list = new VBox(10);
        button_list.getChildren().addAll(order_button, employee_button, overhead_button, shipping_button);
        //button_list.setPadding(new Insets(10, 0, 0, 10));

        // make layout for main page
        HBox main_layout = new HBox(50);
        main_layout.getChildren().addAll(button_list, welcomeMsg); // join buttons and welcomeMsg
        //main_layout.setAlignment(Pos.TOP_CENTER);
        main = new Scene(main_layout, 800, 500);


		/* -----------------------------------------------------------------------------
		 Order Page
		 ----------------------------------------------------------------------------- */
        Button goBack_button1 = new Button("<< Go back");
        goBack_button1.setOnAction(e -> window.setScene(main));


        HBox order_layout = new HBox(20);

        // New Order
        String orderID1, units1, shippingID1;
        Label newOrder_title = new Label("Make a new order");

        TextField text_orderID1 = new TextField();
        text_orderID1.setPromptText("Order ID");
        text_orderID1.setPrefColumnCount(10);
        orderID1 = text_orderID1.getText();
        //GridPane.setConstraints(text_orderID1,  0, 0);

        TextField text_units1 = new TextField();
        text_units1.setPromptText("# of units wanted");
        units1 = text_units1.getText();

        TextField text_shippingID1 = new TextField();
        text_shippingID1.setPromptText("Shipping company ID");
        shippingID1 = text_shippingID1.getText();


        Button submit_order = new Button("Submit");
        submit_order.setOnAction(e -> {
            Order.newOrder(orderID1, units1, shippingID1);
            System.out.println("New Order data has been added.");
        });


        VBox newOrder_texts = new VBox(10);
        newOrder_texts.setPrefWidth(160);
        newOrder_texts.getChildren().addAll(newOrder_title, text_orderID1, text_units1, text_shippingID1, submit_order);

        // Update Order
        String orderID2, units2, shippingID2;
        Label updateOrder_title = new Label("Update order info");

        TextField text_orderID2 = new TextField();
        text_orderID2.setPromptText("Enter existing order ID");
        text_orderID2.setPrefColumnCount(10);
        orderID2 = text_orderID2.getText();

        TextField text_units2 = new TextField();
        text_units2.setPromptText("# of units wanted");
        units2 = text_units2.getText();

        TextField text_shippingID2 = new TextField();
        text_shippingID2.setPromptText("Shipping company ID");
        shippingID2 = text_shippingID2.getText();

        Button update_order = new Button("Update");
        update_order.setOnAction(e -> {
            Order.updateOrder(orderID2, units2, shippingID2);
            System.out.println("Order data has been updated.");
        });

        VBox updateOrder_texts = new VBox(10);
        updateOrder_texts.setPrefWidth(160);
        updateOrder_texts.getChildren().addAll(updateOrder_title, text_orderID2, text_units2, text_shippingID2, update_order);

        // Check Order
        String orderID3;
        Label checkOrder_title = new Label("Check Order");

        TextField text_orderID3 = new TextField();
        text_orderID3.setPromptText("Enter existing order ID");
        orderID3 = text_orderID3.getText();

        TextField text_orderColumn = new TextField();
        text_orderColumn.setPromptText("Enter column #");
        try {
            order_column = Integer.parseInt(text_orderColumn.getText());
        } catch (NumberFormatException ex) { }


        Button check_order = new Button("Check");
        check_order.setOnAction(e -> Order.checkOrder(orderID3, order_column));


        VBox checkOrder_texts = new VBox(10);
        checkOrder_texts.getChildren().addAll(checkOrder_title, text_orderID3, text_orderColumn, check_order);


        order_layout.getChildren().addAll(goBack_button1, newOrder_texts, updateOrder_texts, checkOrder_texts);
        order = new Scene(order_layout, 800, 500);


		/* -----------------------------------------------------------------------------
		 Overhead Page
		 ----------------------------------------------------------------------------- */
        Button goBack_button2 = new Button("<< Go back");
        goBack_button2.setOnAction(e -> window.setScene(main));

        HBox overhead_layout = new HBox(20);

        // New Overhead
        String manufacturer1, rent1, utilities1, insurance1, machinery1;
        Label newOverhead_title = new Label("Add Manufacturer to the Database");

        TextField text_manufacturer1 = new TextField();
        text_manufacturer1.setPromptText("Manufacturer");
        text_manufacturer1.setPrefColumnCount(10);
        manufacturer1 = text_manufacturer1.getText();

        TextField text_rent1 = new TextField();
        text_rent1.setPromptText("Rent");
        rent1 = text_rent1.getText();

        TextField text_utilities1 = new TextField();
        text_utilities1.setPromptText("Utilities");
        utilities1 = text_utilities1.getText();

        TextField text_insurance1 = new TextField();
        text_insurance1.setPromptText("Insurance");
        insurance1 = text_insurance1.getText();

        TextField text_machinery1 = new TextField();
        text_machinery1.setPromptText("Machinery");
        machinery1 = text_machinery1.getText();


        Button submit_manufacturer = new Button("Submit");
        submit_manufacturer.setOnAction(e -> {
            Overhead.newManufacturer(manufacturer1, rent1, utilities1, text_insurance1, text_machinery1);
            System.out.println("Manufacturer data has been updated.");
        });

        VBox newManuf_texts = new VBox(10);
        newManuf_texts.getChildren().addAll(newOverhead_title, text_manufacturer1, text_rent1, text_utilities1, text_insurance1, text_machinery1, submit_manufacturer);


        // Update Manufacturer
        String manufacture2, rent2, utilities2, insurance2, machinery2;
        Label updateOverhead_title = new Label("Update Existing Manufacturer");

        TextField text_manufacturer2 = new TextField();
        text_manufacturer2.setPromptText("Manufacturer");
        text_manufacturer2.setPrefColumnCount(10);
        manufacture2 = text_manufacturer2.getText();

        TextField text_rent2 = new TextField();
        text_rent2.setPromptText("Rent");
        rent2 = text_rent2.getText();

        TextField text_utilities2 = new TextField();
        text_utilities2.setPromptText("Utilities");
        utilities2 = text_utilities2.getText();

        TextField text_insurance2 = new TextField();
        text_insurance2.setPromptText("Insurance");
        insurance2 = text_insurance2.getText();

        TextField text_machinery2 = new TextField();
        text_machinery2.setPromptText("Machinery");
        machinery2 = text_machinery2.getText();


        Button update_manufacturer = new Button("Submit");
        update_manufacturer.setOnAction(e -> {
            Overhead.newManufacturer(manufacturer1, rent1, utilities1, text_insurance1, text_machinery1);
            System.out.println("Manufacturer data has been updated.");
        });

        VBox updateManuf_texts = new VBox(10);
        updateManuf_texts.getChildren().addAll(updateOverhead_title, text_manufacturer2, text_rent2, text_utilities2, text_insurance2, text_machinery2, update_manufacturer);


        // Check Manufacturer
        String manufacturer3;
        Label checkManuf_title = new Label("Check Manufacturer");

        TextField text_manufacturer3 = new TextField();
        text_manufacturer3.setPromptText("Enter existing manufacturer ID");
        manufacturer3 = text_manufacturer3.getText();

        TextField text_manufColumn = new TextField();
        text_manufColumn.setPromptText("Enter column #");
        try {
            manuf_column = Integer.parseInt(text_manufColumn.getText());
        } catch (NumberFormatException ex) { }


        Button check_manufacturer = new Button("Check");
        check_manufacturer.setOnAction(e -> Overhead.check(manufacturer3, manuf_column));


        VBox checkManuf_texts = new VBox(10);
        checkManuf_texts.getChildren().addAll(checkManuf_title, text_manufacturer3, text_manufColumn, check_manufacturer);


        overhead_layout.getChildren().addAll(goBack_button2, newManuf_texts, updateManuf_texts, checkManuf_texts);
        overhead = new Scene(overhead_layout, 800, 500);


		/* -----------------------------------------------------------------------------
		 Employee Page
		 ----------------------------------------------------------------------------- */
        Button goBack_button3 = new Button("<< Go back");
        goBack_button3.setOnAction(e -> window.setScene(main));


        HBox employee_layout = new HBox(20);

        // New Employee
        String id1, fname1, lname1, pay1, position1;
        Label newEmpl_title = new Label("Add Employee to the Database");

        TextField text_ID1 = new TextField();
        text_ID1.setPromptText("Employee ID");
        text_ID1.setPrefColumnCount(10);
        id1 = text_ID1.getText();

        TextField text_fname1 = new TextField();
        text_fname1.setPromptText("First name");
        fname1 = text_fname1.getText();

        TextField text_lname1 = new TextField();
        text_lname1.setPromptText("Last name");
        lname1 = text_lname1.getText();

        TextField text_pay1 = new TextField();
        text_pay1.setPromptText("Paycheck");
        pay1 = text_pay1.getText();

        TextField text_position1 = new TextField();
        text_position1.setPromptText("Position");
        position1 = text_position1.getText();


        Button submit_employee = new Button("Submit");
        submit_employee.setOnAction(e -> {
            Employees.newEmployee(id1, fname1, lname1, pay1, position1);
            System.out.println("Employee data has been added.");
        });


        VBox text_fields1 = new VBox(10);
        //text_fields1.setPrefWidth(160);
        text_fields1.getChildren().addAll(newEmpl_title, text_ID1, text_fname1, text_lname1, text_pay1, text_position1, submit_employee);

        // Update Employee
        String id2, fname2, lname2, pay2, position2;
        Label updateEmpl_title = new Label("Update Employee Info");

        TextField text_ID2 = new TextField();
        text_ID2.setPromptText("Enter existing employee ID");
        text_ID2.setPrefColumnCount(10);
        id2 = text_ID2.getText();

        TextField text_fname2 = new TextField();
        text_fname2.setPromptText("First name");
        fname2 = text_fname2.getText();

        TextField text_lname2 = new TextField();
        text_lname2.setPromptText("Last name");
        lname2 = text_lname2.getText();

        TextField text_pay2 = new TextField();
        text_pay2.setPromptText("Paycheck");
        pay2 = text_pay2.getText();

        TextField text_position2 = new TextField();
        text_position2.setPromptText("Position");
        position2 = text_position2.getText();


        Button update_employee = new Button("Update");
        update_employee.setOnAction(e -> {
            Employees.updateEmployee(id2, fname2, lname2, pay2, position2);
            System.out.println("Employee info has been updated.");
        });

        VBox text_fields2 = new VBox(10);
        text_fields2.getChildren().addAll(updateEmpl_title, text_ID2, text_fname2, text_lname2, text_pay2, text_position2, update_employee);

        // check Employee
        String id3;
        Label checkEmpl_title = new Label("Check Employee");

        TextField text_ID3 = new TextField();
        text_ID3.setPromptText("Enter existing employee ID");
        id3 = text_ID3.getText();

        TextField text_column = new TextField();
        text_column.setPromptText("Enter column #");
        try {
            employee_column = Integer.parseInt(text_column.getText());
        } catch (NumberFormatException ex) { }


        Button check_employee = new Button("Check");
        check_employee.setOnAction(e -> Employees.check(id3, employee_column));


        VBox text_fields3 = new VBox(10);
        text_fields3.getChildren().addAll(checkEmpl_title, text_ID3, text_column, check_employee);


        employee_layout.getChildren().addAll(goBack_button3, text_fields1, text_fields2, text_fields3);
        employee = new Scene(employee_layout, 800, 500);


		/* -----------------------------------------------------------------------------
		 Shipping Page
		 ----------------------------------------------------------------------------- */
        Button goBack_button4 = new Button("<< Go back");
        goBack_button4.setOnAction(e -> window.setScene(main));
        String shipID, shipName, shipAddress, shipPhone, shipCost;

        Label shipping_title = new Label("New Shipping Information");

        TextField text_ShipID = new TextField();
        text_ShipID.setPromptText("Shipping ID");
        text_ShipID.setPrefColumnCount(10);
        shipID = text_ShipID.getText();

        TextField text_shipName = new TextField();
        text_shipName.setPromptText("Full Name");
        shipName = text_shipName.getText();

        TextField text_shipAddy = new TextField();
        text_shipAddy.setPromptText("Address");
        shipAddress = text_shipAddy.getText();

        TextField text_shipPhone = new TextField();
        text_shipPhone.setPromptText("Phone Number");
        shipPhone = text_shipPhone.getText();

        TextField text_shipCost = new TextField();
        text_shipCost.setPromptText("Cost");
        shipCost = text_shipCost.getText();

        Button complete_shipDetails = new Button("Complete");
        complete_shipDetails.setOnAction(e -> {
            Shipping.newShipping(shipID, shipName, shipAddress, shipPhone, shipCost);
            System.out.println("Shipping data has been added.");
        });

        VBox vBoxShip = new VBox(10);
        vBoxShip.getChildren().addAll(shipping_title, text_ShipID, text_shipName, text_shipAddy, text_shipPhone, text_shipCost, complete_shipDetails);

        String shipID1, shipName1, shipAddress1, shipPhone1, shipCost1;

        Label shipping_title1 = new Label("Update Shipping Information");

        TextField text_ShipID1 = new TextField();
        text_ShipID1.setPromptText("Shipping ID");
        text_ShipID1.setPrefColumnCount(10);
        shipID1 = text_ShipID1.getText();

        TextField text_shipName1 = new TextField();
        text_shipName1.setPromptText("Full Name");
        shipName1 = text_shipName1.getText();

        TextField text_shipAddy1 = new TextField();
        text_shipAddy1.setPromptText("Address");
        shipAddress1 = text_shipAddy1.getText();

        TextField text_shipPhone1 = new TextField();
        text_shipPhone1.setPromptText("Phone Number");
        shipPhone1 = text_shipPhone1.getText();

        TextField text_shipCost1 = new TextField();
        text_shipCost1.setPromptText("Cost");
        shipCost1 = text_shipCost1.getText();

        Button update_shipDetails1 = new Button("Update");
        complete_shipDetails.setOnAction(e -> {
            Shipping.updateShipping(shipID1, shipName1, shipAddress1, shipPhone1, shipCost1);
            System.out.println("Shipping data has been updated.");
        });

        VBox vBoxShip1 = new VBox(10);
        vBoxShip1.getChildren().addAll(shipping_title1, text_ShipID1, text_shipName1, text_shipAddy1, text_shipPhone1, text_shipCost1, update_shipDetails1);

        String shipID3;
        Label checkShip_title = new Label("Check Shipping Information");

        TextField text_shipID3 = new TextField();
        text_shipID3.setPromptText("Enter existing Shipping ID");
        shipID3 = text_shipID3.getText();

        TextField text_shipColumn = new TextField();
        text_shipColumn.setPromptText("Enter column #");
        try {
            ship_column = Integer.parseInt(text_shipColumn.getText());
        } catch (NumberFormatException ex) { }


        Button check_shipping = new Button("Check");
        check_shipping.setOnAction(e -> Shipping.check(shipID3, ship_column));


        VBox vboxShipCheck = new VBox(10);
        vboxShipCheck.getChildren().addAll(checkShip_title, text_shipID3, text_shipColumn, check_shipping);


        HBox hboxShipping = new HBox(10);
        hboxShipping.getChildren().addAll(vBoxShip, vBoxShip1, vboxShipCheck);

        shipping = new Scene(hboxShipping, 800, 500);
        window.setScene(main);
        window.show();


    }

}