package Model;

public class GenderException extends Exception{
    String input;

    public GenderException(String input) {
        this.input = input;
    }

    @Override
    public String getMessage() {
        return "Неправильно указан пол (используйте только латинские буквы f и m), а не '" + input + "'\n";
    }
}
