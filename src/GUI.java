package sample;
 
import java.lang.Integer;
import java.lang.NumberFormatException;
import java.io.*;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.Group; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.*;
import javafx.scene.web.*;
import javafx.scene.image.*;
import javafx.scene.Group;  
import javafx.scene.text.*;




public class GUI extends Application {

    Stage window;
    Scene main, order, data, overhead, employee, shipping;
    private int employee_column, manuf_column, ship_column; // For check functions
    ComboBox<String> shipCompany;
    String order_number;
  	int employee_costs, shipping_costs, overhead_costs, total_cost;
  	
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public  void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;
        window.setTitle("Shamwow - Billing and Payment System");

        Label welcome_title = new Label("Welcome to Shamwow Billing and Payment System.");
        Label welcomeMsg = new Label("\nYou may make new order, look up or update cost information of materials, manufacturers, shippings, \noverheads and employees."
                + "\n\nClick on the buttons on the left to proceed.");
        welcome_title.setPadding(new Insets(10, 0, 0, 0));
        welcome_title.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
        //welcome_title.setTextFill(Color.rgb(14, 87, 155));
        
		/* -----------------------------------------------------------------------------
		 Main Page
		 ----------------------------------------------------------------------------- */
		
    
        // list of buttons in the main page
        Button order_button = new Button("Make New Order");
        order_button.setOnAction(e -> window.setScene(order));
        order_button.setPrefWidth(150);
        
        Button data_button = new Button("Enter Data");
        data_button.setOnAction(e -> window.setScene(data));
        data_button.setPrefWidth(150);

        Button exit_button = new Button("Exit");
        exit_button.setOnAction(e -> System.exit(0));
        exit_button.setPrefWidth(150);
        
        Button report_button = new Button("Check overall costs");
        report_button.setOnAction(e -> {
        	shipping_costs = general.shippingCosts();
        	employee_costs = general.laborCost();
        	overhead_costs = general.overheadCost();
        	total_cost = general.totalCost();
        	
        	CostSummaryBox.display("Cost Summary", shipping_costs, employee_costs, overhead_costs, total_cost);
        });
        report_button.setPrefWidth(150);
        
    	
        
        VBox button_list = new VBox(10);
        button_list.setPrefWidth(200);
        button_list.getChildren().addAll(order_button, data_button, report_button, exit_button);
        button_list.setPadding(new Insets(30, 0, 0, 10));
        
        VBox main_content = new VBox();
        main_content.getChildren().addAll(welcome_title, welcomeMsg);
        // make layout for main page
        HBox main_layout = new HBox(45);
        // try {
           Image image = new Image("https://cdn.discordapp.com/attachments/551214454587654155/838517821742252143/6e8e5fc722a47865e3d299a7b8090d5b.w973.h765.jpg");
           BackgroundImage backgroundimage = new BackgroundImage(image, 
                                             BackgroundRepeat.NO_REPEAT, 
                                             BackgroundRepeat.NO_REPEAT, 
                                             BackgroundPosition.DEFAULT, 
                                                BackgroundSize.DEFAULT);
  
            // create Background
            Background background = new Background(backgroundimage);
            // set background
            main_layout.setBackground(background);
            
            main_layout.getChildren().addAll(button_list, main_content);
           
       // } catch (FileNotFoundException  ex) {
        	
        	// join buttons and welcomeMsg
            // main_layout.getChildren().addAll(button_list, welcomeMsg);
            
       // }
        
        main = new Scene(main_layout, 800, 500);

        /* -----------------------------------------------------------------------------
		 Data Page
		 ----------------------------------------------------------------------------- */
        Label dataPageMsg = new Label("Please choose the data you would like to add/update/remove."
                + "\n\nClick on the buttons on the left to proceed.");
        
        Button goBack_button = new Button("<< Go back");
        goBack_button.setOnAction(e -> window.setScene(main));
        goBack_button.setPrefWidth(150);
        
        Button overhead_button = new Button("Overhead");
        overhead_button.setOnAction(e -> window.setScene(overhead));
        overhead_button.setPrefWidth(150);

        Button employee_button = new Button("Employee");
        employee_button.setOnAction(e -> window.setScene(employee));
        employee_button.setPrefWidth(150);

        Button shipping_button = new Button("Shipping");
        shipping_button.setOnAction(e -> window.setScene(shipping));
        shipping_button.setPrefWidth(150);
        
        Button exit_button1 = new Button("Exit");
        exit_button1.setOnAction(e -> System.exit(0));
        exit_button1.setPrefWidth(150);
        
