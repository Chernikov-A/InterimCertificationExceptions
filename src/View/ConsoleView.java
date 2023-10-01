package View;

import java.util.Scanner;

public class ConsoleView implements View {
    private Scanner in = new Scanner(System.in, "IBM866");


    @Override
    public void printOutput(String messenger) {
        System.out.printf("%s", messenger);
    }

    @Override
    public String getInput(String messenger) {
        System.out.printf("%s", messenger);
        return in.nextLine();
    }
}
