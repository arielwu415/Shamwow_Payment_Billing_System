public class Employees extends DataAccess
{
    /* adds new employee data in the file */
    public static void newEmployee(String id, String fname, String lname, String pay, String position) {
        String filepath = "employees.csv";
        String[] input = {id, fname, lname, pay, position};
        saveRecord(input, filepath);
    }

    /* returns specified employee ID
     * column 0 = ID, 1 = fname, 2 = lname, 3 = paycheck, 4 = position */
    public static String check(String id, int columnSearch) {
        String filepath = "employees.csv";
        int columnNum = 4;
        return query(filepath, id, columnSearch, columnNum);
    }

    /* update employee data
     * put -1 for fields that remain the same except for id */
    public static void updateEmployee(String id, String fname, String lname, String pay, String position) {
        String filepath = "employees.csv";
        String[] newValue = new String[5];
        newValue[0] = id;
        newValue[1] = fname;
        newValue[2] = lname;
        newValue[3] = pay;
        newValue[4] = position;
        editRecord(filepath, newValue);
    }

}
