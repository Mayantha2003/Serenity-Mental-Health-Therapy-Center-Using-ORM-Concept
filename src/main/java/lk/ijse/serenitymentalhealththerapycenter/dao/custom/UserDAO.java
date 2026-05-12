package lk.ijse.serenitymentalhealththerapycenter.dao.custom;

import lk.ijse.serenitymentalhealththerapycenter.dao.CrudDAO;
import lk.ijse.serenitymentalhealththerapycenter.entity.User;

import java.util.Optional;

public interface UserDAO extends CrudDAO<User> {

    Optional<User> findByUsername(String username) throws Exception;

    Optional<User> findByEmail(String email) throws Exception;

    boolean existsByUsername(String username) throws Exception;

    boolean existsByEmail(String email) throws Exception;

    void updateLastLogin(String userId) throws Exception;

    void updatePassword(String userId, String newPasswordHash) throws Exception;
}