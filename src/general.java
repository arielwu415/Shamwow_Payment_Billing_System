import java.io.File;
import java.util.Scanner;

public class general extends DataAccess{
    public static int shippingCosts(){
        String filepath = "shipping.csv";
        return sumColumn(filepath, 3, 4);
    }
    public static int rawMaterialsCost(){
        File filepath = new File("materialCost.csv");
        Scanner scn = new Scanner(filepath);
        int matCost = Integer.parseInt(scn.nextLine());
        return matCost;

    }
    public static int laborCost(){
        String filepath = "Employees.csv";
        return sumColumn(filepath, 3, 5);
    }
    public static int overheadCost(){
        String filepath = "manufacturer.csv";
        int rent = sumColumn(filepath, 1, 5);
        int utilities = sumColumn(filepath, 2, 5);
        int insurance = sumColumn(filepath, 3, 5);
        int machinery = sumColumn(filepath, 4, 5);
        return rent + utilities + insurance + machinery;
    }
    public static int manufacturerCost(String manufacturerName){
        String filepath = "manufacturer.csv";
        return sumRow(filepath, manufacturerName, 5);
    }
    public static int totalCost(){
        int total = shippingCosts() + rawMaterialsCost() + laborCost() + overheadCost();
        return total;

    }
}
