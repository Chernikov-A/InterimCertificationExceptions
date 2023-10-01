package Model;

public class PhoneException extends Exception{
    String input;

    public PhoneException(String input) {
        this.input = input;
    }

    @Override
    public String getMessage() {
        return "Не получилось преобразовать '" + input + "' в номер телефона (правильный формат телефона 9587899875)\n ";
    }
}
