package Daniel_Niv;

public class LecturerAlreadyInDept extends CollegeException {
    public LecturerAlreadyInDept(String message) {
        super("the lecturer "+message+" is already assigned to department");
    }
}
