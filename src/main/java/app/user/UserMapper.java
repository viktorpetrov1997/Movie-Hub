package app.user;

import app.user.dto.UserDto;
import app.user.dto.UserRegisterRequest;

public final class UserMapper
{
    public static User toUserEntity(UserRegisterRequest request, String encodedPassword)
    {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encodedPassword)
                .role(UserRole.USER)
                .build();
    }

    public static UserDto toUserDto(User user)
    {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
