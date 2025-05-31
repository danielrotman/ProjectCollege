package Daniel_Niv.Exceptions;

public class LecturerAlreadyInDept extends CollegeException {
    public LecturerAlreadyInDept(String message) {
        super("the lecturer "+message+" is already assigned to department");
    }
}
