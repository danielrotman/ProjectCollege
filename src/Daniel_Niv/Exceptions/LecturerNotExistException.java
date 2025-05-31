package Daniel_Niv.Exceptions;


public class LecturerNotExistException extends CollegeException {
    public LecturerNotExistException(String name) {
        super("the lecturer "+name+" not exist !");
    }
}
