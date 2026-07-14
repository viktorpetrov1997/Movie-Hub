package app.security.interceptor;

import app.user.UserRepository;
import app.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SessionInterceptor implements HandlerInterceptor
{
    private final UserRepository userRepository;

    public SessionInterceptor(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler)
    {
        HttpSession session = request.getSession();

        Object userId = session.getAttribute("user_id");

        if(userId != null)
        {
            User user = userRepository.findById((UUID) userId).orElse(null);

            request.setAttribute("loggedInUser", user);
        }

        return true;
    }
}