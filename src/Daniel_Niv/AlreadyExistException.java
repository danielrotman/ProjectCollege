package Daniel_Niv;

public class AlreadyExistException extends CollegeException {
    public AlreadyExistException(String name) {
        super("the lecturer "+name+" is already exist !");
    }
}