        VBox data_buttonlist = new VBox(10);
        data_buttonlist.setPrefWidth(200);
        data_buttonlist.getChildren().addAll(goBack_button, overhead_button, employee_button, shipping_button, exit_button1);
        
        HBox data_layout = new HBox(50);
        data_layout.setPadding(new Insets(10, 0, 0, 10));
        data_layout.getChildren().addAll(data_buttonlist, dataPageMsg);
        data = new Scene(data_layout, 800, 500);
        
		/* -----------------------------------------------------------------------------
		 Order Page
		 ----------------------------------------------------------------------------- */
        Button goBack_button1 = new Button("<< Go back");
        goBack_button1.setOnAction(e -> window.setScene(main));
        
        HBox order_layout = new HBox(20);
        
        Label order_step1 = new Label("Enter Number of Units Wanted:");
        TextField units =  new TextField();
        
        Label order_step2 = new Label("Choose Shipping Company:");
        shipCompany = new ComboBox<>();
        shipCompany.setEditable(true);
        String[] ids = DataAccess.returnColumn("D:\\shipping.csv", 1, 5);
        shipCompany.getItems().addAll(ids);
        //TextField shipping_company =  new TextField();
        //shipping_company.setPromptText("Enter shipping company ID");
        
        
        Label order_label = new Label("");
        Button submit_order = new Button("Submit");
        
        submit_order.setOnAction(e -> { 
        	String units_input, shipping_ID;
        	order_number = Order.generateOrderNumber();
        	units_input = units.getText();
        	shipping_ID = shipCompany.getValue();
        	Order.newOrder(order_number, units_input, shipping_ID);
        	order_label.setText("Order created. Your order number is " + order_number);
        	
        	// clear text in fields
        	units.clear();
            shipCompany.setValue(null);
        });
        
        VBox order_steps = new VBox(10);
        order_steps.setPrefWidth(360);
        order_steps.getChildren().addAll(order_step1, units, order_step2, shipCompany, submit_order, order_label);
        
        order_layout.getChildren().addAll(goBack_button1, order_steps);
        order_layout.setPadding(new Insets(10, 0, 0, 10));
        order = new Scene(order_layout, 800, 500);
        
        /*Button goBack_button1 = new Button("<< Go back");
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

        Label order_label1 = new Label("");
        Button submit_order = new Button("Submit");
        submit_order.setOnAction(e -> {
            //Order.newOrder(orderID1, units1, shippingID1);
        	order_label1.setText("New order has been created");
            System.out.println("New order has been created");
            text_orderID1.clear();
            text_units1.clear();
            text_shippingID1.clear();
        });


        VBox newOrder_texts = new VBox(10);
        newOrder_texts.setPrefWidth(200);
        newOrder_texts.getChildren().addAll(newOrder_title, text_orderID1, text_units1, text_shippingID1, submit_order, order_label1);

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

        
        Label order_label2 = new Label("");
        Button update_order = new Button("Update");
        update_order.setOnAction(e -> {
            //Order.updateOrder(orderID2, units2, shippingID2);
        	order_label2.setText("Order data has been updated");
            System.out.println("Order data has been updated");
            text_orderID2.clear();
            text_units2.clear();
            text_shippingID2.clear();
        });

        VBox updateOrder_texts = new VBox(10);
        updateOrder_texts.setPrefWidth(200);
        updateOrder_texts.getChildren().addAll(updateOrder_title, text_orderID2, text_units2, text_shippingID2, update_order, order_label2);

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

        
        Label order_label3 = new Label("");
        Button check_order = new Button("Check");
        check_order.setOnAction(e -> {
        	String search_order = Order.checkOrder(orderID3, order_column);
        	order_label3.setText(search_order);
        	text_orderID3.clear();
        	text_orderColumn.clear();
        });


        VBox checkOrder_texts = new VBox(10);
        checkOrder_texts.setPrefWidth(200);
        checkOrder_texts.getChildren().addAll(checkOrder_title, text_orderID3, text_orderColumn, check_order, order_label3);


        order_layout.getChildren().addAll(goBack_button1, newOrder_texts, updateOrder_texts, checkOrder_texts);
        order = new Scene(order_layout, 800, 500);*/


		/* -----------------------------------------------------------------------------
		 Overhead Page
		 ----------------------------------------------------------------------------- */
        Button goBack_button2 = new Button("<< Go back");
        goBack_button2.setOnAction(e -> window.setScene(data));

