package app.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer implements CommandLineRunner
{
    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    private final UserService userService;

    public UserInitializer(UserService userService)
    {
        this.userService = userService;
    }

    @Override
    public void run(String... args)
    {
        if(userService.existsByRole(UserRole.ADMIN))
        {
            return;
        }

        userService.createAdmin(
                adminUsername,
                adminEmail,
                adminPassword
        );
    }
}