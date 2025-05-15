package Daniel_Niv;


public class LecturerNotExistException extends CollegeException {
    public LecturerNotExistException(String name) {
        super("the lecturer "+name+" not exist !");
    }
}
