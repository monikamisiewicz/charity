package pl.coderslab.charity.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.Category.Category;
import pl.coderslab.charity.Category.CategoryService;
import pl.coderslab.charity.Donation.Donation;
import pl.coderslab.charity.Donation.DonationService;
import pl.coderslab.charity.DonationStatus.Status;
import pl.coderslab.charity.DonationStatus.StatusService;
import pl.coderslab.charity.Institution.Institution;
import pl.coderslab.charity.Institution.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("user")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;
    private final DonationService donationService;
    private final StatusService statusService;
    private final InstitutionService institutionService;
    private final CategoryService categoryService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/profile")
    public String profile(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("user", user);
        return "user/profile";
    }

    //?
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

    @GetMapping("/change-password")
    public String changePassword(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("user", user);
        return "user/change-password";
    }

    @PostMapping("/update-password")
    public String updatePassword(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                                 @AuthenticationPrincipal CurrentUser currentUser,
                                 @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("passConfirm") String passConfirm, Model model) {

        if (bindingResult.hasErrors()) {
            return "user/change-password";
        }

        if (!user.getPassword().equals(passConfirm)) {
            model.addAttribute("noMatch", true);
            return "user/change-password";
        }

        if (!bCryptPasswordEncoder.matches(oldPassword, currentUser.getUser().getPassword())) {
            model.addAttribute("wrongPass", true);
            return "user/change-password";
        }

        model.addAttribute("passChanged", true);
        userService.saveUser(user);
        return "redirect:/home?passChanged=yes";
    }

    @GetMapping("/donation-list")
    public String donationList(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Donation> userDonations = donationService.getDonationsByUser(currentUser.getUser());
        model.addAttribute("donations", userDonations);
        return "user/donation-list";
    }

    @ModelAttribute("statuses")
    public List<Status> getAllStatuses() {
        return statusService.getStatuses();
    }

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions() {
        return institutionService.getInstitutions();
    }


    @ModelAttribute("categories")
    public List<Category> getAllCategories() {
        return categoryService.getCategories();
    }

}

