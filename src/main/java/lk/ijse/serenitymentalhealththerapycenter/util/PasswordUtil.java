package lk.ijse.serenitymentalhealththerapycenter.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordUtil {

    private static final int WORK_FACTOR = 12;

    public static String hashPassword(String plainPassword) {
        return BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(WORK_FACTOR, plainPassword.toCharArray());
    }

    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(plainPassword.toCharArray(), hashedPassword);
        return result.verified;
    }
}