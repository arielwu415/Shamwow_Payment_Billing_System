import java.io.*;
import java.util.*;
public class ui
{
    public static Scanner reader = new Scanner(System.in);
    public static void main(String[] args)
    {
        
        System.out.println("Welcome to Shamwow what would you like to do");
        System.out.println("1: Employees");
        System.out.println("please enter the number of the option you would like to see");
        int menuop1 = reader.nextInt();
        if(menuop1 == 1)
        {
            employeeCall();
        }
    }
    public static void employeeCall()
    {
        System.out.println("welcome to employee database what would you like to do");
        System.out.println("1: enter a new employee");
        System.out.println("2: update employees");
        System.out.println("3: check employees");
        Employees emplo1 = new Employees();
        System.out.println("please enter the number of the option you want");
        int menuop1 = reader.nextInt();
        
        if(menuop1 == 1)
        {
            System.out.println("welcome to add a new employee please enter the data when asked");
            System.out.println("please give your employees id: ");
            String id1 = reader.next();
            System.out.println("please give your employees first name: ");
            String firstn1 = reader.next();
            System.out.println("please give your employees last name: ");
            String lastn1 = reader.next();
            System.out.println("please give your employees pay: ");
            String pay1 = reader.next();
            System.out.println("please give your employees posistion: ");
            String pos1 = reader.next();
            emplo1.newEmployee(id1, firstn1, lastn1, pay1, pos1);
            System.out.println("your employee has been made");
        }
        if(menuop1 == 2)
        {
            System.out.println("welcome to update an employee please enter the data when asked enter -1 for all feilds you dont want to change");
            System.out.println("please give your employees id: ");
            String id2 = reader.next();
            System.out.println("please give your employees first name: ");
            String firstn2 = reader.next();
            System.out.println("please give your employees last name: ");
            String lastn2 = reader.next();
            System.out.println("please give your employees pay: ");
            String pay2 = reader.next();
            System.out.println("please give your employees posistion: ");
            String pos2 = reader.next();
            emplo1.updateEmployee(id2, firstn2, lastn2, pay2, pos2);
            System.out.println("your employee has been updated");
        }
        if(menuop1 == 3)// maybe check can show the employee data or return a boolean so its an actual check
        {
            System.out.println("welcome to check an employee please enter the data when asked");
            System.out.println("please give your employees id: ");
            String id3 = reader.next();
            System.out.println("please give your employees colum number: ");
            int col1 = reader.nextInt();
            emplo1.check(id3, col1);
            System.out.println("your employee has been checked");
        }
    }
}
//System.out.println("");
//Employees stuff = new Employees();
       //stuff.check("28211", 4);
       // Shipping stuff2 = new Shipping();
        //stuff2.check("28211", 4);