        HBox overhead_layout = new HBox(20);

        // New Overhead
        Label newOverhead_title = new Label("Add Manufacturer to the Database");

        TextField text_manufacturer1 = new TextField();
        text_manufacturer1.setPromptText("Manufacturer");
        text_manufacturer1.setPrefColumnCount(10);
        
        TextField text_rent1 = new TextField();
        text_rent1.setPromptText("Rent");
        
        TextField text_utilities1 = new TextField();
        text_utilities1.setPromptText("Utilities");
        
        TextField text_insurance1 = new TextField();
        text_insurance1.setPromptText("Insurance");
        
        TextField text_machinery1 = new TextField();
        text_machinery1.setPromptText("Machinery");
        

        Label overhead_label1 = new Label("");
        Button submit_manufacturer = new Button("Submit");
        
        submit_manufacturer.setOnAction(e -> {
        	String manufacturer1, rent1, utilities1, insurance1, machinery1;
        	manufacturer1 = text_manufacturer1.getText();
        	rent1 = text_rent1.getText();
        	utilities1 = text_utilities1.getText();
        	insurance1 = text_insurance1.getText();
        	machinery1 = text_machinery1.getText();
        	
            Overhead.newManufacturer(manufacturer1, rent1, utilities1, insurance1, machinery1);
            overhead_label1.setText("Manufacturer data has been added");
            System.out.println("Manufacturer data has been added");
            
            text_manufacturer1.clear();
            text_rent1.clear();
            text_utilities1.clear();
            text_insurance1.clear();
            text_machinery1.clear();
        });

        VBox newManuf_texts = new VBox(10);
        newManuf_texts.setPrefWidth(200);
        newManuf_texts.getChildren().addAll(newOverhead_title, text_manufacturer1, text_rent1, text_utilities1, text_insurance1, text_machinery1, submit_manufacturer, overhead_label1);


        // Update Manufacturer
        Label updateOverhead_title = new Label("Update Existing Manufacturer");

        TextField text_manufacturer2 = new TextField();
        text_manufacturer2.setPromptText("Manufacturer");
        text_manufacturer2.setPrefColumnCount(10);
        
        TextField text_rent2 = new TextField();
        text_rent2.setPromptText("Rent");
        
        TextField text_utilities2 = new TextField();
        text_utilities2.setPromptText("Utilities");
        
        TextField text_insurance2 = new TextField();
        text_insurance2.setPromptText("Insurance");
        
        TextField text_machinery2 = new TextField();
        text_machinery2.setPromptText("Machinery");
        

        Label overhead_label2 = new Label("");
        Button update_manufacturer = new Button("Submit");
        
        update_manufacturer.setOnAction(e -> {
        	String manufacturer2, rent2, utilities2, insurance2, machinery2;
        	manufacturer2 = text_manufacturer2.getText();
        	rent2 = text_rent2.getText();
        	utilities2 = text_utilities2.getText();
        	insurance2 = text_insurance2.getText();
        	machinery2 = text_machinery2.getText();
            
        	Overhead.newManufacturer(manufacturer2, rent2, utilities2, insurance2, machinery2);
            overhead_label2.setText("Manufacturer data has been updated");
            System.out.println("Manufacturer data has been updated");
            
            text_manufacturer2.clear();
            text_rent2.clear();
            text_utilities2.clear();
            text_insurance2.clear();
            text_machinery2.clear();
        });

        VBox updateManuf_texts = new VBox(10);
        updateManuf_texts.setPrefWidth(200);
        updateManuf_texts.getChildren().addAll(updateOverhead_title, text_manufacturer2, text_rent2, text_utilities2, text_insurance2, text_machinery2, update_manufacturer, overhead_label2);


        // Check Manufacturer (no need)
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

        Label overhead_label3 = new Label("");
        Button check_manufacturer = new Button("Check");
        check_manufacturer.setOnAction(e -> {
        	String search_overhead = Overhead.check(manufacturer3, manuf_column);
        	overhead_label3.setText(search_overhead);
        	text_manufacturer3.clear();
        	text_manufColumn.clear();
        });


        VBox checkManuf_texts = new VBox(10);
        //checkManuf_texts.setPrefWidth(200);
        checkManuf_texts.getChildren().addAll(checkManuf_title, text_manufacturer3, text_manufColumn, check_manufacturer, overhead_label3);


        overhead_layout.getChildren().addAll(goBack_button2, newManuf_texts, updateManuf_texts);
        overhead_layout.setPadding(new Insets(10, 0, 0, 10));
        overhead = new Scene(overhead_layout, 800, 500);


