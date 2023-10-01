package Present;

import Model.Checker;
import Model.StringException;
import View.View;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Present <V extends View>{
    private Checker checker;
    private final V view;
    public Present(V v) {
        view = v;
    }
    public  void start(){
        boolean working = true;
        do {
            String input = view.getInput(
                    "Введите данные через пробел 'фамилию' 'имя' 'отчество' 'дату_рождения' 'номер_телефона' 'пол' или exit ");
        if (input.equals("Exit")){
            working = false;
            break;
        }else {
            String[] Input = input.replaceAll("\\s+"," ").split(" ");

            int inputDataCount = checkInputDataCount(Input.length);
            if (inputDataCount == -1){
                view.printOutput("Слишком мало данных на вводе (должны быть " + Checker.DataCount
                        +"разделенных пробелом ' ': Фамилия Имя Отчество НомерТелефона ДатаРождения Пол)\n");
            } else if (inputDataCount == 0) {
                view.printOutput("Слишком много данных на вводе (должны быть " + Checker.DataCount
                        +"разделенных пробелом ' ': Фамилия Имя Отчество НомерТелефона ДатаРождения Пол)\n");
            }else {
                try {
                    Checker checker = new Checker();
                    checker.CheckData(Input);
                    writePersonData(checker);
                }catch (IOException e){
                    e.printStackTrace();
                }catch (StringException e){
                    view.printOutput(e.getMessage());
                }
            }
        }
        }while (working);
    }
    private int checkInputDataCount(int inputDataCount) {
        if (inputDataCount < Checker.DataCount) {
            return -1;
        } else if (inputDataCount > Checker.DataCount) {
            return 0;
        } else {
            return inputDataCount;
        }
    }

    //создаём или подключаемся к фаилу по фамилии и дописываем туда новые данные
    private void writePersonData(Checker data) throws IOException {
        File filepath = new File(data.getLastName());
        try (FileWriter fw = new FileWriter(filepath, true)) {
            fw.append(data.toString() + "\n");
        } catch (IOException e) {
            throw e;
        }
    }
}
