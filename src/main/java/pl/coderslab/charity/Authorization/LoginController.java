package pl.coderslab.charity.Authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.Donation.DonationService;
import pl.coderslab.charity.Institution.Institution;
import pl.coderslab.charity.Institution.InstitutionService;
import pl.coderslab.charity.User.User;
import pl.coderslab.charity.User.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;

    @GetMapping("/registration")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String createNewUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "Użytkownik z podaną nazwą już istnieje");
        }
        if (bindingResult.hasErrors()) {
            return ("registration");
        } else {
            userService.saveUser(user);
            model.addAttribute("successMessage", "Użytkownik został pomyślnie zarejestrowany");
            model.addAttribute("user", new User());
        }
        return "login";
    }

    @GetMapping(value = {"/", "/login"})
    public String login(Model model) {
        return "login";
    }


    @GetMapping(value = "/admin/home")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Witaj " + user.getFirstName());
        model.addAttribute("adminMessage", "Ta zawartość dostępna jest tylko dla Administratora");
        model.addAttribute("fullName", user.getFirstName() + " " + user.getLastName());
        return "admin/index";
    }

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions() {
        return institutionService.getInstitutions();
    }

    @ModelAttribute("countBags")
    public Long countAllBags() {
        return donationService.countTotalBags();
    }

    @ModelAttribute("countDonations")
    public Long countAllDonations() {
        return donationService.countAllDonations();
    }

}
