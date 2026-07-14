package app.auth;

import app.user.UserService;
import app.user.dto.UserDto;
import app.user.dto.UserLoginRequest;
import app.user.dto.UserRegisterRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController
{
    private final UserService userService;

    public AuthController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage()
    {
        UserRegisterRequest userRegisterRequest = UserRegisterRequest.builder().build();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        modelAndView.addObject("userRegisterRequest", userRegisterRequest);

        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@Valid UserRegisterRequest userRegisterRequest,
                                     BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("register");
            modelAndView.addObject("userRegisterRequest", userRegisterRequest);
            return modelAndView;
        }

        userService.register(userRegisterRequest);

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage()
    {
        UserLoginRequest userLoginRequest = UserLoginRequest.builder().build();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("userLoginRequest", userLoginRequest);

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid UserLoginRequest userLoginRequest,
                              BindingResult bindingResult,
                              HttpSession httpSession
    )
    {
        if(bindingResult.hasErrors())
        {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            modelAndView.addObject("userLoginRequest", userLoginRequest);
            return modelAndView;
        }

        UserDto user = userService.login(userLoginRequest);

        httpSession.setAttribute("user_id", user.getId());

        return new ModelAndView("redirect:/");
    }
}
