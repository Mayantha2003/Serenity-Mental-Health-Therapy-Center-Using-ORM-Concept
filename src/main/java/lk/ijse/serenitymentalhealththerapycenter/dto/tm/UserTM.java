package lk.ijse.serenitymentalhealththerapycenter.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserTM {
    private Long userId;
    private String username;
    private String role;
    private String email;
    private String phone;
    private String status;
}