package Daniel_Niv.Exceptions;

public class CommissionNotExistException extends CollegeException {
    public CommissionNotExistException(String message) {
        super("The commission "+message+" is not exist !");
    }
}
