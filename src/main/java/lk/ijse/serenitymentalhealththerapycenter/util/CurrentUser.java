package lk.ijse.serenitymentalhealththerapycenter.util;

import lk.ijse.serenitymentalhealththerapycenter.dto.UserDTO;

public class CurrentUser {

    private static CurrentUser instance;
    private UserDTO currentUser;

    private CurrentUser() {}

    public static CurrentUser getInstance() {
        if (instance == null) {
            synchronized (CurrentUser.class) {
                if (instance == null) {
                    instance = new CurrentUser();
                }
            }
        }
        return instance;
    }

    public void setUser(UserDTO user) {
        this.currentUser = user;
    }

    public UserDTO getUser() {
        return currentUser;
    }

    public void clear() {
        this.currentUser = null;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public boolean isAdmin() {
        return currentUser != null && "ADMIN".equals(currentUser.getRole());
    }

    public String getUsername() {
        return currentUser != null ? currentUser.getUsername() : "";
    }

    public String getRole() {
        return currentUser != null ? currentUser.getRole() : "";
    }

    public String getUserId() {
        return currentUser != null ? currentUser.getUserId() : "";
    }
}