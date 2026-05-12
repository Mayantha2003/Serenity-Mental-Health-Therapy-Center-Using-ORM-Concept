package lk.ijse.serenitymentalhealththerapycenter.dto;

import java.time.LocalDateTime;

public class UserDTO {
    private String userId;
    private String username;
    private String password;
    private String role;
    private String email;
    private String phone;
    private String fullName;
    private String status;
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;

    public UserDTO() {}

    public UserDTO(String userId, String username, String role, String email,
                   String phone, String fullName, String status, LocalDateTime lastLogin) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.fullName = fullName;
        this.status = status;
        this.lastLogin = lastLogin;
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getLastLogin() { return lastLogin; }
    public void setLastLogin(LocalDateTime lastLogin) { this.lastLogin = lastLogin; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}