package pl.coderslab.charity.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("admin/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("list")
    public String getList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users/list";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "admin/users/edit";
    }


    @PostMapping("edit")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/users/edit";
        }

//        user.setPassword(userService.findById(user.getId()).getPassword());

        if (user.getActive()) {
            userService.saveUser(user);
            return "redirect:/admin/users/list";
        } else {
            userService.blockUser(user);
            return "redirect:/admin/users/list";
        }
    }

    @GetMapping("delete/{id}")
    public String deleteCheck(@PathVariable long id, Model model) {
        model.addAttribute("userId", id);
        return "admin/users/delete";
    }

    @GetMapping("delete-action/{id}")
    public String delete(@PathVariable long id, @RequestParam("action") boolean action, Model model) {
        if (action) {
            userService.deleteById(id);
        }
        return "redirect:/admin/users/list";
    }

}
