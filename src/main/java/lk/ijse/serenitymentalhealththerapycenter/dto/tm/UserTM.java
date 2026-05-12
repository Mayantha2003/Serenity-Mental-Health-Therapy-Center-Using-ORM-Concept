package lk.ijse.serenitymentalhealththerapycenter.dto.tm;

import javafx.beans.property.*;

public class UserTM {
    private StringProperty userId = new SimpleStringProperty();
    private StringProperty username = new SimpleStringProperty();
    private StringProperty role = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty phone = new SimpleStringProperty();
    private StringProperty status = new SimpleStringProperty();

    public UserTM() {}

    public UserTM(String userId, String username, String role, String email, String phone, String status) {
        this.userId.set(userId);
        this.username.set(username);
        this.role.set(role);
        this.email.set(email);
        this.phone.set(phone);
        this.status.set(status);
    }

    public String getUserId() { return userId.get(); }
    public StringProperty userIdProperty() { return userId; }
    public void setUserId(String userId) { this.userId.set(userId); }

    public String getUsername() { return username.get(); }
    public StringProperty usernameProperty() { return username; }
    public void setUsername(String username) { this.username.set(username); }

    public String getRole() { return role.get(); }
    public StringProperty roleProperty() { return role; }
    public void setRole(String role) { this.role.set(role); }

    public String getEmail() { return email.get(); }
    public StringProperty emailProperty() { return email; }
    public void setEmail(String email) { this.email.set(email); }

    public String getPhone() { return phone.get(); }
    public StringProperty phoneProperty() { return phone; }
    public void setPhone(String phone) { this.phone.set(phone); }

    public String getStatus() { return status.get(); }
    public StringProperty statusProperty() { return status; }
    public void setStatus(String status) { this.status.set(status); }
}