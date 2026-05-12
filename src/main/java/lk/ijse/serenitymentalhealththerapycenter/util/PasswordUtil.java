package lk.ijse.serenitymentalhealththerapycenter.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordUtil {

    private static final int BCRYPT_COST = 12;

    public static String hashPassword(String plainPassword) {
        if (plainPassword == null || plainPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        return BCrypt.withDefaults().hashToString(BCRYPT_COST, plainPassword.toCharArray());
    }


    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            return false;
        }


        BCrypt.Result result = BCrypt.verifyer().verify(
                plainPassword.toCharArray(),
                hashedPassword.toCharArray()
        );

        return result.verified;
    }

    public static boolean isPasswordStrong(String password) {
        if (password == null || password.length() < 6) {
            return false;
        }

        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*\\d.*");

        return hasUpper && hasLower && hasDigit;
    }
}