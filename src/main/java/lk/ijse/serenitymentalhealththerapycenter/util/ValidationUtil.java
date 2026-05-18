package lk.ijse.serenitymentalhealththerapycenter.util;

import lk.ijse.serenitymentalhealththerapycenter.bo.exception.ValidationException;

public class ValidationUtil {

    public static void validateEmail(String email) throws ValidationException {
        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("Email is required");
        }
        if (!RegexUtil.isValidEmail(email)) {
            throw new ValidationException("Invalid email format. Example: user@example.com");
        }
    }

    public static void validatePhone(String phone) throws ValidationException {
        if (phone == null || phone.trim().isEmpty()) {
            throw new ValidationException("Phone number is required");
        }
        if (!RegexUtil.isValidPhone(phone)) {
            throw new ValidationException("Invalid Sri Lankan phone. Use: 07X XXX XXXX");
        }
    }

    public static void validateNic(String nic) throws ValidationException {
        if (nic == null || nic.trim().isEmpty()) {
            throw new ValidationException("NIC is required");
        }
        if (!RegexUtil.isValidNic(nic)) {
            throw new ValidationException("Invalid NIC format. Use: 2000XXXXXXX or 000000000V");
        }
    }

    public static void validateUsername(String username) throws ValidationException {
        if (username == null || username.trim().isEmpty()) {
            throw new ValidationException("Username is required");
        }
        if (!RegexUtil.isValidUsername(username)) {
            throw new ValidationException("Username must be 3-20 alphanumeric characters");
        }
    }

    public static void validatePassword(String password) throws ValidationException {
        if (password == null || password.isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (!RegexUtil.isValidPassword(password)) {
            throw new ValidationException("Password must be at least 6 characters");
        }
    }

    public static void validateRequired(String value, String fieldName) throws ValidationException {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException(fieldName + " is required");
        }
    }

    public static void validateName(String name, String fieldName) throws ValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException(fieldName + " is required");
        }
        if (!RegexUtil.isValidName(name)) {
            throw new ValidationException(fieldName + " must contain only letters and spaces");
        }
    }

    public static void validateMoney(String amount, String fieldName) throws ValidationException {
        if (amount == null || amount.trim().isEmpty()) {
            throw new ValidationException(fieldName + " is required");
        }
        if (!RegexUtil.isValidMoney(amount)) {
            throw new ValidationException(fieldName + " must be a valid amount");
        }
    }
}