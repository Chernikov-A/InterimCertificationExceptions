package Model;

public class FIOException  extends Exception{
    String input;

    public FIOException(String input) {
        this.input = input;
    }

    @Override
    public String getMessage() {
        return "Неправильный формат ФИО '" + input + "'. ФИО могу состоять только из букв.\n";
    }
}
