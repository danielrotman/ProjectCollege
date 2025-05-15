package Daniel_Niv;

public class CommissionNotExistException extends CollegeException {
    public CommissionNotExistException(String message) {
        super("The commission "+message+" is not exist !");
    }
}
