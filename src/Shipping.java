//peer programmed by Daniel Yoon and David Kim
public class Shipping extends DataAccess
{
    public static void newShipping(String id, String name, String address, String phoneNumber, String costs) {
        String filepath = "shipping.csv";
        String[] input = {id, name, address, phoneNumber, costs};
        saveRecord(input, filepath);
    }

    //column 0 = id, 1 = name, 2 = address, 3 = phoneNumber, 4 = costs
    public static String check(String id, int columnSearch) {
        String filepath = "shipping.csv";
        int columnNum = 4;
        return query(filepath, id, columnSearch, columnNum);
    }

    //put -1 for fields that remain the same except for id
    public static void updateShipping(String id, String name, String address, String phoneNumber, String costs) {
        String filepath = "shipping.csv";
        String[] newValue = new String[5];
        newValue[0] = id;
        newValue[1] = name;
        newValue[2] = address;
        newValue[3] = phoneNumber;
        newValue[4] = costs;
        editRecord(filepath, newValue);
    }

}