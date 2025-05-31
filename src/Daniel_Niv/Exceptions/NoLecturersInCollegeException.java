package Daniel_Niv.Exceptions;

public class NoLecturersInCollegeException extends CollegeException {
    public NoLecturersInCollegeException() {
        super("There are no lecturers in the college!");
    }
}
