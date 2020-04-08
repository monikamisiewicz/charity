package pl.coderslab.charity.Donation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.User.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {

private final DonationRepository donationRepository;

public long countTotalBags() {
    return donationRepository.sumBags();
}

public long countAllDonations() {
    return donationRepository.count();
}

public List<Donation> getDonationsByUser(User user) {
    return donationRepository.findAllByUser(user);
}


}
