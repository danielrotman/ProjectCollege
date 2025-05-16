package Daniel_Niv;

public class NotQualifiedException extends CollegeException {

    public NotQualifiedException(String name) {
        super("The lecturer "+name+" is not Qualified enough! ");
    }
}
