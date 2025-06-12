package Daniel_Niv.Exceptions;

public class IlegalMemberAddedException extends CollegeException {
    public IlegalMemberAddedException(String message) {
        super("member "+message+ " does not meet the requirements to the commission");
    }
}
