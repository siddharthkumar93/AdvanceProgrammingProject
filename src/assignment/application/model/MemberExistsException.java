package assignment.application.model;

public class MemberExistsException extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = 7165206591300369566L;

    public MemberExistsException(String passedErrMessage) {
        super(passedErrMessage);
    }
}