		/* -----------------------------------------------------------------------------
		 Employee Page
		 ----------------------------------------------------------------------------- */
        Button goBack_button3 = new Button("<< Go back");
        goBack_button3.setOnAction(e -> window.setScene(data));


        HBox employee_layout = new HBox(20);

        // New Employee
        Label newEmpl_title = new Label("Add Employee to the Database");

        TextField text_ID1 = new TextField();
        text_ID1.setPromptText("Employee ID");
        text_ID1.setPrefColumnCount(10);
        
        TextField text_fname1 = new TextField();
        text_fname1.setPromptText("First name");
        
        TextField text_lname1 = new TextField();
        text_lname1.setPromptText("Last name");
        
        TextField text_pay1 = new TextField();
        text_pay1.setPromptText("Paycheck");
        
        TextField text_position1 = new TextField();
        text_position1.setPromptText("Position");
        

        Label employee_label1 = new Label("");
        Button submit_employee = new Button("Submit");
        submit_employee.setOnAction(e -> {
        	String id1, fname1, lname1, pay1, position1;
        	id1 = text_ID1.getText();
        	fname1 = text_fname1.getText();
        	lname1 = text_lname1.getText();
        	pay1 = text_pay1.getText();
        	position1 = text_position1.getText();
            
        	Employees.newEmployee(id1, fname1, lname1, pay1, position1);
            employee_label1.setText("Employee data has been added");
            System.out.println("Employee data has been added");
            
            text_ID1.clear();
            text_fname1.clear();
            text_lname1.clear();
            text_pay1.clear();
            text_position1.clear();
        });


        VBox text_fields1 = new VBox(10);
        text_fields1.setPrefWidth(200);
        text_fields1.getChildren().addAll(newEmpl_title, text_ID1, text_fname1, text_lname1, text_pay1, text_position1, submit_employee, employee_label1);

        // Update Employee
        Label updateEmpl_title = new Label("Update Employee Info");

        TextField text_ID2 = new TextField();
        text_ID2.setPromptText("Enter existing employee ID");
        text_ID2.setPrefColumnCount(10);
        
        TextField text_fname2 = new TextField();
        text_fname2.setPromptText("First name");
        
        TextField text_lname2 = new TextField();
        text_lname2.setPromptText("Last name");
        
        TextField text_pay2 = new TextField();
        text_pay2.setPromptText("Paycheck");
        
        TextField text_position2 = new TextField();
        text_position2.setPromptText("Position");
        

        Label employee_label2 = new Label("");
        Button update_employee = new Button("Update");
        
        update_employee.setOnAction(e -> {
        	String id2, fname2, lname2, pay2, position2;
        	id2 = text_ID2.getText();
        	fname2 = text_fname2.getText();
        	lname2 = text_lname2.getText();
        	pay2 = text_pay2.getText();
        	position2 = text_position2.getText();
            
        	Employees.updateEmployee(id2, fname2, lname2, pay2, position2);
            employee_label2.setText("Employee info has been updated");
            System.out.println("Employee info has been updated");
            
            text_ID2.clear();
            text_fname2.clear();
            text_lname2.clear();
            text_pay2.clear();
            text_position2.clear();
        });

        VBox text_fields2 = new VBox(10);
        text_fields2.setPrefWidth(200);
        text_fields2.getChildren().addAll(updateEmpl_title, text_ID2, text_fname2, text_lname2, text_pay2, text_position2, update_employee, employee_label2);

        // check Employee (no need)
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

        
        Label employee_label3 = new Label("");
        Button check_employee = new Button("Check");
        check_employee.setOnAction(e -> {
        	String search_employee = Employees.check(id3, employee_column);
        	employee_label3.setText(search_employee);
        });


        VBox text_fields3 = new VBox(10);
        text_fields3.setPrefWidth(200);
        text_fields3.getChildren().addAll(checkEmpl_title, text_ID3, text_column, check_employee, employee_label3);


        employee_layout.getChildren().addAll(goBack_button3, text_fields1, text_fields2);
        employee_layout.setPadding(new Insets(10, 0, 0, 10));
        employee = new Scene(employee_layout, 800, 500);


		/* -----------------------------------------------------------------------------
		 Shipping Page
		 ----------------------------------------------------------------------------- */
        Button goBack_button4 = new Button("<< Go back");
        goBack_button4.setOnAction(e -> window.setScene(data));
        
        // New Shipping
        Label shipping_title = new Label("New Shipping Information");

