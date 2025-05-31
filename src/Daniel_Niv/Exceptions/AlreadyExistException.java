package Daniel_Niv.Exceptions;

public class AlreadyExistException extends CollegeException {
    public AlreadyExistException(String name) {
        super(name+" is already exist !");
    }
}
