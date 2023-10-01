import Present.Present;
import View.View;
import View.ConsoleView;

import java.io.Console;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Present<View> prog = new Present<View>(new ConsoleView());
        prog.start();
    }
}