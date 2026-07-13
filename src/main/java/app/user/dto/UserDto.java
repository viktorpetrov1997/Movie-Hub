package app.user.dto;

import app.user.UserRole;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserDto
{
    private UUID id;
    private String username;
    private String email;
    private UserRole role;
}
