package pl.coderslab.charity.Admin;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.Role.RoleRepository;
import pl.coderslab.charity.User.User;
import pl.coderslab.charity.User.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("admin/admins")
@RequiredArgsConstructor
public class AdminController {

private final UserService userService;

    @GetMapping
    public String add(Model model) {
        User admin = new User();
        model.addAttribute("admin", admin);
        return "admin/admins/add";
    }

    @PostMapping
    public String saveAdmin(@ModelAttribute("admin") @Valid User admin, BindingResult bindingResult, Model model) {

        User adminExists = userService.findUserByUserName(admin.getUserName());
        if (adminExists != null) {
            bindingResult
                    .rejectValue("userName", "error.admin",
                            "Administrator z podaną nazwą już istnieje");
        }
        if (bindingResult.hasErrors()) {
            return ("admin/admins/add");
        } else {
            userService.saveAdmin(admin);
            model.addAttribute("successMessage", "Administrator został pomyślnie dodany");
            model.addAttribute("admin", new User());
        }
        return "redirect:/admin/admins/list";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        User admin = userService.findById(id);
        model.addAttribute("admin", admin);
        return "admin/admins/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("admin") @Valid User admin, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "admin/admins/edit";
        }

        userService.saveAdmin(admin);
        return "redirect:/admin/admins/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCheck(@PathVariable long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findUserByUserName(auth.getName());

        model.addAttribute("adminId", id);
        if(userService.findById(id)==currentUser) {
            model.addAttribute("cannotDelete", "Nie można usunąć administratora!");
            return "admin/admins/list";
        }
        return "admin/admins/delete";
    }

    @GetMapping("/delete-action/{id}")
    public String delete(@PathVariable long id, @RequestParam("action") boolean action, Model model) {
        if (action) {
            userService.deleteById(id);
        }
        return "redirect:/admin/admins/list";
    }


    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("admins", userService.getAllAdmins());
        return "admin/admins/list";
    }

    @GetMapping("/username")
    public String getByUserNameContaining(@RequestParam("userName") String userName, Model model) {
        model.addAttribute("admins", userService.findByUserNameContaining(userName));
        return "admin/admins/list";
    }
}

