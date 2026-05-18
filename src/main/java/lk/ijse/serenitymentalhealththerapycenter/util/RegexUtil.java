package lk.ijse.serenitymentalhealththerapycenter.util;

import java.util.regex.Pattern;

public class RegexUtil {

    // Email: user@example.com
    public static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    // Sri Lankan Phone: 07X XXX XXXX or +94 7X XXX XXXX
    public static final Pattern PHONE_PATTERN =
            Pattern.compile("^(\\+94|0)?7[0-9]{8}$");

    // Sri Lankan NIC: 9 digits + V/X or 12 digits
    public static final Pattern NIC_PATTERN =
            Pattern.compile("^[0-9]{9}[vVxX]$|^[0-9]{12}$");

    // Username: 3-20 alphanumeric
    public static final Pattern USERNAME_PATTERN =
            Pattern.compile("^[a-zA-Z0-9_]{3,20}$");

    // Password: min 6 chars
    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^.{6,}$");

    // Name: letters and spaces only
    public static final Pattern NAME_PATTERN =
            Pattern.compile("^[a-zA-Z\\s]{2,100}$");

    // Money: decimal number
    public static final Pattern MONEY_PATTERN =
            Pattern.compile("^[0-9]+(\\.[0-9]{1,2})?$");

    // Invoice: INV-YYYY-NNNN
    public static final Pattern INVOICE_PATTERN =
            Pattern.compile("^INV-[0-9]{4}-[0-9]{4}$");

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean isValidNic(String nic) {
        return nic != null && NIC_PATTERN.matcher(nic).matches();
    }

    public static boolean isValidUsername(String username) {
        return username != null && USERNAME_PATTERN.matcher(username).matches();
    }

    public static boolean isValidPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    public static boolean isValidName(String name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }

    public static boolean isValidMoney(String amount) {
        return amount != null && MONEY_PATTERN.matcher(amount).matches();
    }

    public static boolean isValidInvoice(String invoice) {
        return invoice != null && INVOICE_PATTERN.matcher(invoice).matches();
    }
}