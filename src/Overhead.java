public class Overhead extends DataAccess {
    public static void newManufacturer(String manufacturer, String rent, String utilities, String insurance, String machinery){
        String filepath = "manufacturer.csv";
        String[] input = {manufacturer, rent, utilities, insurance, machinery};
        saveRecord(input, filepath);
    }
    public static String check(String manufacturer, int columnSearch){ //column 0 = manufacturer, 1=rent, 2 =utilities, 3=insurance,4=,machinery
        String filepath = "manufacturer.csv";
        int columnNum = 5;
        return query(filepath, manufacturer, columnSearch, columnNum);
    }
    public static void updateManufacturer(String manufacturer, String rent, String utilities, String insurance, String machinery){//put -1 for fields that remain the same except for id
        String filepath = "manufacturer.csv";
        String[] newValue = new String[5];
        newValue[0] = manufacturer;
        newValue[1] = rent;
        newValue[2] = utilities;
        newValue[3] = insurance;
        newValue[4] = machinery;
        editRecord(filepath, newValue);
    }

}