        TextField text_ShipID = new TextField();
        text_ShipID.setPromptText("Shipping ID");
        text_ShipID.setPrefColumnCount(10);

        TextField text_shipName = new TextField();
        text_shipName.setPromptText("Full Name");
        
        TextField text_shipAddy = new TextField();
        text_shipAddy.setPromptText("Address");
        
        TextField text_shipPhone = new TextField();
        text_shipPhone.setPromptText("Phone Number");
        
        TextField text_shipCost = new TextField();
        text_shipCost.setPromptText("Cost");
                
        
        Label shipping_label1 = new Label("");
        Button complete_shipDetails = new Button("Complete");
        
        complete_shipDetails.setOnAction(e -> {
        	String shipID, shipName, shipAddress, shipPhone, shipCost;
        	shipID = text_ShipID.getText();
        	shipName = text_shipName.getText();
        	shipAddress = text_shipAddy.getText();
        	shipPhone = text_shipPhone.getText();
        	shipCost = text_shipCost.getText();
        	
            Shipping.newShipping(shipID, shipName, shipAddress, shipPhone, shipCost);
            shipping_label1.setText("Shipping data has been added");
            System.out.println("Shipping data has been added");
            
            text_ShipID.clear();
            text_shipAddy.clear();
            text_shipPhone.clear();
            text_shipCost.clear();
        });

        VBox vBoxShip = new VBox(10);
        vBoxShip.setPrefWidth(200);
        vBoxShip.getChildren().addAll(shipping_title, text_ShipID, text_shipName, text_shipAddy, text_shipPhone, text_shipCost, complete_shipDetails, shipping_label1);

        
        // Update Shipping
        Label shipping_title1 = new Label("Update Shipping Information");

        TextField text_ShipID1 = new TextField();
        text_ShipID1.setPromptText("Shipping ID");
        text_ShipID1.setPrefColumnCount(10);
        
        TextField text_shipName1 = new TextField();
        text_shipName1.setPromptText("Full Name");
        
        TextField text_shipAddy1 = new TextField();
        text_shipAddy1.setPromptText("Address");
        
        TextField text_shipPhone1 = new TextField();
        text_shipPhone1.setPromptText("Phone Number");
        
        TextField text_shipCost1 = new TextField();
        text_shipCost1.setPromptText("Cost");
        
        
        Label shipping_label2 = new Label("");
        Button update_shipDetails1 = new Button("Update");
        
        update_shipDetails1.setOnAction(e -> {
        	String shipID1, shipName1, shipAddress1, shipPhone1, shipCost1;
        	shipID1 = text_ShipID1.getText();
        	shipName1 = text_shipName1.getText();
        	shipAddress1 = text_shipAddy1.getText();
        	shipPhone1 = text_shipPhone1.getText();
        	shipCost1 = text_shipCost1.getText();
        	
            Shipping.updateShipping(shipID1, shipName1, shipAddress1, shipPhone1, shipCost1);
            shipping_label2.setText("Shipping data has been updated");
            System.out.println("Shipping data has been updated");
            
            text_ShipID1.clear();
            text_shipName1.clear();
            text_shipAddy1.clear();
            text_shipPhone1.clear();
            text_shipCost1.clear();
        });

        VBox vBoxShip1 = new VBox(10);
        vBoxShip1.setPrefWidth(200);
        vBoxShip1.getChildren().addAll(shipping_title1, text_ShipID1, text_shipName1, text_shipAddy1, text_shipPhone1, text_shipCost1, update_shipDetails1, shipping_label2);

        
        // Check Shipping (no need)
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


        Label shipping_label3 = new Label("");
        Button check_shipping = new Button("Check");
        check_shipping.setOnAction(e -> {
        	String search_shipping = Shipping.check(shipID3, ship_column);
        	shipping_label3.setText(search_shipping);
        	text_shipID3.clear();
        	text_shipColumn.clear();
        });


        VBox vboxShipCheck = new VBox(10);
        vboxShipCheck.setPrefWidth(200);
        vboxShipCheck.getChildren().addAll(checkShip_title, text_shipID3, text_shipColumn, check_shipping, shipping_label3);


        HBox hboxShipping = new HBox(10);
        hboxShipping.getChildren().addAll(goBack_button4, vBoxShip, vBoxShip1);
        hboxShipping.setPadding(new Insets(10, 0, 0, 10));

        shipping = new Scene(hboxShipping, 800, 500);
        
        window.setScene(main);
        window.show();


    }

}
