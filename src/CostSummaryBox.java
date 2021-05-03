package application;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class CostSummaryBox {
	
	public static void display(String title, int shipping_costs, int employee_costs, int overhead_costs, int total_cost) {
		Stage window = new Stage();
		Scene container;
		GridPane box_layout = new GridPane();
		VBox list = new VBox(10);
		
		Label Shipping_Costs = new Label("Shipping Costs: " + shipping_costs);
		Label Employee_Costs = new Label("Shipping Costs: " + employee_costs);
		Label Overhead_Costs = new Label("Overhead Costs: " + overhead_costs);
		Label Total_Costs = new Label("Total Cost: " + total_cost);
		
		list.getChildren().addAll(Shipping_Costs, Employee_Costs, Overhead_Costs, Total_Costs);
		box_layout.getChildren().add(list);
		box_layout.setAlignment(Pos.CENTER);
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		container = new Scene(box_layout, 500, 300);
		
		window.setScene(container);
		window.show();
	}
}
