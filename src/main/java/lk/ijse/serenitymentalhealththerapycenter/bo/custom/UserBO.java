package lk.ijse.serenitymentalhealththerapycenter.bo.custom;

import lk.ijse.serenitymentalhealththerapycenter.bo.SuperBO;
import lk.ijse.serenitymentalhealththerapycenter.dto.UserDTO;
import lk.ijse.serenitymentalhealththerapycenter.dto.tm.UserTM;

import java.util.List;

public interface UserBO extends SuperBO {

    boolean authenticate(String username, String password) throws Exception;

    boolean saveUser(UserDTO userDTO) throws Exception;

    boolean updateUser(UserDTO userDTO) throws Exception;

    boolean deleteUser(String userId) throws Exception;

    UserDTO findUserById(String userId) throws Exception;

    List<UserTM> getAllUsers() throws Exception;

    UserDTO findByUsername(String username) throws Exception;

    boolean usernameExists(String username) throws Exception;

    boolean emailExists(String email) throws Exception;

    boolean changePassword(String userId, String currentPassword, String newPassword) throws Exception;

    void resetPassword(String userId, String newPassword) throws Exception;

    void activateUser(String userId) throws Exception;

    void deactivateUser(String userId) throws Exception;

    long getUserCount() throws Exception;
}