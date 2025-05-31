package Daniel_Niv.Exceptions;

public class DepartmentNotExistException extends CollegeException {
    public DepartmentNotExistException(String message) {
        super("the department "+message+" does not exist");
    }
}
