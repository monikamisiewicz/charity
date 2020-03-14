package pl.coderslab.charity.Authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

//    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
//    public ModelAndView login(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("login");
//        return modelAndView;
//    }

    @GetMapping(value = {"/", "/login"})
    public String login() {
        return "login";
    }

//    @RequestMapping(value="/registration", method = RequestMethod.GET)
//    public ModelAndView registration(){
//        ModelAndView modelAndView = new ModelAndView();
//        User user = new User();
//        modelAndView.addObject("user", user);
//        modelAndView.setViewName("registration");
//        return modelAndView;
//    }

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
        return "registration";
    }

//    @RequestMapping(value = "/registration", method = RequestMethod.POST)
//    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
//        ModelAndView modelAndView = new ModelAndView();
//        User userExists = userService.findUserByUserName(user.getUserName());
//        if (userExists != null) {
//            bindingResult
//                    .rejectValue("userName", "error.user",
//                            "Użytkownik z podaną nazwą już istnieje");
//        }
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("registration");
//        } else {
//            userService.saveUser(user);
//            modelAndView.addObject("successMessage", "Użytkownik został pomyślnie zarejestrowany");
//            modelAndView.addObject("user", new User());
//            modelAndView.setViewName("registration");
//        }
//        return modelAndView;
//    }

    @GetMapping("/admin/index")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Witaj " + user.getFirstName());
        model.addAttribute("adminMessage", "Ta zawartość dostępna jest tylko dla Administratora");
        return"admin/index";

    }

//    @RequestMapping(value = "/admin/index", method = RequestMethod.GET)
//    public ModelAndView home() {
//        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByUserName(auth.getName());
//        modelAndView.addObject("userName", "Witaj " + user.getFirstName());
//        modelAndView.addObject("adminMessage", "Ta zawartość dostępna jest tylko dla Administratora");
//        modelAndView.setViewName("admin/index");
//        return modelAndView;
//    }
}
