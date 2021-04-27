public class Order extends Shipping{
    public static void newOrder(String id, String units, String shippingCompanyID){
        String filepath = "order.csv";
        String shippingCosts = query(shippingCompanyID, 4);
        String[] input = {id, units, shippingCompanyID, shippingCosts};
        saveRecord(input, filepath);
    }
    public static String checkOrder(String id, int columnSearch,){ //column 0 = ID, 1=units, 2 =shippingCompanyID, 3=shippingCosts
        String filepath = "order.csv";
        int columnNum = 3;
        return query(filepath, id, columnSearch, columnNum);
    }
    public static void updateOrder(String id, String units, String shippingCompanyID){//put -1 for fields that remain the same except for id
        String filepath = "order.csv";
        String[] newValue = new String[4];
        String shippingCosts = query(shippingCompanyID, 4);
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
