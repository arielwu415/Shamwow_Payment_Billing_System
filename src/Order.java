//package application;
//peer programmed by Daniel Yoon and David Kim
import java.util.*;

import org.graalvm.compiler.nodes.calc.IntegerConvertNode;

import java.io.*;
public class Order extends Shipping
{
    public static void main(String[] args) {
        inventoryUpdate("a1", "1200");
    }

    public static void newOrder(String id, String units, String shippingCompanyID) {
        String filepath = "order.csv";
        String shippingCosts = check(shippingCompanyID, 4);
        String[] input = {id, units, shippingCompanyID, shippingCosts};
        saveRecord(input, filepath);
    }

    //column 0 = ID, 1 = units, 2 = shippingCompanyID, 3 = shippingCosts
    public static String checkOrder(String id, int columnSearch) {
        String filepath = "order.csv";
        int columnNum = 3;
        return query(filepath, id, columnSearch, columnNum);
    }

    //put -1 for fields that remain the same except for id
    public static void updateOrder(String id, String units, String shippingCompanyID) {
        String filepath = "order.csv";
        String[] newValue = new String[4];
        String shippingCosts = check(shippingCompanyID, 4);
        if(shippingCompanyID== "-1"){
            shippingCosts ="-1";
        }
        newValue[0] = id;
        newValue[1] = units;
        newValue[2] = shippingCompanyID;
        newValue[3] = shippingCosts;
        editRecord(filepath, newValue);
    }

    public static void addInventory(String id, String units) {
        String filepath = "inventory.csv";
        String rawMaterial = Integer.toString((2* Integer.parseInt(units)));
        String[] input = {id, units, rawMaterial};
        saveRecord(input, filepath);
        inventoryChecker(rawMaterial);
    }

    public static void inventoryChecker(String rawM) {
        int intrawM = Integer.parseInt(rawM);
        String newFile = "temp.txt";  
        File filepath = new File("materialCost.csv");
        File tempFile = new File(newFile);
        try{
            Scanner scn = new Scanner(filepath);
            FileWriter fw = new FileWriter(newFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            int matAmount = Integer.parseInt(scn.nextLine());
            int matCost = Integer.parseInt(scn.nextLine());
            
            if(matAmount < intrawM) {
                matCost += (intrawM *2);
            }
            else{
                matAmount = matAmount - intrawM;
            }

            if(matAmount < 100) {
                matAmount += 100;
                matCost += 200;
            }
            pw.println(matAmount);
            pw.println(matCost);
            scn.close();
            pw.flush();
            pw.close();
            filepath.delete();
            
            File dump = new File("materialCost.csv");
            tempFile.renameTo(dump); 
        }
        catch(Exception e) {
            System.out.println("caught an error");
        }
    }

    public static void inventoryUpdate(String id, String orderAmount) {
        int oldOrderAmount = Integer.parseInt(query("order.csv", id, 1, 4));
        int deltaOrder = oldOrderAmount - Integer.parseInt(orderAmount);
        String newFile = "temp.txt";  
        File filepath = new File("materialCost.csv");
        File tempFile = new File(newFile);
        try{
            Scanner scn = new Scanner(filepath);
            FileWriter fw = new FileWriter(newFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            int matAmount = Integer.parseInt(scn.nextLine());
            int matCost = Integer.parseInt(scn.nextLine());
            
            if(deltaOrder > 0){
                matAmount = matAmount + (deltaOrder * 2);
            }
            if(deltaOrder < 0) {
                matCost += -(4 * deltaOrder);
            }

            if(matAmount < 100) {
                matAmount += 100;
                matCost += 200;
            }

            pw.println(matAmount);
            pw.println(matCost);
            scn.close();
            pw.flush();
            pw.close();
            filepath.delete();
            
            File dump = new File("materialCost.csv");
            tempFile.renameTo(dump); 
        }
        catch(Exception e) {
            System.out.println("caught an error");
        }
        String[] inputArray = {id, orderAmount, Integer.toString(oldOrderAmount*2)};
        editRecord("inventory.csv", inputArray);
    }
}