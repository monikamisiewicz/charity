package pl.coderslab.charity.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;

    @GetMapping("/profile")
    public String profile(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("user", user );
        return "user/profile";
    }

    //żle
    @PostMapping("/edit-profile")
    public String updateProfile(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user/profile";
        }

        user.setPassword(user.getPassword());

        userService.saveUser(user);
        model.addAttribute("successMessage", "Twoje dane zostały zmienione");
        model.addAttribute("errorMessage", "Zmiana danych nie powiodła się");

            return "user/success-profile-update";
    }

}

