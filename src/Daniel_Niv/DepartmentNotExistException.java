package Daniel_Niv;

public class DepartmentNotExistException extends CollegeException {
    public DepartmentNotExistException(String message) {
        super("the department "+message+" does not exist");
    }
}
