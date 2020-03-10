package pl.coderslab.charity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.Institution.InstitutionRepository;
import pl.coderslab.charity.Institution.InstitutionService;


@Controller
@RequiredArgsConstructor

public class HomeController {

    private final InstitutionService institutionService;


    @RequestMapping("/")
    public String homeAction(Model model){
        return "index";
    }


    @GetMapping("/institutions")
    public String getList(Model model) {
        model.addAttribute("institutions", institutionService.getInstitutions());
        return "institutions/list";
    }
}
