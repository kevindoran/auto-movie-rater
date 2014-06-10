package exception;

public class MovieNotFoundException extends Exception{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public MovieNotFoundException() {
        super();
    }
    public MovieNotFoundException(String msg) {
        super(msg);
    }
    
    public MovieNotFoundException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
