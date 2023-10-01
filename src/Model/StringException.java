package Model;

public class StringException extends Exception{
    String message;

    public StringException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
