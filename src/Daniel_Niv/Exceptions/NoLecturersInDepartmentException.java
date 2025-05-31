package Daniel_Niv.Exceptions;

public class NoLecturersInDepartmentException extends CollegeException {
    public NoLecturersInDepartmentException(String message) {
        super("the department "+message+" does not have any lecturers!");
    }
}
