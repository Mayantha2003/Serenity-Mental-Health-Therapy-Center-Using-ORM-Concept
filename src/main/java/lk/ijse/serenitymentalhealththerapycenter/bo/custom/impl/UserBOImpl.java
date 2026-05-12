package lk.ijse.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.serenitymentalhealththerapycenter.bo.custom.UserBO;
import lk.ijse.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.serenitymentalhealththerapycenter.dao.DAOTypes;
import lk.ijse.serenitymentalhealththerapycenter.dao.custom.UserDAO;
import lk.ijse.serenitymentalhealththerapycenter.dto.UserDTO;
import lk.ijse.serenitymentalhealththerapycenter.dto.tm.UserTM;
import lk.ijse.serenitymentalhealththerapycenter.entity.User;
import lk.ijse.serenitymentalhealththerapycenter.util.CurrentUser;
import lk.ijse.serenitymentalhealththerapycenter.util.PasswordUtil;

import java.util.List;
import java.util.stream.Collectors;

public class UserBOImpl implements UserBO {

    private final UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOTypes.USER);

    @Override
    public boolean authenticate(String username, String password) throws Exception {
        User user = userDAO.findByUsername(username).orElse(null);

        if (user == null) {
            return false;
        }

        if (!"ACTIVE".equals(user.getStatus())) {
            throw new SecurityException("Account is " + user.getStatus());
        }

        if (!PasswordUtil.verifyPassword(password, user.getPasswordHash())) {
            return false;
        }

        userDAO.updateLastLogin(user.getUserId());
        CurrentUser.getInstance().setUser(convertToDTO(user));

        return true;
    }

    @Override
    public boolean saveUser(UserDTO userDTO) throws Exception {
        if (!PasswordUtil.isPasswordStrong(userDTO.getPassword())) {
            throw new IllegalArgumentException(
                    "Password must be at least 6 characters with uppercase, lowercase, and digit");
        }

        if (userDAO.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPasswordHash(PasswordUtil.hashPassword(userDTO.getPassword()));
        user.setRole(userDTO.getRole());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setFullName(userDTO.getFullName());
        user.setStatus(userDTO.getStatus());

        return userDAO.save(user);
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws Exception {
        User existing = userDAO.findById(userDTO.getUserId()).orElse(null);
        if (existing == null) {
            throw new IllegalArgumentException("User not found");
        }

        existing.setUsername(userDTO.getUsername());
        existing.setRole(userDTO.getRole());
        existing.setEmail(userDTO.getEmail());
        existing.setPhone(userDTO.getPhone());
        existing.setFullName(userDTO.getFullName());
        existing.setStatus(userDTO.getStatus());

        return userDAO.update(existing);
    }

    @Override
    public boolean deleteUser(String userId) throws Exception {
        return userDAO.delete(userId);
    }

    @Override
    public UserDTO findUserById(String userId) throws Exception {
        User user = userDAO.findById(userId).orElse(null);
        return user != null ? convertToDTO(user) : null;
    }

    @Override
    public List<UserTM> getAllUsers() throws Exception {
        return userDAO.getAll().stream()
                .map(this::convertToTM)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUsername(String username) throws Exception {
        User user = userDAO.findByUsername(username).orElse(null);
        return user != null ? convertToDTO(user) : null;
    }

    @Override
    public boolean usernameExists(String username) throws Exception {
        return userDAO.existsByUsername(username);
    }

    @Override
    public boolean emailExists(String email) throws Exception {
        return userDAO.existsByEmail(email);
    }

    @Override
    public boolean changePassword(String userId, String currentPassword, String newPassword) throws Exception {
        User user = userDAO.findById(userId).orElse(null);
        if (user == null) return false;

        if (!PasswordUtil.verifyPassword(currentPassword, user.getPasswordHash())) {
            return false;
        }

        if (!PasswordUtil.isPasswordStrong(newPassword)) {
            throw new IllegalArgumentException("New password too weak");
        }

        userDAO.updatePassword(userId, PasswordUtil.hashPassword(newPassword));
        return true;
    }

    @Override
    public void resetPassword(String userId, String newPassword) throws Exception {
        userDAO.updatePassword(userId, PasswordUtil.hashPassword(newPassword));
    }

    @Override
    public void activateUser(String userId) throws Exception {
        User user = userDAO.findById(userId).orElse(null);
        if (user != null) {
            user.setStatus("ACTIVE");
            user.setFailedLoginAttempts(0);
            userDAO.update(user);
        }
    }

    @Override
    public void deactivateUser(String userId) throws Exception {
        User user = userDAO.findById(userId).orElse(null);
        if (user != null) {
            user.setStatus("INACTIVE");
            userDAO.update(user);
        }
    }

    @Override
    public long getUserCount() throws Exception {
        return userDAO.getAll().size();
    }


    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setFullName(user.getFullName());
        dto.setStatus(user.getStatus());
        dto.setLastLogin(user.getLastLogin());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }

    private UserTM convertToTM(User user) {
        return new UserTM(
                user.getUserId(),
                user.getUsername(),
                user.getRole(),
                user.getEmail(),
                user.getPhone(),
                user.getStatus()
        );
    }
}