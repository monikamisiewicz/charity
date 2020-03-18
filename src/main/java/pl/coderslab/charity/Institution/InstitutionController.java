package pl.coderslab.charity.Institution;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("admin/institutions")
@RequiredArgsConstructor
public class InstitutionController {

    private final InstitutionService institutionService;

    @GetMapping
    public String add(Model model) {
        model.addAttribute("institution", new Institution());
        return "admin/institutions/add";
    }

    @PostMapping
    public String save(@ModelAttribute @Valid Institution institution, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/institutions/add";
        }
        institutionService.save(institution);
        return "redirect:/admin/institutions/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        Institution institution = institutionService.findById(id);
        model.addAttribute("institution", institution);
        return "admin/institutions/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("institution") @Valid Institution institution, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "admin/institution/edit";
        }
        institutionService.save(institution);
        return "redirect:/admin/institutions/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCheck(@PathVariable long id, Model model) {
        model.addAttribute("institutionId", id);
        return "admin/institutions/delete";
    }

    @GetMapping("/delete-action/{id}")
    public String delete(@PathVariable long id, @RequestParam("action") boolean action) {
        if (action) {
            institutionService.deleteById(id);
        }
        return "redirect:/admin/institutions/list";
    }


    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("institutions", institutionService.getInstitutions());
        return "admin/institutions/list";
    }

    @GetMapping("/name")
    public String getByNameStart(@RequestParam("name") String name, Model model) {
        model.addAttribute("institutions", institutionService.findByName(name));
        return "admin/institutions/list";
    }
}
