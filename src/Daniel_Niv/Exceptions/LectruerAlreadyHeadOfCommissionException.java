package Daniel_Niv.Exceptions;

public class LectruerAlreadyHeadOfCommissionException extends CollegeException {
    public LectruerAlreadyHeadOfCommissionException(String name) {
        super("The lecturer "+name+" is already the head of commission !");
    }
}
