package application;
public class Order extends Shipping
{

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


}
