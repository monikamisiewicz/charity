package pl.coderslab.charity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.Donation.DonationService;
import pl.coderslab.charity.Institution.Institution;
import pl.coderslab.charity.Institution.InstitutionRepository;
import pl.coderslab.charity.Institution.InstitutionService;

import java.util.List;


@Controller
@RequiredArgsConstructor

public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;

    @RequestMapping("/")
    public String homeAction(Model model){
        return "index";
    }

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions()
    {     return institutionService.getInstitutions();    }

    @ModelAttribute("countBags")
    public Long countAllBags() {
        return donationService.countTotalBags();
    }

    @ModelAttribute("countDonations")
    public Long countAllDonations() {
        return donationService.countAllDonations();
    }


}
