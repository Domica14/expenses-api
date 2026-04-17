package exception;

public class ApiException extends RuntimeException {
    
    public int status;

    public ApiException(String message, int status) {
        super(message);
        this.status = status;
    }
}
