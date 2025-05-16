package Daniel_Niv;

public class AlreadyExistException extends CollegeException {
    public AlreadyExistException(String name) {
        super(name+" is already exist !");
    }
}
