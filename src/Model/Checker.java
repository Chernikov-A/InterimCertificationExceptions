package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Checker {

    public static int DataCount = 6;
    private String lastName;
    private String firstName;
    private String SecondName;

    private LocalDate birthData;
    private Gender gender;
    private Long phoneNumber;

    public Checker() {

    }

    public void CheckData(String[] split) throws StringException {
        if (split == null) {
            throw new NullPointerException("Нет данных");
        }

        StringBuilder fullErrorsMessages = new StringBuilder();
        for (String string : split) {
            if (Character.isLetter(string.charAt(0))) {
                if (string.length() == 1) {
                    if (this.gender == null) {
                        try {
                            this.gender = checkGender(string);
                        } catch (GenderException e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else {
                        fullErrorsMessages.append("Гендер указан больше 1 раза\n");
                    }
                } else {
                    if (this.lastName == null) {
                        try {
                            this.lastName = checkFIO(string);
                        } catch (FIOException e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else if (this.firstName == null) {
                        try {
                            this.firstName = checkFIO(string);
                        } catch (FIOException e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else if (this.SecondName == null) {
                        try {
                            this.SecondName = checkFIO(string);
                        } catch (FIOException e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else {
                        fullErrorsMessages.append("Слишком много элементов распознаны как ФИО.\n");
                    }
                }
            } else {

                if (string.matches("[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}")) {
                    if (this.birthData == null) {
                        try {
                            this.birthData = checkBirthDate(string);
                        } catch (BadBirthDateException e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else {
                        fullErrorsMessages.append("Дата рождения должна быть только одна, а обнаружены две: '").append(this.birthData).append("','").append(string).append("'\n");
                    }
                } else {
                    if (this.phoneNumber == null) {
                        try {
                            this.phoneNumber = checkPhoneNumber(string);
                        } catch (PhoneException e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else {
                        fullErrorsMessages.append("Должен быть только один телефонный норме, а не несколько: '").append(this.phoneNumber).append("','").append(string).append("'\n");
                    }
                }

            }
        }
        if (!fullErrorsMessages.isEmpty()) {
            throw new StringException(fullErrorsMessages.toString());
        }
    }



    public String getLastName() {
        return lastName;
    }

    private String checkFIO(String inputString) throws FIOException {
        if (inputString.toLowerCase().matches("^[a-zа-яё]*$")) {
            return inputString;
        } else {
            throw new FIOException(inputString);
        }
    }

    private long checkPhoneNumber(String inpuString) throws PhoneException {
        if (inpuString.length() == 10) {
            try {
                return Long.parseLong(inpuString);
            } catch (NumberFormatException e) {
                throw new PhoneException(inpuString);
            }
        } else {
            throw new PhoneException(inpuString);
        }
    }

    private Gender checkGender(String inputString) throws GenderException {
        try {
            return Gender.valueOf(inputString);
        } catch (IllegalArgumentException e) {
            throw new GenderException(inputString);
        }
    }

    private LocalDate checkBirthDate(String inputString) throws BadBirthDateException {
        try {
            return LocalDate.parse(inputString,
                    DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException e) {
            throw new BadBirthDateException(inputString);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(lastName).append(">")
                .append("<").append(firstName).append(">")
                .append("<").append(SecondName).append(">")
                .append("<").append(birthData.toString()).append(">")
                .append("<").append(phoneNumber).append(">")
                .append("<").append(gender).append(">");
        return sb.toString();
    }
}
