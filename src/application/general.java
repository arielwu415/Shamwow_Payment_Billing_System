package application;

import java.io.File;
import java.util.Scanner;
//peer programmed by Daniel Yoon and David Kim

public class general extends DataAccess{
    public static void main(String[] args) {
        System.out.println(shippingCosts());
    }
    public static int shippingCosts(){
        String filepath = "order.csv";
        return sumColumn(filepath, 3, 4);
    }
    public static int rawMaterialsCost(){
        File filepath = new File("materialCost.csv");
        try{
            Scanner scn = new Scanner(filepath);
            scn.nextLine();
            int matCost = Integer.parseInt(scn.nextLine());
            scn.close();
            return matCost;
        }
        catch(Exception e){
            return -1;
        }
    }
    public static int laborCost(){
        String filepath = "employees.csv";
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