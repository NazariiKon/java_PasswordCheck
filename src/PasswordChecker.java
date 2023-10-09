public class PasswordChecker {
    public static PasswordStrength checkPassword(String password) {
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        // Минимальная и максимальная длина пароля
        int minLength = 8;
        int maxLength = 20;

        // Специальные символы
        String specialChars = "!@#$%^&*()_+";

        if (password.length() < minLength) {
            return PasswordStrength.TOO_SHORT;
        }
        if (password.length() > maxLength) {
            return PasswordStrength.TOO_LONG;
        }

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (specialChars.contains(String.valueOf(ch))) {
                hasSpecialChar = true;
            }
        }

        if (hasUppercase && hasLowercase && hasDigit && hasSpecialChar) {
            return PasswordStrength.STRONG;
        } else {
            return PasswordStrength.WEAK;
        }
    }

    public enum PasswordStrength {
        WEAK,
        STRONG,
        TOO_SHORT,
        TOO_LONG
    }
}
