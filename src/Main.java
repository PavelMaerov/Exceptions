public class Main {
    private static boolean existWrongSymbols(String s) {
        // только латинские буквы, цифры и знак подчеркивания
        for (char ch : s.toCharArray()) {
            if (!(ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' || ch >= '0' && ch <= '9' || ch == '_')) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkWhichThrowsExceptions(String login, String password, String confirmPassword)
            throws WrongLoginException, WrongPasswordException {
        if (login.length() > 20) {
            throw new WrongLoginException("Ошибка! Длина логина > 20");
        }
        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Ошибка! Пароль и подтверждение пароля не равны");
        }
        if (password.length() > 19) {
            throw new WrongPasswordException("Ошибка! Длина пароля > 19");
        }
        // только латинские буквы, цифры и знак подчеркивания
        if (existWrongSymbols(login)) {
            throw new WrongLoginException("Ошибка! В логине есть недопустимые символы");
        }
        if (existWrongSymbols(password)) {
            throw new WrongPasswordException("Ошибка! В пароле есть недопустимые символы");
        }
        return true;
    }

    public static boolean checkWhichHandlesExceptions(String login, String password, String confirmPassword) {
        try {
            return checkWhichThrowsExceptions(login, password, confirmPassword);
        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //испытания
    public static void main(String[] args) {
        String login = "1234567890123456789.";
        String password = "A1234567890_123456z";
        String confirmPassword = "A1234567890_123456z";
        boolean result = checkWhichHandlesExceptions(login, password, confirmPassword);
        System.out.println((result) ? "Проверка успешна" : "Проверка не успешна");
    }
}