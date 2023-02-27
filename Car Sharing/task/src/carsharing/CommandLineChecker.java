package carsharing;


public class CommandLineChecker {

    private final String CHECK_NAME = "-databaseFileName";

    public String getName(String[] arr) {
        String nameDB = null;
        if(arr != null) {
            if(arr.length > 1) {
                for(int i = 0; i < arr.length; i++) {
                    if(arr[i].equals(CHECK_NAME) && (i + 1) != arr.length) {
                        nameDB = arr[i + 1];
                        break;
                    }
                }
            }
        }
        return nameDB;
    }
}
