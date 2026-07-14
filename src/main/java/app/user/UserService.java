package app.user;

import app.user.dto.UserDto;
import app.user.dto.UserLoginRequest;
import app.user.dto.UserRegisterRequest;
import app.user.exception.InvalidLoginException;
import app.user.exception.UserAlreadyExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto register(UserRegisterRequest userRegisterRequest)
    {
        userRepository.findByUsernameOrEmail(userRegisterRequest.getUsername(), userRegisterRequest.getEmail())
                .ifPresent(user ->
                {
                    throw new UserAlreadyExistsException("User with this username or email already exists!");
                });

        String encodedPassword = passwordEncoder.encode(userRegisterRequest.getPassword());

        User user = UserMapper.toUserEntity(userRegisterRequest, encodedPassword);

        User savedUser = userRepository.save(user);

        return UserMapper.toUserDto(savedUser);
    }

    public UserDto login(UserLoginRequest userLoginRequest)
    {
        Optional<User> optionalUser = userRepository.findByEmail(userLoginRequest.getEmail());

        if(optionalUser.isEmpty() ||
                !passwordEncoder.matches(userLoginRequest.getPassword(), optionalUser.get().getPassword())
        )
        {
            throw new InvalidLoginException("Invalid email or password!");
        }

        return UserMapper.toUserDto(optionalUser.get());
    }
}
