package application;
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
	private int column;
	
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
		GridPane.setConstraints(text_orderID1,  0, 0);
		
		TextField text_units1 = new TextField();
		text_units1.setPromptText("# of units wanted");
		units1 = text_units1.getText();
		GridPane.setConstraints(text_units1,  0, 0);
		
		TextField text_shippingID1 = new TextField();
		text_shippingID1.setPromptText("Shipping company ID");
		shippingID1 = text_shippingID1.getText();
		GridPane.setConstraints(text_shippingID1,  0, 0);

		
		Button submit_order = new Button("Submit");
		submit_order.setOnAction(e -> {
			//Order.newOrder(orderID1, units1, shippingID1);
			System.out.println("New Order data has been added.");
		});
		
		
		VBox newOrder_texts = new VBox(10);
		newOrder_texts.getChildren().addAll(newOrder_title, text_orderID1, text_units1, text_shippingID1, submit_order);
		
		// Update Order
		String orderID2, units2, shippingID2;
		Label updateOrder_title = new Label("Update order info");
				
		TextField text_orderID2 = new TextField();
		text_orderID2.setPromptText("Enter existing order ID");
		text_orderID2.setPrefColumnCount(10);
		orderID2 = text_orderID2.getText();
		GridPane.setConstraints(text_orderID2,  0, 0);
				
		TextField text_units2 = new TextField();
		text_units2.setPromptText("# of units wanted");
		units2 = text_units2.getText();
		GridPane.setConstraints(text_units2,  0, 0);
				
		TextField text_shippingID2 = new TextField();
		text_shippingID2.setPromptText("Shipping company ID");
		shippingID2 = text_shippingID2.getText();
		GridPane.setConstraints(text_shippingID2,  0, 0);
				
		Button update_order = new Button("Update");
		update_order.setOnAction(e -> {
			//Order.updateOrder(orderID2, units2, shippingID2);
			System.out.println("Order data has been updated.");
		});
				
		VBox updateOrder_texts = new VBox(10);
		updateOrder_texts.getChildren().addAll(updateOrder_title, text_orderID2, text_units2, text_shippingID2, update_order);
		
		// Check Order
		/*String id3;
		Label checkEmpl_title = new Label("Check Emplyee");
		
		TextField text_ID3 = new TextField();
		text_ID3.setPromptText("Enter existing emplyee ID");
		id3 = text_ID3.getText();
		GridPane.setConstraints(text_ID3,  0, 0);
		
		TextField text_column = new TextField();
		text_column.setPromptText("Enter column #");
		try {
			column = Integer.parseInt(text_column.getText());
		} catch (NumberFormatException ex){
			
		}
		GridPane.setConstraints(text_column,  0, 0);
		
		Button check_employee = new Button("Check");
		check_employee.setOnAction(e -> Employees.check(id3, column));
		
		VBox text_fields3 = new VBox(10);
		text_fields3.getChildren().addAll(checkEmpl_title, text_ID3, text_column, check_employee);*/
		
		
		
		order_layout.getChildren().addAll(goBack_button1, newOrder_texts, updateOrder_texts);
		order = new Scene(order_layout, 800, 500);
		
		
		/* -----------------------------------------------------------------------------
		 Overhead Page
		 ----------------------------------------------------------------------------- */
		Button goBack_button2 = new Button("<< Go back");
		goBack_button2.setOnAction(e -> window.setScene(main));
		
		StackPane overhead_layout = new StackPane();
		overhead_layout.getChildren().add(goBack_button2);
		overhead = new Scene(overhead_layout, 800, 500);
		
		
		/* -----------------------------------------------------------------------------
		 Employee Page
		 ----------------------------------------------------------------------------- */
		Button goBack_button3 = new Button("<< Go back");
		goBack_button3.setOnAction(e -> window.setScene(main));
		
		
		HBox employee_layout = new HBox(20);
		
		// New Employee
		String id1, fname1, lname1, pay1, position1;
		Label newEmpl_title = new Label("Add Emplyee to the Database");
		
		TextField text_ID1 = new TextField();
		text_ID1.setPromptText("Emplyee ID");
		text_ID1.setPrefColumnCount(10);
		id1 = text_ID1.getText();
		GridPane.setConstraints(text_ID1,  0, 0);
		
		TextField text_fname1 = new TextField();
		text_fname1.setPromptText("First name");
		fname1 = text_fname1.getText();
		GridPane.setConstraints(text_fname1,  0, 0);
		
		TextField text_lname1 = new TextField();
		text_lname1.setPromptText("Last name");
		lname1 = text_lname1.getText();
		GridPane.setConstraints(text_lname1,  0, 0);
		
		TextField text_pay1 = new TextField();
		text_pay1.setPromptText("Paycheck");
		pay1 = text_pay1.getText();
		GridPane.setConstraints(text_pay1,  0, 0);
		
		TextField text_position1 = new TextField();
		text_position1.setPromptText("Position");
		position1 = text_position1.getText();
		GridPane.setConstraints(text_position1,  0, 0);

		
		Button submit_employee = new Button("Submit");
		submit_employee.setOnAction(e -> {
			Employees.newEmployee(id1, fname1, lname1, pay1, position1);
			System.out.println("Employee data has been added.");
		});
		
		
		VBox text_fields1 = new VBox(10);
		text_fields1.getChildren().addAll(newEmpl_title, text_ID1, text_fname1, text_lname1, text_pay1, text_position1, submit_employee);
		
		// Update Employee
		String id2, fname2, lname2, pay2, position2;
		Label updateEmpl_title = new Label("Update Emplyee Info");
				
		TextField text_ID2 = new TextField();
		text_ID2.setPromptText("Enter existing emplyee ID");
		text_ID2.setPrefColumnCount(10);
		id2 = text_ID2.getText();
		GridPane.setConstraints(text_ID2,  0, 0);
				
		TextField text_fname2 = new TextField();
		text_fname2.setPromptText("First name");
		fname2 = text_fname2.getText();
		GridPane.setConstraints(text_fname2,  0, 0);
				
		TextField text_lname2 = new TextField();
		text_lname2.setPromptText("Last name");
		lname2 = text_lname2.getText();
		GridPane.setConstraints(text_lname2,  0, 0);
				
		TextField text_pay2 = new TextField();
		text_pay2.setPromptText("Paycheck");
		pay2 = text_pay2.getText();
		GridPane.setConstraints(text_pay2,  0, 0);
				
		TextField text_position2 = new TextField();
		text_position2.setPromptText("Position");
		position2 = text_position2.getText();
		GridPane.setConstraints(text_position2,  0, 0);

				
		Button update_employee = new Button("Update");
		update_employee.setOnAction(e -> {
			Employees.updateEmployee(id2, fname2, lname2, pay2, position2);
			System.out.println("Employee info has been updated.");
		});
				
		VBox text_fields2 = new VBox(10);
		text_fields2.getChildren().addAll(updateEmpl_title, text_ID2, text_fname2, text_lname2, text_pay2, text_position2, update_employee);
		
		// check Employee
		String id3;
		Label checkEmpl_title = new Label("Check Emplyee");
		
		TextField text_ID3 = new TextField();
		text_ID3.setPromptText("Enter existing emplyee ID");
		id3 = text_ID3.getText();
		GridPane.setConstraints(text_ID3,  0, 0);
		
		TextField text_column = new TextField();
		text_column.setPromptText("Enter column #");
		try {
			column = Integer.parseInt(text_column.getText());
		} catch (NumberFormatException ex){
			
		}
		GridPane.setConstraints(text_column,  0, 0);
		
		Button check_employee = new Button("Check");
		check_employee.setOnAction(e -> Employees.check(id3, column));
		
		VBox text_fields3 = new VBox(10);
		text_fields3.getChildren().addAll(checkEmpl_title, text_ID3, text_column, check_employee);
		
		
		
		employee_layout.getChildren().addAll(goBack_button3, text_fields1, text_fields2, text_fields3);
		employee = new Scene(employee_layout, 800, 500);
		
		
		/* -----------------------------------------------------------------------------
		 Shipping Page
		 ----------------------------------------------------------------------------- */
		Button goBack_button4 = new Button("<< Go back");
		goBack_button4.setOnAction(e -> window.setScene(main));
		
		StackPane shipping_layout = new StackPane();
		shipping_layout.getChildren().add(goBack_button4);
		shipping = new Scene(shipping_layout, 800, 500);
		window.setScene(main);
		window.show();
		
		
	}
	
}
